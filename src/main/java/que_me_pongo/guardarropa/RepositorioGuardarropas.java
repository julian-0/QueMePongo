package que_me_pongo.guardarropa;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioGuardarropas implements WithGlobalEntityManager {
	static private RepositorioGuardarropas instancia;
	
	static public RepositorioGuardarropas getInstance() {
		if(instancia == null)
			instancia = new RepositorioGuardarropas();
		return instancia;
	}
	
	private RepositorioGuardarropas(){}
	
	public Guardarropa createGuardarropas(Guardarropa guardarropa) {
		entityManager().persist(guardarropa);
		return guardarropa;
	}
}
