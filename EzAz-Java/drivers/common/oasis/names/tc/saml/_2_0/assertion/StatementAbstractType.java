
package oasis.names.tc.saml._2_0.assertion;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.assertion.wd_13.XACMLAuthzDecisionStatementType;
import oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.assertion.wd_13.XACMLPolicyStatementType;


/**
 * <p>Java class for StatementAbstractType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="StatementAbstractType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "StatementAbstractType")
@XmlSeeAlso({
    AuthnStatementType.class,
    AuthzDecisionStatementType.class,
    AttributeStatementType.class,
    XACMLPolicyStatementType.class,
    XACMLAuthzDecisionStatementType.class
})
public abstract class StatementAbstractType {


}
