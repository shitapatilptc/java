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

package jdk.javadoc.internal.doclets.toolkit.builders;

import java.util.*;

import javax.lang.model.element.Element;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;

import jdk.javadoc.internal.doclets.toolkit.Configuration;
import jdk.javadoc.internal.doclets.toolkit.ConstructorWriter;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.DocletException;
import jdk.javadoc.internal.doclets.toolkit.util.VisibleMemberMap;


/**
 * Builds documentation for a constructor.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 * @author Jamie Ho
 * @author Bhavesh Patel (Modified)
 */
public class ConstructorBuilder extends AbstractMemberBuilder {

    /**
     * The name of this builder.
     */
    public static final String NAME = "ConstructorDetails";

    /**
     * The current constructor that is being documented at this point in time.
     */
    private ExecutableElement currentConstructor;

    /**
     * The class whose constructors are being documented.
     */
    private final TypeElement typeElement;

    /**
     * The visible constructors for the given class.
     */
    private final VisibleMemberMap visibleMemberMap;

    /**
     * The writer to output the constructor documentation.
     */
    private final ConstructorWriter writer;

    /**
     * The constructors being documented.
     */
    private final List<Element> constructors;

    /**
     * Construct a new ConstructorBuilder.
     *
     * @param context  the build context.
     * @param typeElement the class whoses members are being documented.
     * @param writer the doclet specific writer.
     */
    private ConstructorBuilder(Context context,
            TypeElement typeElement,
            ConstructorWriter writer) {
        super(context);
        this.typeElement = typeElement;
        this.writer = writer;
        visibleMemberMap =
                new VisibleMemberMap(
                typeElement,
                VisibleMemberMap.Kind.CONSTRUCTORS,
                configuration);
        constructors = visibleMemberMap.getMembers(typeElement);
        for (Element ctor : constructors) {
            if (utils.isProtected(ctor) || utils.isPrivate(ctor)) {
                writer.setFoundNonPubConstructor(true);
            }
        }
    }

    /**
     * Construct a new ConstructorBuilder.
     *
     * @param context  the build context.
     * @param typeElement the class whoses members are being documented.
     * @param writer the doclet specific writer.
     * @return the new ConstructorBuilder
     */
    public static ConstructorBuilder getInstance(Context context,
            TypeElement typeElement, ConstructorWriter writer) {
        return new ConstructorBuilder(context, typeElement, writer);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasMembersToDocument() {
        return !constructors.isEmpty();
    }

    /**
     * Return the constructor writer for this builder.
     *
     * @return the constructor writer for this builder.
     */
    public ConstructorWriter getWriter() {
        return writer;
    }

    /**
     * Build the constructor documentation.
     *
     * @param node the XML element that specifies which components to document
     * @param memberDetailsTree the content tree to which the documentation will be added
     * @throws DocletException is there is a problem while building the documentation
     */
    public void buildConstructorDoc(XMLNode node, Content memberDetailsTree) throws DocletException {
        if (writer == null) {
            return;
        }
        if (hasMembersToDocument()) {
            Content constructorDetailsTree = writer.getConstructorDetailsTreeHeader(typeElement,
                    memberDetailsTree);

            Element lastElement = constructors.get(constructors.size() - 1);
            for (Element contructor : constructors) {
                currentConstructor = (ExecutableElement)contructor;
                Content constructorDocTree = writer.getConstructorDocTreeHeader(currentConstructor, constructorDetailsTree);
                buildChildren(node, constructorDocTree);
                constructorDetailsTree.addContent(writer.getConstructorDoc(constructorDocTree,
                        currentConstructor == lastElement));
            }
            memberDetailsTree.addContent(
                    writer.getConstructorDetails(constructorDetailsTree));
        }
    }

    /**
     * Build the signature.
     *
     * @param node the XML element that specifies which components to document
     * @param constructorDocTree the content tree to which the documentation will be added
     */
    public void buildSignature(XMLNode node, Content constructorDocTree) {
        constructorDocTree.addContent(writer.getSignature(currentConstructor));
    }

    /**
     * Build the deprecation information.
     *
     * @param node the XML element that specifies which components to document
     * @param constructorDocTree the content tree to which the documentation will be added
     */
    public void buildDeprecationInfo(XMLNode node, Content constructorDocTree) {
        writer.addDeprecated(currentConstructor, constructorDocTree);
    }

    /**
     * Build the comments for the constructor.  Do nothing if
     * {@link Configuration#nocomment} is set to true.
     *
     * @param node the XML element that specifies which components to document
     * @param constructorDocTree the content tree to which the documentation will be added
     */
    public void buildConstructorComments(XMLNode node, Content constructorDocTree) {
        if (!configuration.nocomment) {
            writer.addComments(currentConstructor, constructorDocTree);
        }
    }

    /**
     * Build the tag information.
     *
     * @param node the XML element that specifies which components to document
     * @param constructorDocTree the content tree to which the documentation will be added
     */
    public void buildTagInfo(XMLNode node, Content constructorDocTree) {
        writer.addTags(currentConstructor, constructorDocTree);
    }
}
