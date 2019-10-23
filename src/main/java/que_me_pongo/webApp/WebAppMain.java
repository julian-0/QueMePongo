package que_me_pongo.webApp;
import org.quartz.SchedulerException;

import que_me_pongo.EventoMain;
import que_me_pongo.ExampleDataCreator;
import que_me_pongo.usuario.RepositorioUsuarios;
import que_me_pongo.usuario.TipoUsuario;
import que_me_pongo.usuario.Usuario;
import spark.Spark;
import spark.debug.DebugScreen;

public class WebAppMain {

	public static void main(String[] args) throws SchedulerException  {
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
		Router.configurar();
		new DB().configurarTransacciones();
		EventoMain.main(args);
	}
}
