package que_me_pongo.webApp;

import que_me_pongo.webApp.controllers.EventosController;
import que_me_pongo.webApp.controllers.GuardarropasController;
import que_me_pongo.webApp.controllers.LogInController;
import que_me_pongo.webApp.controllers.SugerenciasController;
import spark.Spark;

public class Router {
	public static void configurar() {
		Spark.staticFileLocation("/public");
		LogInController loginController = new LogInController();
		Spark.get("/login", loginController::show);
		Spark.post("/login", loginController::create);

		GuardarropasController guardarropasController = new GuardarropasController();
		Spark.get("/guardarropas", guardarropasController::show);
		Spark.get("/guardarropas/:id", guardarropasController::listarPrendas);

		EventosController eventosController = new EventosController();
		Spark.get("/eventos", eventosController::index);
		Spark.post("/evento", eventosController::create);
		Spark.get("/evento/nuevo", eventosController::nuevo);
		Spark.get("/evento/:id", eventosController::show);
		Spark.get("/eventosJson", eventosController::entradasCalendario);

		SugerenciasController sugerenciasController = new SugerenciasController();
		Spark.get("/evento/:id/sugerencias", sugerenciasController::show);
		Spark.post("/evento/:id/sugerencias", sugerenciasController::modificar);

	}
}
