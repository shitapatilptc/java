/*
 * Copyright (c) 2014, 2015, Oracle and/or its affiliates. All rights reserved.
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

module jdk.naming.rmi {
    requires java.naming;
    requires java.rmi;
    provides javax.naming.spi.InitialContextFactory
        with com.sun.jndi.rmi.registry.RegistryContextFactory;

    // temporary export until NamingManager.getURLContext uses services
    exports com.sun.jndi.url.rmi to java.naming;
    exports com.sun.jndi.rmi.registry to java.rmi;
}

