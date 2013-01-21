
package oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.protocol.wd_13;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.protocol.wd_13 package. 
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

    private final static QName _XACMLPolicyQuery_QNAME = new QName("urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", "XACMLPolicyQuery");
    private final static QName _XACMLAuthzDecisionQuery_QNAME = new QName("urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", "XACMLAuthzDecisionQuery");
    private final static QName _AdditionalAttributes_QNAME = new QName("urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", "AdditionalAttributes");
    private final static QName _HolderAttributes_QNAME = new QName("urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", "HolderAttributes");
    private final static QName _Extensions_QNAME = new QName("urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", "Extensions");
    private final static QName _Holders_QNAME = new QName("urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", "Holders");
    private final static QName _AssignedAttributes_QNAME = new QName("urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", "AssignedAttributes");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.protocol.wd_13
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AdditionalAttributesType }
     * 
     */
    public AdditionalAttributesType createAdditionalAttributesType() {
        return new AdditionalAttributesType();
    }

    /**
     * Create an instance of {@link AssignedAttributesType }
     * 
     */
    public AssignedAttributesType createAssignedAttributesType() {
        return new AssignedAttributesType();
    }

    /**
     * Create an instance of {@link HoldersType }
     * 
     */
    public HoldersType createHoldersType() {
        return new HoldersType();
    }

    /**
     * Create an instance of {@link XACMLAuthzDecisionQueryType }
     * 
     */
    public XACMLAuthzDecisionQueryType createXACMLAuthzDecisionQueryType() {
        return new XACMLAuthzDecisionQueryType();
    }

    /**
     * Create an instance of {@link HolderAttributesType }
     * 
     */
    public HolderAttributesType createHolderAttributesType() {
        return new HolderAttributesType();
    }

    /**
     * Create an instance of {@link ExtensionsType }
     * 
     */
    public ExtensionsType createExtensionsType() {
        return new ExtensionsType();
    }

    /**
     * Create an instance of {@link XACMLPolicyQueryType }
     * 
     */
    public XACMLPolicyQueryType createXACMLPolicyQueryType() {
        return new XACMLPolicyQueryType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", name = "XACMLPolicyQuery")
    public JAXBElement<Object> createXACMLPolicyQuery(Object value) {
        return new JAXBElement<Object>(_XACMLPolicyQuery_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", name = "XACMLAuthzDecisionQuery")
    public JAXBElement<Object> createXACMLAuthzDecisionQuery(Object value) {
        return new JAXBElement<Object>(_XACMLAuthzDecisionQuery_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", name = "AdditionalAttributes")
    public JAXBElement<Object> createAdditionalAttributes(Object value) {
        return new JAXBElement<Object>(_AdditionalAttributes_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", name = "HolderAttributes")
    public JAXBElement<Object> createHolderAttributes(Object value) {
        return new JAXBElement<Object>(_HolderAttributes_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", name = "Extensions")
    public JAXBElement<Object> createExtensions(Object value) {
        return new JAXBElement<Object>(_Extensions_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", name = "Holders")
    public JAXBElement<Object> createHolders(Object value) {
        return new JAXBElement<Object>(_Holders_QNAME, Object.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Object }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13", name = "AssignedAttributes")
    public JAXBElement<Object> createAssignedAttributes(Object value) {
        return new JAXBElement<Object>(_AssignedAttributes_QNAME, Object.class, null, value);
    }

}
