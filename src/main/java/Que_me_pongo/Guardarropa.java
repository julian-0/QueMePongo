package Que_me_pongo;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import java.util.*;
import java.util.stream.Collectors;

public class Guardarropa {
	public Map<Categoria,Set<Prenda>> prendas = new HashMap<Categoria, Set<Prenda>>();

	private int cantidadMaxima;

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
		Set<List<Prenda>> atuendosMinimos = Sets.cartesianProduct(ImmutableList.of(
				this.filtrarPorCapa(Categoria.SUPERIOR, 0),
				prendas.get(Categoria.INFERIOR),
				prendas.get(Categoria.CALZADO)
				));
		
		Set<List<List<Prenda>>> paresCombinacionAccesorio = Sets.cartesianProduct(
				ImmutableList.of(atuendosMinimos, this.subConjuntos(prendas.get(Categoria.ACCESORIO))));
		
		Set<List<Prenda>> atuendosConAccesorios = this.aplanarAtuendos(paresCombinacionAccesorio);
		
		Set<List<Prenda>> atuendosConSuperiores = this.agregarCapas(Categoria.SUPERIOR, atuendosConAccesorios);
		
		return atuendosConSuperiores;

	}
	
	private Set<List<Prenda>> subConjuntos(Set<Prenda> prendas){
		return Sets.powerSet(prendas).stream()
							.map(set -> new ArrayList<Prenda>(set)).collect(Collectors.toSet());
	}
	
	private Set<List<Prenda>> aplanarAtuendos(Set<List<List<Prenda>>> paresCombinacionAccesorio){
		return paresCombinacionAccesorio.stream()
							.map(par -> par.stream().flatMap(List::stream).collect(Collectors.toList()))
							.collect(Collectors.toSet());
	}
	
	private Set<List<Prenda>> agregarCapas(Categoria categoria, Set<List<Prenda>> atuendosBase) {
		int maximaCapa = this.getCapaMaxima(categoria);
		Set<Prenda> prendasDeCapa = null;
		Set<List<Prenda>> subConjuntoPrendasDeCapa = null;
		Set<List<List<Prenda>>> combinaciones = null;
		
		for(int capa = 1; capa <= maximaCapa; capa++) {
			prendasDeCapa = this.filtrarPorCapa(categoria, capa);
			if(prendasDeCapa.isEmpty())
				continue;
			subConjuntoPrendasDeCapa = this.subConjuntos(prendasDeCapa);
			combinaciones = Sets.cartesianProduct(
					ImmutableList.of(atuendosBase, subConjuntoPrendasDeCapa));
			atuendosBase = this.aplanarAtuendos(combinaciones);
		}
		return atuendosBase;
	}
	
	private Set<Prenda> filtrarPorCapa(Categoria categoria, int capa) {
		return prendas.get(categoria).stream().filter(prenda -> prenda.getCapa() == capa).collect(Collectors.toSet());
	}
	
	private int getCapaMaxima(Categoria categoria) {
		Optional<Prenda> max = prendas.get(categoria).stream().max(Comparator.comparing(prenda-> prenda.getCapa()));
		if(max.isPresent())
			return max.get().getCapa();
		else
			return 0;
	}

	public int cantidadPrendas() {
		return prendas.size();
	}

	public boolean estaLleno(){
		if(cantidadPrendas()< cantidadMaxima){
			return false;
		}
		else{
			return true;
		}
	}



}
