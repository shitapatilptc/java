/*
 * Copyright (c) 2003, 2005, Oracle and/or its affiliates. All rights reserved.
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

package javax.xml.namespace;

import java.util.Iterator;

/**
 * Interface for read only XML Namespace context processing.
 *
 * <p>An XML Namespace has the properties:
 * <ul>
 *   <li>Namespace URI:
 *       Namespace name expressed as a URI to which the prefix is bound</li>
 *   <li>prefix: syntactically, this is the part of the attribute name
 *       following the {@code XMLConstants.XMLNS_ATTRIBUTE}
 *       ("xmlns") in the Namespace declaration</li>
 * </ul>
 * <p>example:
 * {@code <element xmlns:prefix="http://Namespace-name-URI">}
 *
 * <p>All {@code get*(*)} methods operate in the current scope
 * for Namespace URI and prefix resolution.
 *
 * <p>Note that a Namespace URI can be bound to
 * <strong>multiple</strong> prefixes in the current scope.  This can
 * occur when multiple {@code XMLConstants.XMLNS_ATTRIBUTE}
 * ("xmlns") Namespace declarations occur in the same Start-Tag and
 * refer to the same Namespace URI. e.g.<br>
 * <pre> {@code
 * <element xmlns:prefix1="http://Namespace-name-URI"
 *          xmlns:prefix2="http://Namespace-name-URI"> }
 * </pre>
 * This can also occur when the same Namespace URI is used in multiple
 * {@code XMLConstants.XMLNS_ATTRIBUTE} ("xmlns") Namespace
 * declarations in the logical parent element hierarchy.  e.g.<br>
 * <pre> {@code
 * <parent xmlns:prefix1="http://Namespace-name-URI">
 *   <child xmlns:prefix2="http://Namespace-name-URI">
 *     ...
 *   </child>
 * </parent> }
 * </pre>
 *
 * <p>A prefix can only be bound to a <strong>single</strong>
 * Namespace URI in the current scope.
 *
 * @author <a href="mailto:Jeff.Suttor@Sun.com">Jeff Suttor</a>
 * @see javax.xml.XMLConstants
 *   javax.xml.XMLConstants for declarations of common XML values
 * @see <a href="http://www.w3.org/TR/xmlschema-2/#QName">
 *   XML Schema Part2: Datatypes</a>
 * @see <a href="http://www.w3.org/TR/REC-xml-names/#ns-qualnames">
 *   Namespaces in XML</a>
 * @see <a href="http://www.w3.org/XML/xml-names-19990114-errata">
 *   Namespaces in XML Errata</a>
 * @since 1.5
 */

public interface NamespaceContext {

    /**
     * Get Namespace URI bound to a prefix in the current scope.
     *
     * <p>When requesting a Namespace URI by prefix, the following
     * table describes the returned Namespace URI value for all
     * possible prefix values:
     *
     * <table border="2" rules="all" cellpadding="4">
     *   <thead>
     *     <tr>
     *       <td align="center" colspan="2">
     *         {@code getNamespaceURI(prefix)}
     *         return value for specified prefixes
     *       </td>
     *     </tr>
     *     <tr>
     *       <td>prefix parameter</td>
     *       <td>Namespace URI return value</td>
     *     </tr>
     *   </thead>
     *   <tbody>
     *     <tr>
     *       <td>{@code DEFAULT_NS_PREFIX} ("")</td>
     *       <td>default Namespace URI in the current scope or
     *         <code> {@link
     *         javax.xml.XMLConstants#NULL_NS_URI XMLConstants.NULL_NS_URI("")}
     *         </code>
     *         when there is no default Namespace URI in the current scope</td>
     *     </tr>
     *     <tr>
     *       <td>bound prefix</td>
     *       <td>Namespace URI bound to prefix in current scope</td>
     *     </tr>
     *     <tr>
     *       <td>unbound prefix</td>
     *       <td>
     *         <code> {@link
     *         javax.xml.XMLConstants#NULL_NS_URI XMLConstants.NULL_NS_URI("")}
     *         </code>
     *       </td>
     *     </tr>
     *     <tr>
     *       <td>{@code XMLConstants.XML_NS_PREFIX} ("xml")</td>
     *       <td>{@code XMLConstants.XML_NS_URI}
     *           ("http://www.w3.org/XML/1998/namespace")</td>
     *     </tr>
     *     <tr>
     *       <td>{@code XMLConstants.XMLNS_ATTRIBUTE} ("xmlns")</td>
     *       <td>{@code XMLConstants.XMLNS_ATTRIBUTE_NS_URI}
     *         ("http://www.w3.org/2000/xmlns/")</td>
     *     </tr>
     *     <tr>
     *       <td>{@code null}</td>
     *       <td>{@code IllegalArgumentException} is thrown</td>
     *     </tr>
     *    </tbody>
     * </table>
     *
     * @param prefix prefix to look up
     *
     * @return Namespace URI bound to prefix in the current scope
     *
     * @throws IllegalArgumentException When {@code prefix} is
     *   {@code null}
     */
    String getNamespaceURI(String prefix);

