/*
 * Copyright (c) 2013, 2016, Oracle and/or its affiliates. All rights reserved.
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
import javax.lang.model.element.TypeElement;

import jdk.javadoc.internal.doclets.toolkit.AnnotationTypeFieldWriter;
import jdk.javadoc.internal.doclets.toolkit.Configuration;
import jdk.javadoc.internal.doclets.toolkit.Content;
import jdk.javadoc.internal.doclets.toolkit.DocletException;
import jdk.javadoc.internal.doclets.toolkit.util.VisibleMemberMap;


/**
 * Builds documentation for annotation type fields.
 *
 *  <p><b>This is NOT part of any supported API.
 *  If you write code that depends on this, you do so at your own risk.
 *  This code and its internal interfaces are subject to change or
 *  deletion without notice.</b>
 *
 * @author Bhavesh Patel
 */
public class AnnotationTypeFieldBuilder extends AbstractMemberBuilder {

    /**
     * The annotation type whose members are being documented.
     */
    protected TypeElement typeElement;

    /**
     * The visible members for the given class.
     */
    protected VisibleMemberMap visibleMemberMap;

    /**
     * The writer to output the member documentation.
     */
    protected AnnotationTypeFieldWriter writer;

    /**
     * The list of members being documented.
     */
    protected List<Element> members;

    /**
     * The index of the current member that is being documented at this point
     * in time.
     */
    protected Element currentMember;

    /**
     * Construct a new AnnotationTypeFieldsBuilder.
     *
     * @param context  the build context.
     * @param typeElement the class whose members are being documented.
     * @param writer the doclet specific writer.
     * @param memberType the type of member that is being documented.
     */
    protected AnnotationTypeFieldBuilder(Context context,
            TypeElement typeElement,
            AnnotationTypeFieldWriter writer,
            VisibleMemberMap.Kind memberType) {
        super(context);
        this.typeElement = typeElement;
        this.writer = writer;
        this.visibleMemberMap = new VisibleMemberMap(typeElement, memberType, configuration);
        this.members = this.visibleMemberMap.getMembers(typeElement);
    }


    /**
     * Construct a new AnnotationTypeFieldBuilder.
     *
     * @param context  the build context.
     * @param typeElement the class whose members are being documented.
     * @param writer the doclet specific writer.
     * @return the new AnnotationTypeFieldBuilder
     */
    public static AnnotationTypeFieldBuilder getInstance(
            Context context, TypeElement typeElement,
            AnnotationTypeFieldWriter writer) {
        return new AnnotationTypeFieldBuilder(context, typeElement,
                    writer, VisibleMemberMap.Kind.ANNOTATION_TYPE_FIELDS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getName() {
        return "AnnotationTypeFieldDetails";
    }


    /**
     * Returns whether or not there are members to document.
     * @return whether or not there are members to document
     */
    @Override
    public boolean hasMembersToDocument() {
        return !members.isEmpty();
    }

    /**
     * Build the annotation type field documentation.
     *
     * @param node the XML element that specifies which components to document
     * @param memberDetailsTree the content tree to which the documentation will be added
     * @throws DocletException if there is a problem while building the documentation
     */
    public void buildAnnotationTypeField(XMLNode node, Content memberDetailsTree)
            throws DocletException {
        buildAnnotationTypeMember(node, memberDetailsTree);
    }

    /**
     * Build the member documentation.
     *
     * @param node the XML element that specifies which components to document
     * @param memberDetailsTree the content tree to which the documentation will be added
     * @throws DocletException if there is a problem while building the documentation
     */
    public void buildAnnotationTypeMember(XMLNode node, Content memberDetailsTree)
            throws DocletException {
        if (writer == null) {
            return;
        }
        if (hasMembersToDocument()) {
            writer.addAnnotationFieldDetailsMarker(memberDetailsTree);

            Element lastElement = members.get(members.size() - 1);
            for (Element member : members) {
                currentMember = member;
                Content detailsTree = writer.getMemberTreeHeader();
                writer.addAnnotationDetailsTreeHeader(typeElement, detailsTree);
                Content annotationDocTree = writer.getAnnotationDocTreeHeader(currentMember,
                        detailsTree);
                buildChildren(node, annotationDocTree);
                detailsTree.addContent(writer.getAnnotationDoc(
                        annotationDocTree, currentMember == lastElement));
                memberDetailsTree.addContent(writer.getAnnotationDetails(detailsTree));
            }
        }
    }

    /**
     * Build the signature.
     *
     * @param node the XML element that specifies which components to document
     * @param annotationDocTree the content tree to which the documentation will be added
     */
    public void buildSignature(XMLNode node, Content annotationDocTree) {
        annotationDocTree.addContent(
                writer.getSignature(currentMember));
    }

    /**
     * Build the deprecation information.
     *
     * @param node the XML element that specifies which components to document
     * @param annotationDocTree the content tree to which the documentation will be added
     */
    public void buildDeprecationInfo(XMLNode node, Content annotationDocTree) {
        writer.addDeprecated(currentMember, annotationDocTree);
    }

    /**
     * Build the comments for the member.  Do nothing if
     * {@link Configuration#nocomment} is set to true.
     *
     * @param node the XML element that specifies which components to document
     * @param annotationDocTree the content tree to which the documentation will be added
     */
    public void buildMemberComments(XMLNode node, Content annotationDocTree) {
        if(! configuration.nocomment){
            writer.addComments(currentMember, annotationDocTree);
        }
    }

    /**
     * Build the tag information.
     *
     * @param node the XML element that specifies which components to document
     * @param annotationDocTree the content tree to which the documentation will be added
     */
    public void buildTagInfo(XMLNode node, Content annotationDocTree) {
        writer.addTags(currentMember, annotationDocTree);
    }

    /**
     * Return the annotation type field writer for this builder.
     *
     * @return the annotation type field writer for this builder.
     */
    public AnnotationTypeFieldWriter getWriter() {
        return writer;
    }
}
