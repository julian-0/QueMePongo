import que_me_pongo.*;
import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.prenda.Material;
import que_me_pongo.prenda.Prenda;
import que_me_pongo.prenda.TipoDePrendaFactory;
import que_me_pongo.usuario.Gratuito;
import que_me_pongo.usuario.Premium;
import que_me_pongo.usuario.Usuario;
import que_me_pongo.usuario.UsuarioGratuitoNoTieneLugarException;

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
	Prenda remeraA = new Prenda(TipoDePrendaFactory.getInstance().remeraMangaCorta(),Material.SEDA, Color.BLACK, null,null);
	Prenda remeraB = new Prenda(TipoDePrendaFactory.getInstance().remeraMangaCorta(),Material.ALGODON, Color.WHITE, null,null);
	Prenda pantalonA = new Prenda(TipoDePrendaFactory.getInstance().shorts(),Material.ALGODON, Color.BLACK, null,null);
	Prenda pantalonB = new Prenda(TipoDePrendaFactory.getInstance().shorts(),Material.ALGODON, Color.PINK, null,null);
	Prenda accesorioA = new Prenda(TipoDePrendaFactory.getInstance().anteojos(),Material.PLASTICO, Color.ORANGE, null,null);
	Prenda zapatoA = new Prenda(TipoDePrendaFactory.getInstance().zapatosDeTacon(),Material.CUERO, Color.BLUE, null,null);
	Prenda zapatoB = new Prenda(TipoDePrendaFactory.getInstance().zapatosDeTacon(),Material.CUERO, Color.GREEN, null,null);

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
	public void usuarioDevuelveLosAtuendosCorrectamente() {
		Usuario usuario = new Usuario("DDS",null,new Premium());
		Guardarropa guardarropa1 = new Guardarropa(),
								guardarropa2 = new Guardarropa();
		usuario.agregarGuardarropas(guardarropa1);
		usuario.agregarGuardarropas(guardarropa2);

		Set<List<Prenda>> conjunto1 = new HashSet<>();
		conjunto1.addAll(Arrays.asList(atuendoA, atuendoC));

		Set<List<Prenda>> conjunto2 = new HashSet<>();
		conjunto2.addAll(Arrays.asList(atuendoP));

		usuario.agregarPrenda(remeraA,guardarropa1);
		usuario.agregarPrenda(pantalonA,guardarropa1);
		usuario.agregarPrenda(zapatoA,guardarropa1);
		usuario.agregarPrenda(accesorioA,guardarropa1);

		usuario.agregarPrenda(remeraB,guardarropa2);
		usuario.agregarPrenda(pantalonB,guardarropa2);
		usuario.agregarPrenda(zapatoB,guardarropa2);

		Set<List<Prenda>> atuendos = usuario.atuendos();

		Assert.assertEquals(conjunto1.size() + conjunto2.size(), atuendos.size());
		conjunto1.forEach(atuendo -> Assert.assertTrue(atuendos.contains(atuendo)));
		conjunto2.forEach(atuendo -> Assert.assertTrue(atuendos.contains(atuendo)));
	}




	@Test
	public void usuarioPremiumPuedeAgregarMuchasPrendas() {
		Usuario usuario = new Usuario("DDS",null,new Premium());
		Guardarropa guardarropa = new Guardarropa();
		usuario.agregarGuardarropas(guardarropa);

		usuario.agregarPrenda(remeraA, guardarropa);
		usuario.agregarPrenda(pantalonA,guardarropa);
		usuario.agregarPrenda(zapatoA,guardarropa);
		usuario.agregarPrenda(accesorioA,guardarropa);

		usuario.agregarPrenda(remeraB,guardarropa);
		usuario.agregarPrenda(pantalonB,guardarropa);
		usuario.agregarPrenda(zapatoB,guardarropa);


		Assert.assertEquals(7, guardarropa.cantidadPrendas());
	}


	@Rule
	public ExpectedException expectedEx = ExpectedException.none();

	@Test
	public void usuarioGratuitoNoPuedeAgregarMuchasPrendas() throws Exception {

		expectedEx.expect(UsuarioGratuitoNoTieneLugarException.class);
		expectedEx.expectMessage("Su guardarropas esta lleno, si desea tener mas lugar puede hacerse socio premium");

		Guardarropa guardarropa = new Guardarropa();
		Usuario usuario = new Usuario("DDS",null,new Gratuito());
		usuario.agregarGuardarropas(guardarropa);

		usuario.agregarPrenda(remeraA,guardarropa);
		usuario.agregarPrenda(pantalonA,guardarropa);
		usuario.agregarPrenda(zapatoA,guardarropa);
		usuario.agregarPrenda(accesorioA,guardarropa);
		usuario.agregarPrenda(remeraB,guardarropa);
		usuario.agregarPrenda(pantalonB,guardarropa);
		usuario.agregarPrenda(zapatoB,guardarropa);

	}

}
