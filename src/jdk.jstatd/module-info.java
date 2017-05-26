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

module jdk.jstatd {
    requires java.rmi;
    requires jdk.internal.jvmstat;

    // RMI needs to serialize types in this package
    exports sun.jvmstat.monitor.remote to java.rmi;

    provides sun.jvmstat.monitor.MonitoredHostService with sun.jvmstat.perfdata.monitor.protocol.rmi.MonitoredHostRmiService;
}

