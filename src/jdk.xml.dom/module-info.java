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

module jdk.xml.dom {
    requires transitive java.xml;
    exports org.w3c.dom.css;
    exports org.w3c.dom.html;
    exports org.w3c.dom.stylesheets;
    exports org.w3c.dom.xpath;
}
