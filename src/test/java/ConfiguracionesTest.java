import org.junit.Test;
import org.junit.rules.ExpectedException;

import Que_me_pongo.Configuraciones;
import Que_me_pongo.InstanciaInvalidaEnConfiguracionException;

import org.junit.Assert;
import org.junit.Rule;

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
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void instanciasDeTipoIncorrectoNoVa() {
		expectedEx.expect(InstanciaInvalidaEnConfiguracionException.class);
		Configuraciones.set(String.class, 1);
	}
	
	@Test
	public void masDeUnaConfiguracion() {
		Configuraciones.set(String.class, "Hola");
		Configuraciones.set(Object.class, new Object());
	}

}
