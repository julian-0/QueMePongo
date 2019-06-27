import org.junit.Test;

import que_me_pongo.configuraciones.Configuraciones;

import org.junit.Assert;

public class ConfiguracionesTest {
	@Test
	public void guardarInstancia() {
		Configuraciones.set(String.class, "Una instancia");
	}
	
	@Test
	public void recuperarInstancia() {
		String instance = "Una instancia";
		Configuraciones.set(String.class, instance);
		Assert.assertEquals(instance, Configuraciones.get(String.class));
	}
	
	@Test
	public void masDeUnaConfiguracion() {
		Configuraciones.set(String.class, "Hola");
		Configuraciones.set(Object.class, new Object());
	}
	
	@Test
	public void retornoDeTipoCorrecto() {
		Configuraciones.set(String.class, "Hola");
		Assert.assertEquals(String.class, Configuraciones.get(String.class).getClass());
	}
}
