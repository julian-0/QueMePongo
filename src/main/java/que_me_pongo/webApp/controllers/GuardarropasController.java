package que_me_pongo.webApp.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.guardarropa.RepositorioGuardarropas;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;
public class GuardarropasController {

    public String show(Request req, Response res) {
        String user = req.queryParams("user");
        Set<Guardarropa> guardarropas = RepositorioGuardarropas.getInstance().buscarPorUsuario(user);
        Map<String, Object> mapa = new HashMap<String, Object>();

        final int[] index = {1};
        guardarropas.forEach(g -> { mapa.put("guardarropa"+index[0], "Soy el guardarropa con id:"+g.getId());
                                    index[0]++;});

        ModelAndView modelAndView = new ModelAndView(mapa, "ListarGuardarropas.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
    }



}
