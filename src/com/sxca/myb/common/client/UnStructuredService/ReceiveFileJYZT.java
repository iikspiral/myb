package com.sxca.myb.common.client.UnStructuredService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

/**
 * <p>
 * Java class for ReceiveFile_JYZT complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType name="ReceiveFile_JYZT">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="userId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="key" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="serialNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attachmentSetCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attachmentCount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attachmentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attachmentName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="attachmentType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="MD5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="arg9" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="fileStream" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ReceiveFile_JYZT", propOrder = { "userId", "key", "serialNumber", "attachmentSetCode", "attachmentCount", "attachmentCode", "attachmentName", "attachmentType", "md5", "arg9",
		"fileStream" })
public class ReceiveFileJYZT {

	protected String userId;
	protected String key;
	protected String serialNumber;
	protected String attachmentSetCode;
	protected String attachmentCount;
	protected String attachmentCode;
	protected String attachmentName;
	protected String attachmentType;
	@XmlElement(name = "MD5")
	protected String md5;
	protected String arg9;
	@XmlElementRef(name = "fileStream", type = JAXBElement.class)
	protected JAXBElement<byte[]> fileStream;

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
	 * Gets the value of the attachmentSetCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAttachmentSetCode() {
		return attachmentSetCode;
	}

	/**
	 * Sets the value of the attachmentSetCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAttachmentSetCode(String value) {
		this.attachmentSetCode = value;
	}

	/**
	 * Gets the value of the attachmentCount property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAttachmentCount() {
		return attachmentCount;
	}

	/**
	 * Sets the value of the attachmentCount property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAttachmentCount(String value) {
		this.attachmentCount = value;
	}

	/**
	 * Gets the value of the attachmentCode property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAttachmentCode() {
		return attachmentCode;
	}

	/**
	 * Sets the value of the attachmentCode property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAttachmentCode(String value) {
		this.attachmentCode = value;
	}

	/**
	 * Gets the value of the attachmentName property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAttachmentName() {
		return attachmentName;
	}

	/**
	 * Sets the value of the attachmentName property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAttachmentName(String value) {
		this.attachmentName = value;
	}

	/**
	 * Gets the value of the attachmentType property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getAttachmentType() {
		return attachmentType;
	}

	/**
	 * Sets the value of the attachmentType property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setAttachmentType(String value) {
		this.attachmentType = value;
	}

	/**
	 * Gets the value of the md5 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getMD5() {
		return md5;
	}

	/**
	 * Sets the value of the md5 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setMD5(String value) {
		this.md5 = value;
	}

	/**
	 * Gets the value of the arg9 property.
	 * 
	 * @return possible object is {@link String }
	 * 
	 */
	public String getArg9() {
		return arg9;
	}

	/**
	 * Sets the value of the arg9 property.
	 * 
	 * @param value
	 *            allowed object is {@link String }
	 * 
	 */
	public void setArg9(String value) {
		this.arg9 = value;
	}

	/**
	 * Gets the value of the fileStream property.
	 * 
	 * @return possible object is {@link JAXBElement }{@code <}{@link byte[]}
	 *         {@code >}
	 * 
	 */
	public JAXBElement<byte[]> getFileStream() {
		return fileStream;
	}

	/**
	 * Sets the value of the fileStream property.
	 * 
	 * @param value
	 *            allowed object is {@link JAXBElement }{@code <}{@link byte[]}
	 *            {@code >}
	 * 
	 */
	public void setFileStream(JAXBElement<byte[]> value) {
		this.fileStream = ((JAXBElement<byte[]>) value);
	}

}
