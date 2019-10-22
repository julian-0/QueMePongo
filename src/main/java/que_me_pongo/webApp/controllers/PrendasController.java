package que_me_pongo.webApp.controllers;

import que_me_pongo.prenda.Material;
import que_me_pongo.prenda.PrendaBuilder;
import que_me_pongo.prenda.TipoDePrendaFactory;
import que_me_pongo.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PrendasController implements ControllerInterface {

    public String show (Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if(!requireLogin(usuario, req.uri(), res))
            return null;

        Map<String, Object> mapa = new HashMap();
        mapa.put("ruta",req.url());


        ModelAndView modelAndView = new ModelAndView(mapa,"wizardPrenda/PartialTipo.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    public String nueva (Request req, Response res) {
        Map<String, Object> mapa = new HashMap();
        Usuario usuario = req.session().attribute("usuario");
        if(req.session().attribute("prendaBuilder")==null)
            req.session().attribute("prendaBuilder",new PrendaBuilder());

        PrendaBuilder pb = req.session().attribute("prendaBuilder");

        String vista = construirPaso(pb,req);

        ModelAndView modelAndView = new ModelAndView(mapa,vista);
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    public String construirPaso(PrendaBuilder pb,Request req){
        String vista;
        if(req.queryParams("paso")==null)
            return "wizardPrenda/PartialTipo.hbs";

        switch (req.queryParams("paso")){
            case "tipo":
                try {
                    pb.buildTipo(TipoDePrendaFactory.parse(req.queryParams("tipo")));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                vista="wizardPrenda/PartialMaterial.hbs";
                break;
            case "material":
                pb.buildMaterial(Material.valueOf(req.queryParams("material")));
                vista="wizardPrenda/PartialColorPrimario.hbs";
                break;
            case "wizardPrenda/colorPrimario":
                pb.buildColorPrimario(Color.getColor(req.queryParams("colorPrimario")));
                vista="wizardPrenda/PartialColorSecundario.hbs";
                break;
            case "wizardPrenda/colorSecundario":
                pb.buildColorSecundario(Color.getColor(req.queryParams("colorSecundario")));
                vista="wizardPrenda/PartialImagen.hbs";
                break;
            case "imagen":
                pb.buildImagen(req.queryParams("pathImagen"));
                vista="wizardPrenda/PartialFinal.hbs";
                break;
            default:
                vista="wizardPrenda/PartialTipo.hbs";
        }
        return vista;
    }
}
