/*
 * Copyright (c) 2014, 2017, Oracle and/or its affiliates. All rights reserved.
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
 * The SunPKCS11 security provider.
 *
 * @since 9
 */
module jdk.crypto.cryptoki {
    // Depends on SunEC provider for EC related functionality
    requires jdk.crypto.ec;
    provides java.security.Provider with sun.security.pkcs11.SunPKCS11;
}

