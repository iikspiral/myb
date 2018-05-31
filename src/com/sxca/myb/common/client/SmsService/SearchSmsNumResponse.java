package com.sxca.myb.common.client.SmsService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="result" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="number" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "result", "number" })
@XmlRootElement(name = "SearchSmsNumResponse")
public class SearchSmsNumResponse {

	@XmlElement(required = true, nillable = true)
	protected String result;
	@XmlElement(required = true, nillable = true)
	protected String number;

	/**
	 * Gets the value of the result property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getResult() {
		return result;
	}

	/**
	 * Sets the value of the result property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setResult(String value) {
		this.result = value;
	}

	/**
	 * Gets the value of the number property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getNumber() {
		return number;
	}

	/**
	 * Sets the value of the number property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setNumber(String value) {
		this.number = value;
	}

}
