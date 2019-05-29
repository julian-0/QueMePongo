package Que_me_pongo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.google.common.collect.Range;

public class Sugeridor {
	static public Set<List<Prenda>> sugerir(Set<List<Prenda>> atuendos, double celsius) {
		ConfiguracionesSugeridor conf = Configuraciones.get(ConfiguracionesSugeridor.class); 
		Set<List<Prenda>> sugeridos = Sugeridor.filtrar(atuendos, celsius, conf.getMargenBase());
		
		if(sugeridos.size() <= conf.getCantParaExtender())
			sugeridos = Sugeridor.filtrar(atuendos, celsius, conf.getMargenExtendido());
		
		return sugeridos;
	}
	
	static private Set<List<Prenda>> filtrar(Set<List<Prenda>> atuendos, double celsius, double margen) {
		return atuendos.stream().
		 filter(atuendo -> Sugeridor.sugerirAtuendo(atuendo, celsius, margen)).
		 collect(Collectors.toSet());
	}
	
	static private boolean sugerirAtuendo(List<Prenda> atuendo, double celsius, double margen) {
		double nivelAbrigoTotal = atuendo.stream().reduce(.0, Sugeridor::reducirNivelAbrigo, (n1, n2) -> n1 + n2); 
		return Range.open(celsius-margen, celsius+margen).contains(1 / nivelAbrigoTotal);
	}
	
	static private double reducirNivelAbrigo(double nivelPrenda1, Prenda prenda2) {
		return nivelPrenda1 + prenda2.getNivelAbrigo();
	}
	

}
