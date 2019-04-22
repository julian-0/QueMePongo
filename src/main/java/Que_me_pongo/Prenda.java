package Que_me_pongo;

import java.awt.Color;
import java.util.List;
import java.util.Objects;

public class Prenda {
	private Tipo tipo;
	private Categoria categoria;
	private Material material;
	private Color colorPrimario;
	private Color colorSecundario;

	public Prenda (Tipo tipo,Categoria categoria, Material material, Color colorPrimario, Color colorSecundario) {
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