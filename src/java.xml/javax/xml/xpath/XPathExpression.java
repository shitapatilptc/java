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

package javax.xml.xpath;

import javax.xml.namespace.QName;
import org.xml.sax.InputSource;

/**
 * {@code XPathExpression} provides access to compiled XPath expressions.
 *
 * <a name="XPathExpression-evaluation"></a>
 * <table border="1" cellpadding="2">
 *    <thead>
 *      <tr>
 *        <th colspan="2">Evaluation of XPath Expressions.</th>
 *      </tr>
 *    </thead>
 *    <tr>
 *      <td>context</td>
 *      <td>
 *        The type of the context is implementation-dependent. If the value is
 *        null, the operation must have no dependency on the context, otherwise
 *        an XPathExpressionException will be thrown.
 *
 *        For the purposes of evaluating XPath expressions, a DocumentFragment
 *        is treated like a Document node.
 *      </td>
 *    </tr>
 *    <tr>
 *      <td>variables</td>
 *      <td>
 *        If the expression contains a variable reference, its value will be found through the {@link XPathVariableResolver}.
 *        An {@link XPathExpressionException} is raised if the variable resolver is undefined or
 *        the resolver returns {@code null} for the variable.
 *        The value of a variable must be immutable through the course of any single evaluation.
 *      </td>
 *    </tr>
 *    <tr>
 *      <td>functions</td>
 *      <td>
 *        If the expression contains a function reference, the function will be found through the {@link XPathFunctionResolver}.
 *        An {@link XPathExpressionException} is raised if the function resolver is undefined or
 *        the function resolver returns {@code null} for the function.
 *      </td>
 *    </tr>
 *    <tr>
 *      <td>QNames</td>
 *      <td>
 *        QNames in the expression are resolved against the XPath namespace context.
 *      </td>
 *    </tr>
 *    <tr>
 *      <td>result</td>
 *      <td>
 *        This result of evaluating an expression is converted to an instance of the desired return type.
 *        Valid return types are defined in {@link XPathConstants}.
 *        Conversion to the return type follows XPath conversion rules.
 *      </td>
 *    </tr>
 *   </tbody>
 * </table>
 *
 * <p>An XPath expression is not thread-safe and not reentrant.
 * In other words, it is the application's responsibility to make
 * sure that one {@link XPathExpression} object is not used from
 * more than one thread at any given time, and while the {@code evaluate}
 * method is invoked, applications may not recursively call
 * the {@code evaluate} method.
 *
 * @author  <a href="mailto:Norman.Walsh@Sun.com">Norman Walsh</a>
 * @author  <a href="mailto:Jeff.Suttor@Sun.com">Jeff Suttor</a>
 * @see <a href="http://www.w3.org/TR/xpath#section-Expressions">XML Path Language (XPath) Version 1.0, Expressions</a>
 * @since 1.5
 */
public interface XPathExpression {


    /**
     * Evaluate the compiled XPath expression in the specified context and return the result as the specified type.
     *
     * <p>See <a href="#XPathExpression-evaluation">Evaluation of XPath Expressions</a> for context item evaluation,
     * variable, function and QName resolution and return type conversion.
     *
     * <p>
     * The parameter {@code item} represents the context the XPath expression
     * will be operated on. The type of the context is implementation-dependent.
     * If the value is {@code null}, the operation must have no dependency on
     * the context, otherwise an XPathExpressionException will be thrown.
     *
     * @implNote
     * The type of the context is usually {@link org.w3c.dom.Node}.
     *
     * @param item The context the XPath expression will be evaluated in.
     * @param returnType The result type expected to be returned by the XPath expression.
     *
     * @return The {@code Object} that is the result of evaluating the expression and converting the result to
     *   {@code returnType}.
     *
     * @throws XPathExpressionException If the expression cannot be evaluated.
     * @throws IllegalArgumentException If {@code returnType} is not one of the types defined in {@link XPathConstants}.
     * @throws NullPointerException If {@code returnType} is {@code null}.
     */
    public Object evaluate(Object item, QName returnType)
        throws XPathExpressionException;

    /**
     * Evaluate the compiled XPath expression in the specified context and return the result as a {@code String}.
     *
     * <p>This method calls {@link #evaluate(Object item, QName returnType)} with a {@code returnType} of
     * {@link XPathConstants#STRING}.
     *
     * <p>See <a href="#XPathExpression-evaluation">Evaluation of XPath Expressions</a> for context item evaluation,
     * variable, function and QName resolution and return type conversion.
     *
     * <p>
     * The parameter {@code item} represents the context the XPath expression
     * will be operated on. The type of the context is implementation-dependent.
     * If the value is {@code null}, the operation must have no dependency on
     * the context, otherwise an XPathExpressionException will be thrown.
     *
     * @implNote
     * The type of the context is usually {@link org.w3c.dom.Node}.
     *
     * @param item The context the XPath expression will be evaluated in.
     *
     * @return The result of evaluating an XPath expression as a {@code String}.
     *
     * @throws XPathExpressionException If the expression cannot be evaluated.
     */
    public String evaluate(Object item)
        throws XPathExpressionException;

