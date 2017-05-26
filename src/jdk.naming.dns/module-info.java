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

module jdk.naming.dns {
    requires java.naming;

    // temporary export until NamingManager.getURLContext uses services
    exports com.sun.jndi.url.dns to java.naming;

    provides javax.naming.spi.InitialContextFactory
        with com.sun.jndi.dns.DnsContextFactory;
}

