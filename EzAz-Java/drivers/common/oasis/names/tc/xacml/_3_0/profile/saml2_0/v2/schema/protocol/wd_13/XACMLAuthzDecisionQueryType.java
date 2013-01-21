
package oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.protocol.wd_13;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.protocol.RequestAbstractType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.PolicySetType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.PolicyType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.RequestType;
import oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.assertion.wd_13.ReferencedPoliciesType;


/**
 * <p>Java class for XACMLAuthzDecisionQueryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XACMLAuthzDecisionQueryType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:protocol}RequestAbstractType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}Request"/>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13}AdditionalAttributes" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}Policy" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}PolicySet" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:assertion:wd-13}ReferencedPolicies" minOccurs="0"/>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13}Extensions" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="InputContextOnly" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="ReturnContext" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="CombinePolicies" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XACMLAuthzDecisionQueryType", propOrder = {
    "request",
    "additionalAttributes",
    "policy",
    "policySet",
    "referencedPolicies",
    "extensions"
})
public class XACMLAuthzDecisionQueryType
    extends RequestAbstractType
{

    @XmlElement(name = "Request", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", required = true)
    protected RequestType request;
    @XmlElement(name = "AdditionalAttributes")
    protected Object additionalAttributes;
    @XmlElement(name = "Policy", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17")
    protected List<PolicyType> policy;
    @XmlElement(name = "PolicySet", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17")
    protected List<PolicySetType> policySet;
    @XmlElement(name = "ReferencedPolicies", namespace = "urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:assertion:wd-13")
    protected ReferencedPoliciesType referencedPolicies;
    @XmlElement(name = "Extensions")
    protected Object extensions;
    @XmlAttribute(name = "InputContextOnly")
    protected Boolean inputContextOnly;
    @XmlAttribute(name = "ReturnContext")
    protected Boolean returnContext;
    @XmlAttribute(name = "CombinePolicies")
    protected Boolean combinePolicies;

    /**
     * Gets the value of the request property.
     * 
     * @return
     *     possible object is
     *     {@link RequestType }
     *     
     */
    public RequestType getRequest() {
        return request;
    }

    /**
     * Sets the value of the request property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestType }
     *     
     */
    public void setRequest(RequestType value) {
        this.request = value;
    }

    /**
     * Gets the value of the additionalAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getAdditionalAttributes() {
        return additionalAttributes;
    }

    /**
     * Sets the value of the additionalAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setAdditionalAttributes(Object value) {
        this.additionalAttributes = value;
    }

    /**
     * Gets the value of the policy property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the policy property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolicy().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PolicyType }
     * 
     * 
     */
    public List<PolicyType> getPolicy() {
        if (policy == null) {
            policy = new ArrayList<PolicyType>();
        }
        return this.policy;
    }

    /**
     * Gets the value of the policySet property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the policySet property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPolicySet().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link PolicySetType }
     * 
     * 
     */
    public List<PolicySetType> getPolicySet() {
        if (policySet == null) {
            policySet = new ArrayList<PolicySetType>();
        }
        return this.policySet;
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

    /**
     * Gets the value of the extensions property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getExtensions() {
        return extensions;
    }

    /**
     * Sets the value of the extensions property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setExtensions(Object value) {
        this.extensions = value;
    }

    /**
     * Gets the value of the inputContextOnly property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isInputContextOnly() {
        if (inputContextOnly == null) {
            return false;
        } else {
            return inputContextOnly;
        }
    }

    /**
     * Sets the value of the inputContextOnly property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setInputContextOnly(Boolean value) {
        this.inputContextOnly = value;
    }

    /**
     * Gets the value of the returnContext property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isReturnContext() {
        if (returnContext == null) {
            return false;
        } else {
            return returnContext;
        }
    }

    /**
     * Sets the value of the returnContext property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setReturnContext(Boolean value) {
        this.returnContext = value;
    }

    /**
     * Gets the value of the combinePolicies property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public boolean isCombinePolicies() {
        if (combinePolicies == null) {
            return true;
        } else {
            return combinePolicies;
        }
    }

    /**
     * Sets the value of the combinePolicies property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setCombinePolicies(Boolean value) {
        this.combinePolicies = value;
    }

}
