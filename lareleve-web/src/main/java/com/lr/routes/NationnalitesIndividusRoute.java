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

import com.lr.entity.NationnalitesIndividus;
import com.lr.remote.INationnalitesIndividusEJBRemote;

/**
 * Handler of routes /nationnalitesindividus
 */
@Path("/nationnalitesindividus")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class NationnalitesIndividusRoute extends BasicRoute {

	@EJB
	private INationnalitesIndividusEJBRemote nationnalitesindividusEJB;

	@POST
	public Response create(NationnalitesIndividus anationnalitesindividus) {
		LOGGER.logDebug(this, "<POST>", "nationnalitesindividusEJB=[%s], nationnalitesindividus=%s",(nationnalitesindividusEJB != null ? "set" : "null"), anationnalitesindividus);
		try {
			nationnalitesindividusEJB.create(anationnalitesindividus);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll(@Context UriInfo info) {
		LOGGER.logDebug(this, "<GET />", "nationnalitesindividusEJB=[%s]",(nationnalitesindividusEJB != null ? "set" : "null"));
		String idIndividu = info.getQueryParameters().getFirst("idindividu");
		String idNationnalite = info.getQueryParameters().getFirst("idnationnalite");
		if(idIndividu!=null){
			return findByIdIndividu(idIndividu);
		}else if(idNationnalite!=null){
			return findByIdNationnalite(idNationnalite);
		}

		List<NationnalitesIndividus> nationnalitesindividuss = nationnalitesindividusEJB.findAll();
		if (!nationnalitesindividuss.isEmpty()) {
			return responseBuilder(Status.OK).entity(nationnalitesindividuss).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdIndividu(String id) {
		LOGGER.logDebug(this, "<GET /{:idindividu}>", "nationnalitesindividusEJB=[%s], idindividu=%s",(nationnalitesindividusEJB != null ? "set" : "null"), id);
		List<NationnalitesIndividus> nationnalitesindividus = null;
		try{
			int idindividu = Integer.parseInt(id);
			nationnalitesindividus = nationnalitesindividusEJB.findByIdIndividu(idindividu);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (nationnalitesindividus != null) {
			return responseBuilder(Response.Status.OK).entity(nationnalitesindividus).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdNationnalite(String id) {
		LOGGER.logDebug(this, "<GET /{:idlangue}>", "nationnalitesindividusEJB=[%s], idNationnalite=%s",(nationnalitesindividusEJB != null ? "set" : "null"), id);
		List<NationnalitesIndividus> nationnalitesindividus = null;
		try{
			int idNationnalite = Integer.parseInt(id);
			nationnalitesindividus = nationnalitesindividusEJB.findByIdNationnalite(idNationnalite);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (nationnalitesindividus != null) {
			return responseBuilder(Response.Status.OK).entity(nationnalitesindividus).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}


}

