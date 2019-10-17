package que_me_pongo.webApp.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.gson.Gson;

import que_me_pongo.evento.Evento;
import que_me_pongo.evento.RepositorioEventos;
import que_me_pongo.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class EventosController {

	public String index(Request req, Response res) {
		if(req.session().attribute("usuario") == null)
			res.redirect("/login");
		ModelAndView modelAndView = new ModelAndView(new HashMap<String, Object>(), "Eventos.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String json(Request req, Response res) {
		Usuario usuario = req.session().attribute("usuario");
		if(usuario == null)
		{
			res.status(401);
			return "";
		}
		Set<Evento> eventos = RepositorioEventos.getInstance().eventosDeUsuario(usuario, null, null);
		
		List<EntradaCalendario> list = eventos.stream()
																	 .map(evento -> new EntradaCalendario(evento.getDescripcion(), evento.getFecha()))
																	 .collect(Collectors.toList());
		/*
		 * StringBuilder stringBuilder = new StringBuilder("["); List<String>
		 * eventosJson = eventos.stream().map(evento -> new
		 * StringBuilder().append("{title:"). append(evento.getDescripcion()).
		 * append(",start:"). append(evento.getFecha()). append("}").toString())
		 * .collect(Collectors.toList()); stringBuilder.append(String.join(",",
		 * eventosJson)); stringBuilder.append("]");
		 */
		res.type("application/json");
		return new Gson().toJson(list);
	}
}
