package org.ritvik.cxf;

import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.log4j.Logger;

public class CxfRestClient {
	
	private Logger logger = Logger.getLogger(CxfRestClient.class);

	@GET
	@Produces(value = MediaType.TEXT_PLAIN)
	public Response process() {
		logger.info("In CxfRestClient");
		
		return Response.ok("Client OK").build();
	}
}
