package que_me_pongo.prenda;

import javax.persistence.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.time.LocalDate;
import java.util.Objects;
import java.util.Set;
import java.util.LinkedHashSet;

@Entity
public class Prenda {

	@Id @GeneratedValue
	private long id;
	@ManyToOne
	private TipoDePrenda tipo;
	@Enumerated(EnumType.STRING)
	private Material material;
	private Color colorPrimario;
	private Color colorSecundario;
	private BufferedImage imagen = null;
	private Set<LocalDate> reservas = new LinkedHashSet<LocalDate>();

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

	public Prenda (TipoDePrenda tipo, Material material, Color colorPrimario, Color colorSecundario, String path) {
		this.tipo = Objects.requireNonNull(tipo, "es obligatorio introducir un tipo");
		this.material = Objects.requireNonNull(material, "es obligatorio introducir un material");
		this.validarMateriales(material);
		this.colorPrimario = Objects.requireNonNull(colorPrimario, "es obligatorio introducir un color primario");
		this.validarColor(colorPrimario, colorSecundario);
		this.colorSecundario = colorSecundario;
		if(path != null)
			this.imagen = RedimensionadorImagen.getInstance().redimensionar(path);
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

	public BufferedImage getImagen() {
		return imagen;
	}

	public int getCapa() {
		return this.tipo.getCapa();
	}
	
	public double getNivelAbrigo() {
		return this.tipo.getNivelAbrigo();
	}
	
	public void addReserva(LocalDate fecha) {
		if(getReserva(fecha))
			throw new PrendaYaReservadaException();
		reservas.add(fecha);
	}
	
	public void removeReserva(LocalDate fecha) {
		if(!getReserva(fecha))
			throw new PrendaNoReservadaException();
		reservas.remove(fecha);
	}
	
	public boolean getReserva(LocalDate fecha) {
		return reservas.contains(fecha);
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