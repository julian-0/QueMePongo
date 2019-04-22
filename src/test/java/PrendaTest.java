import java.awt.Color;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import Que_me_pongo.Categoria;
import Que_me_pongo.ColoresIgualesException;
import Que_me_pongo.Material;
import Que_me_pongo.MaterialInvalidoException;
import Que_me_pongo.Prenda;
import Que_me_pongo.PrendaFactory;
import Que_me_pongo.Tipo;

public class PrendaTest {

	@Test
	public void crearRemeraSinSecundario() {
		Prenda prueba = new Prenda(Tipo.REMERAMANGASCORTAS,Categoria.SUPERIOR, Material.SEDA, Color.BLACK, null);
		PrendaFactory factory = new PrendaFactory();
		Prenda remera = factory.crearRemera(Tipo.REMERAMANGASCORTAS, Material.SEDA, Color.BLACK, null);
		Assert.assertTrue(prueba.getTipo()==remera.getTipo());
		Assert.assertTrue(prueba.getCategoria()==remera.getCategoria());
		Assert.assertTrue(prueba.getColorPrimario()==remera.getColorPrimario());
		Assert.assertTrue(prueba.getColorSecundario()==remera.getColorSecundario());
		Assert.assertTrue(prueba.getMaterial()==remera.getMaterial());
	}
	
	@Test
	public void crearRemeraConSecundario() {
		Prenda prueba = new Prenda(Tipo.REMERAMANGASCORTAS,Categoria.SUPERIOR, Material.SEDA, Color.BLACK, Color.WHITE);
		PrendaFactory factory = new PrendaFactory();
		Prenda remera = factory.crearRemera(Tipo.REMERAMANGASCORTAS, Material.SEDA, Color.BLACK, Color.WHITE);
		Assert.assertTrue(prueba.getTipo()==remera.getTipo());
		Assert.assertTrue(prueba.getCategoria()==remera.getCategoria());
		Assert.assertTrue(prueba.getColorPrimario()==remera.getColorPrimario());
		Assert.assertTrue(prueba.getColorSecundario()==remera.getColorSecundario());
		Assert.assertTrue(prueba.getMaterial()==remera.getMaterial());
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void deberiaTirarExcepcionColoresIguales() throws Exception {
	    expectedEx.expect(ColoresIgualesException.class);
	    expectedEx.expectMessage("Los colores son iguales");
	    // do something that should throw the exception...
	    PrendaFactory factory = new PrendaFactory();
		Prenda remera = factory.crearRemera(Tipo.REMERAMANGASCORTAS, Material.SEDA, Color.BLACK, Color.BLACK);
	}

	@Test
	public void deberiaTirarExcepcionMaterialInvalido() throws Exception {
	    expectedEx.expect(MaterialInvalidoException.class);
	    expectedEx.expectMessage("Material invalido");
	    // do something that should throw the exception...
	    PrendaFactory factory = new PrendaFactory();
		Prenda remera = factory.crearRemera(Tipo.REMERAMANGASCORTAS, Material.CUERO, Color.BLACK, null);
	}
	
}
