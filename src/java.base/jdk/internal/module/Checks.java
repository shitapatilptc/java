/*
 * Copyright (c) 2009, 2017, Oracle and/or its affiliates. All rights reserved.
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

package jdk.internal.module;

/**
 * Utility class for checking module, package, and class names.
 */

public final class Checks {

    private Checks() { }

    /**
     * Checks a name to ensure that it's a legal module name.
     *
     * @throws IllegalArgumentException if name is null or not a legal
     *         module name
     */
    public static String requireModuleName(String name) {
        if (name == null)
            throw new IllegalArgumentException("Null module name");
        int next;
        int off = 0;
        while ((next = name.indexOf('.', off)) != -1) {
            if (isJavaIdentifier(name, off, (next - off)) == -1) {
                String id = name.substring(off, next);
                throw new IllegalArgumentException(name + ": Invalid module name"
                        + ": '" + id + "' is not a Java identifier");
            }
            off = next+1;
        }
        int last = isJavaIdentifier(name, off, name.length() - off);
        if (last == -1) {
            String id = name.substring(off);
            throw new IllegalArgumentException(name + ": Invalid module name"
                    + ": '" + id + "' is not a Java identifier");
        }
        return name;
    }

    /**
     * Returns {@code true} if the given name is a legal module name.
     */
    public static boolean isModuleName(String name) {
        int next;
        int off = 0;
        while ((next = name.indexOf('.', off)) != -1) {
            if (isJavaIdentifier(name, off, (next - off)) == -1)
                return false;
            off = next+1;
        }
        int last = isJavaIdentifier(name, off, name.length() - off);
        if (last == -1)
            return false;
        return true;
    }

    /**
     * Checks a name to ensure that it's a legal package name.
     *
     * @throws IllegalArgumentException if name is null or not a legal
     *         package name
     */
    public static String requirePackageName(String name) {
        return requireTypeName("package name", name);
    }

    /**
     * Returns {@code true} if the given name is a legal package name.
     */
    public static boolean isPackageName(String name) {
        return isTypeName(name);
    }

    /**
     * Checks a name to ensure that it's a legal qualified class name
     *
     * @throws IllegalArgumentException if name is null or not a legal
     *         qualified class name
     */
    public static String requireServiceTypeName(String name) {
        return requireQualifiedClassName("service type name", name);
    }

    /**
     * Checks a name to ensure that it's a legal qualified class name.
     *
     * @throws IllegalArgumentException if name is null or not a legal
     *         qualified class name
     */
    public static String requireServiceProviderName(String name) {
        return requireQualifiedClassName("service provider name", name);
    }

    /**
     * Checks a name to ensure that it's a legal qualified class name in
     * a named package.
     *
     * @throws IllegalArgumentException if name is null or not a legal
     *         qualified class name in a named package
     */
    public static String requireQualifiedClassName(String what, String name) {
        requireTypeName(what, name);
        if (name.indexOf('.') == -1)
            throw new IllegalArgumentException(name + ": is not a qualified name of"
                                               + " a Java class in a named package");
        return name;
    }

    /**
     * Returns {@code true} if the given name is a legal class name.
     */
    public static boolean isClassName(String name) {
        return isTypeName(name);
    }

    /**
     * Returns {@code true} if the given name is a legal type name.
     */
    private static boolean isTypeName(String name) {
        int next;
        int off = 0;
        while ((next = name.indexOf('.', off)) != -1) {
            if (isJavaIdentifier(name, off, (next - off)) == -1)
                return false;
            off = next+1;
        }
        int count = name.length() - off;
        return (isJavaIdentifier(name, off, count) != -1);
    }

    /**
     * Checks if the given name is a legal type name.
     *
     * @throws IllegalArgumentException if name is null or not a legal
     *         type name
     */
    private static String requireTypeName(String what, String name) {
        if (name == null)
            throw new IllegalArgumentException("Null " + what);
        int next;
        int off = 0;
        while ((next = name.indexOf('.', off)) != -1) {
            if (isJavaIdentifier(name, off, (next - off)) == -1) {
                String id = name.substring(off, next);
                throw new IllegalArgumentException(name + ": Invalid " + what
                        + ": '" + id + "' is not a Java identifier");
            }
            off = next + 1;
        }
        if (isJavaIdentifier(name, off, name.length() - off) == -1) {
            String id = name.substring(off, name.length());
            throw new IllegalArgumentException(name + ": Invalid " + what
                    + ": '" + id + "' is not a Java identifier");
        }
        return name;
    }

    /**
     * Returns {@code true} if the last character of the given name is legal
     * as the last character of a module name.
     *
     * @throws IllegalArgumentException if name is empty
     */
    public static boolean hasLegalModuleNameLastCharacter(String name) {
        if (name.isEmpty())
            throw new IllegalArgumentException("name is empty");
        int len = name.length();
        if (isASCIIString(name)) {
            char c = name.charAt(len-1);
            return Character.isJavaIdentifierStart(c);
        } else {
            int i = 0;
            int cp = -1;
            while (i < len) {
                cp = name.codePointAt(i);
                i += Character.charCount(cp);
            }
            return Character.isJavaIdentifierStart(cp);
        }
    }

    /**
     * Returns true if the given string only contains ASCII characters.
     */
    private static boolean isASCIIString(String s) {
        int i = 0;
        while (i < s.length()) {
            int c = s.charAt(i);
            if (c > 0x7F)
                return false;
            i++;
        }
        return true;
    }

    /**
     * Checks if a char sequence is a legal Java identifier, returning the code
     * point of the last character if legal or {@code -1} if not legal.
     */
    private static int isJavaIdentifier(CharSequence cs, int offset, int count) {
        if (count == 0)
            return -1;
        int first = Character.codePointAt(cs, offset);
        if (!Character.isJavaIdentifierStart(first))
            return -1;

        int cp = first;
        int i = Character.charCount(first);
        while (i < count) {
            cp = Character.codePointAt(cs, offset+i);
            if (!Character.isJavaIdentifierPart(cp))
                return -1;
            i += Character.charCount(cp);
        }

        return cp;
    }
}
