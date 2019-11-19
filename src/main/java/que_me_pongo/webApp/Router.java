package que_me_pongo.webApp;

import que_me_pongo.webApp.controllers.*;
import spark.Spark;

public class Router {
	public static void configurar() {
		Spark.staticFileLocation("/public");
		LogInController loginController = new LogInController();
		Spark.get("/login", loginController::show);
		Spark.post("/login", loginController::create);

		GuardarropasController guardarropasController = new GuardarropasController();
		Spark.get("/guardarropas", guardarropasController::index);
		Spark.get("/guardarropas/:id", guardarropasController::show);

		PrendasController prendasController = new PrendasController();
		Spark.get("/guardarropas/:id/prenda", prendasController::nuevo);
		Spark.post("/guardarropas/:id/prenda", prendasController::create);

		EventosController eventosController = new EventosController();
		Spark.get("/eventos", eventosController::index);
		Spark.post("/evento", eventosController::create);
		Spark.get("/evento/nuevo", eventosController::nuevo);
		Spark.get("/evento/:id", eventosController::show);
		Spark.get("/api/eventos", eventosController::entradasCalendario);

		SugerenciasController sugerenciasController = new SugerenciasController();
		Spark.get("/evento/:id/sugerencias", sugerenciasController::show);
		Spark.post("/evento/:id/sugerencias", sugerenciasController::move);
		AtuendoAceptadoController aceptadoController = new AtuendoAceptadoController();
		Spark.get("/evento/:id/atuendo", aceptadoController::show);
		Spark.post("/evento/:id/atuendo", aceptadoController::edit);

		MenuController menuController = new MenuController();
		Spark.get("/", eventosController::index);

	}
}
