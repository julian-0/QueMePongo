package Que_me_pongo.prenda;

import java.util.List;
import java.util.Objects;

public class TipoDePrenda {
	private Tipo tipo;
	private Categoria categoria;
	private List<Material> materialesValidos;
	private int capa;
	private double nivelAbrigo;
	
	public TipoDePrenda(Tipo tipo, Categoria categoria, List<Material> materialesValidos, int capa, double nivelAbrigo) {
		this.tipo = Objects.requireNonNull(tipo);
		this.categoria = Objects.requireNonNull(categoria);
		this.materialesValidos = Objects.requireNonNull(materialesValidos);
		this.capa = capa;
		this.nivelAbrigo = nivelAbrigo;
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
	
	public int getCapa() {
		return this.capa;
	}
	
	public double getNivelAbrigo() {
		return this.nivelAbrigo;
	}
}
