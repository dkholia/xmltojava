//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.8-b130911.1802 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2018.02.03 at 08:17:48 PM EST 
//


package com.expr.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
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
 *       &lt;attribute name="field-name" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *       &lt;attribute name="field-value" use="required" type="{http://www.w3.org/2001/XMLSchema}anySimpleType" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
	    "multiLineText",
	    "multiSelect"
	})
@XmlRootElement(name = "custom-field-value")
public class CustomFieldValue {
    @XmlElement(name = "multi-line-text")
    public MultiLineText multiLineText;
    @XmlElement(name = "multi-select")
    public MultiSelect multiSelect;
    @XmlAttribute(name = "field-name", required = true)
    @XmlSchemaType(name = "anySimpleType")
    public String fieldName;
    @XmlAttribute(name = "field-value", required = true)
    @XmlSchemaType(name = "anySimpleType")
    public String fieldValue;

    /**
	 * @return the multiLineText
	 */
	public MultiLineText getMultiLineText() {
		if(multiLineText==null)
			multiLineText = new MultiLineText();
		return multiLineText;
	}

	/**
	 * @param multiLineText the multiLineText to set
	 */
	public void setMultiLineText(MultiLineText multiLineText) {
		this.multiLineText = multiLineText;
	}

	/**
	 * @return the multiSelect
	 */
	public MultiSelect getMultiSelect() {
		if(multiSelect==null)
			multiSelect = new MultiSelect();
		return multiSelect;
	}

	/**
	 * @param multiSelect the multiSelect to set
	 */
	public void setMultiSelect(MultiSelect multiSelect) {
		this.multiSelect = multiSelect;
	}

	/**
     * Gets the value of the fieldName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldName() {
        return fieldName;
    }

    /**
     * Sets the value of the fieldName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldName(String value) {
        this.fieldName = value;
    }

    /**
     * Gets the value of the fieldValue property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFieldValue() {
        return fieldValue;
    }

    /**
     * Sets the value of the fieldValue property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFieldValue(String value) {
        this.fieldValue = value;
    }

	@Override
	public String toString() {
		return  fieldName + " = " + fieldValue ;
	}

}
