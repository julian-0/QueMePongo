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
    
    public Prenda crearAnteojos(Tipo tipo, Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.PLASTICO);
    	return crearPrenda(tipo.ANTEOJOS, Categoria.ACCESORIO, material, colorPrimario, colorSecundario, materiales);
    }
    
    public Prenda crearRemera(Tipo tipo, Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.SEDA);
    	materiales.add(Material.DRIFIT);
    	return crearPrenda(tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, material, colorPrimario, colorSecundario, materiales);
    }
    
    public Prenda crearBuzo(Tipo tipo, Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.SEDA);
    	return crearPrenda(tipo.BUZO, Categoria.SUPERIOR, material, colorPrimario, colorSecundario, materiales);
    }
    
    public Prenda crearShort(Tipo tipo, Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.DRIFIT);
    	return crearPrenda(tipo.SHORT, Categoria.INFERIOR, material, colorPrimario, colorSecundario, materiales);
    }
    
    
    public Prenda crearZapatosDeTacon(Tipo tipo, Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.CUERO);
    	return crearPrenda(tipo.ZAPATOSDETACON, Categoria.CALZADO, material, colorPrimario, colorSecundario, materiales);
    }
    
    private void validarColor(Color colorPrimario, Color colorSecundario) {
		if(colorPrimario.equals(colorSecundario))
			throw new ColoresIgualesException("Los colores son iguales");
	}
	
	private void validarMateriales(Material material, List<Material> materialesValidos) {
		if(!materialesValidos.contains(material))
			throw new MaterialInvalidoException("Material invalido");
	}

}
