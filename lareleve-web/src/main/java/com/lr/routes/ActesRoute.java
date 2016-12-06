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

import com.lr.entity.Acte;
import com.lr.remote.IActeEJBRemote;

/**
 * Handler of routes /Actes
 */
@Path("/Actes")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActesRoute extends BasicRoute {
	@EJB
	private IActeEJBRemote acteEJB;

	@POST
	public Response createActe(Acte aActe) {
		LOGGER.logDebug(this, "<POST>", "acteEJB=[%s], acte=%s", (acteEJB != null ? "set" : "null"),
				aActe);

		try {
			// TODO ? verif des listes vides
			acteEJB.create(aActe);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response deleteActe(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "acteEJB=[%s], acte=%s",
				(acteEJB != null ? "set" : "null"), id);

		try {
			Acte acte = acteEJB.find(Integer.parseInt(id));
			acteEJB.remove(acte);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "acteEJB=[%s]", (acteEJB != null ? "set" : "null"));

		List<Acte> Actes = acteEJB.findAll();
		if (!Actes.isEmpty()) {
			return responseBuilder(Status.OK).entity(Actes).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "acteEJB=[%s], id=%s",
				(acteEJB != null ? "set" : "null"), id);

		Acte Acte = acteEJB.find(Integer.parseInt(id));
		if (Acte != null) {
			return responseBuilder(Response.Status.OK).entity(Acte).build();
		}

		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response updateUser(@PathParam("id") String id, Acte aActe) {
		LOGGER.logDebug(this, "<PUT>", "acteEJB=[%s], acte=%s", (acteEJB != null ? "set" : "null"),
				aActe);

		try {
			Acte acte = acteEJB.find(Integer.parseInt(id));
			acte.setLibelle(aActe.getLibelle());

			acteEJB.edit(acte);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}
}
