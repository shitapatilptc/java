/*
 * Copyright (c) 2014, Oracle and/or its affiliates. All rights reserved.
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

/**
 * Defines services that allow agents to
 * instrument programs running on the JVM.
 *
 * @since 9
 */
module java.instrument {
    // source file: file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/java.instrument/share/classes/module-info.java
    //              file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/closed/java.instrument/share/classes/module-info.java.extra
    exports java.lang.instrument;
    exports jdk.internal.instrumentation to jdk.jfr;

}
