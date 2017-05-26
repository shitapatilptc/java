/*
 * Copyright (c) 1997, 2016, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.tools.internal.ws.util;

import com.sun.xml.internal.ws.util.exception.JAXWSExceptionBase;

import java.util.Locale;
import java.util.ResourceBundle;

/**
  * @author WS Development Team
  */
public class WSDLParseException extends JAXWSExceptionBase {

    public WSDLParseException(String key, Object... args) {
        super(key, args);
    }

    public WSDLParseException(Throwable throwable) {
        super(throwable);
    }

    public String getDefaultResourceBundleName() {
        return "com.sun.tools.internal.ws.resources.util";
    }

    @Override
    public ResourceBundle getResourceBundle(Locale locale) {
        return ResourceBundle.getBundle(getDefaultResourceBundleName(), locale);
    }
}
