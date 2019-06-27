package que_me_pongo.configuraciones;

import java.util.HashMap;
import java.util.Map;

public class Configuraciones {
	private static Map<Class, Object> configuraciones = new HashMap<Class, Object>();
	
	public static <T> void set(Class<T> key, T instance) {
		configuraciones.put(key, instance);
	}
	
	public static <T> T get(Class<T> key) {
		return (T) configuraciones.get(key);
	}

}
