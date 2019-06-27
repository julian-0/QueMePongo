import Que_me_pongo.*;
import Que_me_pongo.guardarropa.Guardarropa;
import Que_me_pongo.guardarropa.PrendaYaEnGuardarropasException;
import Que_me_pongo.prenda.Categoria;
import Que_me_pongo.prenda.Material;
import Que_me_pongo.prenda.Prenda;
import Que_me_pongo.prenda.TipoDePrendaFactory;
import Que_me_pongo.usuario.Premium;
import Que_me_pongo.usuario.Usuario;

import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.awt.Color;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.List;



public class GuardarropaTest {
	Prenda remera = new Prenda(TipoDePrendaFactory.remeraMangaCorta(),Material.SEDA, Color.BLACK, null,null);
	Prenda remeraB = new Prenda(TipoDePrendaFactory.remeraMangaCorta(),Material.ALGODON, Color.WHITE, null,null);
	Prenda pantalonA = new Prenda(TipoDePrendaFactory.shorts(),Material.ALGODON, Color.BLACK, null,null);
	Prenda pantalonB = new Prenda(TipoDePrendaFactory.shorts(),Material.ALGODON, Color.PINK, null,null);
	Prenda accesorioA = new Prenda(TipoDePrendaFactory.anteojos(),Material.PLASTICO, Color.ORANGE, null,null);
	Prenda zapatoA = new Prenda(TipoDePrendaFactory.zapatosDeTacon(),Material.CUERO, Color.BLUE, null,null);
	Prenda zapatoB = new Prenda(TipoDePrendaFactory.zapatosDeTacon(),Material.CUERO, Color.GREEN, null,null);

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

	public static <T1, T2> boolean listContainsIgnoreOrder(Collection<List<T1>> list1, List<T2> list2) {
		return list1.stream().anyMatch(element1 -> new HashSet<>(element1).equals(new HashSet<>(list2)));
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
		Prenda accesorioB = new Prenda(TipoDePrendaFactory.aros(), Material.PLASTICO, Color.blue, null,null);
		Guardarropa guardarropa = new Guardarropa();
		guardarropa.agregarPrenda(remera);
		guardarropa.agregarPrenda(pantalonA);
		guardarropa.agregarPrenda(zapatoA);
		guardarropa.agregarPrenda(accesorioA);
		guardarropa.agregarPrenda(accesorioB);

		List<Prenda> atuendoConB1 = Arrays.asList(remera, pantalonA, zapatoA, accesorioB);
		List<Prenda> atuendoConB2 = Arrays.asList(remera, pantalonA, zapatoA, accesorioB, accesorioA);

		Set<List<Prenda>> atuendos = guardarropa.atuendos();
		List<List<Prenda>> setAtuendos = Arrays.asList(atuendoA, atuendoC, atuendoConB1, atuendoConB2);

		Assert.assertEquals(setAtuendos.size(), atuendos.size());
		atuendos.forEach(atuendoRecibido -> Assert.assertTrue(listContainsIgnoreOrder(setAtuendos, atuendoRecibido)));
	}

	@Test
	public void generaAtuendosDeDosCapas() {
		Guardarropa guardarropa = new Guardarropa();
		Prenda buzo = new Prenda(TipoDePrendaFactory.buzo(),Material.ALGODON, Color.black, null,null);
		guardarropa.agregarPrenda(remera);
		guardarropa.agregarPrenda(pantalonA);
		guardarropa.agregarPrenda(zapatoA);
		guardarropa.agregarPrenda(buzo);

		List<Prenda> atuendoConBuzo = Arrays.asList(remera, pantalonA, zapatoA, buzo);
		List<Prenda> atuendoSinBuzo = Arrays.asList(remera, pantalonA, zapatoA);

		Set<List<Prenda>> atuendosGenerados = guardarropa.atuendos();
		List<List<Prenda>> atuendosEsperados = Arrays.asList(atuendoConBuzo, atuendoSinBuzo);

		Assert.assertEquals(atuendosGenerados.size(), atuendosEsperados.size());
		atuendosGenerados.forEach(generado -> Assert.assertTrue(listContainsIgnoreOrder(atuendosEsperados, generado)));
	}

	@Test
	public void generaAtuendosDeVariasCapas() {
		Guardarropa guardarropa = new Guardarropa();
		Prenda buzo = new Prenda(TipoDePrendaFactory.buzo(),Material.ALGODON, Color.black, null,null);
		Prenda chaleco = new Prenda(TipoDePrendaFactory.chaleco(),Material.CUERO, Color.black, null,null);
		guardarropa.agregarPrenda(remera);
		guardarropa.agregarPrenda(pantalonA);
		guardarropa.agregarPrenda(zapatoA);
		guardarropa.agregarPrenda(buzo);
		guardarropa.agregarPrenda(chaleco);

		List<Prenda> atuendoConBuzo = Arrays.asList(remera, pantalonA, zapatoA, buzo);
		List<Prenda> atuendoConBuzoYChaleco = Arrays.asList(remera, pantalonA, zapatoA, buzo, chaleco);
		List<Prenda> atuendoConChaleco = Arrays.asList(remera, pantalonA, zapatoA, chaleco);
		List<Prenda> atuendoSimple = Arrays.asList(remera, pantalonA, zapatoA);

		Set<List<Prenda>> atuendosGenerados = guardarropa.atuendos();
		List<List<Prenda>> atuendosEsperados = Arrays.asList(atuendoConBuzo, atuendoSimple, atuendoConChaleco, atuendoConBuzoYChaleco);

		Assert.assertEquals(atuendosGenerados.size(), atuendosEsperados.size());
		atuendosGenerados.forEach(generado -> Assert.assertTrue(listContainsIgnoreOrder(atuendosEsperados, generado)));
	}

}
