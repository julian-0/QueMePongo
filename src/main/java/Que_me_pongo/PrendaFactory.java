package Que_me_pongo;

import java.awt.Color;

public class PrendaFactory {

    public PrendaFactory() {
    }

    public Prenda crearRemera(Tipo tipo, Material material, Color colorPrimario) {
        Prenda remera = new Prenda();
        remera.setTipo(tipo);
        remera.setCategoria(Categoria.SUPERIOR);
        remera.setMaterial(material);
        remera.setColorPrimario(colorPrimario);

        return remera;
    }

    public Prenda crearRemera(Tipo tipo, Material material, Color colorPrimario, Color colorSecundario) {
        Prenda remera = new Prenda();
        remera.setTipo(tipo);
        remera.setCategoria(Categoria.SUPERIOR);
        remera.setMaterial(material);
        remera.setColorPrimario(colorPrimario);
        remera.setColorSecundario(colorSecundario);

        return remera;
    }
}
