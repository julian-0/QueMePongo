package que_me_pongo.prenda;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioPrendas implements WithGlobalEntityManager {
	static private RepositorioPrendas instance;
	
	static RepositorioPrendas getInstance() {
		if(instance == null)
			instance = new RepositorioPrendas();
		return instance;
	}
	
	public TipoDePrenda getTipoDePrenda(Tipo tipo) {
		return entityManager().
				createQuery("FROM TipoDePrenda WHERE tipo=:tipo",TipoDePrenda.class).
				setParameter("tipo", tipo).
				getSingleResult();
	}
	
	public TipoDePrenda createTipoDePrenda(TipoDePrenda tipo) {
		entityManager().persist(tipo);
		return tipo;
	}
	
	public Prenda createTipoDePrenda(Prenda prenda) {
		entityManager().persist(prenda);
		return prenda;
	}

}
