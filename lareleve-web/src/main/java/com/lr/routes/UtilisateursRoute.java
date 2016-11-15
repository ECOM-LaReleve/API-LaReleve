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
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

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

	@POST
	public Response createUser(Utilisateur aUtilisateur) {
		LOGGER.logDebug(this, "<POST>", "utilisateurEJB=[%s], utilisateur=%s",
				(utilisateurEJB != null ? "set" : "null"), aUtilisateur);

		try {
			utilisateurEJB.create(aUtilisateur);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@DELETE
	@Path("{id : \\d+}") // id must be digits
	public Response deleteUser(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<DELETE>", "utilisateurEJB=[%s], utilisateur=%s",
				(utilisateurEJB != null ? "set" : "null"), id);

		try {
			Utilisateur utilisateur = utilisateurEJB.find(Integer.parseInt(id));
			utilisateurEJB.remove(utilisateur);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<DELETE>", "Bad Request");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}

	@GET
	public Response findAll() {
		LOGGER.logDebug(this, "<GET />", "utilisateurEJB=[%s]",
				(utilisateurEJB != null ? "set" : "null"));

		List<Utilisateur> utilisateurs = utilisateurEJB.findAll();
		if (!utilisateurs.isEmpty()) {
			return getResponseBuilder(Status.OK).entity(utilisateurs).build();
		}
		return Response.status(Response.Status.NO_CONTENT).build();

	}

	@GET
	@Path("{id : \\d+}") // id must be digits
	public Response findById(@PathParam("id") String id) {
		LOGGER.logDebug(this, "<GET /{:id}>", "utilisateurEJB=[%s], id=%s",
				(utilisateurEJB != null ? "set" : "null"), id);

		Utilisateur utilisateur = utilisateurEJB.find(Integer.parseInt(id));
		if (utilisateur != null) {
			return Response.status(Response.Status.OK).entity(utilisateur).build();
		}

		return Response.status(Response.Status.NO_CONTENT).build();
	}

	@PUT
	@Path("{id : \\d+}") // id must be digits
	public Response updateUser(@PathParam("id") String id, Utilisateur aUtilisateur) {
		LOGGER.logDebug(this, "<PUT>", "utilisateurEJB=[%s], utilisateur=%s",
				(utilisateurEJB != null ? "set" : "null"), aUtilisateur);

		try {
			Utilisateur utilisateur = utilisateurEJB.find(Integer.parseInt(id));
			utilisateur.setNom(aUtilisateur.getNom());
			utilisateur.setPassword(aUtilisateur.getPassword());
			utilisateur.setPrenom(aUtilisateur.getPrenom());
			aUtilisateur.setUsername(aUtilisateur.getUsername());

			utilisateurEJB.edit(utilisateur);
			return Response.status(Response.Status.OK).build();
		} catch (Exception e) {
			LOGGER.logDebug(this, "<POST>", "Bad Request");
			return Response.status(Response.Status.BAD_REQUEST).build();
		}
	}
}
