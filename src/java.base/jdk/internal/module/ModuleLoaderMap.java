/*
 * Copyright (c) 2015, 2017, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.module;

import java.lang.module.Configuration;
import java.lang.module.ResolvedModule;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;

import jdk.internal.loader.ClassLoaders;


/**
 * The module to class loader map.  The list of boot modules and platform modules
 * are generated at build time.
 */
final class ModuleLoaderMap {

    /**
     * Returns the function to map modules in the given configuration to the
     * built-in class loaders.
     */
    static Function<String, ClassLoader> mappingFunction(Configuration cf) {

        // The list of boot modules and platform modules are generated at build time.
        final String[] BOOT_MODULES = new String[] { "java.base",
            "java.datatransfer",
            "java.desktop",
            "java.instrument",
            "java.logging",
            "java.management",
            "java.management.rmi",
            "java.naming",
            "java.prefs",
            "java.rmi",
            "java.security.sasl",
            "java.xml",
            "jdk.httpserver",
            "jdk.internal.vm.ci",
            "jdk.jfr",
            "jdk.management",
            "jdk.management.agent",
            "jdk.management.cmm",
            "jdk.management.jfr",
            "jdk.management.resource",
            "jdk.naming.rmi",
            "jdk.net",
            "jdk.sctp",
            "jdk.snmp",
            "jdk.unsupported" };
        final String[] PLATFORM_MODULES = new String[] { "java.activation",
            "java.compiler",
            "java.corba",
            "java.jnlp",
            "java.scripting",
            "java.se",
            "java.se.ee",
            "java.security.jgss",
            "java.smartcardio",
            "java.sql",
            "java.sql.rowset",
            "java.transaction",
            "java.xml.bind",
            "java.xml.crypto",
            "java.xml.ws",
            "java.xml.ws.annotation",
            "javafx.base",
            "javafx.controls",
            "javafx.deploy",
            "javafx.fxml",
            "javafx.graphics",
            "javafx.media",
            "javafx.swing",
            "javafx.web",
            "jdk.accessibility",
            "jdk.charsets",
            "jdk.crypto.cryptoki",
            "jdk.crypto.ec",
            "jdk.crypto.mscapi",
            "jdk.deploy",
            "jdk.dynalink",
            "jdk.incubator.httpclient",
            "jdk.internal.vm.compiler",
            "jdk.javaws",
            "jdk.jsobject",
            "jdk.localedata",
            "jdk.naming.dns",
            "jdk.plugin",
            "jdk.plugin.dom",
            "jdk.plugin.server",
            "jdk.scripting.nashorn",
            "jdk.security.auth",
            "jdk.security.jgss",
            "jdk.xml.dom",
            "jdk.zipfs",
            "oracle.desktop",
            "oracle.net" };

        Set<String> bootModules = new HashSet<>(BOOT_MODULES.length);
        for (String mn : BOOT_MODULES) {
            bootModules.add(mn);
        }

        Set<String> platformModules = new HashSet<>(PLATFORM_MODULES.length);
        for (String mn : PLATFORM_MODULES) {
            platformModules.add(mn);
        }

        ClassLoader platformClassLoader = ClassLoaders.platformClassLoader();
        ClassLoader appClassLoader = ClassLoaders.appClassLoader();

        Map<String, ClassLoader> map = new HashMap<>();

        for (ResolvedModule resolvedModule : cf.modules()) {
            String mn = resolvedModule.name();
            if (!bootModules.contains(mn)) {
                if (platformModules.contains(mn)) {
                    map.put(mn, platformClassLoader);
                } else {
                    map.put(mn, appClassLoader);
                }
            }
        }

        return new Function<String, ClassLoader> () {
            @Override
            public ClassLoader apply(String mn) {
                return map.get(mn);
            }
        };
    }
}
