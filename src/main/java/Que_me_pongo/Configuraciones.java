package Que_me_pongo;

import java.util.HashMap;
import java.util.Map;

public class Configuraciones {
	static Map<Class, Object> configuraciones = new HashMap<Class, Object>();
	
	public static void set(Class key, Object instance) {
		if(instance.getClass() != key)
			throw new InstanciaInvalidaEnConfiguracionException();
		configuraciones.put(key, instance);
	}
	
	public static Object get(Class key) {
		return configuraciones.get(key);
	}

}
