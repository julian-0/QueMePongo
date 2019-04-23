import Que_me_pongo.*;
import org.junit.Assert;
import org.junit.Test;
import java.awt.Color;


public class GuardarropaTest {
	PrendaFactory factory = new PrendaFactory();
	Prenda remera = factory.crearRemera(Tipo.REMERAMANGASCORTAS, Material.SEDA, Color.BLACK, null);
	
	@Test
	public void CargaGuardarropaEnUsuario() {
		Usuario usuario = new Usuario();
		usuario.agregarGuardarropas();
	}

	@Test
	public void CargaRemeraEnGuardarropa() {
		
		Guardarropa guardarropa = new Guardarropa();
		Assert.assertEquals(0, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
		guardarropa.agregarPrenda(remera);
		Assert.assertEquals(1, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
	}
	
	@Test
	public void NoCargaDosVecesLoMismo() {
		
		Guardarropa guardarropa = new Guardarropa();
		Assert.assertEquals(0, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
		guardarropa.agregarPrenda(remera);
		Assert.assertEquals(1, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
		guardarropa.agregarPrenda(remera);
		Assert.assertEquals(1, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
	}

}
