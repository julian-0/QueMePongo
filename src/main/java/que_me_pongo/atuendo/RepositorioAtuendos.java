package que_me_pongo.atuendo;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioAtuendos implements WithGlobalEntityManager, TransactionalOps {
	static private RepositorioAtuendos instancia;
	
	static public RepositorioAtuendos getInstance() {
		if(instancia == null)
			instancia = new RepositorioAtuendos();
		return instancia;
	}
	
	private RepositorioAtuendos(){}
	
	public Atuendo createAtuendos(Atuendo atuendo) {
		withTransaction(() -> entityManager().persist(atuendo));
		return atuendo;
	}
}
