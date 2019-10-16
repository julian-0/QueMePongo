package que_me_pongo.webApp;
import spark.Spark;
import spark.debug.DebugScreen;

public class WebAppMain {

	public static void main(String[] args) {
		Spark.port(9000);
		DebugScreen.enableDebugScreen();
	}

}
