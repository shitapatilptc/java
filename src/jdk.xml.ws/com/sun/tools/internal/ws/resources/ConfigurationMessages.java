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

package com.sun.tools.internal.ws.resources;

import com.sun.istack.internal.localization.Localizable;
import com.sun.istack.internal.localization.LocalizableMessageFactory;
import com.sun.istack.internal.localization.Localizer;

import java.util.Locale;
import java.util.ResourceBundle;


/**
 * Defines string formatting method for each constant in the resource file
 *
 */
public final class ConfigurationMessages {
    private final static String BUNDLE_NAME = "com.sun.tools.internal.ws.resources.configuration";
    private final static LocalizableMessageFactory messageFactory =
        new LocalizableMessageFactory(BUNDLE_NAME, ConfigurationMessages::getResourceBundle);
    private final static Localizer localizer = new Localizer();

    private static ResourceBundle getResourceBundle(Locale locale) {
        return ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static Localizable localizableCONFIGURATION_NOT_BINDING_FILE(Object arg0) {
        return messageFactory.getMessage("configuration.notBindingFile", arg0);
    }


    /**
     * Ignoring: binding file "{0}". It is not a jaxws or a jaxb binding file.
     *
     */
    public static String CONFIGURATION_NOT_BINDING_FILE(Object arg0) {
        return localizer.localize(localizableCONFIGURATION_NOT_BINDING_FILE(arg0));
    }

}
