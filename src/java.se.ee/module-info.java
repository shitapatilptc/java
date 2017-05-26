/*
 * Copyright (c) 2016, 2017, Oracle and/or its affiliates. All rights reserved.
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
 * Defines the full API of the Java SE Platform.
 * <P>
 * This module requires {@code java.se} and supplements it with modules
 * that define CORBA and Java EE APIs. These modules are upgradeable.
 *
 * @since 9
 */
@SuppressWarnings("deprecation")
module java.se.ee {

    requires transitive java.se;

    // Upgradeable modules for Java EE technologies
    requires transitive java.activation;
    requires transitive java.corba;
    requires transitive java.transaction;
    requires transitive java.xml.bind;
    requires transitive java.xml.ws;
    requires transitive java.xml.ws.annotation;

}
