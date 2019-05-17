package Que_me_pongo;

import java.util.List;

public class TipoDePrenda {
	private Tipo tipo;
	private Categoria categoria;
	private List<Material> materialesValidos;
	
	public TipoDePrenda(Tipo tipo, Categoria categoria, List<Material> materialesValidos) {
		this.tipo = tipo;
		this.categoria = categoria;
		this.materialesValidos = materialesValidos;
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
