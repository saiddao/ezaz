
package oasis.names.tc.xacml._3_0.profile.saml2_0.v2.schema.protocol.wd_13;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlElementRefs;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.saml._2_0.protocol.RequestAbstractType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.IdReferenceType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.RequestType;


/**
 * <p>Java class for XACMLPolicyQueryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="XACMLPolicyQueryType">
 *   &lt;complexContent>
 *     &lt;extension base="{urn:oasis:names:tc:SAML:2.0:protocol}RequestAbstractType">
 *       &lt;choice maxOccurs="unbounded">
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}Request"/>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}PolicySetIdReference"/>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}PolicyIdReference"/>
 *       &lt;/choice>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "XACMLPolicyQueryType", propOrder = {
    "requestOrPolicySetIdReferenceOrPolicyIdReference"
})
public class XACMLPolicyQueryType
    extends RequestAbstractType
{

    @XmlElementRefs({
        @XmlElementRef(name = "Request", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", type = JAXBElement.class),
        @XmlElementRef(name = "PolicyIdReference", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", type = JAXBElement.class),
        @XmlElementRef(name = "PolicySetIdReference", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", type = JAXBElement.class)
    })
    protected List<JAXBElement<?>> requestOrPolicySetIdReferenceOrPolicyIdReference;

    /**
     * Gets the value of the requestOrPolicySetIdReferenceOrPolicyIdReference property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the requestOrPolicySetIdReferenceOrPolicyIdReference property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getRequestOrPolicySetIdReferenceOrPolicyIdReference().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JAXBElement }{@code <}{@link IdReferenceType }{@code >}
     * {@link JAXBElement }{@code <}{@link RequestType }{@code >}
     * {@link JAXBElement }{@code <}{@link IdReferenceType }{@code >}
     * 
     * 
     */
    public List<JAXBElement<?>> getRequestOrPolicySetIdReferenceOrPolicyIdReference() {
        if (requestOrPolicySetIdReferenceOrPolicyIdReference == null) {
            requestOrPolicySetIdReferenceOrPolicyIdReference = new ArrayList<JAXBElement<?>>();
        }
        return this.requestOrPolicySetIdReferenceOrPolicyIdReference;
    }

}
