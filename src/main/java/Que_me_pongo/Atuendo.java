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
			switch (cat) {
				case SUPERIOR:
					this.superior = prenda;
					break;
				case INFERIOR:
					this.inferior = prenda;
					break;
				case CALZADO:
					this.calzado = prenda;
					break;
				default:
					this.accesorio = prenda;
					break;
			}
		}

	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Atuendo atuendo = (Atuendo) o;
		return superior.equals(atuendo.superior) &&
				inferior.equals(atuendo.inferior) &&
				calzado.equals(atuendo.calzado) &&
				accesorio.equals(atuendo.accesorio);
	}

	@Override
	public int hashCode() {
		return Objects.hash(superior, inferior, calzado, accesorio);
	}

	@Override
	public String toString() {
		return "Atuendo{" +
				"superior=" + superior +
				", inferior=" + inferior +
				", calzado=" + calzado +
				", accesorio=" + accesorio +
				'}';
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
