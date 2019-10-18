package que_me_pongo.webApp.controllers;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.base.Optional;
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
	
	public String show(Request req, Response res) {
		Usuario usuario = req.session().attribute("usuario"); 
		if(usuario == null)
		{
			res.redirect("/login");
			return null;
		}
		String stringId = req.params("id");
		Long id = Long.valueOf(stringId);
		Optional<Evento> talVezEvento = RepositorioEventos.getInstance().getEvento(id);
		if(!talVezEvento.isPresent()) {
			res.status(404);
			return null;
		}
		Evento evento = talVezEvento.get();
		if(usuario.getId() != evento.getUsuario().getId())
		{
			res.status(401);
			return null;
		}
		
		Map<String, Object> mapa = new HashMap();
		mapa.put("titulo", evento.getDescripcion());
		mapa.put("linkSugerencias", req.url() + "/sugerencias");
		mapa.put("sugirio", evento.getSugirio());
		mapa.put("sinSugerencias", evento.sugerenciasVacias());
		
		
		ModelAndView modelAndView = new ModelAndView(mapa, "Evento.hbs");
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String entradasCalendario(Request req, Response res) {
		Usuario usuario = req.session().attribute("usuario");
		if(usuario == null)
		{
			res.status(401);
			return "";
		}
		
		String startString = req.queryParams("start"),
					 endString = req.queryParams("end");
		
		LocalDateTime.parse(startString.substring(0, 19));
		
		Set<Evento> eventos = RepositorioEventos.getInstance().eventosDeUsuario(usuario, LocalDateTime.parse(startString.substring(0, 19)), LocalDateTime.parse(endString.substring(0, 19)));
		
		List<EntradaCalendario> list = eventos.stream()
																	 .map(evento -> new EntradaCalendario(evento))
																	 .collect(Collectors.toList());
		
		res.type("application/json");
		return new Gson().toJson(list);
	}
}
