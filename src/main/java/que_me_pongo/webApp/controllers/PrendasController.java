package que_me_pongo.webApp.controllers;

import que_me_pongo.prenda.Material;
import que_me_pongo.prenda.PrendaBuilder;
import que_me_pongo.prenda.TipoDePrenda;
import que_me_pongo.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PrendasController {

    public String show (Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if(usuario==null)
            res.redirect("/login");

        Map<String, Object> mapa = new HashMap();
        mapa.put("ruta",req.url());

        ModelAndView modelAndView = new ModelAndView(mapa,"WizardPrendas.hbs");
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
        switch (req.queryParams("paso")){
            case "tipo":
             //   pb.buildTipo(TipoDePrenda.parse(req.queryParams("tipo")));
                vista="PartialMaterial.hbs";
                break;
            case "material":
                pb.buildMaterial(Material.valueOf(req.queryParams("material")));
                vista="PartialColorPrimario.hbs";
                break;
            case "colorPrimario":
                pb.buildColorPrimario(Color.getColor(req.queryParams("colorPrimario")));
                vista="PartialColorSecundario.hbs";
                break;
            case "colorSecundario":
                pb.buildColorSecundario(Color.getColor(req.queryParams("colorSecundario")));
                vista="PartialImagen.hbs";
                break;
            case "imagen":
                pb.buildImagen(req.queryParams("pathImagen"));
                vista="PartialFinal.hbs";
                break;
            default:
                vista="PartialTipo.hbs";
        }
        return vista;
    }
}
