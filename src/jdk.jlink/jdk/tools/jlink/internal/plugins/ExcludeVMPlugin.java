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
package jdk.tools.jlink.internal.plugins;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UncheckedIOException;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import jdk.tools.jlink.plugin.Plugin;
import jdk.tools.jlink.plugin.ResourcePool;
import jdk.tools.jlink.plugin.ResourcePoolBuilder;
import jdk.tools.jlink.plugin.ResourcePoolModule;
import jdk.tools.jlink.plugin.ResourcePoolEntry;
import jdk.tools.jlink.plugin.PluginException;

/**
 *
 * Exclude VM plugin
 */
public final class ExcludeVMPlugin implements Plugin {

    private static final class JvmComparator implements Comparator<Jvm> {

        @Override
        public int compare(Jvm o1, Jvm o2) {
            return o1.getEfficience() - o2.getEfficience();
        }
    }

    private enum Jvm {
        // The efficience order server - client - minimal.
        SERVER("server", 3), CLIENT("client", 2), MINIMAL("minimal", 1);
        private final String name;
        private final int efficience;

        Jvm(String name, int efficience) {
            this.name = name;
            this.efficience = efficience;
        }

        private String getName() {
            return name;
        }

        private int getEfficience() {
            return efficience;
        }
    }

    private static final String JVM_CFG = "jvm.cfg";

    public static final String NAME = "vm";
    private static final String ALL = "all";
    private static final String CLIENT = "client";
    private static final String SERVER = "server";
    private static final String MINIMAL = "minimal";

    private Predicate<String> predicate;
    private Jvm target;
    private boolean keepAll;

    @Override
    public String getName() {
        return NAME;
    }

    /**
     * VM paths:
     * /java.base/lib/{architecture}/{server|client|minimal}/{shared lib}
     * e.g.: /java.base/lib/server/libjvm.so
     * /java.base/lib/server/libjvm.dylib
     */
    private List<ResourcePoolEntry> getVMs(ResourcePoolModule javaBase, String[] jvmlibs) {
        List<ResourcePoolEntry> ret = javaBase.entries().filter((t) -> {
            String path = t.path();
            for (String jvmlib : jvmlibs) {
                if (t.path().endsWith("/" + jvmlib)) {
                    return true;
                }
            }
            return false;
        }).collect(Collectors.toList());
        return ret;
    }

    @Override
    public ResourcePool transform(ResourcePool in, ResourcePoolBuilder out) {
        ResourcePoolModule javaBase = in.moduleView().findModule("java.base").get();
        String[] jvmlibs = jvmlibs(javaBase.descriptor().osName().get());
        TreeSet<Jvm> existing = new TreeSet<>(new JvmComparator());
        TreeSet<Jvm> removed = new TreeSet<>(new JvmComparator());
        if (!keepAll) {
            // First retrieve all available VM names and removed VM
            List<ResourcePoolEntry> jvms = getVMs(javaBase, jvmlibs);
            for (Jvm jvm : Jvm.values()) {
                for (ResourcePoolEntry md : jvms) {
                    String mdPath = md.path();
                    for (String jvmlib : jvmlibs) {
                        if (mdPath.endsWith("/" + jvm.getName() + "/" + jvmlib)) {
                            existing.add(jvm);
                            if (isRemoved(md)) {
                                removed.add(jvm);
                            }
                        }
                    }
                }
            }
        }
        // Check that target exists
        if (!keepAll) {
            if (!existing.contains(target)) {
                throw new PluginException("Selected VM " + target.getName() + " doesn't exist.");
            }
        }

        // Rewrite the jvm.cfg file.
        in.transformAndCopy((file) -> {
            if (!keepAll) {
                if (file.type().equals(ResourcePoolEntry.Type.NATIVE_LIB)) {
                    if (file.path().endsWith(JVM_CFG)) {
                        try {
                            file = handleJvmCfgFile(file, existing, removed);
                        } catch (IOException ex) {
                            throw new UncheckedIOException(ex);
                        }
                    }
                }
                file = isRemoved(file) ? null : file;
            }
            return file;
        }, out);

        return out.build();
    }

    private boolean isRemoved(ResourcePoolEntry file) {
        return !predicate.test(file.path());
    }

    @Override
    public Category getType() {
        return Category.FILTER;
    }

    @Override
    public String getDescription() {
        return PluginsResourceBundle.getDescription(NAME);
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
        String value = config.get(NAME);
        String exclude = "";
        switch (value) {
            case ALL: {
                // no filter.
                keepAll = true;
                break;
            }
            case CLIENT: {
                target = Jvm.CLIENT;
                exclude = "/java.base/lib**server/**,/java.base/lib**minimal/**";
                break;
            }
            case SERVER: {
                target = Jvm.SERVER;
                exclude = "/java.base/lib**client/**,/java.base/lib**minimal/**";
                break;
            }
            case MINIMAL: {
                target = Jvm.MINIMAL;
                exclude = "/java.base/lib**server/**,/java.base/lib**client/**";
                break;
            }
            default: {
                throw new IllegalArgumentException("Unknown exclude VM option: " + value);
            }
        }
        predicate = ResourceFilter.excludeFilter(exclude);
    }

    private ResourcePoolEntry handleJvmCfgFile(ResourcePoolEntry orig,
            TreeSet<Jvm> existing,
            TreeSet<Jvm> removed) throws IOException {
        if (keepAll) {
            return orig;
        }
        StringBuilder builder = new StringBuilder();
        // Keep comments
        try (BufferedReader reader
                = new BufferedReader(new InputStreamReader(orig.content(),
                        StandardCharsets.UTF_8))) {
            reader.lines().forEach((s) -> {
                if (s.startsWith("#")) {
                    builder.append(s).append("\n");
                }
            });
        }
        TreeSet<Jvm> remaining = new TreeSet<>(new JvmComparator());
        // Add entry in jvm.cfg file from the more efficient to less efficient.
        for (Jvm platform : existing) {
            if (!removed.contains(platform)) {
                remaining.add(platform);
                builder.append("-").append(platform.getName()).append(" KNOWN\n");
            }
        }

        // removed JVM are aliased to the most efficient remaining JVM (last one).
        // The order in the file is from most to less efficient platform
        for (Jvm platform : removed.descendingSet()) {
            builder.append("-").append(platform.getName()).
                    append(" ALIASED_TO -").
                    append(remaining.last().getName()).append("\n");
        }

        byte[] content = builder.toString().getBytes(StandardCharsets.UTF_8);

        return orig.copyWithContent(content);
    }

    private static String[] jvmlibs(String osName) {
        if (isWindows(osName)) {
            return new String[] { "jvm.dll" };
        } else if (isMac(osName)) {
            return new String[] { "libjvm.dylib", "libjvm.a" };
        } else {
            return new String[] { "libjvm.so", "libjvm.a" };
        }
    }

    private static boolean isWindows(String osName) {
        return osName.startsWith("Windows");
    }

    private static boolean isMac(String osName) {
        return osName.startsWith("Mac OS") || osName.startsWith("Darwin");
    }
}
