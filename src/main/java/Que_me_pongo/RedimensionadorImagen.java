package Que_me_pongo;

import javax.swing.*;
import java.awt.*;

public class RedimensionadorImagen {

    private int ancho = 100;
    private int alto = 100;

    private static RedimensionadorImagen instancia;

    private RedimensionadorImagen(){

    }

    public static RedimensionadorImagen getInstance(){
        if(instancia == null){
            instancia = new RedimensionadorImagen();
        }
        return instancia;
    }

    public ImageIcon redimensionar(String nombre){
        Image original = new ImageIcon(nombre).getImage();
        Image modificada = original.getScaledInstance(ancho,alto,java.awt.Image.SCALE_SMOOTH);

        return new ImageIcon(modificada);
    }
}

