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
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.PLASTICO);
    	return crearPrenda(new TipoDePrenda(Tipo.ANTEOJOS, Categoria.ACCESORIO, materiales), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda crearRemeraMangaCorta(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.SEDA);
    	materiales.add(Material.DRIFIT);
    	return crearPrenda(new TipoDePrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, materiales), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda crearBuzo(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.SEDA);
    	return crearPrenda(new TipoDePrenda(Tipo.BUZO, Categoria.SUPERIOR, materiales), material, colorPrimario, colorSecundario);
    }
    
    static public Prenda crearShort(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.DRIFIT);
    	return crearPrenda(new TipoDePrenda(Tipo.SHORT, Categoria.INFERIOR, materiales), material, colorPrimario, colorSecundario);
    }
    
    
    static public Prenda crearZapatosDeTacon(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.CUERO);
    	return crearPrenda(new TipoDePrenda(Tipo.ZAPATOSDETACON, Categoria.CALZADO, materiales), material, colorPrimario, colorSecundario);
    }

}
