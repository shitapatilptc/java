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
 * This modules defines the UI controls, charts, and skins that are available
 * for the JavaFX UI toolkit.
 */
module javafx.controls {
    requires transitive javafx.base;
    requires transitive javafx.graphics;

    exports javafx.scene.chart;
    exports javafx.scene.control;
    exports javafx.scene.control.cell;
    exports javafx.scene.control.skin;

    exports com.sun.javafx.scene.control to
        javafx.web;
    exports com.sun.javafx.scene.control.behavior to
        javafx.web;
    exports com.sun.javafx.scene.control.inputmap to
        javafx.web;
    exports com.sun.javafx.scene.control.skin to
        javafx.graphics,
        javafx.web;
}
