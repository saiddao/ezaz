
package oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.assertion.wd_13;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.assertion.wd_13 package. 
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

    private final static QName _ReferencedPolicies_QNAME = new QName("urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:assertion:wd-13", "ReferencedPolicies");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.assertion.wd_13
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link ReferencedPoliciesType }
     * 
     */
    public ReferencedPoliciesType createReferencedPoliciesType() {
        return new ReferencedPoliciesType();
    }

    /**
     * Create an instance of {@link XACMLPolicyStatementType }
     * 
     */
    public XACMLPolicyStatementType createXACMLPolicyStatementType() {
        return new XACMLPolicyStatementType();
    }

    /**
     * Create an instance of {@link XACMLAuthzDecisionStatementType }
     * 
     */
    public XACMLAuthzDecisionStatementType createXACMLAuthzDecisionStatementType() {
        return new XACMLAuthzDecisionStatementType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ReferencedPoliciesType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:assertion:wd-13", name = "ReferencedPolicies")
    public JAXBElement<ReferencedPoliciesType> createReferencedPolicies(ReferencedPoliciesType value) {
        return new JAXBElement<ReferencedPoliciesType>(_ReferencedPolicies_QNAME, ReferencedPoliciesType.class, null, value);
    }

}
