package que_me_pongo.webApp.controllers;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Optional;
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
            res.redirect("/login");
        }
        else{
            Set<Guardarropa> guardarropas = user.getGuardarropas();
            mapa.put("guardarropas", guardarropas);
            mapa.put("link", req.url());
        }

        ModelAndView modelAndView = new ModelAndView(mapa, "ListarGuardarropas.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    public String listarPrendas(Request req, Response res){
        Usuario user = req.session().attribute("usuario");
        Map<String, Object> mapa = new HashMap<String, Object>();

        if(user==null) {
            res.redirect("/login");
        }
        else{
            Optional<Guardarropa> optGuarda = RepositorioGuardarropas.getInstance().buscarPorId(req.queryParams("id"));
            if(optGuarda.isPresent())
                mapa.put("prendas",optGuarda.get().getPrendas());
            else
                mapa.put("resultado","No tiene prendas");
        }

        ModelAndView modelAndView = new ModelAndView(mapa, "ListarPrendas.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

}
