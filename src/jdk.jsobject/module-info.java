/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
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

module jdk.jsobject {
    // source file: file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/jdk.jsobject/share/classes/module-info.java
    //              file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/closed/jdk.jsobject/windows/classes/module-info.java.extra
    requires java.desktop;
    exports netscape.javascript;
    exports jdk.internal.netscape.javascript.spi to jdk.plugin;

    uses jdk.internal.netscape.javascript.spi.JSObjectProvider;
}
