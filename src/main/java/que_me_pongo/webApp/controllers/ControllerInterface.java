package que_me_pongo.webApp.controllers;

import que_me_pongo.usuario.Usuario;
import spark.Response;

public interface ControllerInterface {
	default boolean requireLogin(Usuario user, String url, Response res) {
		if(user == null) {
			res.redirect("/login?redirect_to=" + url);
			return false;
		}
		return true;
	}
}
