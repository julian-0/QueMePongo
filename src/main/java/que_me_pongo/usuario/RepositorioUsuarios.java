package que_me_pongo.usuario;

import com.google.common.base.Optional;

import que_me_pongo.QueriesInterfaces;

public class RepositorioUsuarios implements QueriesInterfaces {
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
	
	public Optional<Usuario> buscarPorNombre(String nombre) {
		return buscarUno("FROM Usuario WHERE nombre = :nombre", "nombre", nombre);						 
	}
	
	public Optional<Usuario> buscarPorMail(String mail) {
		return buscarUno("FROM Usuario WHERE mail = :mail", "mail", mail);				 
	}
	
	public Optional<Usuario> buscarPorGuardarropa(int id){
		return buscarUno("FROM Usuario WHERE Usuario.Guardarropas.id = :id", ":id", id);
	}
}
