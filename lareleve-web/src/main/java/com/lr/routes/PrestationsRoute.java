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

import com.lr.entity.Prestation;
import com.lr.remote.IPrestationEJBRemote;

/**
 * Handler of routes /prestations
 */
@Path("/prestations")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PrestationsRoute extends BasicRoute {

	@EJB
	private IPrestationEJBRemote prestationEJB;

	@POST
	public Response create(Prestation aPrestation) {
		LOGGER.logDebug(this, "<POST>", "prestationEJB=[%s], prestation=%s",(prestationEJB != null ? "set" : "null"), aPrestation);
		try {
			prestationEJB.create(aPrestation);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "prestationEJB=[%s], prestation=%s",(prestationEJB != null ? "set" : "null"), id);
		try {
			Prestation prestation = prestationEJB.find(Integer.parseInt(id));
			prestationEJB.remove(prestation);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "prestationEJB=[%s]",(prestationEJB != null ? "set" : "null"));
		List<Prestation> prestations = prestationEJB.findAll();
		if (!prestations.isEmpty()) {
			return responseBuilder(Status.OK).entity(prestations).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("/besoin{id : \\d+}") // id must be digits
	public Response findByBesoinId(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET BY BESOIN /{:id}>", "prestationEJB=[%s], id=%s",(prestationEJB != null ? "set" : "null"), id);
		List<Prestation> prestation = prestationEJB.findByBesoinId(Integer.parseInt(id));
		if (prestation != null) {
			return responseBuilder(Response.Status.OK).entity(prestation).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "prestationEJB=[%s], id=%s",(prestationEJB != null ? "set" : "null"), id);
		Prestation prestation = prestationEJB.find(Integer.parseInt(id));
		if (prestation != null) {
			return responseBuilder(Response.Status.OK).entity(prestation).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}


}
