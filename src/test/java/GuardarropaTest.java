import Que_me_pongo.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.List;



public class GuardarropaTest {
	Prenda remera = PrendaFactory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, null);
	Prenda remeraB = PrendaFactory.crearRemeraMangaCorta(Material.ALGODON, Color.WHITE, null);
	Prenda pantalonA = PrendaFactory.crearShort(Material.ALGODON, Color.BLACK, null);
	Prenda pantalonB = PrendaFactory.crearShort(Material.ALGODON, Color.PINK, null);
	Prenda accesorioA = PrendaFactory.crearAnteojos(Material.PLASTICO, Color.ORANGE, null);
	Prenda zapatoA = PrendaFactory.crearZapatosDeTacon(Material.CUERO, Color.BLUE, null);
	Prenda zapatoB = PrendaFactory.crearZapatosDeTacon(Material.CUERO, Color.GREEN, null);
	
	List<Prenda> atuendoA = Arrays.asList(remera, pantalonA, zapatoA, accesorioA);
	List<Prenda> atuendoB = Arrays.asList(remera, pantalonA, zapatoB, accesorioA);
	List<Prenda> atuendoC = Arrays.asList(remera, pantalonA, zapatoA);
	List<Prenda> atuendoD = Arrays.asList(remera, pantalonA, zapatoB);
	List<Prenda> atuendoE = Arrays.asList(remera, pantalonB, zapatoA, accesorioA);
	List<Prenda> atuendoF = Arrays.asList(remera, pantalonB, zapatoB, accesorioA);
	List<Prenda> atuendoG = Arrays.asList(remera, pantalonB, zapatoA);
	List<Prenda> atuendoH = Arrays.asList(remera, pantalonB, zapatoB);
	List<Prenda> atuendoI = Arrays.asList(remeraB, pantalonA, zapatoA, accesorioA);
	List<Prenda> atuendoJ = Arrays.asList(remeraB, pantalonA, zapatoB, accesorioA);
	List<Prenda> atuendoK = Arrays.asList(remeraB, pantalonA, zapatoA);
	List<Prenda> atuendoL = Arrays.asList(remeraB, pantalonA, zapatoB);
	List<Prenda> atuendoM = Arrays.asList(remeraB, pantalonB, zapatoA, accesorioA);
	List<Prenda> atuendoN = Arrays.asList(remeraB, pantalonB, zapatoB, accesorioA);
	List<Prenda> atuendoO = Arrays.asList(remeraB, pantalonB, zapatoA);
	List<Prenda> atuendoP = Arrays.asList(remeraB, pantalonB, zapatoB);

	@Test
	public void cargaGuardarropaEnUsuario() {
		Usuario usuario = new Usuario();
		usuario.agregarGuardarropas();
	}

	@Test
	public void cargaRemeraEnGuardarropa() {
		Guardarropa guardarropa = new Guardarropa();
		Assert.assertEquals(0, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
		guardarropa.agregarPrenda(remera);
		Assert.assertEquals(1, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
	@Test
	public void noCargaDosVecesLoMismo() throws Exception {
		expectedEx.expect(PrendaYaEnGuardarropasException.class);
		Guardarropa guardarropa = new Guardarropa();
		guardarropa.agregarPrenda(remera);
		guardarropa.agregarPrenda(remera);
	}
	
	@Test 
	public void cargarPrendaConNullGeneraNullPointerException() throws Exception {
		expectedEx.expect(NullPointerException.class);
		(new Guardarropa()).agregarPrenda(null);
	}

	@Test
	public void generaCorrectamenteLosAtuendos() {
		Guardarropa guardarropa = new Guardarropa();
		guardarropa.agregarPrenda(remera);
		guardarropa.agregarPrenda(remeraB);
		guardarropa.agregarPrenda(pantalonA);
		guardarropa.agregarPrenda(pantalonB);
		guardarropa.agregarPrenda(accesorioA);
		guardarropa.agregarPrenda(zapatoA);
		guardarropa.agregarPrenda(zapatoB);

		Set<List<Prenda>> setAtuendos = new HashSet<>();
		setAtuendos.addAll(Arrays.asList(atuendoA, atuendoB, atuendoC, atuendoD, 
				atuendoE, atuendoF, atuendoG, atuendoH, atuendoI, atuendoJ, atuendoK,
				atuendoL, atuendoM, atuendoN, atuendoO, atuendoP));
		
		Set<List<Prenda>> atuendos = guardarropa.atuendos();

		Assert.assertEquals(setAtuendos.size(), atuendos.size());
		setAtuendos.forEach(atuendo -> Assert.assertTrue(atuendos.contains(atuendo)));
	}
	
	@Test
	public void sinNoTieneSuficientesPrendasGeneraUnaColeccionVacia() {
		Guardarropa guardarropa = new Guardarropa();
		
		guardarropa.agregarPrenda(remera);
		guardarropa.agregarPrenda(remeraB);
		guardarropa.agregarPrenda(pantalonA);
		
		Assert.assertTrue(guardarropa.atuendos().isEmpty());
	}
	
	@Test
	public void generaCorrectamenteAtuendosDeVariosAccesorios() {
		Prenda accesorioB = new Prenda(TipoDePrendaFactory.aros(), Material.PLASTICO, Color.blue, null);
		Guardarropa guardarropa = new Guardarropa();
		guardarropa.agregarPrenda(remera);
		guardarropa.agregarPrenda(pantalonA);
		guardarropa.agregarPrenda(zapatoA);
		guardarropa.agregarPrenda(accesorioA);
		guardarropa.agregarPrenda(accesorioB);
		
		List<Prenda> atuendoConB1 = Arrays.asList(remera, pantalonA, zapatoA, accesorioB);
		List<Prenda> atuendoConB2 = Arrays.asList(remera, pantalonA, zapatoA, accesorioA, accesorioB);
		
		Set<List<Prenda>> atuendos = guardarropa.atuendos();
		List<List<Prenda>> setAtuendos = Arrays.asList(atuendoA, atuendoC, atuendoConB1, atuendoConB2);
		
		Assert.assertEquals(setAtuendos.size(), atuendos.size());
		setAtuendos.forEach(atuendo -> Assert.assertTrue(atuendos.contains(atuendo)));
	}

}
