/*
 * Copyright (c) 2003, 2016, Oracle and/or its affiliates. All rights reserved.
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

package jdk.javadoc.doclets;

import java.util.Locale;
import java.util.Set;

import javax.lang.model.SourceVersion;

import jdk.javadoc.doclet.Doclet;
import jdk.javadoc.doclet.DocletEnvironment;
import jdk.javadoc.doclet.Reporter;
import jdk.javadoc.internal.doclets.formats.html.HtmlDoclet;

/**
 * This doclet generates HTML-formatted documentation for the specified modules, packages and types.
 */
public class StandardDoclet implements Doclet {

    private final HtmlDoclet htmlDoclet;

    public StandardDoclet() {
        htmlDoclet = new HtmlDoclet();
    }

    @Override
    public void init(Locale locale, Reporter reporter) {
        htmlDoclet.init(locale, reporter);
    }

    @Override
    public String getName() {
        return "Standard";
    }

    @Override
    public Set<Doclet.Option> getSupportedOptions() {
        return htmlDoclet.getSupportedOptions();
    }

    @Override
    public SourceVersion getSupportedSourceVersion() {
        return htmlDoclet.getSupportedSourceVersion();
    }

    @Override
    public boolean run(DocletEnvironment docEnv) {
        return htmlDoclet.run(docEnv);
    }
}
