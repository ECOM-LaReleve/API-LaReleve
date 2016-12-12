package com.lr.routes;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lr.entity.Besoin;
import com.lr.remote.IBesoinEJBRemote;

/**
 * Handler of routes /besoins
 */
@Path("/besoins")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class BesoinsRoute extends BasicRoute {

	@EJB
	private IBesoinEJBRemote besoinEJB;

	@POST
	public Response create(Besoin aBesoin) {
		LOGGER.logDebug(this, "<POST>", "besoinEJB=[%s], besoin=%s",(besoinEJB != null ? "set" : "null"), aBesoin);
		try {
			besoinEJB.create(aBesoin);
			return responseBuilder(Response.Status.CREATED).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "besoinEJB=[%s], besoin=%s",(besoinEJB != null ? "set" : "null"), id);
		try {
			Besoin besoin = besoinEJB.find(Integer.parseInt(id));
			besoinEJB.remove(besoin);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "besoinEJB=[%s]",(besoinEJB != null ? "set" : "null"));
		List<Besoin> besoins = besoinEJB.findAll();
		if (!besoins.isEmpty()) {
			return responseBuilder(Status.OK).entity(besoins).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "besoinEJB=[%s], id=%s",(besoinEJB != null ? "set" : "null"), id);
		Besoin besoin = besoinEJB.find(Integer.parseInt(id));
		if (besoin != null) {
			return responseBuilder(Response.Status.OK).entity(besoin).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response update(@PathParam("id") String id, Besoin aBesoin) {
		LOGGER.logDebug(this, "<PUT>", "besoinsEJB=[%s], besoins=%s",(besoinEJB != null ? "set" : "null"), aBesoin);
		try {
			Besoin besoin = besoinEJB.find(Integer.parseInt(id));
			besoin.setLibelle(aBesoin.getLibelle());
			besoinEJB.edit(besoin);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}


}
