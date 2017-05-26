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

package com.sun.tools.internal.ws.wsdl.framework;

import com.sun.xml.internal.ws.util.exception.JAXWSExceptionBase;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 * An exception signalling that validation of an entity failed.
 *
 * @author WS Development Team
 */
public class ValidationException extends JAXWSExceptionBase {

    public ValidationException(String key, Object... args) {
        super(key, args);
    }

    public ValidationException(Throwable throwable) {
        super(throwable);
    }

    public String getDefaultResourceBundleName() {
        return "com.sun.tools.internal.ws.resources.wsdl";
    }

    @Override
    public ResourceBundle getResourceBundle(Locale locale) {
        return ResourceBundle.getBundle(getDefaultResourceBundleName(), locale);
    }
}
