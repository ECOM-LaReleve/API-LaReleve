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

import com.lr.entity.ActesRealises;
import com.lr.remote.IActesRealisesEJBRemote;

/**
 * Handler of routes /actesrealises
 */
@Path("/actesrealises")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class ActesRealisesRoute extends BasicRoute {

	@EJB
	private IActesRealisesEJBRemote acteEJB;

	@POST
	public Response create(ActesRealises aActe) {
		LOGGER.logDebug(this, "<POST>", "actesrealisesEJB=[%s], acteationsRealisee=%s", (acteEJB != null ? "set" : "null"), aActe);

		try {
			acteEJB.create(aActe);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "actesrealisesEJB=[%s], acteationsRealisee=%s", (acteEJB != null ? "set" : "null"), id);

		try {
			ActesRealises acte = acteEJB.find(Integer.parseInt(id));
			acteEJB.remove(acte);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll(@Context UriInfo info) {
		LOGGER.logDebug(this, "<GET />", "actesrealisesEJB=[%s]", (acteEJB != null ? "set" : "null"));
		String idmenage = info.getQueryParameters().getFirst("idmenage");
		String idindividu = info.getQueryParameters().getFirst("idindividu");
		String idutilisateur = info.getQueryParameters().getFirst("idutilisateur");
		String idbesoin = info.getQueryParameters().getFirst("idbesoin");
		String idprestation = info.getQueryParameters().getFirst("idprestation");
		String idacte = info.getQueryParameters().getFirst("idacte");
		if(idmenage!=null){
			return findByIdMenage(idmenage);
		}else if(idindividu!=null){
			return findByIdIndividu(idindividu);
		}else if(idutilisateur!=null) {
			return findByIdUtilisateur(idutilisateur);
		}else if(idbesoin!=null) {
			return findByIdBesoin(idbesoin);
		}else if(idprestation!=null) {
			return findByIdPrestation(idprestation);
		}else if(idacte!=null) {
			return findByIdActe(idacte);
		}else {
			List<ActesRealises> acteations = acteEJB.findAll();
			if (!acteations.isEmpty()) {
				return responseBuilder(Status.OK).entity(acteations).build();
			}
			return responseBuilder(Response.Status.NO_CONTENT).build();
		}

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "actesrealisesEJB=[%s], id=%s",
				(acteEJB != null ? "set" : "null"), id);

		ActesRealises acte = acteEJB.find(Integer.parseInt(id));
		if (acte != null) {
			return responseBuilder(Response.Status.OK).entity(acte).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdActe(String id) {
		LOGGER.logDebug(this, "<GET /{:idacte}>", "menageEJB=[%s], idacte=%s",(acteEJB != null ? "set" : "null"), id);
		List<ActesRealises> acte = null;
		try{
			int idacte = Integer.parseInt(id);
			acte = acteEJB.findByIdActe(idacte);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (acte != null) {
			return responseBuilder(Response.Status.OK).entity(acte).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdBesoin(String id) {
		LOGGER.logDebug(this, "<GET /{:idbesoin}>", "menageEJB=[%s], idbesoin=%s",(acteEJB != null ? "set" : "null"), id);
		List<ActesRealises> acte = null;
		try{
			int idbesoin = Integer.parseInt(id);
			acte = acteEJB.findByIdBesoin(idbesoin);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (acte != null) {
			return responseBuilder(Response.Status.OK).entity(acte).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdIndividu(String id) {
		LOGGER.logDebug(this, "<GET /{:idindividu}>", "menageEJB=[%s], idindividu=%s",(acteEJB != null ? "set" : "null"), id);
		List<ActesRealises> acte = null;
		try{
			int idindividu = Integer.parseInt(id);
			acte = acteEJB.findByIdIndividu(idindividu);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (acte != null) {
			return responseBuilder(Response.Status.OK).entity(acte).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdMenage(String id) {
		LOGGER.logDebug(this, "<GET /{:idmenage}>", "menageEJB=[%s], idmenage=%s",(acteEJB != null ? "set" : "null"), id);
		List<ActesRealises> acte = null;
		try{
			int idmenage = Integer.parseInt(id);
			acte = acteEJB.findByIdMenage(idmenage);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (acte != null) {
			return responseBuilder(Response.Status.OK).entity(acte).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdPrestation(String id) {
		LOGGER.logDebug(this, "<GET /{:idprestation}>", "menageEJB=[%s], idprestation=%s",(acteEJB != null ? "set" : "null"), id);
		List<ActesRealises> acte = null;
		try{
			int idprestation = Integer.parseInt(id);
			acte = acteEJB.findByIdPrestation(idprestation);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (acte != null) {
			return responseBuilder(Response.Status.OK).entity(acte).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdUtilisateur(String id) {
		LOGGER.logDebug(this, "<GET /{:idutilisateur}>", "menageEJB=[%s], idutilisateur=%s",(acteEJB != null ? "set" : "null"), id);
		List<ActesRealises> acte = null;
		try{
			int idutilisateur = Integer.parseInt(id);
			acte = acteEJB.findByIdUtilisateur(idutilisateur);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (acte != null) {
			return responseBuilder(Response.Status.OK).entity(acte).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response update(@PathParam("id") String id, ActesRealises aActe) {
		LOGGER.logDebug(this, "<PUT>", "actesrealisesEJB=[%s], actesrealises=%s",(acteEJB != null ? "set" : "null"), aActe);
		try {
			ActesRealises acte = acteEJB.find(Integer.parseInt(id));
			acte.setActe(aActe.getActe());
			acte.setBesoin(aActe.getBesoin());
			acte.setPrestationRealisee(aActe.getPrestationRealisee());
			acte.setIndividu(aActe.getIndividu());
			acte.setMenage(aActe.getMenage());
			acte.setStatut(aActe.getStatut());
			acte.setUtilisateur(aActe.getUtilisateur());
			acte.setCommentaire(aActe.getCommentaire());
			acte.setDateRealisation(aActe.getDateRealisation());
			acteEJB.edit(acte);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}
}
