/*
 * Copyright (C) 2013 eNitiatives Pty. Ltd.
 */
package org.EzAz.Layer3Driver.ViewDsSOAP;
import java.util.Set;
import java.util.TreeSet;
import javax.xml.namespace.QName;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPHeader;
import javax.xml.ws.ProtocolException;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;

/**
 *
 * @author mennis
 */
public class LocalHandler
        implements SOAPHandler<SOAPMessageContext>
{
    final static String wssePrefix = "wsse";
    final static String wsseNamespace =
            "http://docs.oasis-open.org/wss/2004/01/oasis-200401-wss-wssecurity-secext-1.0.xsd";

    final String username;
    final String password;
    
    public LocalHandler(String username, String password)
    {
        this.username = username;
        this.password = password;
    }
    
    @Override
    public Set<QName> getHeaders()
    {
        Set<QName> set;
        
        set = null;
        if (username != null && password != null) {
            set = new TreeSet<QName>();
            set.add(new QName("Security", wssePrefix, wsseNamespace));
        }
        return set;
    }

    @Override
    public boolean handleMessage(SOAPMessageContext context)
    {
        Boolean outbound;
        SOAPElement element;
        SOAPElement security;
        SOAPElement token;
        SOAPEnvelope envelope;
        SOAPHeader header;
        
        outbound = (Boolean)context.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
        if (username != null && password != null && outbound.booleanValue()) {
            try {
                envelope = context.getMessage().getSOAPPart().getEnvelope();
                header = envelope.addHeader();
                security = header.addChildElement("Security", wssePrefix, wsseNamespace);
                token = security.addChildElement("UsernameToken", wssePrefix, wsseNamespace);
                element = token.addChildElement("Username", wssePrefix, wsseNamespace);
                element.addTextNode(username);
                element = token.addChildElement("Password", wssePrefix, wsseNamespace);
                element.addTextNode(password);
            }
            catch (Exception ex) {
                throw new ProtocolException("header processing failed", ex);
            }
        }
        return true;
    }

    @Override
    public boolean handleFault(SOAPMessageContext context)
    {
        return true;
    }

    @Override
    public void close(MessageContext context)
    {
    }
}
