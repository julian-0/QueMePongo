import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;

import Que_me_pongo.configuraciones.Configuraciones;
import Que_me_pongo.prenda.Material;
import Que_me_pongo.prenda.Prenda;
import Que_me_pongo.proveedorClima.ProveedorClima;
import Que_me_pongo.sugeridor.Sugeridor;

import java.awt.Color;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class SugeridorTest {
	static Prenda remeraMangaCorta = PrendaFactory.remeraMangaCorta(Material.ALGODON, Color.black, null),
				 remeraMangaLarga = PrendaFactory.remeraMangaLarga(Material.ALGODON, Color.black, null),
				 buzo = PrendaFactory.buzo(Material.ALGODON, Color.black, null),
				 zapatosDeTacon = PrendaFactory.zapatosDeTacon(Material.CUERO, Color.black, null),
				 shorts = PrendaFactory.shorts(Material.ALGODON, Color.black, null),
				 pantalon = PrendaFactory.pantalon(Material.ALGODON, Color.black, null),
				 guantes = PrendaFactory.guantes(Material.ALGODON, Color.black, null);

	static List<Prenda> atuendo1 = Arrays.asList(remeraMangaCorta, shorts, zapatosDeTacon), //3NA - 18°
							 atuendo2 = Arrays.asList(remeraMangaCorta, shorts, zapatosDeTacon, guantes), //5NA - 15°
							 atuendo3 = Arrays.asList(remeraMangaCorta, shorts, zapatosDeTacon, buzo), //5
							 atuendo4 = Arrays.asList(remeraMangaCorta, shorts, zapatosDeTacon, buzo, guantes), //7NA - 12°
							 atuendo5 = Arrays.asList(remeraMangaCorta, pantalon, zapatosDeTacon), //4NA - 16.5°
							 atuendo6 = Arrays.asList(remeraMangaCorta, pantalon, zapatosDeTacon, guantes), //6NA - 13,5°
							 atuendo7 = Arrays.asList(remeraMangaCorta, pantalon, zapatosDeTacon, buzo), //6
							 atuendo8 = Arrays.asList(remeraMangaCorta, pantalon, zapatosDeTacon, buzo, guantes), //8NA - 10,5
							 atuendo9 = Arrays.asList(remeraMangaLarga, shorts, zapatosDeTacon), //3.5NA - 17.25°
							 atuendo10 = Arrays.asList(remeraMangaLarga, shorts, zapatosDeTacon, guantes), //5.5NA - 14.25°
							 atuendo11 = Arrays.asList(remeraMangaLarga, shorts, zapatosDeTacon, buzo), //5.5
							 atuendo12 = Arrays.asList(remeraMangaLarga, shorts, zapatosDeTacon, buzo, guantes), //7.5NA - 11.25°
							 atuendo13 = Arrays.asList(remeraMangaLarga, pantalon, zapatosDeTacon), //4.5NA - 15.75°
							 atuendo14 = Arrays.asList(remeraMangaLarga, pantalon, zapatosDeTacon, guantes), //6.5NA - 12.75
							 atuendo15 = Arrays.asList(remeraMangaLarga, pantalon, zapatosDeTacon, buzo), //6.5
							 atuendo16 = Arrays.asList(remeraMangaLarga, pantalon, zapatosDeTacon, buzo, guantes); //8.5NA - 9.75° 
	
	static Set<List<Prenda>> atuendos = new HashSet<List<Prenda>>(
																								Arrays.asList(atuendo1, atuendo2, atuendo3, atuendo4,
																															atuendo5, atuendo6, atuendo7, atuendo8,
																															atuendo9, atuendo10, atuendo11, atuendo12,
																															atuendo13, atuendo14, atuendo15, atuendo16));
	
	public static <T1, T2> boolean listContainsIgnoreOrder(Collection<List<T1>> list1, List<T2> list2) {
		return list1.stream().anyMatch(element1 -> new HashSet<>(element1).equals(new HashSet<>(list2)));
	}
	
	@Mock
	ProveedorClima climaMock;
	
	@Before
	public void setClimaMock()
	{
		Configuraciones.set(ProveedorClima.class, climaMock);
	}
	
	@Test
	public void funcionaRangoBasico() {
		LocalDate fecha = LocalDate.now();
		Sugeridor sug = new Sugeridor(2, 4, 1, fecha);
		Set<List<Prenda>> atuendosFinales = new HashSet<List<Prenda>>(
																						Arrays.asList(atuendo1, atuendo5, atuendo9));
		testDeSugerencia(atuendosFinales, sug, fecha, 18.);
	}
	
	@Test
	public void usaRangoExtendido() {
		LocalDate fecha = LocalDate.now();
		Sugeridor sug = new Sugeridor(0.5, 2, 2, fecha);
		Set<List<Prenda>> atuendosFinales = new HashSet<List<Prenda>>(
																						Arrays.asList(atuendo1, atuendo5, atuendo9));
		testDeSugerencia(atuendosFinales, sug, fecha, 18.);
	}
	
	public void testDeSugerencia(Set<List<Prenda>> esperados, Sugeridor sugeridor, LocalDate fecha, Double temp) {
		Mockito.when(climaMock.getTemp(fecha)).thenReturn(temp);
		Set<List<Prenda>> resultado = sugeridor.sugerir(atuendos);
		
		Assert.assertEquals(esperados.size(), resultado.size());
		resultado.forEach(atuendoRecibido -> Assert.assertTrue(listContainsIgnoreOrder(esperados, atuendoRecibido)));
	}

}
