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

import com.lr.entity.PrestationsRealisees;
import com.lr.remote.IPrestationsRealiseesEJBRemote;

/**
 * Handler of routes /prestationsRealisees
 */
@Path("/prestationsRealisees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class PrestationsRealiseesRoute extends BasicRoute {

	@EJB
	private IPrestationsRealiseesEJBRemote prestationEJB;

	@POST
	public Response createPrestReal(PrestationsRealisees aPrest) {
		LOGGER.logDebug(this, "<POST>", "prestationsRealiseesEJB=[%s], prestationsRealisee=%s", (prestationEJB != null ? "set" : "null"), aPrest);

		try {
			prestationEJB.create(aPrest);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response deletePrestReal(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "prestationsRealiseesEJB=[%s], prestationsRealisee=%s", (prestationEJB != null ? "set" : "null"), id);

		try {
			PrestationsRealisees prest = prestationEJB.find(Integer.parseInt(id));
			prestationEJB.remove(prest);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "prestationsRealiseesEJB=[%s]", (prestationEJB != null ? "set" : "null"));

		List<PrestationsRealisees> prestations = prestationEJB.findAll();
		if (!prestations.isEmpty()) {
			return responseBuilder(Status.OK).entity(prestations).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "prestationsRealiseesEJB=[%s], id=%s",
				(prestationEJB != null ? "set" : "null"), id);

		PrestationsRealisees prest = prestationEJB.find(Integer.parseInt(id));
		if (prest != null) {
			return responseBuilder(Response.Status.OK).entity(prest).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response updatePrestReal(@PathParam("id") String id, PrestationsRealisees aPrest) {
		LOGGER.logDebug(this, "<PUT>", "prestationsRealiseesEJB=[%s], prestationsRealisees=%s",(prestationEJB != null ? "set" : "null"), aPrest);

		try {
			PrestationsRealisees prest = prestationEJB.find(Integer.parseInt(id));
			prest.setDateCreation(aPrest.getDateCreation());
			prest.setDateFin(aPrest.getDateFin());
			prest.setIndividu(aPrest.getIndividu());
			prest.setMenage(aPrest.getMenage());
			prest.setSeqPrestation(aPrest.getSeqPrestation());
			prest.setStatut(aPrest.getStatut());
			//prest.setUtilisateur(aPrest.getUtilisateur());
			prest.setCommentaire(aPrest.getCommentaire());
			prestationEJB.edit(prest);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

}