    /**
     * Get prefix bound to Namespace URI in the current scope.
     *
     * <p>To get all prefixes bound to a Namespace URI in the current
     * scope, use {@link #getPrefixes(String namespaceURI)}.
     *
     * <p>When requesting a prefix by Namespace URI, the following
     * table describes the returned prefix value for all Namespace URI
     * values:
     *
     * <table border="2" rules="all" cellpadding="4">
     *   <thead>
     *     <tr>
     *       <th align="center" colspan="2">
     *         {@code getPrefix(namespaceURI)} return value for
     *         specified Namespace URIs
     *       </th>
     *     </tr>
     *     <tr>
     *       <th>Namespace URI parameter</th>
     *       <th>prefix value returned</th>
     *     </tr>
     *   </thead>
     *   <tbody>
     *     <tr>
     *       <td>{@code <default Namespace URI>}</td>
     *       <td>{@code XMLConstants.DEFAULT_NS_PREFIX} ("")
     *       </td>
     *     </tr>
     *     <tr>
     *       <td>bound Namespace URI</td>
     *       <td>prefix bound to Namespace URI in the current scope,
     *           if multiple prefixes are bound to the Namespace URI in
     *           the current scope, a single arbitrary prefix, whose
     *           choice is implementation dependent, is returned</td>
     *     </tr>
     *     <tr>
     *       <td>unbound Namespace URI</td>
     *       <td>{@code null}</td>
     *     </tr>
     *     <tr>
     *       <td>{@code XMLConstants.XML_NS_URI}
     *           ("http://www.w3.org/XML/1998/namespace")</td>
     *       <td>{@code XMLConstants.XML_NS_PREFIX} ("xml")</td>
     *     </tr>
     *     <tr>
     *       <td>{@code XMLConstants.XMLNS_ATTRIBUTE_NS_URI}
     *           ("http://www.w3.org/2000/xmlns/")</td>
     *       <td>{@code XMLConstants.XMLNS_ATTRIBUTE} ("xmlns")</td>
     *     </tr>
     *     <tr>
     *       <td>{@code null}</td>
     *       <td>{@code IllegalArgumentException} is thrown</td>
     *     </tr>
     *   </tbody>
     * </table>
     *
     * @param namespaceURI URI of Namespace to lookup
     *
     * @return prefix bound to Namespace URI in current context
     *
     * @throws IllegalArgumentException When {@code namespaceURI} is
     *   {@code null}
     */
    String getPrefix(String namespaceURI);

    /**
     * Get all prefixes bound to a Namespace URI in the current
     * scope.
     *
     * <p>An Iterator over String elements is returned in an arbitrary,
     * <strong>implementation dependent</strong>, order.
     *
     * <p><strong>The {@code Iterator} is
     * <em>not</em> modifiable.  e.g. the
     * {@code remove()} method will throw
     * {@code UnsupportedOperationException}.</strong>
     *
     * <p>When requesting prefixes by Namespace URI, the following
     * table describes the returned prefixes value for all Namespace
     * URI values:
     *
     * <table border="2" rules="all" cellpadding="4">
     *   <thead>
     *     <tr>
     *       <th align="center" colspan="2">{@code
     *         getPrefixes(namespaceURI)} return value for
     *         specified Namespace URIs</th>
     *     </tr>
     *     <tr>
     *       <th>Namespace URI parameter</th>
     *       <th>prefixes value returned</th>
     *     </tr>
     *   </thead>
     *   <tbody>
     *     <tr>
     *       <td>bound Namespace URI,
     *         including the {@code <default Namespace URI>}</td>
     *       <td>
     *         {@code Iterator} over prefixes bound to Namespace URI in
     *         the current scope in an arbitrary,
     *         <strong>implementation dependent</strong>,
     *         order
     *       </td>
     *     </tr>
     *     <tr>
     *       <td>unbound Namespace URI</td>
     *       <td>empty {@code Iterator}</td>
     *     </tr>
     *     <tr>
     *       <td>{@code XMLConstants.XML_NS_URI}
     *           ("http://www.w3.org/XML/1998/namespace")</td>
     *       <td>{@code Iterator} with one element set to
     *         {@code XMLConstants.XML_NS_PREFIX} ("xml")</td>
     *     </tr>
     *     <tr>
     *       <td>{@code XMLConstants.XMLNS_ATTRIBUTE_NS_URI}
     *           ("http://www.w3.org/2000/xmlns/")</td>
     *       <td>{@code Iterator} with one element set to
     *         {@code XMLConstants.XMLNS_ATTRIBUTE} ("xmlns")</td>
     *     </tr>
     *     <tr>
     *       <td>{@code null}</td>
     *       <td>{@code IllegalArgumentException} is thrown</td>
     *     </tr>
     *   </tbody>
     * </table>
     *
     * @param namespaceURI URI of Namespace to lookup
     *
     * @return {@code Iterator} for all prefixes bound to the
     *   Namespace URI in the current scope
     *
     * @throws IllegalArgumentException When {@code namespaceURI} is
     *   {@code null}
     */
    Iterator getPrefixes(String namespaceURI);
}
