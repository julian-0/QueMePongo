import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

import Que_me_pongo.Material;
import Que_me_pongo.Prenda;
import Que_me_pongo.PrendaFactory;
import Que_me_pongo.Usuario;
import Que_me_pongo.Guardarropa;;

public class UsuarioTest {
	Prenda remeraA = PrendaFactory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, null);
	Prenda remeraB = PrendaFactory.crearRemeraMangaCorta(Material.ALGODON, Color.WHITE, null);
	Prenda pantalonA = PrendaFactory.crearShort(Material.ALGODON, Color.BLACK, null);
	Prenda pantalonB = PrendaFactory.crearShort(Material.ALGODON, Color.PINK, null);
	Prenda accesorioA = PrendaFactory.crearAnteojos(Material.PLASTICO, Color.ORANGE, null);
	Prenda zapatoA = PrendaFactory.crearZapatosDeTacon(Material.CUERO, Color.BLUE, null);
	Prenda zapatoB = PrendaFactory.crearZapatosDeTacon(Material.CUERO, Color.GREEN, null);
	
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
		Usuario usuario = new Usuario();
		
		Assert.assertEquals(0, usuario.getGuardarropas().size());
		usuario.agregarGuardarropas();
		Assert.assertEquals(1, usuario.getGuardarropas().size());
	}
	
	@Test
	public void usuarioDevuelveLosAtuendosCorrectamente() {
		Usuario usuario = new Usuario();
		usuario.agregarGuardarropas();
		usuario.agregarGuardarropas();
		
		Set<List<Prenda>> conjunto1 = new HashSet<>();
		conjunto1.addAll(Arrays.asList(atuendoA, atuendoC));
		
		Set<List<Prenda>> conjunto2 = new HashSet<>();
		conjunto2.addAll(Arrays.asList(atuendoP));
		
		List<Guardarropa> guardarropas = new ArrayList<>();
		usuario.getGuardarropas().forEach(guardarropa -> guardarropas.add(guardarropa));
		
		guardarropas.get(0).agregarPrenda(remeraA);
		guardarropas.get(0).agregarPrenda(pantalonA);
		guardarropas.get(0).agregarPrenda(zapatoA);
		guardarropas.get(0).agregarPrenda(accesorioA);
		
		guardarropas.get(1).agregarPrenda(remeraB);
		guardarropas.get(1).agregarPrenda(pantalonB);
		guardarropas.get(1).agregarPrenda(zapatoB);
		
		Set<List<Prenda>> atuendos = usuario.atuendos();
		
		Assert.assertEquals(conjunto1.size() + conjunto2.size(), atuendos.size());
		conjunto1.forEach(atuendo -> Assert.assertTrue(atuendos.contains(atuendo)));
		conjunto2.forEach(atuendo -> Assert.assertTrue(atuendos.contains(atuendo)));
	}

}
