
package com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery3;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery3 package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _PDPFault_QNAME = new QName("http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery3", "PDPFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery3
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InstanceTraceTestAccessQuery3Response.Trace }
     * 
     */
    public InstanceTraceTestAccessQuery3Response.Trace createInstanceTraceTestAccessQuery3ResponseTrace() {
        return new InstanceTraceTestAccessQuery3Response.Trace();
    }

    /**
     * Create an instance of {@link InstanceTraceTestAccessQuery3 .Store }
     * 
     */
    public InstanceTraceTestAccessQuery3 .Store createInstanceTraceTestAccessQuery3Store() {
        return new InstanceTraceTestAccessQuery3 .Store();
    }

    /**
     * Create an instance of {@link InstanceTraceTestAccessQuery3Response }
     * 
     */
    public InstanceTraceTestAccessQuery3Response createInstanceTraceTestAccessQuery3Response() {
        return new InstanceTraceTestAccessQuery3Response();
    }

    /**
     * Create an instance of {@link InstanceTraceTestAccessQuery3 }
     * 
     */
    public InstanceTraceTestAccessQuery3 createInstanceTraceTestAccessQuery3() {
        return new InstanceTraceTestAccessQuery3();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTraceTestAccessQuery3", name = "PDPFault")
    public JAXBElement<String> createPDPFault(String value) {
        return new JAXBElement<String>(_PDPFault_QNAME, String.class, null, value);
    }

}
