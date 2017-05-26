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
 * Defines the Remote Method Invocation (RMI) API.
 *
 * @since 9
 */
module java.rmi {
    requires java.logging;

    exports java.rmi;
    exports java.rmi.activation;
    exports java.rmi.dgc;
    exports java.rmi.registry;
    exports java.rmi.server;
    exports javax.rmi.ssl;
    // com.sun.rmi.rmid contains permissions classes that must be
    // accessible to the security manager at initialization time
    exports com.sun.rmi.rmid to java.base;
    exports sun.rmi.registry to
        jdk.management.agent;
    exports sun.rmi.server to
        java.management.rmi,
        jdk.management.agent,
        jdk.jconsole;
    exports sun.rmi.transport to
        java.management.rmi,
        jdk.management.agent,
        jdk.jconsole;
    uses java.rmi.server.RMIClassLoaderSpi;
}
