/*
 * Copyright (c) 2003, 2013, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.misc;

import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.Executable;
import java.lang.reflect.Layer;
import java.lang.reflect.Method;
import java.lang.reflect.Module;
import java.net.URL;
import java.security.AccessControlContext;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

import jdk.internal.reflect.ConstantPool;
import sun.reflect.annotation.AnnotationType;
import sun.nio.ch.Interruptible;

public interface JavaLangAccess {

    /**
     * Returns a {@code Method} object that reflects the specified public
     * member method of the given class. Returns {@code null} if the
     * method is not defined.
     */
    Method getMethodOrNull(Class<?> klass, String name, Class<?>... parameterTypes);

    /** Return the constant pool for a class. */
    ConstantPool getConstantPool(Class<?> klass);

    /**
     * Compare-And-Swap the AnnotationType instance corresponding to this class.
     * (This method only applies to annotation types.)
     */
    boolean casAnnotationType(Class<?> klass, AnnotationType oldType, AnnotationType newType);

    /**
     * Get the AnnotationType instance corresponding to this class.
     * (This method only applies to annotation types.)
     */
    AnnotationType getAnnotationType(Class<?> klass);

    /**
     * Get the declared annotations for a given class, indexed by their types.
     */
    Map<Class<? extends Annotation>, Annotation> getDeclaredAnnotationMap(Class<?> klass);

    /**
     * Get the array of bytes that is the class-file representation
     * of this Class' annotations.
     */
    byte[] getRawClassAnnotations(Class<?> klass);

    /**
     * Get the array of bytes that is the class-file representation
     * of this Class' type annotations.
     */
    byte[] getRawClassTypeAnnotations(Class<?> klass);

    /**
     * Get the array of bytes that is the class-file representation
     * of this Executable's type annotations.
     */
    byte[] getRawExecutableTypeAnnotations(Executable executable);

    /**
     * Returns the elements of an enum class or null if the
     * Class object does not represent an enum type;
     * the result is uncloned, cached, and shared by all callers.
     */
    <E extends Enum<E>> E[] getEnumConstantsShared(Class<E> klass);

    /** Set thread's blocker field. */
    void blockedOn(Thread t, Interruptible b);

    /**
     * Registers a shutdown hook.
     *
     * It is expected that this method with registerShutdownInProgress=true
     * is only used to register DeleteOnExitHook since the first file
     * may be added to the delete on exit list by the application shutdown
     * hooks.
     *
     * @param slot  the slot in the shutdown hook array, whose element
     *              will be invoked in order during shutdown
     * @param registerShutdownInProgress true to allow the hook
     *        to be registered even if the shutdown is in progress.
     * @param hook  the hook to be registered
     *
     * @throws IllegalStateException if shutdown is in progress and
     *         the slot is not valid to register.
     */
    void registerShutdownHook(int slot, boolean registerShutdownInProgress, Runnable hook);

    /**
     * Returns a new string backed by the provided character array. The
     * character array is not copied and must never be modified after the
     * String is created, in order to fulfill String's contract.
     *
     * @param chars the character array to back the string
     * @return a newly created string whose content is the character array
     */
    String newStringUnsafe(char[] chars);

    /**
     * Returns a new Thread with the given Runnable and an
     * inherited AccessControlContext.
     */
    Thread newThreadWithAcc(Runnable target, AccessControlContext acc);

    /**
     * Invokes the finalize method of the given object.
     */
    void invokeFinalize(Object o) throws Throwable;

    /**
     * Returns the boot Layer
     */
    Layer getBootLayer();

    /**
     * Returns the ConcurrentHashMap used as a storage for ClassLoaderValue(s)
     * associated with the given class loader, creating it if it doesn't already exist.
     */
    ConcurrentHashMap<?, ?> createOrGetClassLoaderValueMap(ClassLoader cl);

    /**
     * Returns a class loaded by the bootstrap class loader.
     */
    Class<?> findBootstrapClassOrNull(ClassLoader cl, String name);

    /**
     * Returns a URL to a resource with the given name in a module that is
     * defined to the given class loader.
     */
    URL findResource(ClassLoader cl, String moduleName, String name) throws IOException;

    /**
     * Returns the Packages for the given class loader.
     */
    Stream<Package> packages(ClassLoader cl);

    /**
     * Define a Package of the given name and module by the given class loader.
     */
    Package definePackage(ClassLoader cl, String name, Module module);

    /**
     * Invokes Long.fastUUID
     */
    String fastUUID(long lsb, long msb);

    /**
     * Invalidate package access cache
     */
    void invalidatePackageAccessCache();
}
