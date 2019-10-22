package que_me_pongo.prenda;

import java.awt.*;

public class PrendaBuilder {
    private Prenda prenda;
    private String tipo;

    public PrendaBuilder() {this.prenda = new Prenda();}

    public void buildTipo(String t){tipo=t;}

    public void buildMaterial(Material material){prenda.setMaterial(material);}

    public void buildColorPrimario(Color colorPrimario){prenda.setColorPrimario(colorPrimario);}

    public void buildColorSecundario(Color colorSecundario){prenda.setColorSecundario(colorSecundario);}

    public void buildImagen(String path){prenda.setImagen(path);}

    public Prenda getPrenda() {return prenda; }

    public void buildPrenda(){
        try {
            prenda.setTipo(TipoDePrendaFactory.parse(this.tipo));
            RepositorioPrendas.getInstance().createPrenda(prenda);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        }
    }
}
