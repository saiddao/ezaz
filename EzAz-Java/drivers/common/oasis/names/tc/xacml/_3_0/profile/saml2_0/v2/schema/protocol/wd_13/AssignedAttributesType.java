
package oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.protocol.wd_13;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AssignedAttributesType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AssignedAttributesType">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13}Holders"/>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:profile:saml2.0:v2:schema:protocol:wd-13}HolderAttributes"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AssignedAttributesType", propOrder = {
    "holders",
    "holderAttributes"
})
public class AssignedAttributesType {

    @XmlElement(name = "Holders", required = true)
    protected Object holders;
    @XmlElement(name = "HolderAttributes", required = true)
    protected Object holderAttributes;

    /**
     * Gets the value of the holders property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getHolders() {
        return holders;
    }

    /**
     * Sets the value of the holders property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setHolders(Object value) {
        this.holders = value;
    }

    /**
     * Gets the value of the holderAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link Object }
     *     
     */
    public Object getHolderAttributes() {
        return holderAttributes;
    }

    /**
     * Sets the value of the holderAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Object }
     *     
     */
    public void setHolderAttributes(Object value) {
        this.holderAttributes = value;
    }

}
