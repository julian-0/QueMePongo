package que_me_pongo.webApp.controllers;

import java.util.HashMap;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class EventosController {

	public String index(Request req, Response res) {
		//if(req.session().attribute("usuario") == null)
		//	res.redirect("/menu");
		ModelAndView modelAndView = new ModelAndView(new HashMap<String, Object>(), "Eventos.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
}
