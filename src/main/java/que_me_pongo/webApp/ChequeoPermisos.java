package que_me_pongo.webApp;

import que_me_pongo.usuario.Usuario;
import spark.Request;
import spark.Response;
import spark.Spark;

public class ChequeoPermisos {
	static public void configurar() {
		Spark.before("/eventos", ChequeoPermisos::chequearLogin);
		Spark.before("/evento/*", ChequeoPermisos::chequearLogin);
		Spark.before("/guardarropas", ChequeoPermisos::chequearLogin);
		Spark.before("/guardarropas/*", ChequeoPermisos::chequearLogin);
	}
	
	static private void chequearLogin(Request req, Response res) {
		Usuario user = req.session().attribute("usuario");
		if(user == null) {
			res.redirect("/login?redirect_to=" + req.uri());
			Spark.halt();
		}
	}
}
