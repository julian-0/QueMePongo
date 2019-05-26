package Que_me_pongo;

import java.util.Set;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.List;

public class Usuario {
	private Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();

	private TipoUsuario tipoUsuario;

//	La forma de instanciar una prenda sería:
	//Prenda miRemera = new PrendaFactory().crearRemera(Material.ALGODON, Color.WHITE, Color.BLACK);
	
	public Set<Guardarropa> getGuardarropas() {
		return this.guardarropas;
	}

	public void agregarGuardarropas(){
		guardarropas.add(new Guardarropa());
	}

	public void agregarPrenda(Prenda prenda,Guardarropa guardarropa){
		tipoUsuario.agregarPrenda(prenda, guardarropa);
	}
	public Set<List<Prenda>> atuendos() {
		return this.guardarropas.stream().
				flatMap(guardarropa -> guardarropa.atuendos().stream()).
				collect(Collectors.toSet());
	}
	

	public Set<List<Prenda>> atuendosDe(Guardarropa guardarropa){
		return guardarropa.atuendos();
	}


}
