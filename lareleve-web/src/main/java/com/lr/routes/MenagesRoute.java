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

import com.lr.entity.Menage;
import com.lr.remote.IMenageEJBRemote;

/**
 * Handler of routes /menages
 */
@Path("/menages")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class MenagesRoute extends BasicRoute {

	@EJB
	private IMenageEJBRemote menageEJB;

	@POST
	public Response create(Menage aMenage) {
		LOGGER.logDebug(this, "<POST>", "MenageEJB=[%s], menage=%s", (menageEJB != null ? "set" : "null"), aMenage);
		try {
			menageEJB.create(aMenage);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "menageEJB=[%s], menage=%s", (menageEJB != null ? "set" : "null"), id);
		try {
			Menage menage = menageEJB.find(Integer.parseInt(id));
			menageEJB.remove(menage);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll(@Context UriInfo info) {
		LOGGER.logDebug(this, "<GET />", "menageEJB=[%s]", (menageEJB != null ? "set" : "null"));
		String id = info.getQueryParameters().getFirst("idreferant");
		if(id!=null){
			return findByIdReferant(id);
		}else{
			List<Menage> menages = menageEJB.findAll();
			if (!menages.isEmpty()) {
				return responseBuilder(Status.OK).entity(menages).build();
			}
			return responseBuilder(Response.Status.NO_CONTENT).build();
		}

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "menageEJB=[%s], id=%s",(menageEJB != null ? "set" : "null"), id);
		Menage menage = menageEJB.find(Integer.parseInt(id));
		if (menage != null) {
			return responseBuilder(Response.Status.OK).entity(menage).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}

	private Response findByIdReferant(String id) {
		LOGGER.logDebug(this, "<GET /{:idreferant}>", "menageEJB=[%s], idreferant=%s",(menageEJB != null ? "set" : "null"), id);
		List<Menage> menage = null;
		try{
			int idref = Integer.parseInt(id);
			menage = menageEJB.findByIdReferant(idref);
		}catch(NumberFormatException e){
			responseBuilder(Response.Status.BAD_REQUEST).build();
		}
		if (menage != null) {
			return responseBuilder(Response.Status.OK).entity(menage).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}


	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response update(@PathParam("id") String id, Menage aMenage) {
		LOGGER.logDebug(this, "<PUT>", "menageEJB=[%s], menage=%s",(menageEJB != null ? "set" : "null"), aMenage);

		try {
			Menage menage = menageEJB.find(Integer.parseInt(id));
			//menage.setChefMenage(aMenage.getChefMenage());
			menage.setAdresseActuelle(aMenage.getAdresseActuelle());
			menage.setAdresseSortie(aMenage.getAdresseSortie());
			menage.setDateEntree(aMenage.getDateEntree());
			menage.setDateSortie(aMenage.getDateSortie());
			menage.setLogement(aMenage.getLogement());
			menage.setReferant(aMenage.getReferant());
			menage.setNomChefMenage(aMenage.getNomChefMenage());
			menageEJB.edit(menage);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}
}
