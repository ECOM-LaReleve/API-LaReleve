package com.lr.routes;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.UriInfo;

import com.lr.entity.RessourcesMenages;
import com.lr.remote.IRessourcesMenagesEJBRemote;

/**
 * Handler of routes /ressourcesmenages
 */
@Path("/ressourcesmenages")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class RessourcesMenagesRoute extends BasicRoute {

	@EJB
	private IRessourcesMenagesEJBRemote ressourcemenageEJB;

	@POST
	public Response create(RessourcesMenages aressourcemenage) {
		LOGGER.logDebug(this, "<POST>", "ressourcemenageEJB=[%s], ressourcemenage=%s",(ressourcemenageEJB != null ? "set" : "null"), aressourcemenage);
		try {
			ressourcemenageEJB.create(aressourcemenage);
			return responseBuilder(Response.Status.CREATED).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll(@Context UriInfo info) {
		LOGGER.logDebug(this, "<GET />", "ressourcemenageEJB=[%s]",(ressourcemenageEJB != null ? "set" : "null"));
		String idMenage = info.getQueryParameters().getFirst("idmenage");
		String idRessource = info.getQueryParameters().getFirst("idressource");
		if(idMenage!=null){
			return findByIdMenage(idMenage);
		}else if(idRessource!=null){
			return findByIdRessource(idRessource);
		}

		List<RessourcesMenages> ressourcemenages = ressourcemenageEJB.findAll();
		if (!ressourcemenages.isEmpty()) {
			return responseBuilder(Status.OK).entity(ressourcemenages).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdMenage(String id) {
		LOGGER.logDebug(this, "<GET /{:idindividu}>", "ressourcemenageEJB=[%s], idindividu=%s",(ressourcemenageEJB != null ? "set" : "null"), id);
		List<RessourcesMenages> ressourcesmenages = null;
		try{
			int idmenage = Integer.parseInt(id);
			ressourcesmenages = ressourcemenageEJB.findByIdMenage(idmenage);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (ressourcesmenages != null) {
			return responseBuilder(Response.Status.OK).entity(ressourcesmenages).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdRessource(String id) {
		LOGGER.logDebug(this, "<GET /{:idlangue}>", "ressourcemenageEJB=[%s], idlangue=%s",(ressourcemenageEJB != null ? "set" : "null"), id);
		List<RessourcesMenages> ressourcesmenages = null;
		try{
			int idressource = Integer.parseInt(id);
			ressourcesmenages = ressourcemenageEJB.findByIdRessource(idressource);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (ressourcesmenages != null) {
			return responseBuilder(Response.Status.OK).entity(ressourcesmenages).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}


}
