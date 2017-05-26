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

package com.sun.xml.internal.ws.resources;

import com.sun.istack.internal.localization.Localizable;
import com.sun.istack.internal.localization.LocalizableMessageFactory;
import com.sun.istack.internal.localization.Localizer;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Defines string formatting method for each constant in the resource file
 *
 */
public final class BindingApiMessages {
    private final static String BUNDLE_NAME = "com.sun.xml.internal.ws.resources.bindingApi";
    private final static LocalizableMessageFactory messageFactory =
        new LocalizableMessageFactory(BUNDLE_NAME, BindingApiMessages::getResourceBundle);
    private final static Localizer localizer = new Localizer();

    private static ResourceBundle getResourceBundle(Locale locale) {
        return ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static Localizable localizableBINDING_API_NO_FAULT_MESSAGE_NAME() {
        return messageFactory.getMessage("binding.api.no.fault.message.name");
    }

    /**
     * Fault message name must not be null.
     *
     */
    public static String BINDING_API_NO_FAULT_MESSAGE_NAME() {
        return localizer.localize(localizableBINDING_API_NO_FAULT_MESSAGE_NAME());
    }

}
