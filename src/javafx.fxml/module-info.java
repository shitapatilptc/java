/*
 * Copyright (c) 2015, 2017, Oracle and/or its affiliates. All rights reserved.
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
 * This module defines the FXML APIs for the JavaFX UI toolkit.
 */
module javafx.fxml {
    requires transitive javafx.base;

    requires java.logging;
    requires java.scripting;
    requires java.xml;
    requires javafx.graphics;

    exports javafx.fxml;

}
