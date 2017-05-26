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
 *  {@link javax.tools.ToolProvider#getSystemDocumentationTool system documentation tool}
 *  and its command line equivalent, <em>javadoc</em>.
 *
 *  @since 9
 */
module jdk.javadoc {
    requires transitive java.compiler;
    requires transitive jdk.compiler;
    requires java.xml;

    exports com.sun.javadoc;
    exports com.sun.tools.doclets;
    exports com.sun.tools.doclets.standard;
    exports com.sun.tools.javadoc;

    exports jdk.javadoc.doclet;
    exports jdk.javadoc.doclet.taglet;
    exports jdk.javadoc.doclets;

    provides java.util.spi.ToolProvider
        with jdk.javadoc.internal.tool.JavadocToolProvider;

    provides javax.tools.DocumentationTool
        with jdk.javadoc.internal.api.JavadocTool;

    provides javax.tools.Tool
        with jdk.javadoc.internal.api.JavadocTool;
}

