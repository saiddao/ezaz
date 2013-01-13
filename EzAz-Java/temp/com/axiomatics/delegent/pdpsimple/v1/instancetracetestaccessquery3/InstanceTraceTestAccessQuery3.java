
package com.axiomatics.delegent.pdpsimple.v1.instancetracetestaccessquery3;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;
import oasis.names.tc.xacml._3_0.core.schema.wd_17.RequestType;


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
 *         &lt;element name="InstanceId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element ref="{urn:oasis:names:tc:xacml:3.0:core:schema:wd-17}Request"/>
 *         &lt;element name="Store" maxOccurs="unbounded">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="StoreId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *                   &lt;element name="StoreVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
    "instanceId",
    "request",
    "store"
})
@XmlRootElement(name = "InstanceTraceTestAccessQuery3")
public class InstanceTraceTestAccessQuery3 {

    @XmlElement(name = "InstanceId", required = true)
    protected String instanceId;
    @XmlElement(name = "Request", namespace = "urn:oasis:names:tc:xacml:3.0:core:schema:wd-17", required = true)
    protected RequestType request;
    @XmlElement(name = "Store", required = true)
    protected List<InstanceTraceTestAccessQuery3 .Store> store;

    /**
     * Gets the value of the instanceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getInstanceId() {
        return instanceId;
    }

    /**
     * Sets the value of the instanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setInstanceId(String value) {
        this.instanceId = value;
    }

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
     * Gets the value of the store property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the store property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getStore().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link InstanceTraceTestAccessQuery3 .Store }
     * 
     * 
     */
    public List<InstanceTraceTestAccessQuery3 .Store> getStore() {
        if (store == null) {
            store = new ArrayList<InstanceTraceTestAccessQuery3 .Store>();
        }
        return this.store;
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
     *         &lt;element name="StoreId" type="{http://www.w3.org/2001/XMLSchema}string"/>
     *         &lt;element name="StoreVersion" type="{http://www.w3.org/2001/XMLSchema}string"/>
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
        "storeId",
        "storeVersion"
    })
    public static class Store {

        @XmlElement(name = "StoreId", required = true)
        protected String storeId;
        @XmlElement(name = "StoreVersion", required = true)
        protected String storeVersion;

        /**
         * Gets the value of the storeId property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStoreId() {
            return storeId;
        }

        /**
         * Sets the value of the storeId property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStoreId(String value) {
            this.storeId = value;
        }

        /**
         * Gets the value of the storeVersion property.
         * 
         * @return
         *     possible object is
         *     {@link String }
         *     
         */
        public String getStoreVersion() {
            return storeVersion;
        }

        /**
         * Sets the value of the storeVersion property.
         * 
         * @param value
         *     allowed object is
         *     {@link String }
         *     
         */
        public void setStoreVersion(String value) {
            this.storeVersion = value;
        }

    }

}
