import Que_me_pongo.*;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.*;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;


public class GuardarropaTest {
	Prenda remera = PrendaFactory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, null);
	Prenda remeraB = PrendaFactory.crearRemeraMangaCorta(Material.ALGODON, Color.WHITE, null);
	Prenda pantalonA = PrendaFactory.crearShort(Material.ALGODON, Color.BLACK, null);
	Prenda pantalonB = PrendaFactory.crearShort(Material.ALGODON, Color.PINK, null);
	Prenda accesorioA = PrendaFactory.crearAnteojos(Material.PLASTICO, Color.ORANGE, null);
	Prenda zapatoA = PrendaFactory.crearZapatosDeTacon(Material.CUERO, Color.BLUE, null);
	Prenda zapatoB = PrendaFactory.crearZapatosDeTacon(Material.CUERO, Color.GREEN, null);
	
	Atuendo atuendoA = new Atuendo(remera, pantalonA, zapatoA, accesorioA);
	Atuendo atuendoB = new Atuendo(remera, pantalonA, zapatoB, accesorioA);
	Atuendo atuendoC = new Atuendo(remera, pantalonA, zapatoA, PrendaFactory.noAccesorio());
	Atuendo atuendoD = new Atuendo(remera, pantalonA, zapatoB, PrendaFactory.noAccesorio());
	Atuendo atuendoE = new Atuendo(remera, pantalonB, zapatoA, accesorioA);
	Atuendo atuendoF = new Atuendo(remera, pantalonB, zapatoB, accesorioA);
	Atuendo atuendoG = new Atuendo(remera, pantalonB, zapatoA, PrendaFactory.noAccesorio());
	Atuendo atuendoH = new Atuendo(remera, pantalonB, zapatoB, PrendaFactory.noAccesorio());
	Atuendo atuendoI = new Atuendo(remeraB, pantalonA, zapatoA, accesorioA);
	Atuendo atuendoJ = new Atuendo(remeraB, pantalonA, zapatoB, accesorioA);
	Atuendo atuendoK = new Atuendo(remeraB, pantalonA, zapatoA, PrendaFactory.noAccesorio());
	Atuendo atuendoL = new Atuendo(remeraB, pantalonA, zapatoB, PrendaFactory.noAccesorio());
	Atuendo atuendoM = new Atuendo(remeraB, pantalonB, zapatoA, accesorioA);
	Atuendo atuendoN = new Atuendo(remeraB, pantalonB, zapatoB, accesorioA);
	Atuendo atuendoO = new Atuendo(remeraB, pantalonB, zapatoA, PrendaFactory.noAccesorio());
	Atuendo atuendoP = new Atuendo(remeraB, pantalonB, zapatoB, PrendaFactory.noAccesorio());

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
	
	@Test
	public void noCargaDosVecesLoMismo() {
		Guardarropa guardarropa = new Guardarropa();
		Assert.assertEquals(0, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
		guardarropa.agregarPrenda(remera);
		Assert.assertEquals(1, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
		guardarropa.agregarPrenda(remera);
		Assert.assertEquals(1, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
	}
	
	@Rule
	public ExpectedException expectedEx = ExpectedException.none();
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

		Set<Atuendo> setAtuendos = new HashSet<>();
		setAtuendos.addAll(Arrays.asList(atuendoA, atuendoB, atuendoC, atuendoD, 
				atuendoE, atuendoF, atuendoG, atuendoH, atuendoI, atuendoJ, atuendoK,
				atuendoL, atuendoM, atuendoN, atuendoO, atuendoP));
		
		Set<Atuendo> atuendos = guardarropa.atuendos();

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

}
