package Que_me_pongo;
import java.awt.Color;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;


public class PrendaFactory {
    
    static private Prenda crearPrenda(TipoDePrenda tipo, Material material, Color colorPrimario, Color colorSecundario) {
    	return new Prenda(tipo, material, colorPrimario, colorSecundario);	
    }
    
    static public Prenda crearAnteojos(Material material, Color colorPrimario, Color colorSecundario) {
    	return crearPrenda(TipoDePrendaFactory.anteojos(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda crearRemeraMangaCorta(Material material, Color colorPrimario, Color colorSecundario) {
    	
    	return crearPrenda(TipoDePrendaFactory.remeraMangaCorta(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda crearBuzo(Material material, Color colorPrimario, Color colorSecundario) {
    	return crearPrenda(TipoDePrendaFactory.buzo(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda crearShort(Material material, Color colorPrimario, Color colorSecundario) {
    	return crearPrenda(TipoDePrendaFactory.shorts(), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda crearZapatosDeTacon(Material material, Color colorPrimario, Color colorSecundario) {
    	
    	return crearPrenda(TipoDePrendaFactory.zapatosDeTacon(), material, colorPrimario, colorSecundario);
    }

}
