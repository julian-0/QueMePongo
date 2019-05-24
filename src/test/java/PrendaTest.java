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
import Que_me_pongo.Tipo;
import Que_me_pongo.TipoDePrenda;
import Que_me_pongo.TipoDePrendaFactory;

public class PrendaTest {

	@Test
	public void crearRemeraMangaCortaSinSecundario() {
		Prenda remera = new Prenda(TipoDePrendaFactory.remeraMangaCorta(), Material.ALGODON, Color.BLACK, null, null);

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
		Prenda remera = new Prenda(TipoDePrendaFactory.remeraMangaCorta(), Material.ALGODON, Color.BLACK, Color.WHITE, null);
		//PrendaFactory factory = new PrendaFactory();
		//Prenda remera = factory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, Color.WHITE);

		Assert.assertEquals(Tipo.REMERAMANGASCORTAS, remera.getTipo());
		Assert.assertEquals(Categoria.SUPERIOR, remera.getCategoria());
		Assert.assertEquals(Material.ALGODON, remera.getMaterial());
		Assert.assertEquals(Color.BLACK, remera.getColorPrimario());
		Assert.assertEquals(Color.WHITE, remera.getColorSecundario());
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	
	@Test
	public void deberiaTirarExcepcionNullPointerPorTipo() throws Exception {
		expectedEx.expect(NullPointerException.class);
		
		new Prenda(new TipoDePrenda(null, Categoria.SUPERIOR, Arrays.asList(Material.ALGODON), 1), Material.ALGODON, Color.BLACK, null, null);
	}
	
	@Test
	public void deberiaTirarExcepcionNullPointerPorCategoria() throws Exception {
		expectedEx.expect(NullPointerException.class);
		
		new Prenda(new TipoDePrenda(Tipo.REMERAMANGASCORTAS, null, Arrays.asList(Material.ALGODON), 1), Material.ALGODON, Color.BLACK, null, null);
	}
	
	@Test
	public void deberiaTirarExcepcionNullPointerPorMaterial() throws Exception {
		expectedEx.expect(NullPointerException.class);
		
		new Prenda(TipoDePrendaFactory.remeraMangaCorta(), null, Color.BLACK, null, null);
	}
	
	@Test
	public void deberiaTirarExcepcionNullPointerPorColorPrincipal() throws Exception {
		expectedEx.expect(NullPointerException.class);
		
		new Prenda(TipoDePrendaFactory.remeraMangaCorta(), Material.ALGODON, null, null, null);
	}

	@Test
	public void deberiaTirarExcepcionColoresIguales() throws Exception {
	    expectedEx.expect(ColoresIgualesException.class);
	    expectedEx.expectMessage("Los colores son iguales");
	    // Codigo que deberia tirar la excepcion
	    new Prenda(TipoDePrendaFactory.remeraMangaCorta(), Material.ALGODON, Color.black, Color.black, null);
	}

	@Test
	public void deberiaTirarExcepcionMaterialInvalido() throws Exception {
	    expectedEx.expect(MaterialInvalidoException.class);
	    expectedEx.expectMessage("Material invalido");
	    // Codigo que deberia tirar la excepcion
	    new Prenda(TipoDePrendaFactory.remeraMangaCorta(), Material.CUERO, Color.black, Color.black, null);
	}
	
}
