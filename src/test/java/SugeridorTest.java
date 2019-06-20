import org.junit.Test;
import org.junit.Assert;
import org.junit.BeforeClass;

import Que_me_pongo.prenda.Material;
import Que_me_pongo.prenda.Prenda;

import java.awt.Color;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class SugeridorTest {
	static Prenda remeraMangaCorta = PrendaFactory.remeraMangaCorta(Material.ALGODON, Color.black, null),
				 remeraMangaLarga = PrendaFactory.remeraMangaLarga(Material.ALGODON, Color.black, null),
				 buzo = PrendaFactory.buzo(Material.ALGODON, Color.black, null),
				 zapatosDeTacon = PrendaFactory.zapatosDeTacon(Material.CUERO, Color.black, null),
				 shorts = PrendaFactory.shorts(Material.ALGODON, Color.black, null),
				 pantalon = PrendaFactory.pantalon(Material.ALGODON, Color.black, null),
				 guantes = PrendaFactory.guantes(Material.ALGODON, Color.black, null);

	static List<Prenda> atuendo1 = Arrays.asList(remeraMangaCorta, shorts, zapatosDeTacon),
							 atuendo2 = Arrays.asList(remeraMangaCorta, shorts, zapatosDeTacon, guantes),
							 atuendo3 = Arrays.asList(remeraMangaCorta, shorts, zapatosDeTacon, buzo),
							 atuendo4 = Arrays.asList(remeraMangaCorta, shorts, zapatosDeTacon, buzo, guantes),
							 atuendo5 = Arrays.asList(remeraMangaCorta, pantalon, zapatosDeTacon),
							 atuendo6 = Arrays.asList(remeraMangaCorta, pantalon, zapatosDeTacon, guantes),
							 atuendo7 = Arrays.asList(remeraMangaCorta, pantalon, zapatosDeTacon, buzo),
							 atuendo8 = Arrays.asList(remeraMangaCorta, pantalon, zapatosDeTacon, buzo, guantes),
							 atuendo9 = Arrays.asList(remeraMangaLarga, shorts, zapatosDeTacon),
							 atuendo10 = Arrays.asList(remeraMangaLarga, shorts, zapatosDeTacon, guantes),
							 atuendo11 = Arrays.asList(remeraMangaLarga, shorts, zapatosDeTacon, buzo),
							 atuendo12 = Arrays.asList(remeraMangaLarga, shorts, zapatosDeTacon, buzo, guantes),
							 atuendo13 = Arrays.asList(remeraMangaLarga, pantalon, zapatosDeTacon),
							 atuendo14 = Arrays.asList(remeraMangaLarga, pantalon, zapatosDeTacon, guantes),
							 atuendo15 = Arrays.asList(remeraMangaLarga, pantalon, zapatosDeTacon, buzo),
							 atuendo16 = Arrays.asList(remeraMangaLarga, pantalon, zapatosDeTacon, buzo, guantes);
	
	static Set<List<Prenda>> atuendos = new HashSet<List<Prenda>>(
																								Arrays.asList(atuendo1, atuendo2, atuendo3, atuendo4,
																															atuendo5, atuendo6, atuendo7, atuendo8,
																															atuendo9, atuendo10, atuendo11, atuendo12,
																															atuendo13, atuendo14, atuendo15, atuendo16));
	
	@Test
	public void pruebaDeSugerencia1() {
		
	}

}
