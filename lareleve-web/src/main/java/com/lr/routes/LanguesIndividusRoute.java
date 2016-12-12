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

import com.lr.entity.LanguesIndividus;
import com.lr.remote.ILanguesIndividusEJBRemote;

/**
 * Handler of routes /languesindividus
 */
@Path("/languesindividus")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class LanguesIndividusRoute extends BasicRoute {

	@EJB
	private ILanguesIndividusEJBRemote langueindividuEJB;

	@POST
	public Response create(LanguesIndividus alangueindividu) {
		LOGGER.logDebug(this, "<POST>", "langueindividuEJB=[%s], langueindividu=%s",(langueindividuEJB != null ? "set" : "null"), alangueindividu);
		try {
			langueindividuEJB.create(alangueindividu);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll(@Context UriInfo info) {
		LOGGER.logDebug(this, "<GET />", "langueindividuEJB=[%s]",(langueindividuEJB != null ? "set" : "null"));
		String idIndividu = info.getQueryParameters().getFirst("idindividu");
		String idLangue = info.getQueryParameters().getFirst("idlangue");
		if(idIndividu!=null){
			return findByIdIndividu(idIndividu);
		}else if(idLangue!=null){
			return findByIdLangue(idLangue);
		}

		List<LanguesIndividus> langueindividus = langueindividuEJB.findAll();
		if (!langueindividus.isEmpty()) {
			return responseBuilder(Status.OK).entity(langueindividus).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdIndividu(String id) {
		LOGGER.logDebug(this, "<GET /{:idindividu}>", "langueindividuEJB=[%s], idindividu=%s",(langueindividuEJB != null ? "set" : "null"), id);
		List<LanguesIndividus> languesindividus = null;
		try{
			int idindividu = Integer.parseInt(id);
			languesindividus = langueindividuEJB.findByIdIndividu(idindividu);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (languesindividus != null) {
			return responseBuilder(Response.Status.OK).entity(languesindividus).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdLangue(String id) {
		LOGGER.logDebug(this, "<GET /{:idlangue}>", "languelangueEJB=[%s], idlangue=%s",(langueindividuEJB != null ? "set" : "null"), id);
		List<LanguesIndividus> languesindividus = null;
		try{
			int idlangue = Integer.parseInt(id);
			languesindividus = langueindividuEJB.findByIdLangue(idlangue);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (languesindividus != null) {
			return responseBuilder(Response.Status.OK).entity(languesindividus).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}


}
