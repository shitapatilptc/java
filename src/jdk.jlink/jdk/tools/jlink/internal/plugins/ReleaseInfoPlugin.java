/*
 * Copyright (c) 2016, Oracle and/or its affiliates. All rights reserved.
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
package jdk.tools.jlink.internal.plugins;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.UncheckedIOException;
import java.lang.module.ModuleDescriptor;
import java.util.EnumSet;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.Properties;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;
import jdk.tools.jlink.internal.ModuleSorter;
import jdk.tools.jlink.internal.Utils;
import jdk.tools.jlink.plugin.ResourcePool;
import jdk.tools.jlink.plugin.ResourcePoolBuilder;
import jdk.tools.jlink.plugin.ResourcePoolEntry;
import jdk.tools.jlink.plugin.ResourcePoolModule;
import jdk.tools.jlink.plugin.Plugin.Category;
import jdk.tools.jlink.plugin.Plugin.State;
import jdk.tools.jlink.plugin.Plugin;

/**
 * This plugin adds/deletes information for 'release' file.
 */
public final class ReleaseInfoPlugin implements Plugin {
    // option name
    public static final String NAME = "release-info";
    public static final String KEYS = "keys";
    private final Map<String, String> release = new HashMap<>();

    @Override
    public Category getType() {
        return Category.METAINFO_ADDER;
    }

    @Override
    public String getName() {
        return NAME;
    }

    @Override
    public String getDescription() {
        return PluginsResourceBundle.getDescription(NAME);
    }

    @Override
    public Set<State> getState() {
        return EnumSet.of(State.AUTO_ENABLED, State.FUNCTIONAL);
    }

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public String getArgumentsDescription() {
        return PluginsResourceBundle.getArgument(NAME);
    }

    @Override
    public void configure(Map<String, String> config) {
        String operation = config.get(NAME);
        if (operation == null) {
            return;
        }

        switch (operation) {
            case "add": {
                // leave it to open-ended! source, java_version, java_full_version
                // can be passed via this option like:
                //
                //     --release-info add:build_type=fastdebug,source=openjdk,java_version=9
                // and put whatever value that was passed in command line.

                config.keySet().stream().
                    filter(s -> !NAME.equals(s)).
                    forEach(s -> release.put(s, config.get(s)));
            }
            break;

            case "del": {
                // --release-info del:keys=openjdk,java_version
                Utils.parseList(config.get(KEYS)).stream().forEach((k) -> {
                    release.remove(k);
                });
            }
            break;

            default: {
                // --release-info <file>
                Properties props = new Properties();
                try (FileInputStream fis = new FileInputStream(operation)) {
                    props.load(fis);
                } catch (IOException exp) {
                    throw new UncheckedIOException(exp);
                }
                props.forEach((k, v) -> release.put(k.toString(), v.toString()));
            }
            break;
        }
    }

    @Override
    public ResourcePool transform(ResourcePool in, ResourcePoolBuilder out) {
        in.transformAndCopy(Function.identity(), out);

        Optional<ResourcePoolModule> javaBase = in.moduleView().findModule("java.base");
        javaBase.ifPresent(mod -> {
            // fill release information available from transformed "java.base" module!
            ModuleDescriptor desc = mod.descriptor();
            desc.osName().ifPresent(s -> {
                release.put("OS_NAME", quote(s));
            });
            desc.osVersion().ifPresent(s -> release.put("OS_VERSION", quote(s)));
            desc.osArch().ifPresent(s -> release.put("OS_ARCH", quote(s)));
            desc.version().ifPresent(s -> release.put("JAVA_VERSION",
                    quote(parseVersion(s.toString()))));
            desc.version().ifPresent(s -> release.put("JAVA_FULL_VERSION",
                    quote(s.toString())));
        });

        // put topological sorted module names separated by space
        release.put("MODULES",  new ModuleSorter(in.moduleView())
                .sorted().map(ResourcePoolModule::name)
                .collect(Collectors.joining(" ", "\"", "\"")));

        // create a TOP level ResourcePoolEntry for "release" file.
        out.add(ResourcePoolEntry.create("/java.base/release",
            ResourcePoolEntry.Type.TOP, releaseFileContent()));
        return out.build();
    }

    // Parse version string and return a string that includes only version part
    // leaving "pre", "build" information. See also: java.lang.Runtime.Version.
    private static String parseVersion(String str) {
        return Runtime.Version.parse(str).
            version().
            stream().
            map(Object::toString).
            collect(Collectors.joining("."));
    }

    private static String quote(String str) {
        return "\"" + str + "\"";
    }

    private byte[] releaseFileContent() {
        Properties props = new Properties();
        props.putAll(release);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        try {
            props.store(baos, "");
            return baos.toByteArray();
        } catch (IOException ex) {
            throw new UncheckedIOException(ex);
        }
    }
}
