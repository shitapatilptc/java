/*
 * Copyright (c) 2015, 2016, Oracle and/or its affiliates. All rights reserved.
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

package java.lang.reflect;

/**
 * Thrown when creating a Layer fails.
 *
 * @see Layer
 *
 * @since 9
 * @spec JPMS
 */
public class LayerInstantiationException extends RuntimeException {
    private static final long serialVersionUID = -906239691613568347L;

    /**
     * Constructs a {@code LayerInstantiationException} with no detail message.
     */
    public LayerInstantiationException() {
    }

    /**
     * Constructs a {@code LayerInstantiationException} with the given detail
     * message.
     *
     * @param msg
     *        The detail message; can be {@code null}
     */
    public LayerInstantiationException(String msg) {
        super(msg);
    }

    /**
     * Constructs a {@code LayerInstantiationException} with the given cause.
     *
     * @param cause
     *        The cause; can be {@code null}
     */
    public LayerInstantiationException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a {@code FindException} with the given detail message
     * and cause.
     *
     * @param msg
     *        The detail message; can be {@code null}
     * @param cause
     *        The cause; can be {@code null}
     */
    public LayerInstantiationException(String msg, Throwable cause) {
        super(msg, cause);
    }

}

