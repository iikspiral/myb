package com.sxca.myb.common.client.UnStructuredService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.sxca.ggzyzt.common.client.UnStructuredService package.
 * <p>
 * An ObjectFactory allows you to programatically construct new instances of the
 * Java representation for XML content. The Java representation of XML content
 * can consist of schema derived interfaces and classes representing the binding
 * of schema type definitions, element declarations and model groups. Factory
 * methods for each of these are provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

	private final static QName _ReceiveFileJYZT_QNAME = new QName("http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", "ReceiveFile_JYZT");
	private final static QName _ReceiveFileJYZTResponse_QNAME = new QName("http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", "ReceiveFile_JYZTResponse");
	private final static QName _ReceiveFileJYZTFileStream_QNAME = new QName("", "fileStream");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.sxca.ggzyzt.common.client.UnStructuredService
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link ReceiveFileJYZT }
	 * 
	 */
	public ReceiveFileJYZT createReceiveFileJYZT() {
		return new ReceiveFileJYZT();
	}

	/**
	 * Create an instance of {@link ReceiveFileJYZTResponse }
	 * 
	 */
	public ReceiveFileJYZTResponse createReceiveFileJYZTResponse() {
		return new ReceiveFileJYZTResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveFileJYZT }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", name = "ReceiveFile_JYZT")
	public JAXBElement<ReceiveFileJYZT> createReceiveFileJYZT(ReceiveFileJYZT value) {
		return new JAXBElement<ReceiveFileJYZT>(_ReceiveFileJYZT_QNAME, ReceiveFileJYZT.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ReceiveFileJYZTResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", name = "ReceiveFile_JYZTResponse")
	public JAXBElement<ReceiveFileJYZTResponse> createReceiveFileJYZTResponse(ReceiveFileJYZTResponse value) {
		return new JAXBElement<ReceiveFileJYZTResponse>(_ReceiveFileJYZTResponse_QNAME, ReceiveFileJYZTResponse.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link byte[]}{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "", name = "fileStream", scope = ReceiveFileJYZT.class)
	public JAXBElement<byte[]> createReceiveFileJYZTFileStream(byte[] value) {
		return new JAXBElement<byte[]>(_ReceiveFileJYZTFileStream_QNAME, byte[].class, ReceiveFileJYZT.class, ((byte[]) value));
	}

}
