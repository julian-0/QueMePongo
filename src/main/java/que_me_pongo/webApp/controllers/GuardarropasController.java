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
            String id = req.params("id");
            Optional<Guardarropa> optGuarda = RepositorioGuardarropas.getInstance().buscarPorId(Integer.parseInt(id));

            if(optGuarda.isPresent()){
                req.session().attribute("guardarropa",optGuarda.get());
                mapa.put("prendas",optGuarda.get().getPrendas());
                mapa.put("ruta",req.url());
            }
        }

        ModelAndView modelAndView = new ModelAndView(mapa, "ListarPrendas.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

}
