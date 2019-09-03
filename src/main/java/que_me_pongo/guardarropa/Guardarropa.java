package que_me_pongo.guardarropa;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Sets;

import que_me_pongo.prenda.Categoria;
import que_me_pongo.prenda.Prenda;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Guardarropa {
	private Map<Categoria,Set<Prenda>> prendas = new HashMap<Categoria, Set<Prenda>>();
	private Map<LocalDate,Set<Prenda>> reservas = new HashMap<LocalDate, Set<Prenda>>();


	public Guardarropa()
	{
		//Uso un foreach clasico porque values() devuelve un array
		for(Categoria cat : Categoria.values())
			prendas.put(cat, new HashSet<Prenda>());
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
	
	public int cantidadPrendas() {
		return this.prendas.values().stream().reduce(0, (acc, list) -> acc + list.size(), Integer::sum);
	}

	public boolean estaLleno(int cantidadMaxima){
		return this.cantidadPrendas() >= cantidadMaxima;
	}
	
	public void reservarAtuendo(LocalDate fecha, List<Prenda> atuendo) {
		atuendo.forEach(prenda -> prenda.addReserva(fecha));
	}
	
	public void liberarAtuendo(LocalDate fecha, List<Prenda> atuendo) {
		atuendo.forEach(prenda -> prenda.removeReserva(fecha));
	}
	
	public Set<List<Prenda>> atuendos(LocalDate fecha){
		return atuendos().stream().filter(atuendo -> atuendoDisponibleEnFecha(fecha, atuendo)).collect(Collectors.toSet());

	}
	
	public Set<List<Prenda>> atuendos(){
		Set<List<Prenda>> atuendosMinimos = Sets.cartesianProduct(ImmutableList.of(
				filtrarPorCapa(Categoria.SUPERIOR, 0),
				prendas.get(Categoria.INFERIOR),
				prendas.get(Categoria.CALZADO)
				));
		
		Set<List<List<Prenda>>> paresCombinacionAccesorio = Sets.cartesianProduct(
				ImmutableList.of(atuendosMinimos, subConjuntos(prendas.get(Categoria.ACCESORIO))));
		
		Set<List<Prenda>> atuendosConAccesorios = aplanarAtuendos(paresCombinacionAccesorio);
		
		Set<List<Prenda>> atuendosConSuperiores = agregarCapas(Categoria.SUPERIOR, atuendosConAccesorios);
		
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
		
		Stream<Set<List<Prenda>>> subConjuntosPorCapa = IntStream.rangeClosed(1, maximaCapa).
				<Set<List<Prenda>>>mapToObj(capa -> this.subConjuntos(this.filtrarPorCapa(categoria, capa)));
		
		return subConjuntosPorCapa.reduce(atuendosBase, this::accumulator);
	}
	
	private Set<List<Prenda>> accumulator(Set<List<Prenda>> atuendosBase, Set<List<Prenda>> subConjuntos) {
		Set<List<List<Prenda>>> combinaciones = Sets.cartesianProduct(
				ImmutableList.of(atuendosBase, subConjuntos));
		return this.aplanarAtuendos(combinaciones);
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
	
	private boolean atuendoDisponibleEnFecha(LocalDate fecha, List<Prenda> atuendo) {
		return atuendo.stream().noneMatch(prenda -> prenda.getReserva(fecha));
	}



}
