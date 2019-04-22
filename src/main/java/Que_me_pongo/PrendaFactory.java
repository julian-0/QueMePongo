package Que_me_pongo;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;


public class PrendaFactory {

    public PrendaFactory() {
    }
    
    public Prenda crearPrenda(Tipo tipo, Categoria categoria, Material material, Color colorPrimario, Color colorSecundario, List<Material> materialesValidos) {
    	this.validarColor(colorPrimario, colorSecundario);
    	this.validarMateriales(material, materialesValidos);
    	return new Prenda(tipo, categoria, material, colorPrimario, colorSecundario);	
    }
    
    public Prenda crearRemera(Tipo tipo, Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.SEDA);
    	return crearPrenda(tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, material, colorPrimario, colorSecundario, materiales);
    }
    
    public Boolean validarColor(Color colorPrimario, Color colorSecundario) {
		if(colorPrimario.equals(colorSecundario))
			throw new ColoresIgualesException("Los colores son iguales");
		return true;
	}
	
	public Boolean validarMateriales(Material material, List<Material> materialesValidos) {
		if(materialesValidos.contains(material))
			return true;
		throw new MaterialInvalidoException("Material invalido");
	}

}
