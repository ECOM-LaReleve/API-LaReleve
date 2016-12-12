package com.lr.routes;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.lr.entity.Logement;
import com.lr.remote.ILogementEJBRemote;

/**
 * Handler of routes /logements
 */
@Path("/logements")
@Consumes(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
@Produces(MediaType.APPLICATION_JSON + BasicRoute.ENCODING)
public class LogementsRoute extends BasicRoute {

	@EJB
	private ILogementEJBRemote logementEJB;

	@POST
	public Response create(Logement aLogement) {
		LOGGER.logDebug(this, "<POST>", "logementEJB=[%s], logement=%s",(logementEJB != null ? "set" : "null"), aLogement);
		try {
			logementEJB.create(aLogement);
			return responseBuilder(Response.Status.CREATED).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response delete(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "logementEJB=[%s], logement=%s",(logementEJB != null ? "set" : "null"), id);
		try {
			Logement logement = logementEJB.find(Integer.parseInt(id));
			logementEJB.remove(logement);
			return responseBuilder(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return responseBuilder(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "logementEJB=[%s]",(logementEJB != null ? "set" : "null"));
		List<Logement> logements = logementEJB.findAll();
		if (!logements.isEmpty()) {
			return responseBuilder(Status.OK).entity(logements).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "logementEJB=[%s], id=%s",(logementEJB != null ? "set" : "null"), id);

		Logement logement = logementEJB.find(Integer.parseInt(id));
		if (logement != null) {
			return responseBuilder(Response.Status.OK).entity(logement).build();
		}
		return responseBuilder(Response.Status.NO_CONTENT).build();
	}



}
