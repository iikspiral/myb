package com.sxca.myb.common.client.StructuredService;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * This object contains factory methods for each Java content interface and Java
 * element interface generated in the
 * com.sxca.ggzyzt.common.client.StructuredService package.
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

	private final static QName _ReceiveInfoJYZT_QNAME = new QName("http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", "ReceiveInfo_JYZT");
	private final static QName _ReceiveInfoJYZTResponse_QNAME = new QName("http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", "ReceiveInfo_JYZTResponse");

	/**
	 * Create a new ObjectFactory that can be used to create new instances of
	 * schema derived classes for package:
	 * com.sxca.ggzyzt.common.client.StructuredService
	 * 
	 */
	public ObjectFactory() {
	}

	/**
	 * Create an instance of {@link ReceiveInfoJYZT }
	 * 
	 */
	public ReceiveInfoJYZT createReceiveInfoJYZT() {
		return new ReceiveInfoJYZT();
	}

	/**
	 * Create an instance of {@link ReceiveInfoJYZTResponse }
	 * 
	 */
	public ReceiveInfoJYZTResponse createReceiveInfoJYZTResponse() {
		return new ReceiveInfoJYZTResponse();
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}{@link ReceiveInfoJYZT }
	 * {@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", name = "ReceiveInfo_JYZT")
	public JAXBElement<ReceiveInfoJYZT> createReceiveInfoJYZT(ReceiveInfoJYZT value) {
		return new JAXBElement<ReceiveInfoJYZT>(_ReceiveInfoJYZT_QNAME, ReceiveInfoJYZT.class, null, value);
	}

	/**
	 * Create an instance of {@link JAXBElement }{@code <}
	 * {@link ReceiveInfoJYZTResponse }{@code >}
	 * 
	 */
	@XmlElementDecl(namespace = "http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", name = "ReceiveInfo_JYZTResponse")
	public JAXBElement<ReceiveInfoJYZTResponse> createReceiveInfoJYZTResponse(ReceiveInfoJYZTResponse value) {
		return new JAXBElement<ReceiveInfoJYZTResponse>(_ReceiveInfoJYZTResponse_QNAME, ReceiveInfoJYZTResponse.class, null, value);
	}

}
