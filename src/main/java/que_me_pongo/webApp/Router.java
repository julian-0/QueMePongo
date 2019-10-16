package que_me_pongo.webApp;

import que_me_pongo.webApp.controllers.GuardarropasController;
import que_me_pongo.webApp.controllers.LogInController;
import spark.Spark;

public class Router {
	public static void configurar() {
		LogInController loginController = new LogInController();
		Spark.get("/login", loginController::show);
		Spark.post("/login", loginController::create);
		GuardarropasController guardarropasController = new GuardarropasController();
		Spark.get("/guardarropas", guardarropasController::show);
	}
}
