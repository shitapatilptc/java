/*
 * Copyright (c) 2013, Oracle and/or its affiliates. All rights reserved.
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

package com.sun.xml.internal.messaging.saaj.soap;

import javax.xml.namespace.QName;
import javax.xml.soap.SOAPException;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamReader;

import com.sun.xml.internal.org.jvnet.staxex.util.XMLStreamReaderToXMLStreamWriter;

/**
 * StaxBridge builds Envelope using a XMLStreamReaderToXMLStreamWriter
 *
 * @author shih-chang.chen@oracle.com
 */
public class StaxReaderBridge extends StaxBridge {
        private XMLStreamReader in;

        public StaxReaderBridge(XMLStreamReader reader, SOAPPartImpl soapPart) throws SOAPException {
                super(soapPart);
                in = reader;
                final String soapEnvNS = soapPart.getSOAPNamespace();
                breakpoint =  new XMLStreamReaderToXMLStreamWriter.Breakpoint(reader, saajWriter) {
                        boolean seenBody = false;
                        boolean stopedAtBody = false;
                    public boolean proceedBeforeStartElement()  {
                        if (stopedAtBody) return true;
                        if (seenBody) {
                                stopedAtBody = true;
                                return false;
                        }
                            if ("Body".equals(reader.getLocalName()) && soapEnvNS.equals(reader.getNamespaceURI()) ){
                                seenBody = true;
                            }
                            return true;
                    }
                };
        }

    public XMLStreamReader getPayloadReader() {
        return in;
    }

    public QName getPayloadQName() {
        return (in.getEventType() == XMLStreamConstants.START_ELEMENT) ? in.getName() : null;
    }

    public String getPayloadAttributeValue(String attName) {
        return (in.getEventType() == XMLStreamConstants.START_ELEMENT) ? in.getAttributeValue(null, attName) : null;
    }

    public String getPayloadAttributeValue(QName attName) {
        return (in.getEventType() == XMLStreamConstants.START_ELEMENT) ? in.getAttributeValue(attName.getNamespaceURI(), attName.getLocalPart()) : null;
    }
}
