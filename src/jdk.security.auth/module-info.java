/*
 * Copyright (c) 2014, 2016, Oracle and/or its affiliates. All rights reserved.
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
 * Contains the implementation of the javax.security.auth.* interfaces and
 * various authentication modules.
 *
 * @since 9
 */
module jdk.security.auth {
    requires transitive java.naming;
    requires java.security.jgss;

    exports com.sun.security.auth;
    exports com.sun.security.auth.callback;
    exports com.sun.security.auth.login;
    exports com.sun.security.auth.module;

    provides javax.security.auth.spi.LoginModule with
        com.sun.security.auth.module.Krb5LoginModule,
        com.sun.security.auth.module.UnixLoginModule,
        com.sun.security.auth.module.JndiLoginModule,
        com.sun.security.auth.module.KeyStoreLoginModule,
        com.sun.security.auth.module.LdapLoginModule,
        com.sun.security.auth.module.NTLoginModule;
}

