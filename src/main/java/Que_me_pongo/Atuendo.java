package Que_me_pongo;

import java.util.List;
import java.util.Objects;

public class Atuendo {
	private Prenda superior;
	private Prenda inferior;
	private Prenda calzado;
	private Prenda accesorio;
	
	public Atuendo(Prenda superior, Prenda inferior, Prenda calzado, Prenda accesorio) {
		this.superior = Objects.requireNonNull(superior);
		this.inferior = Objects.requireNonNull(inferior);
		this.calzado = Objects.requireNonNull(calzado);
		this.accesorio = accesorio;
	}

	public Atuendo(List<Prenda> listaPrenda) {

		for (Prenda prenda : listaPrenda) {
			Categoria cat = prenda.getCategoria();
			if(cat.equals(Categoria.SUPERIOR)){
				this.superior = prenda;
			} else if(cat.equals(Categoria.INFERIOR)){
				this.inferior = prenda;
			} else if(cat.equals(Categoria.CALZADO)){
				this.calzado = prenda;
			} else{
				this.accesorio = prenda;
			}
		}

	}
	
	public Prenda getSuperior() {
		return this.superior;
	}
	
	public Prenda getInferior() {
		return this.inferior;
	}
	
	public Prenda getCalzado() {
		return this.calzado;
	}
	
	public Prenda getAccesorio() {
		return this.accesorio;
	}
}
