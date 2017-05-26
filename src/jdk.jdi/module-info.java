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

/**
 * Defines the Java Debugger Interface.
 *
 * @since 9
 */
module jdk.jdi {
    // source file: file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/jdk.jdi/share/classes/module-info.java
    //              file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/jdk.jdi/windows/classes/module-info.java.extra
    requires jdk.attach;
    requires jdk.jdwp.agent;
    exports com.sun.jdi;
    exports com.sun.jdi.connect;
    exports com.sun.jdi.connect.spi;
    exports com.sun.jdi.event;
    exports com.sun.jdi.request;

    uses com.sun.jdi.connect.Connector;
    uses com.sun.jdi.connect.spi.TransportService;
    provides com.sun.jdi.connect.Connector with
        com.sun.tools.jdi.ProcessAttachingConnector,
        com.sun.tools.jdi.RawCommandLineLauncher,
        com.sun.tools.jdi.SocketAttachingConnector,
        com.sun.tools.jdi.SocketListeningConnector,
        com.sun.tools.jdi.SunCommandLineLauncher,
        com.sun.tools.jdi.SharedMemoryAttachingConnector,
        com.sun.tools.jdi.SharedMemoryListeningConnector;
}
