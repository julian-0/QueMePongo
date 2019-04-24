import org.junit.Test;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;

import Que_me_pongo.Atuendo;
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
	
	Atuendo atuendoA = new Atuendo(remeraA, pantalonA, zapatoA, accesorioA);
	Atuendo atuendoB = new Atuendo(remeraA, pantalonA, zapatoB, accesorioA);
	Atuendo atuendoC = new Atuendo(remeraA, pantalonA, zapatoA, PrendaFactory.noAccesorio());
	Atuendo atuendoD = new Atuendo(remeraA, pantalonA, zapatoB, PrendaFactory.noAccesorio());
	Atuendo atuendoE = new Atuendo(remeraA, pantalonB, zapatoA, accesorioA);
	Atuendo atuendoF = new Atuendo(remeraA, pantalonB, zapatoB, accesorioA);
	Atuendo atuendoG = new Atuendo(remeraA, pantalonB, zapatoA, PrendaFactory.noAccesorio());
	Atuendo atuendoH = new Atuendo(remeraA, pantalonB, zapatoB, PrendaFactory.noAccesorio());
	Atuendo atuendoI = new Atuendo(remeraB, pantalonA, zapatoA, accesorioA);
	Atuendo atuendoJ = new Atuendo(remeraB, pantalonA, zapatoB, accesorioA);
	Atuendo atuendoK = new Atuendo(remeraB, pantalonA, zapatoA, PrendaFactory.noAccesorio());
	Atuendo atuendoL = new Atuendo(remeraB, pantalonA, zapatoB, PrendaFactory.noAccesorio());
	Atuendo atuendoM = new Atuendo(remeraB, pantalonB, zapatoA, accesorioA);
	Atuendo atuendoN = new Atuendo(remeraB, pantalonB, zapatoB, accesorioA);
	Atuendo atuendoO = new Atuendo(remeraB, pantalonB, zapatoA, PrendaFactory.noAccesorio());
	Atuendo atuendoP = new Atuendo(remeraB, pantalonB, zapatoB, PrendaFactory.noAccesorio());
	
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
		
		Set<Atuendo> conjunto1 = new HashSet<>();
		conjunto1.addAll(Arrays.asList(atuendoA, atuendoC));
		
		Set<Atuendo> conjunto2 = new HashSet<>();
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
		
		Set<Atuendo> atuendos = usuario.atuendos();
		
		Assert.assertEquals(conjunto1.size() + conjunto2.size(), atuendos.size());
		conjunto1.forEach(atuendo -> Assert.assertTrue(atuendos.contains(atuendo)));
		conjunto2.forEach(atuendo -> Assert.assertTrue(atuendos.contains(atuendo)));
	}

}
