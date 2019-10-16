package que_me_pongo.webApp.controllers;

import java.util.HashMap;
import java.util.Map;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;
public class ListarGuardarropasController {

    public String show(Request req, Response res) {


        Map<String, Object> mapa = new HashMap<String, Object>();
        mapa.put("guardarropa1", "Soy el guardarropa1");
        mapa.put("guardarropa2", "Soy el guardarropa2");
        ModelAndView modelAndView = new ModelAndView(mapa, "ListarGuardarropas.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
    }



}
