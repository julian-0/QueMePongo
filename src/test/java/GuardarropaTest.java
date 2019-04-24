import Que_me_pongo.*;
import org.junit.Assert;
import org.junit.Test;

import java.awt.*;
import java.util.HashSet;
import java.util.Set;


public class GuardarropaTest {
	PrendaFactory factory = new PrendaFactory();
	Prenda remera = factory.crearRemeraMangaCorta(Material.SEDA, Color.BLACK, null);
	Prenda remeraB = factory.crearRemeraMangaCorta(Material.ALGODON, Color.WHITE, null);
	Prenda pantalonA = factory.crearShort(Material.ALGODON, Color.BLACK, null);
	Prenda pantalonB = factory.crearShort(Material.ALGODON, Color.PINK, null);
	Prenda accesorioA = factory.crearAnteojos(Material.PLASTICO, Color.ORANGE, null);
	Prenda accesorioB = factory.crearAnteojos(Material.PLASTICO, Color.BLUE, null);
	Prenda zapatoA = factory.crearZapatosDeTacon(Material.CUERO, Color.BLUE, null);
	Prenda zapatoB = factory.crearZapatosDeTacon(Material.CUERO, Color.GREEN, null);

	@Test
	public void CargaGuardarropaEnUsuario() {
		Usuario usuario = new Usuario();
		usuario.agregarGuardarropas();
	}

	@Test
	public void CargaRemeraEnGuardarropa() {
		
		Guardarropa guardarropa = new Guardarropa();
		Assert.assertEquals(0, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
		guardarropa.agregarPrenda(remera);
		Assert.assertEquals(1, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
	}
	
	@Test
	public void NoCargaDosVecesLoMismo() {
		
		Guardarropa guardarropa = new Guardarropa();
		Assert.assertEquals(0, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
		guardarropa.agregarPrenda(remera);
		Assert.assertEquals(1, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
		guardarropa.agregarPrenda(remera);
		Assert.assertEquals(1, guardarropa.cantidadPrendasEn(Categoria.SUPERIOR));
	}

	@Test
	public void prueba() {
		Guardarropa guardarropa = new Guardarropa();
		guardarropa.agregarPrenda(remera);
		guardarropa.agregarPrenda(remeraB);
		guardarropa.agregarPrenda(pantalonA);
		guardarropa.agregarPrenda(pantalonB);
		guardarropa.agregarPrenda(accesorioA);
		guardarropa.agregarPrenda(accesorioB);
		guardarropa.agregarPrenda(zapatoA);
		guardarropa.agregarPrenda(zapatoB);

		Atuendo atuendoA = new Atuendo(remera, pantalonA, accesorioA, zapatoA);
		Atuendo atuendoB = new Atuendo(remera, pantalonA, accesorioA, zapatoB);
		Atuendo atuendoC = new Atuendo(remera, pantalonA, accesorioB, zapatoA);
		Atuendo atuendoD = new Atuendo(remera, pantalonA, accesorioB, zapatoB);
		Atuendo atuendoE = new Atuendo(remera, pantalonB, accesorioA, zapatoA);
		Atuendo atuendoF = new Atuendo(remera, pantalonB, accesorioA, zapatoB);
		Atuendo atuendoG = new Atuendo(remera, pantalonB, accesorioB, zapatoA);
		Atuendo atuendoH = new Atuendo(remera, pantalonB, accesorioB, zapatoB);
		Atuendo atuendoI = new Atuendo(remeraB, pantalonA, accesorioA, zapatoA);
		Atuendo atuendoJ = new Atuendo(remeraB, pantalonA, accesorioA, zapatoB);
		Atuendo atuendoK = new Atuendo(remeraB, pantalonA, accesorioB, zapatoA);
		Atuendo atuendoL = new Atuendo(remeraB, pantalonA, accesorioB, zapatoB);
		Atuendo atuendoM = new Atuendo(remeraB, pantalonB, accesorioA, zapatoA);
		Atuendo atuendoN = new Atuendo(remeraB, pantalonB, accesorioA, zapatoB);
		Atuendo atuendoO = new Atuendo(remeraB, pantalonB, accesorioB, zapatoA);
		Atuendo atuendoP = new Atuendo(remeraB, pantalonB, accesorioB, zapatoB);

		Set<Atuendo> setAtuendos = new HashSet<>();
		setAtuendos.add(atuendoA);
		setAtuendos.add(atuendoB);
		setAtuendos.add(atuendoC);
		setAtuendos.add(atuendoD);
		setAtuendos.add(atuendoE);
		setAtuendos.add(atuendoF);
		setAtuendos.add(atuendoG);
		setAtuendos.add(atuendoH);
		setAtuendos.add(atuendoI);
		setAtuendos.add(atuendoJ);
		setAtuendos.add(atuendoK);
		setAtuendos.add(atuendoL);
		setAtuendos.add(atuendoM);
		setAtuendos.add(atuendoN);
		setAtuendos.add(atuendoO);
		setAtuendos.add(atuendoP);

        System.out.println(setAtuendos.toString());
        System.out.println(guardarropa.atuendos().toString());

        Assert.assertEquals(setAtuendos.size(),guardarropa.atuendos().size());
        setAtuendos.forEach(atuendo -> Assert.assertTrue(guardarropa.atuendos().contains(atuendo)));
	}

}
