package Que_me_pongo;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class Sugeridor {
	public Set<List<Prenda>> sugerir(Set<List<Prenda>> atuendos, int celsius) {
		return atuendos.stream().
					 filter(atuendo -> this.sugerirAtuendo(atuendo, celsius)).
					 collect(Collectors.toSet());
	}
	
	private boolean sugerirAtuendo(List<Prenda> atuendo, int celsius) {
		return true;
	}

}
