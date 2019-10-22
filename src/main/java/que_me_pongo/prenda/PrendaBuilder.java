package que_me_pongo.prenda;

import java.awt.*;

public class PrendaBuilder {
    private Prenda prenda;

    public PrendaBuilder() {this.prenda = new Prenda();}

    public void buildTipo(TipoDePrenda tipo){prenda.setTipo(tipo);}

    public void buildMaterial(Material material){prenda.setMaterial(material);}

    public void buildColorPrimario(Color colorPrimario){prenda.setColorPrimario(colorPrimario);}

    public void buildColorSecundario(Color colorSecundario){prenda.setColorSecundario(colorSecundario);}

    public void buildImagen(String path){prenda.setImagen(path);}

    public Prenda getPrenda() {return prenda; }

    public void buildPrenda(){RepositorioPrendas.getInstance().createPrenda(prenda);}
}