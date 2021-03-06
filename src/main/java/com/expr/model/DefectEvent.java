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
 *         &lt;element ref="{}event-name"/>
 *         &lt;element ref="{}event-date"/>
 *         &lt;element ref="{}event-author"/>
 *         &lt;element ref="{}notes" minOccurs="0"/>
 *         &lt;choice minOccurs="0">
 *           &lt;element ref="{}custom-field-value"/>
 *           &lt;element ref="{}event-assigned-to"/>
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
@XmlType(name = "", propOrder = {
    "eventName",
    "eventDate",
    "eventAuthor",
    "notes",
    "customFieldValue",
    "eventAssignedTo"
})
@XmlRootElement(name = "defect-event")
public class DefectEvent {

    @XmlElement(name = "event-name", required = true)
    public String eventName;
    @XmlElement(name = "event-date", required = true)
    public String eventDate;
    @XmlElement(name = "event-author", required = true)
    public EventAuthor eventAuthor;
    public String notes;
    @XmlElement(name = "custom-field-value")
    public CustomFieldValue customFieldValue;
    @XmlElement(name = "event-assigned-to")
    public EventAssignedTo eventAssignedTo;

    /**
     * Gets the value of the eventName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventName() {
        return eventName;
    }

    /**
     * Sets the value of the eventName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventName(String value) {
        this.eventName = value;
    }

    /**
     * Gets the value of the eventDate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEventDate() {
        return eventDate;
    }

    /**
     * Sets the value of the eventDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEventDate(String value) {
        this.eventDate = value;
    }

    /**
     * Gets the value of the eventAuthor property.
     * 
     * @return
     *     possible object is
     *     {@link EventAuthor }
     *     
     */
    public EventAuthor getEventAuthor() {
        return eventAuthor;
    }

    /**
     * Sets the value of the eventAuthor property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventAuthor }
     *     
     */
    public void setEventAuthor(EventAuthor value) {
        this.eventAuthor = value;
    }

    /**
     * Gets the value of the notes property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNotes() {
        return notes;
    }

    /**
     * Sets the value of the notes property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNotes(String value) {
        this.notes = value;
    }

    /**
     * Gets the value of the customFieldValue property.
     * 
     * @return
     *     possible object is
     *     {@link CustomFieldValue }
     *     
     */
    public CustomFieldValue getCustomFieldValue() {
        return customFieldValue;
    }

    /**
     * Sets the value of the customFieldValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link CustomFieldValue }
     *     
     */
    public void setCustomFieldValue(CustomFieldValue value) {
        this.customFieldValue = value;
    }

    /**
     * Gets the value of the eventAssignedTo property.
     * 
     * @return
     *     possible object is
     *     {@link EventAssignedTo }
     *     
     */
    public EventAssignedTo getEventAssignedTo() {
        return eventAssignedTo;
    }

    /**
     * Sets the value of the eventAssignedTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link EventAssignedTo }
     *     
     */
    public void setEventAssignedTo(EventAssignedTo value) {
        this.eventAssignedTo = value;
    }

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "eventName=" + eventName + ", eventDate=" + eventDate + ", eventAuthor=" + eventAuthor
				+ ", notes=" + notes + ", customFieldValue=" + customFieldValue + ", eventAssignedTo=" + eventAssignedTo
				+ "\n";
	}
}
