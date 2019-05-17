package Que_me_pongo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TipoDePrendaFactory {
	static public TipoDePrenda aros() {
		return new TipoDePrenda(Tipo.AROS, Categoria.ACCESORIO, Arrays.asList(Material.PLASTICO));
	}
	
	static public TipoDePrenda anteojos() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.PLASTICO);
  	return new TipoDePrenda(Tipo.ANTEOJOS, Categoria.ACCESORIO, materiales);
	}
	
	static public TipoDePrenda remeraMangaCorta() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.SEDA);
  	materiales.add(Material.DRIFIT);
  	return new TipoDePrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, materiales);
	}
	
	static public TipoDePrenda shorts() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.DRIFIT);
  	return new TipoDePrenda(Tipo.SHORT, Categoria.INFERIOR, materiales);
	}
	
	static public TipoDePrenda buzo() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.SEDA);
  	return new TipoDePrenda(Tipo.BUZO, Categoria.SUPERIOR, materiales);
	}
	
	static public TipoDePrenda zapatosDeTacon() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.CUERO);
  	return new TipoDePrenda(Tipo.ZAPATOSDETACON, Categoria.CALZADO, materiales);
	}
}
