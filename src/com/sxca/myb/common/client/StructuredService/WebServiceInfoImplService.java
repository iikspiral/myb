package com.sxca.myb.common.client.StructuredService;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;

/**
 * This class was generated by the JAX-WS RI. JAX-WS RI 2.1.3-hudson-390-
 * Generated source version: 2.0
 * <p>
 * An example of how this class may be used:
 * 
 * <pre>
 * WebServiceInfoImplService service = new WebServiceInfoImplService();
 * WebServiceInfoImpl portType = service.getWebServiceInfoImplPort();
 * portType.receiveInfoJYZT(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "WebServiceInfoImplService", targetNamespace = "http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", wsdlLocation = "http://218.26.178.26:6280/Services/S/StructuredService?wsdl")
public class WebServiceInfoImplService extends Service {

	private final static URL WEBSERVICEINFOIMPLSERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger.getLogger(com.sxca.myb.common.client.StructuredService.WebServiceInfoImplService.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.sxca.myb.common.client.StructuredService.WebServiceInfoImplService.class.getResource(".");
			url = new URL(baseUrl, "http://218.26.178.26:6280/Services/S/StructuredService?wsdl");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'http://218.26.178.26:6280/Services/S/StructuredService?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		WEBSERVICEINFOIMPLSERVICE_WSDL_LOCATION = url;
	}

	public WebServiceInfoImplService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public WebServiceInfoImplService() {
		super(WEBSERVICEINFOIMPLSERVICE_WSDL_LOCATION, new QName("http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", "WebServiceInfoImplService"));
	}

	/**
	 * 
	 * @return returns WebServiceInfoImpl
	 */
	@WebEndpoint(name = "WebServiceInfoImplPort")
	public WebServiceInfoImpl getWebServiceInfoImplPort() {
		return super.getPort(new QName("http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", "WebServiceInfoImplPort"), WebServiceInfoImpl.class);
	}

}
