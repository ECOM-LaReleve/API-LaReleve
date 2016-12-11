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

import com.lr.entity.Individu;
import com.lr.entity.Menage;
import com.lr.remote.IIndividuEJBRemote;

/**
 * Handler of routes /individus
 */
@Path("/individus")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class IndividusRoute extends BasicRoute {

	@EJB
	private IIndividuEJBRemote individuEJB;

	@POST
	public Response create(Individu aIndividu) {
		LOGGER.logDebug(this, "<POST>", "IndividuEJB=[%s], individu=%s", (individuEJB != null ? "set" : "null"), aIndividu);
		try {
			individuEJB.create(aIndividu);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "individuEJB=[%s], individu=%s", (individuEJB != null ? "set" : "null"), id);
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
	public Response findAll(@Context UriInfo info) {
		LOGGER.logDebug(this, "<GET />", "individuEJB=[%s]", (individuEJB != null ? "set" : "null"));
		String id = info.getQueryParameters().getFirst("idmenage");
		String name = info.getQueryParameters().getFirst("menage");
		if(id!=null){
			return findIndividusByIdMenage(id);
		}else if(name!=null){
			return findMenageByNameIndividu(name);
		}else {
			List<Individu> individus = individuEJB.findAll();
			if (!individus.isEmpty()) {
				return responseBuilder(Status.OK).entity(individus).build();
			}else{
				return responseBuilder(Response.Status.NO_CONTENT).build();
			}
		}

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "individuEJB=[%s], id=%s",(individuEJB != null ? "set" : "null"), id);
		Individu individu = individuEJB.find(Integer.parseInt(id));
		if (individu != null) {
			return responseBuilder(Response.Status.OK).entity(individu).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findIndividusByIdMenage(String id) {
		LOGGER.logDebug(this, "<GET /{:name}>", "individuEJB=[%s], id=%s",(individuEJB != null ? "set" : "null"), id);
		List<Individu> individus = null;
		try{
			int idMenage = Integer.parseInt(id);
			individus = individuEJB.findIndividusByIdMenage(idMenage);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (individus != null) {
			return responseBuilder(Response.Status.OK).entity(individus).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findMenageByNameIndividu(String name) {
		LOGGER.logDebug(this, "<GET /{:name}>", "individuEJB=[%s], name=%s",(individuEJB != null ? "set" : "null"), name);
		List<Menage> menage = individuEJB.findMenageByNameIndividu(name);
		if (menage != null) {
			return responseBuilder(Response.Status.OK).entity(menage).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response updateIndividu(@PathParam("id") String id, Individu aIndividu) {
		LOGGER.logDebug(this, "<PUT>", "individuEJB=[%s], individu=%s",(individuEJB != null ? "set" : "null"), aIndividu);
		try {
			Individu individu = individuEJB.find(Integer.parseInt(id));
			individu.setNomNaissance(aIndividu.getNomNaissance());
			individu.setNomUsage(aIndividu.getNomUsage());
			individu.setPrenom(aIndividu.getPrenom());
			individu.setDateEntreeFr(aIndividu.getDateEntreeFr());
			individu.setVilleNaissance(aIndividu.getVilleNaissance());
			individu.setTel(aIndividu.getTel());
			individu.setStatutFr(aIndividu.getStatutFr());
			individu.setStatutMatrimonial(aIndividu.getStatutMatrimonial());
			individuEJB.edit(individu);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

}