    /**
     * Evaluate the compiled XPath expression in the context
     * of the specified {@code InputSource} and return the result as the
     * specified type.
     *
     * <p>This method builds a data model for the {@link InputSource} and calls
     * {@link #evaluate(Object item, QName returnType)} on the resulting document object.
     *
     * <p>See <a href="#XPathExpression-evaluation">Evaluation of XPath Expressions</a> for context item evaluation,
     * variable, function and QName resolution and return type conversion.
     *
     * <p>If {@code returnType} is not one of the types defined in {@link XPathConstants},
     * then an {@code IllegalArgumentException} is thrown.
     *
     * <p>If {@code source} or {@code returnType} is {@code null},
     * then a {@code NullPointerException} is thrown.
     *
     * @param source The {@code InputSource} of the document to evaluate over.
     * @param returnType The desired return type.
     *
     * @return The {@code Object} that is the result of evaluating the expression and converting the result to
     *   {@code returnType}.
     *
     * @throws XPathExpressionException If the expression cannot be evaluated.
     * @throws IllegalArgumentException If {@code returnType} is not one of the types defined in {@link XPathConstants}.
     * @throws NullPointerException If {@code source or returnType} is {@code null}.
     */
    public Object evaluate(InputSource source, QName returnType)
        throws XPathExpressionException;

    /**
     * Evaluate the compiled XPath expression in the context
     * of the specified {@code InputSource} and return the result as a
     * {@code String}.
     *
     * <p>This method calls {@link #evaluate(InputSource source, QName returnType)} with a {@code returnType} of
     * {@link XPathConstants#STRING}.
     *
     * <p>See <a href="#XPathExpression-evaluation">Evaluation of XPath Expressions</a> for context item evaluation,
     * variable, function and QName resolution and return type conversion.
     *
     * <p>If {@code source} is {@code null}, then a {@code NullPointerException} is thrown.
     *
     * @param source The {@code InputSource} of the document to evaluate over.
     *
     * @return The {@code String} that is the result of evaluating the expression and converting the result to a
     *   {@code String}.
     *
     * @throws XPathExpressionException If the expression cannot be evaluated.
     * @throws NullPointerException If {@code source} is {@code null}.
     */
    public String evaluate(InputSource source)
        throws XPathExpressionException;

    /**
     * Evaluate the compiled XPath expression in the specified context, and return
     * the result with the type specified through the {@code class type}.
     *
     * <p>
     * The parameter {@code item} represents the context the XPath expression
     * will be operated on. The type of the context is implementation-dependent.
     * If the value is {@code null}, the operation must have no dependency on
     * the context, otherwise an XPathExpressionException will be thrown.
     *
     * @implNote
     * The type of the context is usually {@link org.w3c.dom.Node}.
     *
     * @implSpec
     * The default implementation in the XPath API is equivalent to:
     * <pre> {@code
     *     (T)evaluate(item, XPathEvaluationResult.XPathResultType.getQNameType(type));
     * }</pre>
     *
     * Since the {@code evaluate} method does not support the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY} type, specifying
     * XPathEvaluationResult as the type will result in IllegalArgumentException.
     * Any implementation supporting the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY} type must override
     * this method.
     *
     * @param <T> The class type that will be returned by the XPath expression.
     * @param item The context the XPath expression will be evaluated in.
     * @param type The class type expected to be returned by the XPath expression.
     *
     * @return The result of evaluating the expression.
     *
     * @throws XPathExpressionException If the expression cannot be evaluated.
     * @throws IllegalArgumentException If {@code type} is not of the types
     * corresponding to the types defined in the {@link XPathEvaluationResult.XPathResultType
     * XPathResultType}, or XPathEvaluationResult is specified as the type but an
     * implementation supporting the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY} type is not available.
     * @throws NullPointerException If {@code type} is {@code null}.
     *
     * @since 9
     */
    default <T>T evaluateExpression(Object item, Class<T> type)
        throws XPathExpressionException
    {
        return type.cast(evaluate(item, XPathEvaluationResult.XPathResultType.getQNameType(type)));
    }

