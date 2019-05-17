package Que_me_pongo;

import java.util.List;
import java.util.Objects;

public class TipoDePrenda {
	private Tipo tipo;
	private Categoria categoria;
	private List<Material> materialesValidos;
	
	public TipoDePrenda(Tipo tipo, Categoria categoria, List<Material> materialesValidos) {
		this.tipo = Objects.requireNonNull(tipo);
		this.categoria = Objects.requireNonNull(categoria);
		this.materialesValidos = Objects.requireNonNull(materialesValidos);
	}
	
	public Boolean validarMaterial(Material material) {
		return materialesValidos.contains(material);
	}
	
	public Tipo getTipo() {
		return this.tipo;
	}
	
	public Categoria getCategoria() {
		return this.categoria;
	}
}
