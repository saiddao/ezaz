
package oasis.names.tc.xacml._3_0.core.schema.wd_17;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for RuleCombinerParametersType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RuleCombinerParametersType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}CombinerParametersType">
 *       &lt;attribute name="RuleIdRef" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RuleCombinerParametersType")
public class RuleCombinerParametersType
    extends CombinerParametersType
{

    @XmlAttribute(name = "RuleIdRef", required = true)
    protected String ruleIdRef;

    /**
     * Gets the value of the ruleIdRef property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRuleIdRef() {
        return ruleIdRef;
    }

    /**
     * Sets the value of the ruleIdRef property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRuleIdRef(String value) {
        this.ruleIdRef = value;
    }

}
