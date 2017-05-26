/*
 * Copyright (c) 2001, 2016, Oracle and/or its affiliates. All rights reserved.
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

package jdk.javadoc.doclet.taglet;

import java.util.List;
import java.util.Set;

import com.sun.source.doctree.DocTree;

/**
 * The interface for a custom tag used by Doclets. A custom
 * tag must implement this interface, and must have a public
 * default constructor (i.e. a public constructor with no
 * parameters), by which, the doclet will instantiate and
 * register the custom tag.
 *
 * @since 9
 */

public interface Taglet {

    /**
     * Returns the set of locations in which a taglet may be used.
     * @return the set of locations in which a taglet may be used
     * allowed in or an empty set.
     */
    Set<Location> getAllowedLocations();

    /**
     * Indicates the tag is an inline or a body tag.
     * @return true if this <code>Taglet</code>
     * is an inline tag, false otherwise.
     */
    boolean isInlineTag();

    /**
     * Returns the name of the tag.
     * @return the name of this custom tag.
     */
    String getName();

    /**
     * Given the {@link DocTree DocTree} representation of this custom
     * tag, return its string representation, which is output
     * to the generated page.
     * @param tag the <code>Tag</code> representation of this custom tag.
     * @return the string representation of this <code>Tag</code>.
     */
    String toString(DocTree tag);

    /**
     * Given a List of {@link DocTree DocTrees} representing this custom
     * tag, return its string representation, which is output
     * to the generated page.  This method should
     * return null if this taglet represents an inline or body tag.
     * @param tags the list of <code>DocTree</code>s representing this custom tag.
     * @return the string representation of this <code>Tag</code>.
     */
    String toString(List<? extends DocTree> tags);

    /**
     * The kind of location.
     */
    public static enum Location {
        /** In an Overview document. */
        OVERVIEW,
        /** In the documentation for a module. */
        MODULE,
        /** In the documentation for a package. */
        PACKAGE,
        /** In the documentation for a class, interface or enum. */
        TYPE,
        /** In the documentation for a constructor. */
        CONSTRUCTOR,
        /** In the documentation for a method. */
        METHOD,
        /** In the documentation for a field. */
        FIELD
    }
}
