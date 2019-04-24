package Que_me_pongo;

import java.awt.*;
import java.util.Objects;

public class Prenda {
	private Tipo tipo;
	private Categoria categoria;
	private Material material;
	private Color colorPrimario;
	private Color colorSecundario;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (!(o instanceof Prenda)) return false;
		Prenda prenda = (Prenda) o;
		return getTipo() == prenda.getTipo() &&
				getCategoria() == prenda.getCategoria() &&
				getMaterial() == prenda.getMaterial() &&
				getColorPrimario().equals(prenda.getColorPrimario()) &&
				Objects.equals(getColorSecundario(), prenda.getColorSecundario());
	}

	@Override
	public int hashCode() {
		return Objects.hash(getTipo(), getCategoria(), getMaterial(), getColorPrimario(), getColorSecundario());
	}

	@Override
	public String toString() {
		return "Prenda{" +
				"tipo=" + tipo +
				", categoria=" + categoria +
				", material=" + material +
				", colorPrimario=" + colorPrimario +
				", colorSecundario=" + colorSecundario +
				'}';
	}

	public Prenda (Tipo tipo, Categoria categoria, Material material, Color colorPrimario, Color colorSecundario) {
		this.tipo = Objects.requireNonNull(tipo, "es obligatorio introducir un tipo");
		this.categoria = Objects.requireNonNull(categoria, "es obligatorio introducir una categoria");
		this.material = Objects.requireNonNull(material, "es obligatorio introducir un material");
		this.colorPrimario = Objects.requireNonNull(colorPrimario, "es obligatorio introducir un color primario");		
		this.colorSecundario = colorSecundario;
		
	}

	public Tipo getTipo() {
		return tipo;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public Material getMaterial() {
		return material;
	}

	public Color getColorPrimario() {
		return colorPrimario;
	}

	public Color getColorSecundario() {
		return colorSecundario;
	}

}