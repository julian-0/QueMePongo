package Que_me_pongo;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import java.util.*;

public class Guardarropa {
	public Map<Categoria,Set<Prenda>> prendas = new HashMap<Categoria, Set<Prenda>>();
	
	public Guardarropa()
	{
		prendas.put(Categoria.SUPERIOR, new HashSet<Prenda>());
		prendas.put(Categoria.INFERIOR, new HashSet<Prenda>());
		prendas.put(Categoria.CALZADO, new HashSet<Prenda>());
		prendas.put(Categoria.ACCESORIO, new HashSet<Prenda>());
		prendas.get(Categoria.ACCESORIO).add(PrendaFactory.noAccesorio());
	}

	public void agregarPrenda(Prenda prenda) {
		Objects.requireNonNull(prenda);
		prendas.get(prenda.getCategoria()).add(prenda);
	}
	
	public int cantidadPrendasEn(Categoria categoria) {
		return prendas.get(categoria).size();
	}
	
	public Set<Atuendo> atuendos(){

		Set<Atuendo> atuendosPosibles = new HashSet<>();

		Set<List<Prenda>> combinaciones = Sets.cartesianProduct(ImmutableList.of(
				prendas.get(Categoria.SUPERIOR),
				prendas.get(Categoria.INFERIOR),
				prendas.get(Categoria.CALZADO),
				prendas.get(Categoria.ACCESORIO)
				));

		combinaciones.forEach(combinacion -> atuendosPosibles.add(new Atuendo(combinacion)));

		return atuendosPosibles;

	}
	
	public Set<Atuendo> atuendos2(){

		Set<Atuendo> atuendosPosibles = new HashSet<>();

		for(Prenda sup: prendas.get(Categoria.SUPERIOR)) {
			for(Prenda inf: prendas.get(Categoria.INFERIOR)) {
				for(Prenda cal: prendas.get(Categoria.CALZADO)) {
					for(Prenda acc: prendas.get(Categoria.ACCESORIO)) {
						atuendosPosibles.add(new Atuendo(sup, inf, cal, acc));
					}
				}
			}
		}


		return atuendosPosibles;

	}

}
