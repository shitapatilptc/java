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

module jdk.hotspot.agent {
    requires java.datatransfer;
    requires java.desktop;
    requires java.rmi;
    requires java.scripting;

    // RMI needs to serialize types in this package
    exports sun.jvm.hotspot.debugger.remote to java.rmi;

}
