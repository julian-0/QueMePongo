package que_me_pongo.guardarropa;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioGuardarropas implements WithGlobalEntityManager, TransactionalOps {
	static private RepositorioGuardarropas instancia;
	
	static public RepositorioGuardarropas getInstance() {
		if(instancia == null)
			instancia = new RepositorioGuardarropas();
		return instancia;
	}
	
	private RepositorioGuardarropas(){}
	
	public Guardarropa createGuardarropas(Guardarropa guardarropa) {
		withTransaction(() -> entityManager().persist(guardarropa));
		return guardarropa;
	}
}
