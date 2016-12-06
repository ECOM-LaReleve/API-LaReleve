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

import com.lr.entity.ActesRealises;
import com.lr.remote.IActesRealisesEJBRemote;

/**
 * Handler of routes /actesRealises
 */
@Path("/actesRealises")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActesRealisesRoute extends BasicRoute {
	@EJB
	private IActesRealisesEJBRemote ActesRealisesEJB;

	@POST
	public Response createActeRealise(ActesRealises aActeRealise) {
		LOGGER.logDebug(this, "<POST>", "ActesRealisesEJB=[%s], ActeRealise=%s",
				(ActesRealisesEJB != null ? "set" : "null"), aActeRealise);

		try {
			// TODO ? verif des listes vides
			ActesRealisesEJB.create(aActeRealise);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response deleteActeRealise(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "ActesRealisesEJB=[%s], ActeRealise=%s",
				(ActesRealisesEJB != null ? "set" : "null"), id);

		try {
			ActesRealises acteRealise = ActesRealisesEJB.find(Integer.parseInt(id));
			ActesRealisesEJB.remove(acteRealise);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "ActesRealisesEJB=[%s]",
				(ActesRealisesEJB != null ? "set" : "null"));

		List<ActesRealises> acteRealises = ActesRealisesEJB.findAll();
		if (!acteRealises.isEmpty()) {
			return responseBuilder(Status.OK).entity(acteRealises).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "ActesRealisesEJB=[%s], id=%s",
				(ActesRealisesEJB != null ? "set" : "null"), id);

		ActesRealises acteRealise = ActesRealisesEJB.find(Integer.parseInt(id));
		if (acteRealise != null) {
			return responseBuilder(Response.Status.OK).entity(acteRealise).build();
		}

		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response updateUser(@PathParam("id") String id, ActesRealises aActeRealise) {
		LOGGER.logDebug(this, "<PUT>", "ActesRealisesEJB=[%s], ActeRealise=%s",
				(ActesRealisesEJB != null ? "set" : "null"), aActeRealise);

		try {
			ActesRealises acteRealise = ActesRealisesEJB.find(Integer.parseInt(id));
			acteRealise.setActe(aActeRealise.getActe());
			acteRealise.setBesoin(aActeRealise.getBesoin());
			acteRealise.setCommentaire(aActeRealise.getCommentaire());
			acteRealise.setDateRealisation(aActeRealise.getDateRealisation());
			acteRealise.setIndividu(aActeRealise.getIndividu());
			acteRealise.setMenage(aActeRealise.getMenage());
			acteRealise.setPrestationRealisee(aActeRealise.getPrestationRealisee());
			acteRealise.setSeqActe(aActeRealise.getSeqActe());
			acteRealise.setStatut(aActeRealise.getStatut());
			acteRealise.setUtilisateur(aActeRealise.getUtilisateur());

			ActesRealisesEJB.edit(acteRealise);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}
}
