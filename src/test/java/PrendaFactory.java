import que_me_pongo.*;
import que_me_pongo.prenda.Material;
import que_me_pongo.prenda.Prenda;
import que_me_pongo.prenda.TipoDePrenda;
import que_me_pongo.prenda.TipoDePrendaFactory;

import java.awt.Color;


public class PrendaFactory {
    
    static private Prenda crearPrenda(TipoDePrenda tipo, Material material, Color colorPrimario, Color colorSecundario) {
    	return new Prenda(tipo, material, colorPrimario, colorSecundario, null);	
    }
    
    static public Prenda anteojos(Material material, Color colorPrimario, Color colorSecundario) {
    	return crearPrenda(TipoDePrendaFactory.anteojos(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda remeraMangaCorta(Material material, Color colorPrimario, Color colorSecundario) {	
    	return crearPrenda(TipoDePrendaFactory.remeraMangaCorta(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda remeraMangaLarga(Material material, Color colorPrimario, Color colorSecundario) {	
    	return crearPrenda(TipoDePrendaFactory.remeraMangaLarga(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda buzo(Material material, Color colorPrimario, Color colorSecundario) {
    	return crearPrenda(TipoDePrendaFactory.buzo(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda shorts(Material material, Color colorPrimario, Color colorSecundario) {
    	return crearPrenda(TipoDePrendaFactory.shorts(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda pantalon(Material material, Color colorPrimario, Color colorSecundario) {
    	return crearPrenda(TipoDePrendaFactory.pantalon(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda zapatosDeTacon(Material material, Color colorPrimario, Color colorSecundario) {
    	return crearPrenda(TipoDePrendaFactory.zapatosDeTacon(), material, colorPrimario, colorSecundario);
    }

    static public Prenda chaleco(Material material, Color colorPrimario, Color colorSecundario) {
    	return crearPrenda(TipoDePrendaFactory.chaleco(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda guantes(Material material, Color colorPrimario, Color colorSecundario) {	
    	return crearPrenda(TipoDePrendaFactory.guantes(), material, colorPrimario, colorSecundario);
    }
}
