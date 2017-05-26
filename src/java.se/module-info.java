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
 * Defines the core Java SE API.
 * <P>
 * The modules defining CORBA and Java EE APIs are not required by
 * this module, but they are required by {@code java.se.ee}.
 *
 * @since 9
 */
module java.se {
    requires transitive java.compiler;
    requires transitive java.datatransfer;
    requires transitive java.desktop;
    requires transitive java.instrument;
    requires transitive java.logging;
    requires transitive java.management;
    requires transitive java.management.rmi;
    requires transitive java.naming;
    requires transitive java.prefs;
    requires transitive java.rmi;
    requires transitive java.scripting;
    requires transitive java.security.jgss;
    requires transitive java.security.sasl;
    requires transitive java.sql;
    requires transitive java.sql.rowset;
    requires transitive java.xml;
    requires transitive java.xml.crypto;
}
