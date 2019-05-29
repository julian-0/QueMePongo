package Que_me_pongo.usuario;

import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;

import Que_me_pongo.administradorAtuendos.AdministradorDeAtuendos;
import Que_me_pongo.evento.Evento;
import Que_me_pongo.guardarropa.Guardarropa;
import Que_me_pongo.prenda.Prenda;

import java.util.HashSet;
import java.util.List;

public class Usuario {
	private Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();

	private TipoUsuario tipoUsuario;

	private Set<Evento> eventos = new HashSet<Evento>();
	
	private AdministradorDeAtuendos adminAtuendos = new AdministradorDeAtuendos();

	public Usuario(TipoUsuario tipo){

		this.tipoUsuario = tipo;

	}

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

	public void agregarEvento(Evento evento){//Agrego un evento
		this.eventos.add(evento);
	}

	public void recolectarAtuendos(Set<List<Prenda>> atuendos){
		this.adminAtuendos.agregarAtuendos(atuendos);
	}
	
	public void aceptarAtuendo() {
		adminAtuendos.aceptar();
	}
	
	public void rechazarAtuendo() {
		adminAtuendos.rechazar();
	}
	
	public void deshacerOpinion() {
		adminAtuendos.deshacer();
	}

}