package Que_me_pongo;

import java.util.LinkedList;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.HashSet;
import java.util.List;

public class Usuario {
	private Set<Guardarropa> guardarropas = new HashSet<Guardarropa>();

	private TipoUsuario tipoUsuario;

	private Set<Evento> eventos = new HashSet<Evento>();
	
	private AdministradorDePrendas adminPrendas = new AdministradorDePrendas();

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
		this.adminPrendas.agregarAtuendos(atuendos);
	}
	
	public void aceptarAtuendo() {
		adminPrendas.aceptar();
	}
	
	public void rechazarAtuendo() {
		adminPrendas.rechazar();
	}
	
	public void deshacerOpinion() {
		adminPrendas.deshacer();
	}

}
