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
		Prenda remera = factory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, null);

		Assert.assertEquals(prueba,remera);
	}
	
	@Test
	public void crearRemeraConSecundario() {
		Prenda prueba = new Prenda(Tipo.REMERAMANGASCORTAS,Categoria.SUPERIOR, Material.SEDA, Color.BLACK, Color.WHITE);
		PrendaFactory factory = new PrendaFactory();
		Prenda remera = factory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, Color.WHITE);

		Assert.assertEquals(prueba,remera);
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void deberiaTirarExcepcionColoresIguales() throws Exception {
	    expectedEx.expect(ColoresIgualesException.class);
	    expectedEx.expectMessage("Los colores son iguales");
	    // Codigo que deberia tirar la excepcion
	    PrendaFactory factory = new PrendaFactory();
		Prenda remera = factory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, Color.BLACK);
	}

	@Test
	public void deberiaTirarExcepcionMaterialInvalido() throws Exception {
	    expectedEx.expect(MaterialInvalidoException.class);
	    expectedEx.expectMessage("Material invalido");
	    // Codigo que deberia tirar la excepcion
	    PrendaFactory factory = new PrendaFactory();
		Prenda remera = factory.crearRemeraMangaCorta(Material.CUERO, Color.BLACK, null);
	}
	
	@Test
	public void crearAnteojos() {
		Prenda prueba = new Prenda(Tipo.ANTEOJOS,Categoria.ACCESORIO, Material.PLASTICO, Color.BLACK, null);
		PrendaFactory factory = new PrendaFactory();
		Prenda anteojos = factory.crearAnteojos(Material.PLASTICO, Color.BLACK, null);

		Assert.assertEquals(prueba,anteojos);
	}
	
	
	@Test
	public void crearShort() {
		Prenda prueba = new Prenda(Tipo.SHORT,Categoria.INFERIOR, Material.DRIFIT, Color.YELLOW, null);
		PrendaFactory factory = new PrendaFactory();
		Prenda shortAmarillo = factory.crearShort(Material.DRIFIT, Color.YELLOW, null);

		Assert.assertEquals(prueba,shortAmarillo);
	}
	
	@Test
	public void crearBuzo() {
		Prenda prueba = new Prenda(Tipo.BUZO,Categoria.SUPERIOR, Material.ALGODON, Color.BLACK, null);
		PrendaFactory factory = new PrendaFactory();
		Prenda buzo = factory.crearBuzo(Material.ALGODON, Color.BLACK, null);

		Assert.assertEquals(prueba,buzo);
	}
	
	@Test
	public void crearZapatosDeTacon() {
		Prenda prueba = new Prenda(Tipo.ZAPATOSDETACON,Categoria.CALZADO, Material.CUERO, Color.BLACK, null);
		PrendaFactory factory = new PrendaFactory();
		Prenda zapatosDeTacon = factory.crearZapatosDeTacon(Material.CUERO, Color.BLACK, null);

		Assert.assertEquals(prueba,zapatosDeTacon);
	}
	
	@Test
	public void deberiaTirarExcepcionMaterialInvalidoZapatos() throws Exception {
	    expectedEx.expect(MaterialInvalidoException.class);
	    expectedEx.expectMessage("Material invalido");
	    // Codigo que deberia tirar la excepcion
	    PrendaFactory factory = new PrendaFactory();
		Prenda zapatosDeTacon = factory.crearZapatosDeTacon(Material.SEDA, Color.BLACK, null);
	}
	
}
