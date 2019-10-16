package que_me_pongo.webApp.controllers;
import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import que_me_pongo.usuario.RepositorioUsuarios;
import que_me_pongo.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class LogInController {
	public String show(Request req, Response res) {
		if(req.session().attribute("usuario") != null)
			res.redirect("/menu");
		ModelAndView modelAndView = new ModelAndView(new HashMap<String, Object>(), "Login.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String create(Request req, Response res) {
		String nombreOMail = req.queryParams("usuario");
		String password = req.queryParams("password");
		
		//Aca seria mejor tener un solo query que tenga el o adentro
		Optional<Usuario> optUser = RepositorioUsuarios.getInstance().buscarPorNombre(nombreOMail);
		optUser = optUser.or(RepositorioUsuarios.getInstance().buscarPorMail(nombreOMail));
		Optional<Boolean> optPassValida = optUser.transform(usuario -> usuario.chequearPassword(password));
		
		Map<String, Object> mapa = new HashMap<String, Object>();
		if(optPassValida.isPresent() && optPassValida.get())
		{
			req.session().attribute("usuario", optUser.get());
			res.redirect("/menu");
		}
		else
		{
			mapa.put("error", "Usuario o contraseña invalidos");
		}
		ModelAndView modelAndView = new ModelAndView(mapa, "Login.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
}
