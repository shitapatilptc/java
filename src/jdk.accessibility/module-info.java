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

module jdk.accessibility {
    // source file: file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/jdk.accessibility/share/classes/module-info.java
    //              file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/jdk.accessibility/windows/classes/module-info.java.extra
    requires transitive java.desktop;
    exports com.sun.java.accessibility.util;

    provides javax.accessibility.AccessibilityProvider with com.sun.java.accessibility.internal.ProviderImpl;
}
