package que_me_pongo.usuario;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

public class RepositorioUsuarios implements WithGlobalEntityManager, TransactionalOps {
	static private RepositorioUsuarios instancia;
	
	static public RepositorioUsuarios getInstance() {
		if(instancia == null)
			instancia = new RepositorioUsuarios();
		return instancia;
	}
	
	private RepositorioUsuarios() {}
	
	public Usuario createUsuario(Usuario usuario) {
		withTransaction(() -> entityManager().persist(usuario));
		return usuario;
	}
}
