/*
 * Copyright (c) 2015, Oracle and/or its affiliates. All rights reserved.
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
 * Defines a subset of the Java Transaction API (JTA) to support CORBA interop.
 * <P>
 * The subset consists of RMI exception types which are mapped to CORBA system
 * exceptions by the 'Java Language to IDL Mapping Specification'.
 *
 * @since 9
 */
module java.transaction {
    requires transitive java.rmi;
    exports javax.transaction;
}

