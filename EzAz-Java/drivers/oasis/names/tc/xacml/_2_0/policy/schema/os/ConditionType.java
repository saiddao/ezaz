
package oasis.names.tc.xacml._2_0.policy.schema.os;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ConditionType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ConditionType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:2.0:policy:schema:os}Expression"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ConditionType", propOrder = {
    "expression"
})
public class ConditionType {

    @XmlElementRef(name = "Expression", namespace = "urn:oasis:names:tc:xacml:2.0:policy:schema:os", type = JAXBElement.class)
    protected JAXBElement<?> expression;

    /**
     * Gets the value of the expression property.
     * 
     * @return
     *     possible object is
     *     {@link JAXBElement }{@code <}{@link SubjectAttributeDesignatorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link FunctionType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AttributeSelectorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExpressionType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AttributeValueType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ApplyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VariableReferenceType }{@code >}
     *     
     */
    public JAXBElement<?> getExpression() {
        return expression;
    }

    /**
     * Sets the value of the expression property.
     * 
     * @param value
     *     allowed object is
     *     {@link JAXBElement }{@code <}{@link SubjectAttributeDesignatorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link FunctionType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AttributeSelectorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AttributeDesignatorType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ExpressionType }{@code >}
     *     {@link JAXBElement }{@code <}{@link AttributeValueType }{@code >}
     *     {@link JAXBElement }{@code <}{@link ApplyType }{@code >}
     *     {@link JAXBElement }{@code <}{@link VariableReferenceType }{@code >}
     *     
     */
    public void setExpression(JAXBElement<?> value) {
        this.expression = ((JAXBElement<?> ) value);
    }

}
