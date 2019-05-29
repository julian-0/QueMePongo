package Que_me_pongo.administradorAtuendos;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import Que_me_pongo.prenda.Prenda;

public class AdministradorDeAtuendos {

	private List<List<Prenda>> prendasPendientes = new LinkedList<List<Prenda>>(), 
			 aceptados = new LinkedList<List<Prenda>>(),
			 rechazados = new LinkedList<List<Prenda>>();

	private BitSet ultimaPrendaAceptada = new BitSet();
	
	private int lastBit = -1;
	
	public void agregarAtuendos(Set<List<Prenda>> prendas) {
		this.prendasPendientes.addAll(prendas);
	}
	
	public void aceptar() {
		this.checkPendientes();
		this.moverEntreListas(prendasPendientes, aceptados, 1);
		this.ultimaPrendaAceptada.set(lastBit, true);
	}
	
	public void rechazar() {
		this.checkPendientes();
		this.moverEntreListas(prendasPendientes, rechazados, 1);
		this.ultimaPrendaAceptada.set(lastBit, false);
	}
	
	public void deshacer() {
		if(lastBit == -1)
			throw new NoHistorialException();
		
		if(ultimaPrendaAceptada.get(lastBit))
			this.moverEntreListas(aceptados, prendasPendientes, -1);
		else
			this.moverEntreListas(rechazados, prendasPendientes, -1);
	}
	
	private void moverEntreListas(List<List<Prenda>> desde, List<List<Prenda>> hasta, int movimiento) {
		hasta.add(0, desde.get(0));
		desde.remove(0);
		lastBit += movimiento;
		
		
	}
	
	private void checkPendientes() {
		if(this.prendasPendientes.isEmpty())
			throw new NoPendientesException();
	}
	
	

}
