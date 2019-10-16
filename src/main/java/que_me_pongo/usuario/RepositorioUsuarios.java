package que_me_pongo.usuario;

import javax.persistence.NoResultException;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.transaction.TransactionalOps;

import com.google.common.base.Optional;

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
	
	public Optional<Usuario> buscarPorNombre(String nombre) {
		return buscarUno("FROM Usuario WHERE nombre = :nombre", "nombre", nombre);						 
	}
	
	public Optional<Usuario> buscarPorMail(String mail) {
		return buscarUno("FROM Usuario WHERE mail = :mail", "mail", mail);				 
	}
	
	private Optional<Usuario> buscarUno(String query, String parameter, String value) {
		try {
			Usuario usuario = entityManager().createQuery(query, Usuario.class)
		 			.setParameter(parameter, value)
		 			.getSingleResult();
			return Optional.of(usuario);
		}
		catch(NoResultException e) {
			return Optional.absent();
		}
	}
}
