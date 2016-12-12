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

import com.lr.entity.RessourcesIndividus;
import com.lr.remote.IRessourcesIndividusEJBRemote;

/**
 * Handler of routes /ressourcesindividus
 */
@Path("/ressourcesindividus")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class RessourcesIndividusRoute extends BasicRoute {

	@EJB
	private IRessourcesIndividusEJBRemote ressourceindividuEJB;

	@POST
	public Response create(RessourcesIndividus aressourceindividu) {
		LOGGER.logDebug(this, "<POST>", "ressourceindividuEJB=[%s], ressourceindividu=%s",(ressourceindividuEJB != null ? "set" : "null"), aressourceindividu);
		try {
			ressourceindividuEJB.create(aressourceindividu);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll(@Context UriInfo info) {
		LOGGER.logDebug(this, "<GET />", "ressourceindividuEJB=[%s]",(ressourceindividuEJB != null ? "set" : "null"));
		String idIndividu = info.getQueryParameters().getFirst("idindividu");
		String idRessource = info.getQueryParameters().getFirst("idressource");
		if(idIndividu!=null){
			return findByIdIndividu(idIndividu);
		}else if(idRessource!=null){
			return findByIdRessource(idRessource);
		}

		List<RessourcesIndividus> ressourceindividus = ressourceindividuEJB.findAll();
		if (!ressourceindividus.isEmpty()) {
			return responseBuilder(Status.OK).entity(ressourceindividus).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdIndividu(String id) {
		LOGGER.logDebug(this, "<GET /{:idindividu}>", "ressourceindividuEJB=[%s], idindividu=%s",(ressourceindividuEJB != null ? "set" : "null"), id);
		List<RessourcesIndividus> ressourcesindividus = null;
		try{
			int idindividu = Integer.parseInt(id);
			ressourcesindividus = ressourceindividuEJB.findByIdIndividu(idindividu);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (ressourcesindividus != null) {
			return responseBuilder(Response.Status.OK).entity(ressourcesindividus).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdRessource(String id) {
		LOGGER.logDebug(this, "<GET /{:idlangue}>", "ressourceindividuEJB=[%s], idlangue=%s",(ressourceindividuEJB != null ? "set" : "null"), id);
		List<RessourcesIndividus> ressourcesindividus = null;
		try{
			int idressource = Integer.parseInt(id);
			ressourcesindividus = ressourceindividuEJB.findByIdRessource(idressource);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (ressourcesindividus != null) {
			return responseBuilder(Response.Status.OK).entity(ressourcesindividus).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}


}
