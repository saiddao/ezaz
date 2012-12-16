
package com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery2;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery2 package. 
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

    private final static QName _PDPFault_QNAME = new QName("http://axiomatics.com/delegent/pdpsimple/v1/InstanceTestAccessQuery2", "PDPFault");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.axiomatics.delegent.pdpsimple.v1.instancetestaccessquery2
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link InstanceTestAccessQuery2 .Store }
     * 
     */
    public InstanceTestAccessQuery2 .Store createInstanceTestAccessQuery2Store() {
        return new InstanceTestAccessQuery2 .Store();
    }

    /**
     * Create an instance of {@link InstanceTestAccessQuery2 }
     * 
     */
    public InstanceTestAccessQuery2 createInstanceTestAccessQuery2() {
        return new InstanceTestAccessQuery2();
    }

    /**
     * Create an instance of {@link InstanceTestAccessQuery2Response }
     * 
     */
    public InstanceTestAccessQuery2Response createInstanceTestAccessQuery2Response() {
        return new InstanceTestAccessQuery2Response();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://axiomatics.com/delegent/pdpsimple/v1/InstanceTestAccessQuery2", name = "PDPFault")
    public JAXBElement<String> createPDPFault(String value) {
        return new JAXBElement<String>(_PDPFault_QNAME, String.class, null, value);
    }

}
