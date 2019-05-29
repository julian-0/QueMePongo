package Que_me_pongo;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

public class TipoDePrendaFactory {
	static public TipoDePrenda aros() {
		return new TipoDePrenda(Tipo.AROS, Categoria.ACCESORIO, Arrays.asList(Material.PLASTICO), 1, 0);
	}
	
	static public TipoDePrenda anteojos() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.PLASTICO);
  	return new TipoDePrenda(Tipo.ANTEOJOS, Categoria.ACCESORIO, materiales, 1, 0);
	}
	
	static public TipoDePrenda remeraMangaCorta() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.SEDA);
  	materiales.add(Material.DRIFIT);
  	return new TipoDePrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, materiales, 0, 3);
	}
	
	static public TipoDePrenda shorts() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.DRIFIT);
  	return new TipoDePrenda(Tipo.SHORT, Categoria.INFERIOR, materiales, 0, 3);
	}
	
	static public TipoDePrenda buzo() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.SEDA);
  	return new TipoDePrenda(Tipo.BUZO, Categoria.SUPERIOR, materiales, 2, 3);
	}
	
	static public TipoDePrenda zapatosDeTacon() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.CUERO);
  	return new TipoDePrenda(Tipo.ZAPATOSDETACON, Categoria.CALZADO, materiales, 0, 2);
	}
	
	static public TipoDePrenda chaleco() {
		List<Material> materiales = Arrays.asList(Material.ALGODON, Material.CUERO);
		return new TipoDePrenda(Tipo.CHALECO, Categoria.SUPERIOR, materiales, 1, 3);
	}
}
