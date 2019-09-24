package que_me_pongo.prenda;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class TipoDePrendaFactory implements WithGlobalEntityManager {
	static TipoDePrendaFactory instance = new TipoDePrendaFactory();
	
	public static TipoDePrendaFactory getInstance() {
		return instance;
	}
	
	private TipoDePrenda tipoGenerico(TipoDePrenda retornoEnFallo) {
		try {
			return entityManager().
					createQuery("FROM TipoDePrenda WHERE tipo=:tipo",TipoDePrenda.class).
					setParameter("tipo", retornoEnFallo.getTipo()).
					getSingleResult();
		}
		catch(NoResultException noResultEx)
		{
			return crearEnDB(retornoEnFallo);
		}
	}
	
	private TipoDePrenda crearEnDB(TipoDePrenda tipo) {
		entityManager().persist(tipo);
		return tipo;
	}
	
	public TipoDePrenda aros() {
		return tipoGenerico(new TipoDePrenda(Tipo.AROS, Categoria.ACCESORIO, Arrays.asList(Material.PLASTICO), 1, 0));
	}
	
	public TipoDePrenda anteojos() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.PLASTICO);
  	return tipoGenerico(new TipoDePrenda(Tipo.ANTEOJOS, Categoria.ACCESORIO, materiales, 2, 0));
	}
	
	public TipoDePrenda remeraMangaCorta() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.SEDA);
  	materiales.add(Material.DRIFIT);
  	return tipoGenerico(new TipoDePrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, materiales, 0, 1));
	}
	
	public TipoDePrenda remeraMangaLarga() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.SEDA);
  	materiales.add(Material.DRIFIT);
  	return tipoGenerico(new TipoDePrenda(Tipo.REMERAMANGASLARGAS, Categoria.SUPERIOR, materiales, 0, 1.5));
	}
	
	public TipoDePrenda shorts() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.DRIFIT);
  	return tipoGenerico(new TipoDePrenda(Tipo.SHORT, Categoria.INFERIOR, materiales, 0, 1));
	}
	
	public TipoDePrenda pantalon() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.DRIFIT);
  	return tipoGenerico(new TipoDePrenda(Tipo.PANTALON, Categoria.INFERIOR, materiales, 0, 2));
	}
	
	public TipoDePrenda buzo() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.ALGODON);
  	materiales.add(Material.SEDA);
  	return tipoGenerico(new TipoDePrenda(Tipo.BUZO, Categoria.SUPERIOR, materiales, 2, 3));
	}
	
	public TipoDePrenda zapatosDeTacon() {
		List<Material> materiales = new LinkedList<Material>();
  	materiales.add(Material.CUERO);
  	return tipoGenerico(new TipoDePrenda(Tipo.ZAPATOSDETACON, Categoria.CALZADO, materiales, 0, 1));
	}
	
	public TipoDePrenda chaleco() {
		List<Material> materiales = Arrays.asList(Material.ALGODON, Material.CUERO);
		return tipoGenerico(new TipoDePrenda(Tipo.CHALECO, Categoria.SUPERIOR, materiales, 1, 2));
	}
	
	public TipoDePrenda guantes() {
		List<Material> materiales = Arrays.asList(Material.ALGODON, Material.CUERO);
		return tipoGenerico(new TipoDePrenda(Tipo.GUANTES, Categoria.ACCESORIO, materiales, 3, 2));
	}
}
