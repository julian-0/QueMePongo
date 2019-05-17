package Que_me_pongo;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;

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
		Objects.requireNonNull(prenda);
		if(prendas.get(prenda.getCategoria()).contains(prenda))
			throw new PrendaYaEnGuardarropasException();
		prendas.get(prenda.getCategoria()).add(prenda);
	}
	
	public int cantidadPrendasEn(Categoria categoria) {
		return prendas.get(categoria).size();
	}
	
	public Set<List<Prenda>> atuendos(){
		Set<List<Prenda>> combinaciones = Sets.cartesianProduct(ImmutableList.of(
				prendas.get(Categoria.SUPERIOR),
				prendas.get(Categoria.INFERIOR),
				prendas.get(Categoria.CALZADO)
				));
		
		Set<List<Prenda>> accesorios = Sets.powerSet(prendas.get(Categoria.ACCESORIO)).stream()
				.map(set -> new ArrayList<Prenda>(set)).collect(Collectors.toSet());
		
		
		Set<List<List<Prenda>>> paresCombinacionAccesorio = Sets.cartesianProduct(
				ImmutableList.of(combinaciones, accesorios));
		
		Set<List<Prenda>> retorno = paresCombinacionAccesorio.stream()
				.map(par -> par.stream().flatMap(List::stream).collect(Collectors.toList()))
				.collect(Collectors.toSet());
		
		return retorno;

	}
}
