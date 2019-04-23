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
	
	@Test
	public void crearAnteojos() {
		Prenda prueba = new Prenda(Tipo.ANTEOJOS,Categoria.ACCESORIO, Material.PLASTICO, Color.BLACK, null);
		PrendaFactory factory = new PrendaFactory();
		Prenda anteojos = factory.crearAnteojos(Tipo.ANTEOJOS, Material.PLASTICO, Color.BLACK, null);
		Assert.assertTrue(prueba.getTipo()==anteojos.getTipo());
		Assert.assertTrue(prueba.getCategoria()==anteojos.getCategoria());
		Assert.assertTrue(prueba.getColorPrimario()==anteojos.getColorPrimario());
		Assert.assertTrue(prueba.getColorSecundario()==anteojos.getColorSecundario());
		Assert.assertTrue(prueba.getMaterial()==anteojos.getMaterial());
	}
	
	
	@Test
	public void crearShort() {
		Prenda prueba = new Prenda(Tipo.SHORT,Categoria.INFERIOR, Material.DRIFIT, Color.YELLOW, null);
		PrendaFactory factory = new PrendaFactory();
		Prenda shortAmarillo = factory.crearShort(Tipo.SHORT, Material.DRIFIT, Color.YELLOW, null);
		Assert.assertTrue(prueba.getTipo()==shortAmarillo.getTipo());
		Assert.assertTrue(prueba.getCategoria()==shortAmarillo.getCategoria());
		Assert.assertTrue(prueba.getColorPrimario()==shortAmarillo.getColorPrimario());
		Assert.assertTrue(prueba.getColorSecundario()==shortAmarillo.getColorSecundario());
		Assert.assertTrue(prueba.getMaterial()==shortAmarillo.getMaterial());
	}
	
	@Test
	public void crearBuzo() {
		Prenda prueba = new Prenda(Tipo.BUZO,Categoria.SUPERIOR, Material.ALGODON, Color.BLACK, null);
		PrendaFactory factory = new PrendaFactory();
		Prenda buzo = factory.crearBuzo(Tipo.BUZO, Material.ALGODON, Color.BLACK, null);
		Assert.assertTrue(prueba.getTipo()==buzo.getTipo());
		Assert.assertTrue(prueba.getCategoria()==buzo.getCategoria());
		Assert.assertTrue(prueba.getColorPrimario()==buzo.getColorPrimario());
		Assert.assertTrue(prueba.getColorSecundario()==buzo.getColorSecundario());
		Assert.assertTrue(prueba.getMaterial()==buzo.getMaterial());
	}
	
	@Test
	public void crearZapatosDeTacon() {
		Prenda prueba = new Prenda(Tipo.ZAPATOSDETACON,Categoria.CALZADO, Material.CUERO, Color.BLACK, null);
		PrendaFactory factory = new PrendaFactory();
		Prenda zapatosDeTacon = factory.crearZapatosDeTacon(Tipo.ZAPATOSDETACON, Material.CUERO, Color.BLACK, null);
		Assert.assertTrue(prueba.getTipo()==zapatosDeTacon.getTipo());
		Assert.assertTrue(prueba.getCategoria()==zapatosDeTacon.getCategoria());
		Assert.assertTrue(prueba.getColorPrimario()==zapatosDeTacon.getColorPrimario());
		Assert.assertTrue(prueba.getColorSecundario()==zapatosDeTacon.getColorSecundario());
		Assert.assertTrue(prueba.getMaterial()==zapatosDeTacon.getMaterial());
	}
	
	@Test
	public void deberiaTirarExcepcionMaterialInvalidoZapatos() throws Exception {
	    expectedEx.expect(MaterialInvalidoException.class);
	    expectedEx.expectMessage("Material invalido");
	    // do something that should throw the exception...
	    PrendaFactory factory = new PrendaFactory();
		Prenda zapatosDeTacon = factory.crearZapatosDeTacon(Tipo.ZAPATOSDETACON, Material.SEDA, Color.BLACK, null);
	}
	
}