    /**
     * Evaluate the compiled XPath expression in the specified context. This is
     * equivalent to calling {@link #evaluateExpression(Object item, Class type)}
     * with type {@link XPathEvaluationResult}:
     * <pre> {@code
     *     evaluateExpression(item, XPathEvaluationResult.class);
     * }</pre>
     * <p>
     * The parameter {@code item} represents the context the XPath expression
     * will be operated on. The type of the context is implementation-dependent.
     * If the value is {@code null}, the operation must have no dependency on
     * the context, otherwise an XPathExpressionException will be thrown.
     *
     * @implNote
     * The type of the context is usually {@link org.w3c.dom.Node}.
     *
     * @implSpec
     * The default implementation in the XPath API is equivalent to:
     * <pre> {@code
     *     evaluateExpression(item, XPathEvaluationResult.class);
     * }</pre>
     *
     * Since the {@code evaluate} method does not support the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY}
     * type, the default implementation of this method will always throw an
     * IllegalArgumentException. Any implementation supporting the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY} type must therefore
     * override this method.
     *
     * @param item The context the XPath expression will be evaluated in.
     *
     * @return The result of evaluating the expression.
     *
     * @throws XPathExpressionException If the expression cannot be evaluated.
     * @throws IllegalArgumentException If the implementation of this method
     * does not support the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY} type.
     *
     * @since 9
     */
    default XPathEvaluationResult<?> evaluateExpression(Object item)
        throws XPathExpressionException
    {
        return evaluateExpression(item, XPathEvaluationResult.class);
    }

    /**
     * Evaluate the compiled XPath expression in the specified context,
     * and return the result with the type specified through the {@code class type}
     * <p>
     * This method builds a data model for the {@link InputSource} and calls
     * {@link #evaluateExpression(Object item, Class type)} on the resulting
     * document object.
     * <P>
     * By default, the JDK's data model is {@link org.w3c.dom.Document}.
     *
     * @implSpec
     * The default implementation in the XPath API is equivalent to:
     * <pre> {@code
           (T)evaluate(source, XPathEvaluationResult.XPathResultType.getQNameType(type));
     * }</pre>
     *
     * Since the {@code evaluate} method does not support the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY} type, specifying
     * XPathEvaluationResult as the type will result in IllegalArgumentException.
     * Any implementation supporting the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY} type must override
     * this method.
     *
     * @param <T> The class type that will be returned by the XPath expression.
     * @param source The {@code InputSource} of the document to evaluate over.
     * @param type The class type expected to be returned by the XPath expression.
     *
     * @return The result of evaluating the expression.
     *
     * @throws XPathExpressionException If the expression cannot be evaluated.
     * @throws IllegalArgumentException If {@code type} is not of the types
     * corresponding to the types defined in the {@link XPathEvaluationResult.XPathResultType
     * XPathResultType}, or XPathEvaluationResult is specified as the type but an
     * implementation supporting the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY} type
     * is not available.
     * @throws NullPointerException If {@code source or type} is {@code null}.
     *
     * @since 9
     */
    default <T>T evaluateExpression(InputSource source, Class<T> type)
        throws XPathExpressionException
    {
        return type.cast(evaluate(source, XPathEvaluationResult.XPathResultType.getQNameType(type)));
    }

    /**
     * Evaluate the compiled XPath expression in the specified context. This is
     * equivalent to calling {@link #evaluateExpression(InputSource source, Class type)}
     * with type {@link XPathEvaluationResult}:
     * <pre> {@code
     *     evaluateExpression(source, XPathEvaluationResult.class);
     * }</pre>
     *
     * @implSpec
     * The default implementation in the XPath API is equivalent to:
     * <pre> {@code
     *     (XPathEvaluationResult)evaluateExpression(source, XPathEvaluationResult.class);
     * }</pre>
     *
     * Since the {@code evaluate} method does not support the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY}
     * type, the default implementation of this method will always throw an
     * IllegalArgumentException. Any implementation supporting the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY} type must therefore
     * override this method.
     *
     * @param source The {@code InputSource} of the document to evaluate over.
     *
     * @return The result of evaluating the expression.
     *
     * @throws XPathExpressionException If the expression cannot be evaluated.
     * @throws IllegalArgumentException If the implementation of this method
     * does not support the
     * {@link XPathEvaluationResult.XPathResultType#ANY ANY} type.
     * @throws NullPointerException If {@code source} is {@code null}.
     *
     * @since 9
     */
    default XPathEvaluationResult<?> evaluateExpression(InputSource source)
        throws XPathExpressionException
    {
        return evaluateExpression(source, XPathEvaluationResult.class);
    }
}
