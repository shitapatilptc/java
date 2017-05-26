/*
 * Copyright (c) 2014, 2015, Oracle and/or its affiliates. All rights reserved.
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

module jdk.localedata {
    provides sun.util.locale.provider.LocaleDataMetaInfo with
        sun.util.resources.cldr.provider.CLDRLocaleDataMetaInfo,
        sun.util.resources.provider.NonBaseLocaleDataMetaInfo;
    provides sun.util.resources.LocaleData.CommonResourceBundleProvider with
        sun.util.resources.provider.LocaleDataProvider;
    provides sun.util.resources.LocaleData.SupplementaryResourceBundleProvider with
        sun.util.resources.provider.SupplementaryLocaleDataProvider;
}
