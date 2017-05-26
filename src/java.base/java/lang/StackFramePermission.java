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
package java.lang;

/**
 * Permission to access {@link StackWalker.StackFrame}.
 *
 * @see java.lang.StackWalker.Option#RETAIN_CLASS_REFERENCE
 * @see StackWalker.StackFrame#getDeclaringClass()
 */
public class StackFramePermission extends java.security.BasicPermission {
    private static final long serialVersionUID = 2841894854386706014L;

    /**
     * Creates a new {@code StackFramePermission} object.
     *
     * @param name Permission name.  Must be "retainClassReference".
     *
     * @throws IllegalArgumentException if {@code name} is invalid.
     * @throws NullPointerException if {@code name} is {@code null}.
     */
    public StackFramePermission(String name) {
        super(name);
        if (!name.equals("retainClassReference")) {
            throw new IllegalArgumentException("name: " + name);
        }
    }
}
