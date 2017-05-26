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

module jdk.jartool {
    exports com.sun.jarsigner;
    exports jdk.security.jarsigner;

    provides java.util.spi.ToolProvider with sun.tools.jar.JarToolProvider;
}

