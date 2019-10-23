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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import java.awt.Color;

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

        Map<String, Object> mapa = setValues(req, paso);
        mapa.put("ruta",req.url());
        mapa.put("paso", paso);
        
        ModelAndView modelAndView = new ModelAndView(mapa, getFileName(paso));
        return new HandlebarsTemplateEngine().render(modelAndView);
    }

    public String create (Request req, Response res) {
        Usuario usuario = req.session().attribute("usuario");
        
        String paso = req.queryParams("paso");
        List<String> errores = construirPaso(req, res);
        if(errores.isEmpty()) {
        	String siguiente = getSiguientePaso("paso");
        	String redirect_to = siguiente == null? "/guardarropas/" + req.params("id") : req.url() + "?paso=" + siguiente; 
          res.redirect(redirect_to);
          return null;
        }
        else
        {
        	Map<String, Object> mapa = setValues(req, paso);
          mapa.put("ruta",req.url());
          mapa.put("paso", paso);
          mapa.put("errores", errores);
          
          ModelAndView modelAndView = new ModelAndView(mapa, getFileName(paso));
          return new HandlebarsTemplateEngine().render(modelAndView);
        }
        
    }
    
    private Map<String, Object> setValues(Request req, String paso) {
    	Map<String, Object> mapa = new HashMap();
    	switch (paso){
      case "Tipo":
      		mapa.put("tipos", Tipo.values());
          break;
      case "Material":
      		PrendaBuilder builder = req.session().attribute("builder");
          mapa.put("validos", builder.getTipo().getMaterialesValidos());
          break;
      case "ColorPrimario":
          break;
      case "ColorSecundario":
          break;
      case "Imagen":
          break;
    	}
    	return mapa;
    }
    
    private String getSiguientePaso(String paso) {
    	switch (paso){
      case "Tipo":
      		return "Material";
      case "Material":
      		return "ColorPrimario";
      case "ColorPrimario":
      		return "ColorSecundario";
      case "ColorSecundario":
      		return "Imagen";
      case "Imagen":
      		return null;
      default:
      	return null;
    	}
    }

    private List<String> construirPaso(Request req, Response res) {
    	PrendaBuilder pb = req.session().attribute("builder");
        switch (req.queryParams("paso")){
            case "Tipo":
            		req.session().attribute("builder", new PrendaBuilder());
                try {
                    pb.setTipo(TipoDePrendaFactory.parse(req.queryParams("tipo")));
                } catch (NoSuchMethodException e) {
                	return Arrays.asList("No es un tipo de prenda valido");
                }
                break;
            case "Material":
                pb.setMaterial(Material.valueOf(req.queryParams("material")));
                break;
            case "ColorPrimario":
                pb.setColorPrimario(Color.decode(req.queryParams("colorPrimario")));
                break;
            case "ColorSecundario":
            		Color secundario = Color.decode(req.queryParams("colorSecundario"));
            		if(pb.getColorPrimario().equals(secundario))
            			return Arrays.asList("Los colores no pueden ser iguales");
                pb.setColorSecundario(secundario);
                break;
            case "Imagen":
                //TODO Rellenar
                break;
        }
        return Arrays.asList();
    }
    
    private String getFileName(String paso) {
    	return "wizardPrenda/" + paso + ".hbs";
    }
}
