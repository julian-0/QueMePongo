package que_me_pongo.webApp;
import que_me_pongo.ExampleDataCreator;
import que_me_pongo.usuario.RepositorioUsuarios;
import que_me_pongo.usuario.TipoUsuario;
import que_me_pongo.usuario.Usuario;
import spark.Spark;
import spark.debug.DebugScreen;

public class WebAppMain {

	public static void main(String[] args) {
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configurar();
		new DB().configurarTransacciones();

		//Usuario user = new Usuario("julian","aaaa", TipoUsuario.PREMIUM,"password");
		//RepositorioUsuarios.getInstance().createUsuario(user);
	}
}
