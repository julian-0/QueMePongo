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
    
    public Prenda crearAnteojos(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.PLASTICO);
    	return crearPrenda(Tipo.ANTEOJOS, Categoria.ACCESORIO, material, colorPrimario, colorSecundario, materiales);
    }
    
    public Prenda crearRemeraMangaCorta(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.SEDA);
    	materiales.add(Material.DRIFIT);
    	return crearPrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, material, colorPrimario, colorSecundario, materiales);
    }
    
    public Prenda crearBuzo(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.SEDA);
    	return crearPrenda(Tipo.BUZO, Categoria.SUPERIOR, material, colorPrimario, colorSecundario, materiales);
    }
    
    public Prenda crearShort(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.DRIFIT);
    	return crearPrenda(Tipo.SHORT, Categoria.INFERIOR, material, colorPrimario, colorSecundario, materiales);
    }
    
    
    public Prenda crearZapatosDeTacon(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.CUERO);
    	return crearPrenda(Tipo.ZAPATOSDETACON, Categoria.CALZADO, material, colorPrimario, colorSecundario, materiales);
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
