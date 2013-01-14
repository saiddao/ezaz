
package com.axiomatics.delegent.pdpsimple.v1.invalidateattributecache;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="ModuleParameter" maxOccurs="unbounded" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="ModuleId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="Parameter" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "moduleParameter"
})
@XmlRootElement(name = "InvalidateAttributeCache")
public class InvalidateAttributeCache {

    @XmlElement(name = "ModuleParameter")
    protected List<InvalidateAttributeCache.ModuleParameter> moduleParameter;

    /**
     * Gets the value of the moduleParameter property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the moduleParameter property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getModuleParameter().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InvalidateAttributeCache.ModuleParameter }
     * 
     * 
     */
    public List<InvalidateAttributeCache.ModuleParameter> getModuleParameter() {
        if (moduleParameter == null) {
            moduleParameter = new ArrayList<InvalidateAttributeCache.ModuleParameter>();
        }
        return this.moduleParameter;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="ModuleId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="Parameter" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "moduleId",
        "parameter"
    })
    public static class ModuleParameter {

        @XmlElement(name = "ModuleId", required = true)
        protected String moduleId;
        @XmlElement(name = "Parameter", required = true)
        protected String parameter;

        /**
         * Gets the value of the moduleId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getModuleId() {
            return moduleId;
        }

        /**
         * Sets the value of the moduleId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setModuleId(String value) {
            this.moduleId = value;
        }

        /**
         * Gets the value of the parameter property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getParameter() {
            return parameter;
        }

        /**
         * Sets the value of the parameter property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setParameter(String value) {
            this.parameter = value;
        }

    }

}
