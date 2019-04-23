import Que_me_pongo.*;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;


public class GuardarropaTest {
	
	Usuario usuario1 = new Usuario();
	PrendaFactory factory = new PrendaFactory();
	Prenda remera = factory.crearRemera(Tipo.REMERAMANGASCORTAS, Material.SEDA, Color.BLACK, null);

	@Test
	public void usuarioCargaRemeraEnGuardarropa() {

		usuario1.agregarGuardarropas();
		usuario1.agregarPrenda(remera,usuario1.guardarropa(0));

		Prenda prueba = usuario1.guardarropa(0).prendas.get(Categoria.SUPERIOR).get(0); // prueba es la remera cargada en el guardarropa

		Assert.assertEquals(prueba,remera);
	}
}
