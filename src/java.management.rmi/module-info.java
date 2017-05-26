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

/**
 * Defines the RMI Connector for the Java Management Extensions (JMX) Remote API.
 * <P>
 * The {@linkplain javax.management.remote.rmi RMI connector} is a connector
 * for the JMX Remote API that uses RMI to transmit client requests to a remote
 * MBean server.
 *
 * @provides javax.management.remote.JMXConnectorProvider
 *           A provider of {@code JMXConnector} for the RMI protocol.<br>
 *           Instances of {@code JMXConnector} using the RMI protocol
 *           are usually created by the {@link
 *           javax.management.remote.JMXConnectorFactory} which will locate
 *           and load the appropriate {@code JMXConnectorProvider} service
 *           implementation for the given protocol.
 *
 * @provides javax.management.remote.JMXConnectorServerProvider
 *           A provider of {@code JMXConnectorServer} for the RMI protocol.<br>
 *           Instances of {@code JMXConnectorServer} using the RMI protocol
 *           are usually created by the {@link
 *           javax.management.remote.JMXConnectorServerFactory} which will locate
 *           and load the appropriate {@code JMXConnectorServerProvider} service
 *           implementation for the given protocol.
 *
 * @since 9
 */
module java.management.rmi {

    requires transitive java.management;
    requires transitive java.rmi;
    requires java.naming;

    exports javax.management.remote.rmi;

    // The qualified export below is required to preserve backward
    // compatibility for the legacy case where an ordered list
    // of package prefixes can be specified to the factory.
    exports com.sun.jmx.remote.protocol.rmi to java.management;

    // jdk.management.agent needs to create an RMIExporter instance.
    exports com.sun.jmx.remote.internal.rmi to jdk.management.agent;

    // The java.management.rmi module provides implementations
    // of the JMXConnectorProvider and JMXConnectorServerProvider
    // services supporting the RMI protocol.
    provides javax.management.remote.JMXConnectorProvider
        with com.sun.jmx.remote.protocol.rmi.ClientProvider;
    provides javax.management.remote.JMXConnectorServerProvider
        with com.sun.jmx.remote.protocol.rmi.ServerProvider;

}
