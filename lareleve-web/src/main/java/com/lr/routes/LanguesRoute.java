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

import com.lr.entity.Langue;
import com.lr.remote.ILangueEJBRemote;

/**
 * Handler of routes /langues
 */
@Path("/langues")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class LanguesRoute extends BasicRoute {

	@EJB
	private ILangueEJBRemote langueEJB;

	@POST
	public Response create(Langue aLangue) {
		LOGGER.logDebug(this, "<POST>", "langueEJB=[%s], langue=%s",(langueEJB != null ? "set" : "null"), aLangue);
		try {
			langueEJB.create(aLangue);
			return responseBuilder(Response.Status.CREATED).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "langueEJB=[%s], langue=%s",(langueEJB != null ? "set" : "null"), id);
		try {
			Langue langue = langueEJB.find(Integer.parseInt(id));
			langueEJB.remove(langue);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "langueEJB=[%s]",(langueEJB != null ? "set" : "null"));
		List<Langue> langues = langueEJB.findAll();
		if (!langues.isEmpty()) {
			return responseBuilder(Status.OK).entity(langues).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "langueEJB=[%s], id=%s",(langueEJB != null ? "set" : "null"), id);

		Langue langue = langueEJB.find(Integer.parseInt(id));
		if (langue != null) {
			return responseBuilder(Response.Status.OK).entity(langue).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response update(@PathParam("id") String id, Langue aLangue) {
		LOGGER.logDebug(this, "<PUT>", "languesEJB=[%s], langues=%s",(langueEJB != null ? "set" : "null"), aLangue);
		try {
			Langue langue = langueEJB.find(Integer.parseInt(id));
			langue.setLibelle(aLangue.getLibelle());
			langueEJB.edit(langue);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}



}
