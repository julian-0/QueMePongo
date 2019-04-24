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
	}

	public void agregarPrenda(Prenda prenda) {
		Categoria categoria = prenda.getCategoria();
		prendas.get(categoria).add(prenda);
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

}
