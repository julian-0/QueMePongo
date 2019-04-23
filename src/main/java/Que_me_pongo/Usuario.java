package Que_me_pongo;

import java.awt.*;
import java.util.List;
import java.util.LinkedList;

public class Usuario {
	List<Guardarropa> guardarropas = new LinkedList<Guardarropa>();

//	La forma de instanciar una prenda sería:
//	Prenda miRemera = new PrendaFactory().crearRemera(Tipo.REMERAMANGASCORTAS, Material.ALGODON, Color.WHITE);

	public Usuario(){
	}

	public void agregarGuardarropas(){
		guardarropas.add(new Guardarropa());
	}

	public void validarGuardarropa(Guardarropa guardarropa){
		if(!guardarropas.contains(guardarropa))
			throw new GuardarropaInvalidoException("Usted no contiene ese guardarropas");
	}

	public void agregarPrenda(Prenda prenda,Guardarropa guardarropa){
		this.validarGuardarropa(guardarropa);
		guardarropa.agregarPrenda(prenda);
	}

}
