package Que_me_pongo.usuario;

import java.util.Set;
import java.util.stream.Collectors;

import Que_me_pongo.evento.Evento;
import Que_me_pongo.guardarropa.Guardarropa;
import Que_me_pongo.prenda.Prenda;
import Que_me_pongo.prenda.Categoria;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

public class Usuario {
	private Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();

	private TipoUsuario tipoUsuario;

	private Set<Evento> eventos = new HashSet<Evento>();
	
	private Map<Categoria, Double> preferencias = new HashMap();

	public Usuario(TipoUsuario tipo){

		this.tipoUsuario = tipo;

	}

//	La forma de instanciar una prenda sería:
	//Prenda miRemera = new PrendaFactory().crearRemera(Material.ALGODON, Color.WHITE, Color.BLACK);
	
	public Set<Guardarropa> getGuardarropas() {
		return this.guardarropas;
	}

	public void agregarGuardarropas(Guardarropa guardarropa){
		guardarropas.add(guardarropa);
	}

	public void agregarPrenda(Prenda prenda, Guardarropa guardarropa){
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

	public void agregarEvento(Evento evento){//Agrego un evento
		this.eventos.add(evento);
	}

	public void ajustarPreferencias(Set<Categoria> aumentarAbrigo, Set<Categoria> reducirAbrigo) {
		aumentarAbrigo.forEach(categoria -> preferencias.put(categoria, preferencias.getOrDefault(categoria, 0.) + 0.25));
		reducirAbrigo.forEach(categoria -> preferencias.put(categoria, preferencias.getOrDefault(categoria, 0.) - 0.25));
	}
	
	public Double getPreferencia(Categoria categoria) {
		return preferencias.getOrDefault(categoria, 0.);
	}
	

}
