package com.sxca.myb.common.client.StructuredService;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ReceiveInfo_JYZT complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ReceiveInfo_JYZT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="datasetCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="XMLContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="requestTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="versionNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="platId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReceiveInfo_JYZT", propOrder = { "userId", "key", "serialNumber", "datasetCode", "xmlContent", "requestTime", "versionNumber", "platId" })
public class ReceiveInfoJYZT {

	protected String userId;
	protected String key;
	protected String serialNumber;
	protected String datasetCode;
	@XmlElement(name = "XMLContent")
	protected String xmlContent;
	protected String requestTime;
	protected String versionNumber;
	protected String platId;

	/**
	 * Gets the value of the userId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getUserId() {
		return userId;
	}

	/**
	 * Sets the value of the userId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setUserId(String value) {
		this.userId = value;
	}

	/**
	 * Gets the value of the key property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getKey() {
		return key;
	}

	/**
	 * Sets the value of the key property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setKey(String value) {
		this.key = value;
	}

	/**
	 * Gets the value of the serialNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getSerialNumber() {
		return serialNumber;
	}

	/**
	 * Sets the value of the serialNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setSerialNumber(String value) {
		this.serialNumber = value;
	}

	/**
	 * Gets the value of the datasetCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getDatasetCode() {
		return datasetCode;
	}

	/**
	 * Sets the value of the datasetCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setDatasetCode(String value) {
		this.datasetCode = value;
	}

	/**
	 * Gets the value of the xmlContent property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getXMLContent() {
		return xmlContent;
	}

	/**
	 * Sets the value of the xmlContent property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setXMLContent(String value) {
		this.xmlContent = value;
	}

	/**
	 * Gets the value of the requestTime property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getRequestTime() {
		return requestTime;
	}

	/**
	 * Sets the value of the requestTime property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setRequestTime(String value) {
		this.requestTime = value;
	}

	/**
	 * Gets the value of the versionNumber property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getVersionNumber() {
		return versionNumber;
	}

	/**
	 * Sets the value of the versionNumber property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setVersionNumber(String value) {
		this.versionNumber = value;
	}

	/**
	 * Gets the value of the platId property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getPlatId() {
		return platId;
	}

	/**
	 * Sets the value of the platId property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setPlatId(String value) {
		this.platId = value;
	}

}
