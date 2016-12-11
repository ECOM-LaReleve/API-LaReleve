package com.lr.routes;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lr.entity.Service;
import com.lr.remote.IServiceEJBRemote;

/**
 * Handler of routes /actes
 */
@Path("/services")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class ServicesRoute extends BasicRoute {

	@EJB
	private IServiceEJBRemote serviceEJB;

	@POST
	public Response create(Service aService) {
		LOGGER.logDebug(this, "<POST>", "serviceEJB=[%s], service=%s",(serviceEJB != null ? "set" : "null"), aService);
		try {
			serviceEJB.create(aService);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "serviceEJB=[%s], service=%s",(serviceEJB != null ? "set" : "null"), id);
		try {
			Service service = serviceEJB.find(Integer.parseInt(id));
			serviceEJB.remove(service);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "serviceEJB=[%s]",(serviceEJB != null ? "set" : "null"));
		List<Service> services = serviceEJB.findAll();
		if (!services.isEmpty()) {
			return responseBuilder(Status.OK).entity(services).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "serviceEJB=[%s], id=%s",(serviceEJB != null ? "set" : "null"), id);

		Service service = serviceEJB.find(Integer.parseInt(id));
		if (service != null) {
			return responseBuilder(Response.Status.OK).entity(service).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

}
