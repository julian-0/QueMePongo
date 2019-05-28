package Que_me_pongo;

import java.util.BitSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class AdministradorDePrendas {

	private List<List<Prenda>> prendasPendientes = new LinkedList<List<Prenda>>(), 
			 aceptados = new LinkedList<List<Prenda>>(),
			 rechazados = new LinkedList<List<Prenda>>();

	private BitSet ultimaPrendaAceptada = new BitSet();
	
	private int lastBit = -1;
	
	public void agregarAtuendos(Set<List<Prenda>> prendas) {
		this.prendasPendientes.addAll(prendas);
	}
	
	public void aceptar() {
		this.moverEntreListas(prendasPendientes, aceptados, 1);
	}
	
	public void rechazar() {
		this.moverEntreListas(prendasPendientes, rechazados, 1);
	}
	
	public void deshacer() {
		if(ultimaPrendaAceptada.get(lastBit)) {
			this.moverEntreListas(aceptados, prendasPendientes, -1);
		}
		else {
			this.moverEntreListas(rechazados, prendasPendientes, -1);
		}
	}
	
	private void moverEntreListas(List<List<Prenda>> desde, List<List<Prenda>> hasta, int movimiento) {
		hasta.add(0, desde.get(0));
		desde.remove(0);
		lastBit += movimiento;
	}
	
	

}
