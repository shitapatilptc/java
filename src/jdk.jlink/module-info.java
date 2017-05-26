/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
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

module jdk.jlink {
    // source file: file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/jdk.jlink/share/classes/module-info.java
    //              file:///c:/cygwin/var/tmp/jib-java_re/install/java/re/javafx/9/promoted/all/160/bundles/windows-x64/javafx-exports.zip/modules_src/jdk.jlink/module-info.java.extra
    requires jdk.internal.opt;
    requires jdk.jdeps;
    exports jdk.tools.jlink.internal.packager to jdk.packager;

    uses jdk.tools.jlink.plugin.Plugin;
    provides java.util.spi.ToolProvider with
        jdk.tools.jmod.Main.JmodToolProvider,
        jdk.tools.jlink.internal.Main.JlinkToolProvider;
    provides jdk.tools.jlink.plugin.Plugin with
        jdk.tools.jlink.internal.plugins.StripDebugPlugin,
        jdk.tools.jlink.internal.plugins.ExcludePlugin,
        jdk.tools.jlink.internal.plugins.ExcludeFilesPlugin,
        jdk.tools.jlink.internal.plugins.ExcludeJmodSectionPlugin,
        jdk.tools.jlink.internal.plugins.LegalNoticeFilePlugin,
        jdk.tools.jlink.internal.plugins.SystemModulesPlugin,
        jdk.tools.jlink.internal.plugins.StripNativeCommandsPlugin,
        jdk.tools.jlink.internal.plugins.OrderResourcesPlugin,
        jdk.tools.jlink.internal.plugins.DefaultCompressPlugin,
        jdk.tools.jlink.internal.plugins.ExcludeVMPlugin,
        jdk.tools.jlink.internal.plugins.IncludeLocalesPlugin,
        jdk.tools.jlink.internal.plugins.GenerateJLIClassesPlugin,
        jdk.tools.jlink.internal.plugins.ReleaseInfoPlugin,
        jdk.tools.jlink.internal.plugins.ClassForNamePlugin;
}
