//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.02.03 at 08:17:48 PM EST 
//


package com.expr.model;

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
 *         &lt;element ref="{}assigned-to-user"/>
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
    "assignedToUser"
})
@XmlRootElement(name = "event-assigned-to")
public class EventAssignedTo {

    @XmlElement(name = "assigned-to-user", required = true)
    public AssignedToUser assignedToUser;

    /**
     * Gets the value of the assignedToUser property.
     * 
     * @return
     *     possible object is
     *     {@link AssignedToUser }
     *     
     */
    public AssignedToUser getAssignedToUser() {
        return assignedToUser;
    }

    /**
     * Sets the value of the assignedToUser property.
     * 
     * @param value
     *     allowed object is
     *     {@link AssignedToUser }
     *     
     */
    public void setAssignedToUser(AssignedToUser value) {
        this.assignedToUser = value;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return  assignedToUser!=null ? assignedToUser.toString() : "";
	}
    
}
