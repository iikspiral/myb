package com.sxca.myb.common.client.UnStructuredService;

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
 * WebServiceFileImplService service = new WebServiceFileImplService();
 * WebServiceFileImpl portType = service.getWebServiceFileImplPort();
 * portType.receiveFileJYZT(...);
 * </pre>
 * 
 * </p>
 * 
 */
@WebServiceClient(name = "WebServiceFileImplService", targetNamespace = "http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", wsdlLocation = "http://218.26.178.26:6280/Services/UnS/UnStructuredService?wsdl")
public class WebServiceFileImplService extends Service {

	private final static URL WEBSERVICEFILEIMPLSERVICE_WSDL_LOCATION;
	private final static Logger logger = Logger.getLogger(com.sxca.myb.common.client.UnStructuredService.WebServiceFileImplService.class.getName());

	static {
		URL url = null;
		try {
			URL baseUrl;
			baseUrl = com.sxca.myb.common.client.UnStructuredService.WebServiceFileImplService.class.getResource(".");
			url = new URL(baseUrl, "http://218.26.178.26:6280/Services/UnS/UnStructuredService?wsdl");
		} catch (MalformedURLException e) {
			logger.warning("Failed to create URL for the wsdl Location: 'http://218.26.178.26:6280/Services/UnS/UnStructuredService?wsdl', retrying as a local file");
			logger.warning(e.getMessage());
		}
		WEBSERVICEFILEIMPLSERVICE_WSDL_LOCATION = url;
	}

	public WebServiceFileImplService(URL wsdlLocation, QName serviceName) {
		super(wsdlLocation, serviceName);
	}

	public WebServiceFileImplService() {
		super(WEBSERVICEFILEIMPLSERVICE_WSDL_LOCATION, new QName("http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", "WebServiceFileImplService"));
	}

	/**
	 * 
	 * @return returns WebServiceFileImpl
	 */
	@WebEndpoint(name = "WebServiceFileImplPort")
	public WebServiceFileImpl getWebServiceFileImplPort() {
		return super.getPort(new QName("http://impl.webservice.ztk.modules.ggzyzt.sxca.com/", "WebServiceFileImplPort"), WebServiceFileImpl.class);
	}

}
