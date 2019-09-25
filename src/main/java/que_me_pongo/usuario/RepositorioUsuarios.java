package que_me_pongo.usuario;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

public class RepositorioUsuarios implements WithGlobalEntityManager {
	static private RepositorioUsuarios instancia;
	
	static public RepositorioUsuarios getInstance() {
		if(instancia == null)
			instancia = new RepositorioUsuarios();
		return instancia;
	}
	
	private RepositorioUsuarios() {}
	
	public Usuario createUsuario(Usuario usuario) {
		entityManager().persist(usuario);
		return usuario;
	}
}
