package que_me_pongo.webApp.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.guardarropa.RepositorioGuardarropas;
import que_me_pongo.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

public class GuardarropasController {

    public String show(Request req, Response res) {
        Usuario user = req.session().attribute("usuario");
        Map<String, Object> mapa = new HashMap<String, Object>();

        if(user==null) {
            mapa.put("resultado", "Primero inicie sesión");
            res.redirect("/login");
        }
        else{
            Set<Guardarropa> guardarropas = user.getGuardarropas();
            mapa.put("guardarropas", guardarropas);
        }

        ModelAndView modelAndView = new ModelAndView(mapa, "ListarGuardarropas.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
    }



}
