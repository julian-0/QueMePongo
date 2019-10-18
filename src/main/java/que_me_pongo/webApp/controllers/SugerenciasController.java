package que_me_pongo.webApp.controllers;

import java.util.HashMap;
import java.util.Map;

import com.google.common.base.Optional;

import que_me_pongo.atuendo.Atuendo;
import que_me_pongo.evento.Evento;
import que_me_pongo.evento.RepositorioEventos;
import que_me_pongo.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class SugerenciasController {
	public String show(Request req, Response res) {
		Usuario usuario = req.session().attribute("usuario");
		if(req.session().attribute("usuario") == null)
			res.redirect("/login");
		
		String stringId = req.params("id");
		Long id = Long.valueOf(stringId);
		Optional<Evento> talVezEvento = RepositorioEventos.getInstance().getEvento(id);
		if(!talVezEvento.isPresent()) {
			res.status(404);
			return null;
		}
		Evento evento = talVezEvento.get();
		
		Map<String, Object> mapa = new HashMap();
		ModelAndView modelAndView;
		if(evento.sugerenciaAceptada()) {
			Atuendo aceptado = evento.getAceptado();
			mapa.put("prendas", aceptado.getPrendas());
			modelAndView = new ModelAndView(mapa, "SugerenciaAceptada.hbs");
		}
		//Este if podria pasarse a la logica del modelo y que se haga solo cuando se generan las sugerencias.
		else if(evento.getSugerenciasPendientes().size() == 1) {
			Atuendo proximo = evento.getProximaSugerenciaPendiente();
			evento.aceptarSugerencia();
			mapa.put("automatico", true);
			mapa.put("prendas", proximo.getPrendas());
			modelAndView = new ModelAndView(mapa, "SugerenciaAceptada.hbs");
		}
		else {
			Atuendo proximo = evento.getProximaSugerenciaPendiente();
			mapa.put("prendas", proximo.getPrendas());
			modelAndView = new ModelAndView(mapa, "Sugerencias.hbs");
		}
		
		return new HandlebarsTemplateEngine().render(modelAndView);
	}
	
	public String modificar(Request req, Response res) {
		String stringId = req.params("id");
		Long id = Long.valueOf(stringId);
		Optional<Evento> talVezEvento = RepositorioEventos.getInstance().getEvento(id);
		if(!talVezEvento.isPresent()) {
			res.status(404);
			return null;
		}
		Evento evento = talVezEvento.get();
		
		String rechazoString = req.queryParams("rechazo"); 
		boolean rechazo = req.queryParams("rechazo") != null;
		boolean acepto = req.queryParams("acepto") != null;
		Map<String, Object> mapa = new HashMap();
		ModelAndView modelAndView = null;
		
		if(rechazo) {
			evento.rechazarSugerencia();
			if(evento.getSugerenciasPendientes().size() == 1) {
				Atuendo proximo = evento.getProximaSugerenciaPendiente();
				evento.aceptarSugerencia();
				mapa.put("automatico", true);
				mapa.put("prendas", proximo.getPrendas());
				modelAndView = new ModelAndView(mapa, "SugerenciaAceptada.hbs");
			}
			else {
				Atuendo proximo = evento.getProximaSugerenciaPendiente();
				mapa.put("prendas", proximo.getPrendas());
				modelAndView = new ModelAndView(mapa, "Sugerencias.hbs");
			}
		}
		else if(acepto) {
			evento.aceptarSugerencia();
			Atuendo aceptado = evento.getAceptado();
			mapa.put("prendas", aceptado.getPrendas());
			modelAndView = new ModelAndView(mapa, "SugerenciaAceptada.hbs");
		}
		return new HandlebarsTemplateEngine().render(modelAndView);
	}

}
