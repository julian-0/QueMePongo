package que_me_pongo.webApp.controllers;

import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.prenda.Material;
import que_me_pongo.prenda.PrendaBuilder;
import que_me_pongo.prenda.Tipo;
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

        mapa.put("tipo",Tipo.values());
        ModelAndView modelAndView = new ModelAndView(mapa,"wizardPrenda/PartialTipo.hbs");
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    public String nueva (Request req, Response res) {
        Map<String, Object> mapa = new HashMap();
        Usuario usuario = req.session().attribute("usuario");
        if(req.session().attribute("prendaBuilder")==null)
            req.session().attribute("prendaBuilder",new PrendaBuilder());

        PrendaBuilder pb = req.session().attribute("prendaBuilder");

        String vista = construirPaso(pb,req,mapa);

        ModelAndView modelAndView = new ModelAndView(mapa,vista);
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    public String construirPaso(PrendaBuilder pb,Request req,Map<String, Object> mapa){
        String vista;
        if(req.queryParams("paso")==null) {
            mapa.put("tipo", Tipo.values());
            return "wizardPrenda/PartialTipo.hbs";
        }
        switch (req.queryParams("paso")){
            case "tipo":
                try {
                    pb.buildTipo(TipoDePrendaFactory.parse(req.queryParams("tipo")));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                mapa.put("validos",pb.getPrenda().getTipoPrenda().getMaterialesValidos());
                vista="wizardPrenda/PartialMaterial.hbs";
                break;
            case "material":
                pb.buildMaterial(Material.valueOf(req.queryParams("material")));
                vista="wizardPrenda/PartialColorPrimario.hbs";
                break;
            case "colorPrimario":
                pb.buildColorPrimario(Color.decode(req.queryParams("colorPrimario")));
                vista="wizardPrenda/PartialColorSecundario.hbs";
                break;
            case "colorSecundario":
                pb.buildColorSecundario(Color.decode(req.queryParams("colorSecundario")));
                vista="wizardPrenda/PartialImagen.hbs";
                break;
            case "imagen":
                pb.buildImagen(req.queryParams("pathImagen"));
                Guardarropa guarda = req.session().attribute("guardarropa");
                guarda.agregarPrenda(pb.buildPrenda());
                vista="wizardPrenda/PartialFinal.hbs";
                break;
            default:
                mapa.put("tipo",Tipo.values());
                vista="wizardPrenda/PartialTipo.hbs";
        }
        return vista;
    }
}
