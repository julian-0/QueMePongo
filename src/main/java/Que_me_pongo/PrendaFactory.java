package Que_me_pongo;
import java.awt.Color;
import java.util.LinkedList;
import java.util.List;


public class PrendaFactory {
	
		private static Prenda SinAccesorio = new Prenda(Tipo.NOACCESSORIO, Categoria.ACCESORIO, Material.SINMATERIAL, new Color(0, 0, 0, 0), null);

    public PrendaFactory() {
    }
    
    static private Prenda crearPrenda(Tipo tipo, Categoria categoria, Material material, Color colorPrimario, Color colorSecundario, List<Material> materialesValidos) {
    	PrendaFactory.validarMateriales(material, materialesValidos);
    	return new Prenda(tipo, categoria, material, colorPrimario, colorSecundario);	
    }
    
    static public Prenda crearAnteojos(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.PLASTICO);
    	return crearPrenda(Tipo.ANTEOJOS, Categoria.ACCESORIO, material, colorPrimario, colorSecundario, materiales);
    }
    
    static public Prenda crearRemeraMangaCorta(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.SEDA);
    	materiales.add(Material.DRIFIT);
    	return crearPrenda(Tipo.REMERAMANGASCORTAS, Categoria.SUPERIOR, material, colorPrimario, colorSecundario, materiales);
    }
    
    static public Prenda crearBuzo(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.SEDA);
    	return crearPrenda(Tipo.BUZO, Categoria.SUPERIOR, material, colorPrimario, colorSecundario, materiales);
    }
    
    static public Prenda crearShort(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.ALGODON);
    	materiales.add(Material.DRIFIT);
    	return crearPrenda(Tipo.SHORT, Categoria.INFERIOR, material, colorPrimario, colorSecundario, materiales);
    }
    
    
    static public Prenda crearZapatosDeTacon(Material material, Color colorPrimario, Color colorSecundario) {
    	List<Material> materiales = new LinkedList<Material>();
    	materiales.add(Material.CUERO);
    	return crearPrenda(Tipo.ZAPATOSDETACON, Categoria.CALZADO, material, colorPrimario, colorSecundario, materiales);
    }
    
    static public Prenda noAccesorio() {
    	return PrendaFactory.SinAccesorio;
    }
	
    static private void validarMateriales(Material material, List<Material> materialesValidos) {
		if(!materialesValidos.contains(material))
			throw new MaterialInvalidoException("Material invalido");
    }

}
