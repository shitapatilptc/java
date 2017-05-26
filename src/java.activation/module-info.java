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
 * Defines the JavaBeans Activation Framework (JAF) API.
 *
 * @since 9
 */
module java.activation {
    requires transitive java.datatransfer;
    requires java.logging;

    exports javax.activation;
}

