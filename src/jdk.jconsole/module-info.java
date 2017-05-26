/*
 * Copyright (c) 2014, 2017, Oracle and/or its affiliates. All rights reserved.
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

module jdk.jconsole {
    requires transitive java.desktop;
    requires transitive java.management;
    requires java.management.rmi;
    requires java.rmi;
    requires jdk.attach;
    requires jdk.internal.jvmstat;
    requires jdk.management;
    requires jdk.management.agent;
    exports com.sun.tools.jconsole;
    uses com.sun.tools.jconsole.JConsolePlugin;
}
