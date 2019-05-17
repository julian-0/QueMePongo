import java.awt.Color;
import java.util.Arrays;

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
import Que_me_pongo.TipoDePrenda;

public class PrendaTest {

	@Test
	public void crearRemeraMangaCortaSinSecundario() {
		Prenda remera = new Prenda(new TipoDePrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, Arrays.asList(Material.ALGODON)), Material.ALGODON, Color.BLACK, null);

		//PrendaFactory factory = new PrendaFactory();
		//Prenda remera = factory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, null);

		Assert.assertEquals(Tipo.REMERAMANGASCORTAS, remera.getTipo());
		Assert.assertEquals(Categoria.SUPERIOR, remera.getCategoria());
		Assert.assertEquals(Material.ALGODON, remera.getMaterial());
		Assert.assertEquals(Color.BLACK, remera.getColorPrimario());
		Assert.assertEquals(null, remera.getColorSecundario());
	}
	
	@Test
	public void crearRemeraMangaCortaConSecundario() {
		Prenda remera = new Prenda(new TipoDePrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, Arrays.asList(Material.ALGODON)), Material.ALGODON, Color.BLACK, Color.WHITE);
		//PrendaFactory factory = new PrendaFactory();
		//Prenda remera = factory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, Color.WHITE);

		Assert.assertEquals(Tipo.REMERAMANGASCORTAS, remera.getTipo());
		Assert.assertEquals(Categoria.SUPERIOR, remera.getCategoria());
		Assert.assertEquals(Material.ALGODON, remera.getMaterial());
		Assert.assertEquals(Color.BLACK, remera.getColorPrimario());
		Assert.assertEquals(Color.WHITE, remera.getColorSecundario());
	}
	
	@Test
	public void crearRemeraMangaCortaConFactory() {
		Prenda remeraDirecta = new Prenda(new TipoDePrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, Arrays.asList(Material.ALGODON)), Material.ALGODON, Color.BLACK, null);

		Prenda remeraFactory = PrendaFactory.crearRemeraMangaCorta(Material.ALGODON, Color.BLACK, null);
		
		Assert.assertEquals(remeraDirecta, remeraFactory);
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void deberiaTirarExcepcionNullPointerPorTipo() throws Exception {
		expectedEx.expect(NullPointerException.class);
		
		new Prenda(new TipoDePrenda(null, Categoria.SUPERIOR, Arrays.asList(Material.ALGODON)), Material.ALGODON, Color.BLACK, null);
	}
	
	@Test
	public void deberiaTirarExcepcionNullPointerPorCategoria() throws Exception {
		expectedEx.expect(NullPointerException.class);
		
		new Prenda(new TipoDePrenda(Tipo.REMERAMANGASCORTAS, null, Arrays.asList(Material.ALGODON)), Material.ALGODON, Color.BLACK, null);
	}
	
	@Test
	public void deberiaTirarExcepcionNullPointerPorMaterial() throws Exception {
		expectedEx.expect(NullPointerException.class);
		
		new Prenda(new TipoDePrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, Arrays.asList(Material.ALGODON)), null, Color.BLACK, null);
	}
	
	@Test
	public void deberiaTirarExcepcionNullPointerPorColorPrincipal() throws Exception {
		expectedEx.expect(NullPointerException.class);
		
		new Prenda(new TipoDePrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, Arrays.asList(Material.ALGODON)), Material.ALGODON, null, null);
	}

	@Test
	public void deberiaTirarExcepcionColoresIguales() throws Exception {
	    expectedEx.expect(ColoresIgualesException.class);
	    expectedEx.expectMessage("Los colores son iguales");
	    // Codigo que deberia tirar la excepcion
	    PrendaFactory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, Color.BLACK);
	}

	@Test
	public void deberiaTirarExcepcionMaterialInvalido() throws Exception {
	    expectedEx.expect(MaterialInvalidoException.class);
	    expectedEx.expectMessage("Material invalido");
	    // Codigo que deberia tirar la excepcion
	    PrendaFactory.crearRemeraMangaCorta(Material.CUERO, Color.BLACK, null);
	}
	
	@Test
	public void crearAnteojosConFactory() {
		Prenda prueba = new Prenda(new TipoDePrenda(Tipo.ANTEOJOS,Categoria.ACCESORIO, Arrays.asList(Material.PLASTICO)), Material.PLASTICO, Color.BLACK, null);
		Prenda anteojos = PrendaFactory.crearAnteojos(Material.PLASTICO, Color.BLACK, null);

		Assert.assertEquals(prueba,anteojos);
	}
	
	
	@Test
	public void crearShortConFactory() {
		Prenda prueba = new Prenda(new TipoDePrenda(Tipo.SHORT,Categoria.INFERIOR, Arrays.asList(Material.DRIFIT)), Material.DRIFIT, Color.YELLOW, null);
		Prenda shortAmarillo = PrendaFactory.crearShort(Material.DRIFIT, Color.YELLOW, null);

		Assert.assertEquals(prueba,shortAmarillo);
	}
	
	@Test
	public void crearBuzoConFactory() {
		Prenda prueba = new Prenda(new TipoDePrenda(Tipo.BUZO,Categoria.SUPERIOR, Arrays.asList(Material.ALGODON)), Material.ALGODON, Color.BLACK, null);
		Prenda buzo = PrendaFactory.crearBuzo(Material.ALGODON, Color.BLACK, null);

		Assert.assertEquals(prueba,buzo);
	}
	
	@Test
	public void crearZapatosDeTaconConFactory() {
		Prenda prueba = new Prenda(new TipoDePrenda(Tipo.ZAPATOSDETACON,Categoria.CALZADO,Arrays.asList(Material.CUERO)), Material.CUERO, Color.BLACK, null);
		Prenda zapatosDeTacon = PrendaFactory.crearZapatosDeTacon(Material.CUERO, Color.BLACK, null);

		Assert.assertEquals(prueba,zapatosDeTacon);
	}
	
	@Test
	public void deberiaTirarExcepcionMaterialInvalidoZapatos() throws Exception {
	    expectedEx.expect(MaterialInvalidoException.class);
	    expectedEx.expectMessage("Material invalido");
	    // Codigo que deberia tirar la excepcion
	    PrendaFactory.crearZapatosDeTacon(Material.SEDA, Color.BLACK, null);
	}
	
}
