package com.lr.routes;

import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lr.entity.Utilisateur;
import com.lr.remote.IUtilisateurEJBRemote;

/**
 * Handler of routes /utilisateurs
 */
@Path("/utilisateurs")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UtilisateursRoute extends BasicRoute {

	@EJB
	private IUtilisateurEJBRemote utilisateurEJB;

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "FIND ALL");
		List<Utilisateur> utilisateurs = utilisateurEJB.findAll();

		ObjectMapper om = new ObjectMapper();
		if (utilisateurs != null) {
			try {
				String jsonUtilisateurs = om.writeValueAsString(utilisateurs);
				return Response.status(Response.Status.OK).entity(jsonUtilisateurs).build();
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		}
		return Response.status(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "FIND", id);
		Utilisateur utilisateur = utilisateurEJB.find(Integer.parseInt(id));
		ObjectMapper om = new ObjectMapper();
		if (utilisateur != null) {
			try {
				String jsonUser = om.writeValueAsString(utilisateur);
				return Response.status(Response.Status.OK).entity(jsonUser).build();
			} catch (Exception e) {
				return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
			}
		}
		return Response.status(Response.Status.NO_CONTENT).build();
	}
}
