package que_me_pongo.webApp.controllers;

import com.google.common.base.Optional;
import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.guardarropa.RepositorioGuardarropas;
import que_me_pongo.prenda.Material;
import que_me_pongo.prenda.PrendaBuilder;
import que_me_pongo.prenda.Tipo;
import que_me_pongo.prenda.TipoDePrendaFactory;
import que_me_pongo.usuario.RepositorioUsuarios;
import que_me_pongo.usuario.Usuario;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.template.handlebars.HandlebarsTemplateEngine;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;

public class PrendasController implements ControllerInterface {

    public String nuevo (Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        if(!requireLogin(usuario, req.uri(), res))
            return null;
        
        int id;
        try {
        	id = Integer.parseInt(req.params("id"));
        }
        catch(NumberFormatException e) {
        	res.status(404);
        	return null;
        }
        
        Optional<Guardarropa> optGuarda = RepositorioGuardarropas.getInstance().buscarPorId(id);
        if(!optGuarda.isPresent()) {
        	res.status(404);
        	return null;
        }
        
        Optional<Usuario> optDuenio = RepositorioUsuarios.getInstance().buscarPorGuardarropa(optGuarda.get().getId());
        
        if(!requireAccess(usuario, optDuenio.get(), res))
        	return null;
        
        String paso = req.queryParams("paso") == null ? "tipo" : req.queryParams("paso"); 

        Map<String, Object> mapa = new HashMap();
        mapa.put("ruta",req.url());
        mapa.put("tipos",Tipo.values());
        
        ModelAndView modelAndView = new ModelAndView(mapa, getFileName(paso));
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    public String create (Request req, Response res) {
        Map<String, Object> mapa = new HashMap();
        Usuario usuario = req.session().attribute("usuario");
        
        String vista = construirPaso(req,mapa);

        ModelAndView modelAndView = new ModelAndView(mapa,vista);
        return new HandlebarsTemplateEngine().render(modelAndView);
    }
    
    private Map<String, Object> setValues(Request req, String paso) {
    	Map<String, Object> mapa = new HashMap();
    	switch (paso){
      case "Tipo":
      		mapa.put("tipos", Tipo.values());
          break;
      case "Material":
      		PrendaBuilder builder = req.session().attribute("builder");
          mapa.put("validos", )
          break;
      case "ColorPrimario":
          pb.buildColorPrimario(Color.decode(req.queryParams("colorPrimario")));
          vista="wizardPrenda/PartialColorSecundario.hbs";
          break;
      case "ColorSecundario":
          pb.buildColorSecundario(Color.decode(req.queryParams("colorSecundario")));
          vista="wizardPrenda/PartialImagen.hbs";
          break;
      case "Imagen":
          pb.buildImagen(req.queryParams("pathImagen"));
          String id = req.params("id");
          Optional<Guardarropa> optGuarda = RepositorioGuardarropas.getInstance().buscarPorId(Integer.parseInt(id));
          optGuarda.get().agregarPrenda(pb.buildPrenda());
          vista="wizardPrenda/PartialFinal.hbs";
          break;
      default:
          mapa.put("tipo",Tipo.values());
          vista="wizardPrenda/PartialTipo.hbs";
  }
    }

    public String construirPaso(Request req, Map<String, Object> mapa){
        String vista;
        
        switch (req.queryParams("paso")){
            case "Tipo":
            		mapa.put("tipo", Tipo.values());
              	return "wizardPrenda/PartialTipo.hbs";
                try {
                	
                    pb.buildTipo(TipoDePrendaFactory.parse(req.queryParams("tipo")));
                } catch (NoSuchMethodException e) {
                    e.printStackTrace();
                }
                mapa.put("validos",pb.getPrenda().getTipoPrenda().getMaterialesValidos());
                vista="wizardPrenda/PartialMaterial.hbs";
                break;
            case "Material":
                pb.buildMaterial(Material.valueOf(req.queryParams("material")));
                vista="wizardPrenda/PartialColorPrimario.hbs";
                break;
            case "ColorPrimario":
                pb.buildColorPrimario(Color.decode(req.queryParams("colorPrimario")));
                vista="wizardPrenda/PartialColorSecundario.hbs";
                break;
            case "ColorSecundario":
                pb.buildColorSecundario(Color.decode(req.queryParams("colorSecundario")));
                vista="wizardPrenda/PartialImagen.hbs";
                break;
            case "Imagen":
                pb.buildImagen(req.queryParams("pathImagen"));
                String id = req.params("id");
                Optional<Guardarropa> optGuarda = RepositorioGuardarropas.getInstance().buscarPorId(Integer.parseInt(id));
                optGuarda.get().agregarPrenda(pb.buildPrenda());
                vista="wizardPrenda/PartialFinal.hbs";
                break;
            default:
                mapa.put("tipo",Tipo.values());
                vista="wizardPrenda/PartialTipo.hbs";
        }
        return vista;
    }
    
    private String getFileName(String paso) {
    	return "wizardPrenda/" + paso + ".hbs";
    }
}
