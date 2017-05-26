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
 * Defines Java extensions to the GSS-API and an implementation of the SASL
 * GSSAPI mechanism.
 *
 * @since 9
 */
module jdk.security.jgss {
    requires transitive java.security.jgss;
    requires java.logging;
    requires java.security.sasl;
    exports com.sun.security.jgss;
    provides java.security.Provider with com.sun.security.sasl.gsskerb.JdkSASL;
}

