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

import com.lr.entity.Individu;
import com.lr.remote.IIndividuEJBRemote;

/**
 * Handler of routes /individus
 */
@Path("/individus")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IndividuRoute extends BasicRoute {
	@EJB
	private IIndividuEJBRemote individuEJB;

	@POST
	public Response createIndividu(Individu aIndividu) {
		LOGGER.logDebug(this, "<POST>", "individuEJB=[%s], individu=%s",
				(individuEJB != null ? "set" : "null"), aIndividu);

		try {
			// TODO ? verif des listes vides
			individuEJB.create(aIndividu);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response deleteIndividu(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "individuEJB=[%s], individu=%s",
				(individuEJB != null ? "set" : "null"), id);

		try {
			Individu individu = individuEJB.find(Integer.parseInt(id));
			individuEJB.remove(individu);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "individuEJB=[%s]",
				(individuEJB != null ? "set" : "null"));

		List<Individu> individus = individuEJB.findAll();
		if (!individus.isEmpty()) {
			return responseBuilder(Status.OK).entity(individus).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "individuEJB=[%s], id=%s",
				(individuEJB != null ? "set" : "null"), id);

		Individu individu = individuEJB.find(Integer.parseInt(id));
		if (individu != null) {
			return responseBuilder(Response.Status.OK).entity(individu).build();
		}

		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response updateUser(@PathParam("id") String id, Individu aIndividu) {
		LOGGER.logDebug(this, "<PUT>", "individuEJB=[%s], individu=%s",
				(individuEJB != null ? "set" : "null"), aIndividu);

		try {
			Individu individu = individuEJB.find(Integer.parseInt(id));
			individu.setNomUsage(aIndividu.getNomUsage());
			individu.setMenage(aIndividu.getMenage());
			individu.setNomUsage(aIndividu.getNomUsage());
			individu.setPrenom(aIndividu.getPrenom());
			individu.setTel(aIndividu.getTel());
			individu.setDateEntreeFr(aIndividu.getDateEntreeFr());
			individu.setStatutFr(aIndividu.getStatutFr());
			individu.setVilleNaissance(aIndividu.getVilleNaissance());
			individu.setLanguesIndividus(aIndividu.getLanguesIndividus());

			individuEJB.edit(individu);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}
}
