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
public final class JavacompilerMessages {
    private final static String BUNDLE_NAME = "com.sun.tools.internal.ws.resources.javacompiler";
    private final static LocalizableMessageFactory messageFactory =
        new LocalizableMessageFactory(BUNDLE_NAME, JavacompilerMessages::getResourceBundle);
    private final static Localizer localizer = new Localizer();

    private static ResourceBundle getResourceBundle(Locale locale) {
        return ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static Localizable localizableNO_JAVACOMPILER_ERROR() {
        return messageFactory.getMessage("no.javacompiler.error");
    }

    /**
     * No system compiler found, check your jdk.
     *
     */
    public static String NO_JAVACOMPILER_ERROR() {
        return localizer.localize(localizableNO_JAVACOMPILER_ERROR());
    }

}
