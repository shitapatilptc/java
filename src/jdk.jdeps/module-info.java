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

/** Defines tools for analysing dependencies in Java libraries and programs, including
 *  the <em>jdeps</em> and <em>javap</em> tools.
 *
 *  @since 9
 */
module jdk.jdeps {
    // source file: file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/langtools/src/jdk.jdeps/share/classes/module-info.java
    //              file:///c:/cygwin/var/tmp/jib-java_re/install/java/re/javafx/9/promoted/all/160/bundles/windows-x64/javafx-exports.zip/modules_src/jdk.jdeps/module-info.java.extra
    requires java.base;
    requires java.compiler;
    requires jdk.compiler;
    exports com.sun.tools.classfile to jdk.jlink;
    exports com.sun.tools.jdeps to jdk.packager;

    provides java.util.spi.ToolProvider with
        com.sun.tools.javap.Main.JavapToolProvider,
        com.sun.tools.jdeps.Main.JDepsToolProvider;
}
