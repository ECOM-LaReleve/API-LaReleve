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

import com.lr.entity.Ressource;
import com.lr.remote.IRessourceEJBRemote;

/**
 * Handler of routes /ressources
 */
@Path("/ressources")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class RessourcesRoute extends BasicRoute {

	@EJB
	private IRessourceEJBRemote ressourceEJB;

	@POST
	public Response create(Ressource aRessource) {
		LOGGER.logDebug(this, "<POST>", "ressourceEJB=[%s], ressource=%s",(ressourceEJB != null ? "set" : "null"), aRessource);
		try {
			int newid = ressourceEJB.create(aRessource);
			aRessource.setId(newid);
			return responseBuilder(Response.Status.CREATED).entity(aRessource).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "ressourceEJB=[%s], ressource=%s",(ressourceEJB != null ? "set" : "null"), id);
		try {
			Ressource ressource = ressourceEJB.find(Integer.parseInt(id));
			ressourceEJB.remove(ressource);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "ressourceEJB=[%s]",(ressourceEJB != null ? "set" : "null"));
		List<Ressource> ressources = ressourceEJB.findAll();
		if (!ressources.isEmpty()) {
			return responseBuilder(Status.OK).entity(ressources).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "ressourceEJB=[%s], id=%s",(ressourceEJB != null ? "set" : "null"), id);

		Ressource ressource = ressourceEJB.find(Integer.parseInt(id));
		if (ressource != null) {
			return responseBuilder(Response.Status.OK).entity(ressource).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response update(@PathParam("id") String id, Ressource aRessource) {
		LOGGER.logDebug(this, "<PUT>", "ressourcesEJB=[%s], ressources=%s",(ressourceEJB != null ? "set" : "null"), aRessource);
		try {
			Ressource ressource = ressourceEJB.find(Integer.parseInt(id));
			ressource.setLibelle(aRessource.getLibelle());
			ressource.setType(aRessource.getType());
			ressourceEJB.edit(ressource);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}



}
