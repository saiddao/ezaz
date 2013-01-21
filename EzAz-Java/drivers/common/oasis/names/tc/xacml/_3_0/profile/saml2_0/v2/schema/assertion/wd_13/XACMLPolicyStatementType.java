
package oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.assertion.wd_13;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.assertion.StatementAbstractType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.PolicySetType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.PolicyType;


/**
 * <p>Java class for XACMLPolicyStatementType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XACMLPolicyStatementType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:assertion}StatementAbstractType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}Policy"/>
 *           &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}PolicySet"/>
 *         &lt;/choice>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:assertion:wd-13}ReferencedPolicies" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XACMLPolicyStatementType", propOrder = {
    "policyOrPolicySet",
    "referencedPolicies"
})
public class XACMLPolicyStatementType
    extends StatementAbstractType
{

    @XmlElements({
        @XmlElement(name = "PolicySet", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", type = PolicySetType.class),
        @XmlElement(name = "Policy", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", type = PolicyType.class)
    })
    protected List<Object> policyOrPolicySet;
    @XmlElement(name = "ReferencedPolicies")
    protected ReferencedPoliciesType referencedPolicies;

    /**
     * Gets the value of the policyOrPolicySet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the policyOrPolicySet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolicyOrPolicySet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PolicySetType }
     * {@link PolicyType }
     * 
     * 
     */
    public List<Object> getPolicyOrPolicySet() {
        if (policyOrPolicySet == null) {
            policyOrPolicySet = new ArrayList<Object>();
        }
        return this.policyOrPolicySet;
    }

    /**
     * Gets the value of the referencedPolicies property.
     * 
     * @return
     *     possible object is
     *     {@link ReferencedPoliciesType }
     *     
     */
    public ReferencedPoliciesType getReferencedPolicies() {
        return referencedPolicies;
    }

    /**
     * Sets the value of the referencedPolicies property.
     * 
     * @param value
     *     allowed object is
     *     {@link ReferencedPoliciesType }
     *     
     */
    public void setReferencedPolicies(ReferencedPoliciesType value) {
        this.referencedPolicies = value;
    }

}
