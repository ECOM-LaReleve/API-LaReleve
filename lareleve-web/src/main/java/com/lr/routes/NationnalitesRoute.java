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

import com.lr.entity.Nationnalite;
import com.lr.remote.INationnaliteEJBRemote;

/**
 * Handler of routes /nationnalites
 */
@Path("/nationnalites")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class NationnalitesRoute extends BasicRoute {

	@EJB
	private INationnaliteEJBRemote nationnaliteEJB;

	@POST
	public Response create(Nationnalite aNationnalite) {
		LOGGER.logDebug(this, "<POST>", "nationnaliteEJB=[%s], nationnalite=%s",(nationnaliteEJB != null ? "set" : "null"), aNationnalite);
		try {
			nationnaliteEJB.create(aNationnalite);
			return responseBuilder(Response.Status.CREATED).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "nationnaliteEJB=[%s], nationnalite=%s",(nationnaliteEJB != null ? "set" : "null"), id);
		try {
			Nationnalite nationnalite = nationnaliteEJB.find(Integer.parseInt(id));
			nationnaliteEJB.remove(nationnalite);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "nationnaliteEJB=[%s]",(nationnaliteEJB != null ? "set" : "null"));
		List<Nationnalite> nationnalites = nationnaliteEJB.findAll();
		if (!nationnalites.isEmpty()) {
			return responseBuilder(Status.OK).entity(nationnalites).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "nationnaliteEJB=[%s], id=%s",(nationnaliteEJB != null ? "set" : "null"), id);

		Nationnalite nationnalite = nationnaliteEJB.find(Integer.parseInt(id));
		if (nationnalite != null) {
			return responseBuilder(Response.Status.OK).entity(nationnalite).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}



}

