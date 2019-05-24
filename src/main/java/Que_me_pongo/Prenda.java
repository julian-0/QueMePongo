package Que_me_pongo;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class Prenda {
	private TipoDePrenda tipo;
	private Material material;
	private Color colorPrimario;
	private Color colorSecundario;
	private ImageIcon imagen;

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Prenda prenda = (Prenda) o;
		return tipo.equals(prenda.tipo) &&
				material == prenda.material &&
				colorPrimario.equals(prenda.colorPrimario) &&
				Objects.equals(colorSecundario, prenda.colorSecundario) &&
				Objects.equals(imagen, prenda.imagen);
	}

	@Override
	public int hashCode() {
		return Objects.hash(tipo, material, colorPrimario, colorSecundario, imagen);
	}

	@Override
	public String toString() {
		return "Prenda{" +
				"tipo=" + getTipo() +
				", categoria=" + getCategoria() +
				", material=" + material +
				", colorPrimario=" + colorPrimario +
				", colorSecundario=" + colorSecundario +
				'}';
	}

	public Prenda (TipoDePrenda tipo, Material material, Color colorPrimario, Color colorSecundario, String nombreImagen) {
		this.tipo = Objects.requireNonNull(tipo, "es obligatorio introducir un tipo");
		this.material = Objects.requireNonNull(material, "es obligatorio introducir un material");
		this.validarMateriales(material);
		this.colorPrimario = Objects.requireNonNull(colorPrimario, "es obligatorio introducir un color primario");
		this.validarColor(colorPrimario, colorSecundario);
		this.colorSecundario = colorSecundario;
		this.imagen = RedimensionadorImagen.getInstance().redimensionar(nombreImagen);
	}

	public Tipo getTipo() {
		return tipo.getTipo();
	}

	public Categoria getCategoria() {
		return tipo.getCategoria();
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

	public ImageIcon getImagen() {
		return imagen;
	}

	public int getCapa() {
		return this.tipo.getCapa();
	}
	
	private void validarColor(Color colorPrimario, Color colorSecundario) {
		if(colorPrimario.equals(colorSecundario))
			throw new ColoresIgualesException("Los colores son iguales");
	}
	
	private void validarMateriales(Material material) {
		if(!this.tipo.validarMaterial(material))
			throw new MaterialInvalidoException("Material invalido");
	}
}