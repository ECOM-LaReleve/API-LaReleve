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
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.lr.entity.PrestationsRealisees;
import com.lr.remote.IPrestationsRealiseesEJBRemote;

/**
 * Handler of routes /prestationsRealisees
 */
@Path("/prestationsrealisees")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class PrestationsRealiseesRoute extends BasicRoute {

	@EJB
	private IPrestationsRealiseesEJBRemote prestationEJB;

	@POST
	public Response create(PrestationsRealisees aPrest) {
		LOGGER.logDebug(this, "<POST>", "prestationsRealiseesEJB=[%s], prestationsRealisee=%s", (prestationEJB != null ? "set" : "null"), aPrest);

		try {
			prestationEJB.create(aPrest);
			return responseBuilder(Response.Status.CREATED).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
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
	public Response findAll(@Context UriInfo info) {
		LOGGER.logDebug(this, "<GET />", "prestationsRealiseesEJB=[%s]", (prestationEJB != null ? "set" : "null"));
		String idmenage = info.getQueryParameters().getFirst("idmenage");
		String idindividu = info.getQueryParameters().getFirst("idindividu");
		String idutilisateur = info.getQueryParameters().getFirst("idutilisateur");
		String idprestation = info.getQueryParameters().getFirst("idprestation");
		if(idmenage!=null){
			return findByIdMenage(idmenage);
		}else if(idindividu!=null){
			return findByIdIndividu(idindividu);
		}else if(idutilisateur!=null) {
			return findByIdUtilisateur(idutilisateur);
		}else if(idprestation!=null){
			return findByIdPrestation(idprestation);
		}else {
			List<PrestationsRealisees> prestations = prestationEJB.findAll();
			if (!prestations.isEmpty()) {
				return responseBuilder(Status.OK).entity(prestations).build();
			}
			return responseBuilder(Response.Status.NO_CONTENT).build();
		}

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

	private Response findByIdIndividu(String id) {
		LOGGER.logDebug(this, "<GET /{:idindividu}>", "menageEJB=[%s], idindividu=%s",(prestationEJB != null ? "set" : "null"), id);
		List<PrestationsRealisees> prest = null;
		try{
			int idindividu = Integer.parseInt(id);
			prest = prestationEJB.findByIdIndividu(idindividu);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (prest != null) {
			return responseBuilder(Response.Status.OK).entity(prest).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdMenage(String id) {
		LOGGER.logDebug(this, "<GET /{:idmenage}>", "menageEJB=[%s], idmenage=%s",(prestationEJB != null ? "set" : "null"), id);
		List<PrestationsRealisees> prest = null;
		try{
			int idmenage = Integer.parseInt(id);
			prest = prestationEJB.findByIdMenage(idmenage);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (prest != null) {
			return responseBuilder(Response.Status.OK).entity(prest).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdPrestation(String id) {
		LOGGER.logDebug(this, "<GET /{:idprestation}>", "menageEJB=[%s], idprestation=%s",(prestationEJB != null ? "set" : "null"), id);
		List<PrestationsRealisees> prest = null;
		try{
			int idprestation = Integer.parseInt(id);
			prest = prestationEJB.findByIdPrestation(idprestation);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (prest != null) {
			return responseBuilder(Response.Status.OK).entity(prest).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdUtilisateur(String id) {
		LOGGER.logDebug(this, "<GET /{:idutilisateur}>", "menageEJB=[%s], idutilisateur=%s",(prestationEJB != null ? "set" : "null"), id);
		List<PrestationsRealisees> prest = null;
		try{
			int idutilisateur = Integer.parseInt(id);
			prest = prestationEJB.findByIdUtilisateur(idutilisateur);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (prest != null) {
			return responseBuilder(Response.Status.OK).entity(prest).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response update(@PathParam("id") String id, PrestationsRealisees aPrest) {
		LOGGER.logDebug(this, "<PUT>", "prestationsRealiseesEJB=[%s], prestationsRealisees=%s",(prestationEJB != null ? "set" : "null"), aPrest);

		try {
			PrestationsRealisees prest = prestationEJB.find(Integer.parseInt(id));
			prest.setDateCreation(aPrest.getDateCreation());
			prest.setDateFin(aPrest.getDateFin());
			prest.setIndividu(aPrest.getIndividu());
			prest.setMenage(aPrest.getMenage());
			prest.setStatut(aPrest.getStatut());
			prest.setUtilisateur(aPrest.getUtilisateur());
			prest.setCommentaire(aPrest.getCommentaire());
			prest.setPrestation(aPrest.getPrestation());
			prestationEJB.edit(prest);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

}
