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

package jdk.internal.misc;

import java.lang.module.ModuleDescriptor;
import java.lang.reflect.Layer;
import java.lang.reflect.Module;
import java.net.URI;
import java.util.stream.Stream;

import jdk.internal.module.ServicesCatalog;

/**
 * Provides access to non-public methods in java.lang.reflect.Module
 */

public interface JavaLangReflectModuleAccess {

    /**
     * Defines the unnamed module for the given class loader.
     */
    Module defineUnnamedModule(ClassLoader loader);

    /**
     * Defines a new module to the Java virtual machine. The module
     * is defined to the given class loader.
     *
     * The URI is for information purposes only, it can be {@code null}.
     */
    Module defineModule(ClassLoader loader, ModuleDescriptor descriptor, URI uri);

    /**
     * Updates the readability so that module m1 reads m2. The new read edge
     * does not result in a strong reference to m2 (m2 can be GC'ed).
     *
     * This method is the same as m1.addReads(m2) but without a permission check.
     */
    void addReads(Module m1, Module m2);

    /**
     * Updates module m to read all unnamed modules.
     */
    void addReadsAllUnnamed(Module m);

    /**
     * Updates module m1 to export a package to module m2. The export does
     * not result in a strong reference to m2 (m2 can be GC'ed).
     */
    void addExports(Module m1, String pkg, Module m2);

    /**
     * Updates module m1 to open a package to module m2. Opening the
     * package does not result in a strong reference to m2 (m2 can be GC'ed).
     */
    void addOpens(Module m1, String pkg, Module m2);

    /**
     * Updates a module m to export a package to all modules.
     */
    void addExportsToAll(Module m, String pkg);

    /**
     * Updates a module m to open a package to all modules.
     */
    void addOpensToAll(Module m, String pkg);

    /**
     * Updates a module m to export a package to all unnamed modules.
     */
    void addExportsToAllUnnamed(Module m, String pkg);

    /**
     * Updates a module m to open a package to all unnamed modules.
     */
    void addOpensToAllUnnamed(Module m, String pkg);

    /**
     * Updates a module m to use a service.
     */
    void addUses(Module m, Class<?> service);

    /**
     * Add a package to the given module.
     */
    void addPackage(Module m, String pkg);

    /**
     * Returns the ServicesCatalog for the given Layer.
     */
    ServicesCatalog getServicesCatalog(Layer layer);

    /**
     * Returns an ordered stream of layers. The first element is is the
     * given layer, the remaining elements are its parents, in DFS order.
     */
    Stream<Layer> layers(Layer layer);

    /**
     * Returns a stream of the layers that have modules defined to the
     * given class loader.
     */
    Stream<Layer> layers(ClassLoader loader);

    /**
     * Tests if a module exports a package at least {@code other} via its
     * module declaration.
     *
     * @apiNote This is a temporary method for debugging features.
     */
    boolean isStaticallyExported(Module module, String pn, Module other);
}