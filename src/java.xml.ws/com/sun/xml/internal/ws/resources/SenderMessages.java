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
public final class SenderMessages {
    private final static String BUNDLE_NAME = "com.sun.xml.internal.ws.resources.sender";
    private final static LocalizableMessageFactory messageFactory =
        new LocalizableMessageFactory(BUNDLE_NAME, SenderMessages::getResourceBundle);
    private final static Localizer localizer = new Localizer();

    private static ResourceBundle getResourceBundle(Locale locale) {
        return ResourceBundle.getBundle(BUNDLE_NAME, locale);
    }

    public static Localizable localizableSENDER_REQUEST_ILLEGAL_VALUE_FOR_CONTENT_NEGOTIATION(Object arg0) {
        return messageFactory.getMessage("sender.request.illegalValueForContentNegotiation", arg0);
    }

    /**
     * illegal value for content negotiation property "{0}"
     *
     */
    public static String SENDER_REQUEST_ILLEGAL_VALUE_FOR_CONTENT_NEGOTIATION(Object arg0) {
        return localizer.localize(localizableSENDER_REQUEST_ILLEGAL_VALUE_FOR_CONTENT_NEGOTIATION(arg0));
    }

    public static Localizable localizableSENDER_RESPONSE_CANNOT_DECODE_FAULT_DETAIL() {
        return messageFactory.getMessage("sender.response.cannotDecodeFaultDetail");
    }

    /**
     * fault detail cannot be decoded
     *
     */
    public static String SENDER_RESPONSE_CANNOT_DECODE_FAULT_DETAIL() {
        return localizer.localize(localizableSENDER_RESPONSE_CANNOT_DECODE_FAULT_DETAIL());
    }

    public static Localizable localizableSENDER_NESTED_ERROR(Object arg0) {
        return messageFactory.getMessage("sender.nestedError", arg0);
    }

    /**
     * sender error: {0}
     *
     */
    public static String SENDER_NESTED_ERROR(Object arg0) {
        return localizer.localize(localizableSENDER_NESTED_ERROR(arg0));
    }

    public static Localizable localizableSENDER_REQUEST_MESSAGE_NOT_READY() {
        return messageFactory.getMessage("sender.request.messageNotReady");
    }

    /**
     * message not ready to be sent
     *
     */
    public static String SENDER_REQUEST_MESSAGE_NOT_READY() {
        return localizer.localize(localizableSENDER_REQUEST_MESSAGE_NOT_READY());
    }

}
