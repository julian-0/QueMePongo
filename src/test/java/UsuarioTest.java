import Que_me_pongo.*;
import Que_me_pongo.guardarropa.Guardarropa;
import Que_me_pongo.prenda.Material;
import Que_me_pongo.prenda.Prenda;
import Que_me_pongo.prenda.TipoDePrendaFactory;
import Que_me_pongo.usuario.Gratuito;
import Que_me_pongo.usuario.Premium;
import Que_me_pongo.usuario.Usuario;
import Que_me_pongo.usuario.UsuarioGratuitoNoTieneLugarException;

import org.junit.Rule;
import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.rules.ExpectedException;


public class UsuarioTest {
	Prenda remeraA = new Prenda(TipoDePrendaFactory.remeraMangaCorta(),Material.SEDA, Color.BLACK, null,null);
	Prenda remeraB = new Prenda(TipoDePrendaFactory.remeraMangaCorta(),Material.ALGODON, Color.WHITE, null,null);
	Prenda pantalonA = new Prenda(TipoDePrendaFactory.shorts(),Material.ALGODON, Color.BLACK, null,null);
	Prenda pantalonB = new Prenda(TipoDePrendaFactory.shorts(),Material.ALGODON, Color.PINK, null,null);
	Prenda accesorioA = new Prenda(TipoDePrendaFactory.anteojos(),Material.PLASTICO, Color.ORANGE, null,null);
	Prenda zapatoA = new Prenda(TipoDePrendaFactory.zapatosDeTacon(),Material.CUERO, Color.BLUE, null,null);
	Prenda zapatoB = new Prenda(TipoDePrendaFactory.zapatosDeTacon(),Material.CUERO, Color.GREEN, null,null);

	List<Prenda> atuendoA = Arrays.asList(remeraA, pantalonA, zapatoA, accesorioA);
	List<Prenda> atuendoB = Arrays.asList(remeraA, pantalonA, zapatoB, accesorioA);
	List<Prenda> atuendoC = Arrays.asList(remeraA, pantalonA, zapatoA);
	List<Prenda> atuendoD = Arrays.asList(remeraA, pantalonA, zapatoB);
	List<Prenda> atuendoE = Arrays.asList(remeraA, pantalonB, zapatoA, accesorioA);
	List<Prenda> atuendoF = Arrays.asList(remeraA, pantalonB, zapatoB, accesorioA);
	List<Prenda> atuendoG = Arrays.asList(remeraA, pantalonB, zapatoA);
	List<Prenda> atuendoH = Arrays.asList(remeraA, pantalonB, zapatoB);
	List<Prenda> atuendoI = Arrays.asList(remeraB, pantalonA, zapatoA, accesorioA);
	List<Prenda> atuendoJ = Arrays.asList(remeraB, pantalonA, zapatoB, accesorioA);
	List<Prenda> atuendoK = Arrays.asList(remeraB, pantalonA, zapatoA);
	List<Prenda> atuendoL = Arrays.asList(remeraB, pantalonA, zapatoB);
	List<Prenda> atuendoM = Arrays.asList(remeraB, pantalonB, zapatoA, accesorioA);
	List<Prenda> atuendoN = Arrays.asList(remeraB, pantalonB, zapatoB, accesorioA);
	List<Prenda> atuendoO = Arrays.asList(remeraB, pantalonB, zapatoA);
	List<Prenda> atuendoP = Arrays.asList(remeraB, pantalonB, zapatoB);


	@Test
	public void usuarioAgregaGuardarropas() {
		Usuario usuario = new Usuario(new Premium());

		Assert.assertEquals(0, usuario.getGuardarropas().size());
		usuario.agregarGuardarropas();
		Assert.assertEquals(1, usuario.getGuardarropas().size());
	}

	@Test
	public void usuarioDevuelveLosAtuendosCorrectamente() {
		Usuario usuario = new Usuario(new Premium());
		usuario.agregarGuardarropas();
		usuario.agregarGuardarropas();

		Set<List<Prenda>> conjunto1 = new HashSet<>();
		conjunto1.addAll(Arrays.asList(atuendoA, atuendoC));

		Set<List<Prenda>> conjunto2 = new HashSet<>();
		conjunto2.addAll(Arrays.asList(atuendoP));

		List<Guardarropa> guardarropas = new ArrayList<>();
		usuario.getGuardarropas().forEach(guardarropa -> guardarropas.add(guardarropa));

		usuario.agregarPrenda(remeraA,guardarropas.get(0));
		usuario.agregarPrenda(pantalonA,guardarropas.get(0));
		usuario.agregarPrenda(zapatoA,guardarropas.get(0));
		usuario.agregarPrenda(accesorioA,guardarropas.get(0));

		usuario.agregarPrenda(remeraB,guardarropas.get(1));
		usuario.agregarPrenda(pantalonB,guardarropas.get(1));
		usuario.agregarPrenda(zapatoB,guardarropas.get(1));

		Set<List<Prenda>> atuendos = usuario.atuendos();

		Assert.assertEquals(conjunto1.size() + conjunto2.size(), atuendos.size());
		conjunto1.forEach(atuendo -> Assert.assertTrue(atuendos.contains(atuendo)));
		conjunto2.forEach(atuendo -> Assert.assertTrue(atuendos.contains(atuendo)));
	}




	@Test
	public void usuarioPremiumPuedeAgregarMuchasPrendas() {
		Usuario usuario = new Usuario(new Premium());
		usuario.agregarGuardarropas();


		List<Guardarropa> guardarropas = new ArrayList<>();
		usuario.getGuardarropas().forEach(guardarropa -> guardarropas.add(guardarropa));

		usuario.agregarPrenda(remeraA,guardarropas.get(0));
		usuario.agregarPrenda(pantalonA,guardarropas.get(0));
		usuario.agregarPrenda(zapatoA,guardarropas.get(0));
		usuario.agregarPrenda(accesorioA,guardarropas.get(0));

		usuario.agregarPrenda(remeraB,guardarropas.get(0));
		usuario.agregarPrenda(pantalonB,guardarropas.get(0));
		usuario.agregarPrenda(zapatoB,guardarropas.get(0));


		Assert.assertEquals(7, guardarropas.get(0).cantidadPrendas());
	}


	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void usuarioGratuitoNoPuedeAgregarMuchasPrendas() throws Exception {

		expectedEx.expect(UsuarioGratuitoNoTieneLugarException.class);
		expectedEx.expectMessage("Su guardarropas esta lleno, si desea tener mas lugar puede hacerse socio premium");


		Usuario usuario = new Usuario(new Gratuito());
		usuario.agregarGuardarropas();

		List<Guardarropa> guardarropas = new ArrayList<>();
		usuario.getGuardarropas().forEach(guardarropa -> guardarropas.add(guardarropa));

		usuario.agregarPrenda(remeraA,guardarropas.get(0));
		usuario.agregarPrenda(pantalonA,guardarropas.get(0));
		usuario.agregarPrenda(zapatoA,guardarropas.get(0));
		usuario.agregarPrenda(accesorioA,guardarropas.get(0));
		usuario.agregarPrenda(remeraB,guardarropas.get(0));
		usuario.agregarPrenda(pantalonB,guardarropas.get(0));
		usuario.agregarPrenda(zapatoB,guardarropas.get(0));

	}

}
