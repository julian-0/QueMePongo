package que_me_pongo.webApp;

import que_me_pongo.webApp.controllers.EventosController;
import que_me_pongo.webApp.controllers.GuardarropasController;
import que_me_pongo.webApp.controllers.LogInController;
import spark.Spark;

public class Router {
	public static void configurar() {
		Spark.staticFileLocation("/public");
		LogInController loginController = new LogInController();
		Spark.get("/login", loginController::show);
		Spark.post("/login", loginController::create);
		GuardarropasController guardarropasController = new GuardarropasController();
		Spark.get("/guardarropas", guardarropasController::show);
		EventosController eventosController = new EventosController();
		Spark.get("/eventos", eventosController::index);
		Spark.get("/eventosJson", eventosController::json);
	}
}
