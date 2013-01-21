
package oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.assertion.wd_13;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElements;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.PolicySetType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.PolicyType;


/**
 * <p>Java class for ReferencedPoliciesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ReferencedPoliciesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;choice maxOccurs="unbounded" minOccurs="0">
 *           &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}Policy"/>
 *           &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}PolicySet"/>
 *         &lt;/choice>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReferencedPoliciesType", propOrder = {
    "policyOrPolicySet"
})
public class ReferencedPoliciesType {

    @XmlElements({
        @XmlElement(name = "Policy", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", type = PolicyType.class),
        @XmlElement(name = "PolicySet", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", type = PolicySetType.class)
    })
    protected List<Object> policyOrPolicySet;

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
     * {@link PolicyType }
     * {@link PolicySetType }
     * 
     * 
     */
    public List<Object> getPolicyOrPolicySet() {
        if (policyOrPolicySet == null) {
            policyOrPolicySet = new ArrayList<Object>();
        }
        return this.policyOrPolicySet;
    }

}
