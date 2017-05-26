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

module jdk.xml.ws {
    requires java.compiler;
    requires java.logging;
    requires java.rmi;
    requires java.xml;
    requires java.xml.bind;
    requires java.xml.ws;
    requires jdk.xml.bind;

    uses com.sun.tools.internal.ws.wscompile.Plugin;
    provides com.sun.tools.internal.ws.wscompile.Plugin with
        com.sun.tools.internal.ws.wscompile.plugin.at_generated.PluginImpl;
}

