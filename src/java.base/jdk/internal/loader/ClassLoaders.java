/*
 * Copyright (c) 2015, 2016, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

package jdk.internal.loader;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Module;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Paths;
import java.security.CodeSource;
import java.security.PermissionCollection;
import java.util.jar.Manifest;

import jdk.internal.misc.JavaLangAccess;
import jdk.internal.misc.SharedSecrets;
import jdk.internal.misc.VM;


/**
 * Creates and provides access to the built-in platform and application class
 * loaders. It also creates the class loader that is used to locate resources
 * in modules defined to the boot class loader.
 */

public class ClassLoaders {

    private ClassLoaders() { }

    private static final JavaLangAccess JLA = SharedSecrets.getJavaLangAccess();

    // the built-in class loaders
    private static final BootClassLoader BOOT_LOADER;
    private static final PlatformClassLoader PLATFORM_LOADER;
    private static final AppClassLoader APP_LOADER;

    /**
     * Creates the built-in class loaders
     */
    static {

        // -Xbootclasspth/a or -javaagent Boot-Class-Path
        URLClassPath bcp = null;
        String s = VM.getSavedProperty("jdk.boot.class.path.append");
        if (s != null && s.length() > 0)
            bcp = toURLClassPath(s);

        // we have a class path if -cp is specified or -m is not specified.
        // If neither is specified then default to -cp <working directory>
        // If -cp is not specified and -m is specified, the value of
        // java.class.path is an empty string, then no class path.
        URLClassPath ucp = new URLClassPath(new URL[0]);
        String mainMid = System.getProperty("jdk.module.main");
        String cp = System.getProperty("java.class.path");
        if (cp == null)
            cp = "";
        if (mainMid == null || cp.length() > 0)
            addClassPathToUCP(cp, ucp);

        // create the class loaders
        BOOT_LOADER = new BootClassLoader(bcp);
        PLATFORM_LOADER = new PlatformClassLoader(BOOT_LOADER);
        APP_LOADER = new AppClassLoader(PLATFORM_LOADER, ucp);
    }

    /**
     * Returns the class loader that is used to find resources in modules
     * defined to the boot class loader.
     *
     * @apiNote This method is not public, it should instead be used via
     * the BootLoader class that provides a restricted API to this class
     * loader.
     */
    static BuiltinClassLoader bootLoader() {
        return BOOT_LOADER;
    }

    /**
     * Returns the platform class loader.
     */
    public static ClassLoader platformClassLoader() {
        return PLATFORM_LOADER;
    }

    /**
     * Returns the application class loader.
     */
    public static ClassLoader appClassLoader() {
        return APP_LOADER;
    }

    /**
     * The class loader that is used to find resources in modules defined to
     * the boot class loader. It is not used for class loading.
     */
    private static class BootClassLoader extends BuiltinClassLoader {
        BootClassLoader(URLClassPath bcp) {
            super(null, null, bcp);
        }

        @Override
        protected Class<?> loadClassOrNull(String cn) {
            return JLA.findBootstrapClassOrNull(this, cn);
        }
    };

    /**
     * The platform class loader, a unique type to make it easier to distinguish
     * from the application class loader.
     */
    private static class PlatformClassLoader extends BuiltinClassLoader {
        static {
            if (!ClassLoader.registerAsParallelCapable())
                throw new InternalError();
        }

        PlatformClassLoader(BootClassLoader parent) {
            super("platform", parent, null);
        }

        /**
         * Called by the VM to support define package for AppCDS.
         *
         * Shared classes are returned in ClassLoader::findLoadedClass
         * that bypass the defineClass call.
         */
        private Package definePackage(String pn, Module module) {
            return JLA.definePackage(this, pn, module);
        }
    }

    /**
     * The application class loader that is a {@code BuiltinClassLoader} with
     * customizations to be compatible with long standing behavior.
     */
    private static class AppClassLoader extends BuiltinClassLoader {
        static {
            if (!ClassLoader.registerAsParallelCapable())
                throw new InternalError();
        }

        final URLClassPath ucp;

        AppClassLoader(PlatformClassLoader parent, URLClassPath ucp) {
            super("app", parent, ucp);
            this.ucp = ucp;
        }

        @Override
        protected Class<?> loadClass(String cn, boolean resolve)
            throws ClassNotFoundException
        {
            // for compatibility reasons, say where restricted package list has
            // been updated to list API packages in the unnamed module.
            SecurityManager sm = System.getSecurityManager();
            if (sm != null) {
                int i = cn.lastIndexOf('.');
                if (i != -1) {
                    sm.checkPackageAccess(cn.substring(0, i));
                }
            }

            return super.loadClass(cn, resolve);
        }

        @Override
        protected PermissionCollection getPermissions(CodeSource cs) {
            PermissionCollection perms = super.getPermissions(cs);
            perms.add(new RuntimePermission("exitVM"));
            return perms;
        }

        /**
         * Called by the VM to support dynamic additions to the class path
         *
         * @see java.lang.instrument.Instrumentation#appendToSystemClassLoaderSearch
         */
        void appendToClassPathForInstrumentation(String path) {
            addClassPathToUCP(path, ucp);
        }

        /**
         * Called by the VM to support define package for AppCDS
         *
         * Shared classes are returned in ClassLoader::findLoadedClass
         * that bypass the defineClass call.
         */
        private Package definePackage(String pn, Module module) {
            return JLA.definePackage(this, pn, module);
        }

        /**
         * Called by the VM to support define package for AppCDS
         */
        protected Package defineOrCheckPackage(String pn, Manifest man, URL url) {
            return super.defineOrCheckPackage(pn, man, url);
        }
    }

    /**
     * Returns a {@code URLClassPath} of file URLs to each of the elements in
     * the given class path.
     */
    private static URLClassPath toURLClassPath(String cp) {
        URLClassPath ucp = new URLClassPath(new URL[0]);
        addClassPathToUCP(cp, ucp);
        return ucp;
    }

    /**
     * Converts the elements in the given class path to file URLs and adds
     * them to the given URLClassPath.
     */
    private static void addClassPathToUCP(String cp, URLClassPath ucp) {
        int off = 0;
        int next;
        while ((next = cp.indexOf(File.pathSeparator, off)) != -1) {
            URL url = toFileURL(cp.substring(off, next));
            if (url != null)
                ucp.addURL(url);
            off = next + 1;
        }

        // remaining
        URL url = toFileURL(cp.substring(off));
        if (url != null)
            ucp.addURL(url);
    }

    /**
     * Attempts to convert the given string to a file URL.
     *
     * @apiNote This is called by the VM
     */
    private static URL toFileURL(String s) {
        try {
            return Paths.get(s).toRealPath().toUri().toURL();
        } catch (InvalidPathException | IOException ignore) {
            // malformed path string or class path element does not exist
            return null;
        }
    }

}
