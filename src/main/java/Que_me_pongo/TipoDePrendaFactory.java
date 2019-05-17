package Que_me_pongo;

import java.util.Arrays;

public class TipoDePrendaFactory {
	static public TipoDePrenda aros() {
		return new TipoDePrenda(Tipo.AROS, Categoria.ACCESORIO, Arrays.asList(Material.PLASTICO));
	}
}
