package Que_me_pongo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Range;

public class Sugeridor {
	public Set<List<Prenda>> sugerir(Set<List<Prenda>> atuendos, double celsius) {
		return atuendos.stream().
					 filter(atuendo -> this.sugerirAtuendo(atuendo, celsius, 10)).
					 collect(Collectors.toSet());
	}
	
	private boolean sugerirAtuendo(List<Prenda> atuendo, double celsius, double margen) {
		double nivelAbrigoTotal = atuendo.stream().reduce(0, this::reducirNivelAbrigo, (n1, n2) -> n1 + n2); 
		return Range.open(celsius-margen, celsius+margen).contains(1 / nivelAbrigoTotal);
	}
	
	private int reducirNivelAbrigo(int nivelPrenda1, Prenda prenda2) {
		return nivelPrenda1 + prenda2.getNivelAbrigo();
	}
	

}
