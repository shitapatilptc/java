/*
 * Copyright (c) 2014, 2016, Oracle and/or its affiliates. All rights reserved.
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

/** Defines the implementation of the
 *  {@link javax.tools.ToolProvider#getSystemJavaCompiler system Java compiler}
 *  and its command line equivalent, <em>javac</em>, as well as <em>javah</em>.
 *
 *  @since 9
 */
module jdk.compiler {
    requires transitive java.compiler;

    exports com.sun.source.doctree;
    exports com.sun.source.tree;
    exports com.sun.source.util;
    exports com.sun.tools.javac;
    exports com.sun.tools.doclint to
        jdk.javadoc;
    exports com.sun.tools.javac.api to
        jdk.javadoc,
        jdk.jshell;
    exports com.sun.tools.javac.code to
        jdk.javadoc,
        jdk.jshell;
    exports com.sun.tools.javac.comp to
        jdk.javadoc,
        jdk.jshell;
    exports com.sun.tools.javac.file to
        jdk.jdeps,
        jdk.javadoc;
    exports com.sun.tools.javac.jvm to
        jdk.javadoc;
    exports com.sun.tools.javac.main to
        jdk.javadoc;
    exports com.sun.tools.javac.model to
        jdk.javadoc;
    exports com.sun.tools.javac.parser to
        jdk.jshell;
    exports com.sun.tools.javac.platform to
        jdk.javadoc;
    exports com.sun.tools.javac.tree to
        jdk.javadoc,
        jdk.jshell;
    exports com.sun.tools.javac.util to
        jdk.jdeps,
        jdk.javadoc,
        jdk.jshell;
    exports jdk.internal.shellsupport.doc to
        jdk.jshell,
        jdk.scripting.nashorn.shell;

    uses javax.annotation.processing.Processor;
    uses com.sun.source.util.Plugin;
    uses com.sun.tools.javac.platform.PlatformProvider;

    provides java.util.spi.ToolProvider
        with com.sun.tools.javac.main.JavacToolProvider;

    provides com.sun.tools.javac.platform.PlatformProvider
        with com.sun.tools.javac.platform.JDKPlatformProvider;

    provides javax.tools.JavaCompiler
        with com.sun.tools.javac.api.JavacTool;

    provides javax.tools.Tool
        with com.sun.tools.javac.api.JavacTool;
}

