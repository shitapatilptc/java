/*
 * Copyright (c) 2017, Oracle and/or its affiliates. All rights reserved.
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

module jdk.management.agent {
    // source file: file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/jdk.management.agent/share/classes/module-info.java
    //              file:///c:/workspace/9-2-build-windows-amd64-cygwin-phase2/jdk9/6180.nc/jdk/src/closed/jdk.management.agent/share/classes/module-info.java.extra
    requires java.management;
    requires java.management.rmi;
    exports jdk.internal.agent to jdk.jconsole;
    exports jdk.internal.agent.spi to jdk.snmp;

    uses jdk.internal.agent.spi.AgentProvider;
}
