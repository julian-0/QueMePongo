package Que_me_pongo.administradorAtuendos;


import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Deque;

import Que_me_pongo.prenda.Prenda;

public class AdministradorDeAtuendos {

	private Deque<List<Prenda>> prendasPendientes = new LinkedList<List<Prenda>>(), 
			 aceptados = new LinkedList<List<Prenda>>(),
			 rechazados = new LinkedList<List<Prenda>>();

	private Deque<Boolean> estadosDeAceptacion = new LinkedList<Boolean>();
	
	public void agregarAtuendos(Set<List<Prenda>> prendas) {
		this.prendasPendientes.addAll(prendas);
	}
	
	public void aceptar() {
		this.checkPendientes();
		this.moverEntreListas(prendasPendientes, aceptados);
		estadosDeAceptacion.addFirst(true);
	}
	
	public void rechazar() {
		this.checkPendientes();
		this.moverEntreListas(prendasPendientes, rechazados);
		estadosDeAceptacion.addFirst(false);
	}
	
	public void deshacer() {
		if(estadosDeAceptacion.isEmpty())
			throw new NoHistorialException();
		
		if(estadosDeAceptacion.removeFirst())
			this.moverEntreListas(aceptados, prendasPendientes);
		else
			this.moverEntreListas(rechazados, prendasPendientes);
	}
	
	private void moverEntreListas(Deque<List<Prenda>> desde, Deque<List<Prenda>> hasta) {
		hasta.addFirst(desde.removeFirst());
	}
	
	private void checkPendientes() {
		if(this.prendasPendientes.isEmpty())
			throw new NoPendientesException();
	}
	
	

}
