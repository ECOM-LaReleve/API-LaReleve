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

import com.lr.entity.Pole;
import com.lr.remote.IPoleEJBRemote;

/**
 * Handler of routes /actes
 */
@Path("/poles")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class PolesRoute extends BasicRoute {

	@EJB
	private IPoleEJBRemote poleEJB;

	@POST
	public Response create(Pole aPole) {
		LOGGER.logDebug(this, "<POST>", "poleEJB=[%s], pole=%s",(poleEJB != null ? "set" : "null"), aPole);
		try {
			int newid = poleEJB.create(aPole);
			aPole.setId(newid);
			return responseBuilder(Response.Status.CREATED).entity(aPole).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "poleEJB=[%s], pole=%s",(poleEJB != null ? "set" : "null"), id);
		try {
			Pole pole = poleEJB.find(Integer.parseInt(id));
			poleEJB.remove(pole);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "poleEJB=[%s]",(poleEJB != null ? "set" : "null"));
		List<Pole> poles = poleEJB.findAll();
		if (!poles.isEmpty()) {
			return responseBuilder(Status.OK).entity(poles).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "poleEJB=[%s], id=%s",(poleEJB != null ? "set" : "null"), id);

		Pole pole = poleEJB.find(Integer.parseInt(id));
		if (pole != null) {
			return responseBuilder(Response.Status.OK).entity(pole).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response update(@PathParam("id") String id, Pole aPole) {
		LOGGER.logDebug(this, "<PUT>", "polesEJB=[%s], poles=%s",(poleEJB != null ? "set" : "null"), aPole);
		try {
			Pole pole = poleEJB.find(Integer.parseInt(id));
			pole.setLibelle(aPole.getLibelle());
			poleEJB.edit(pole);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

}
