package Que_me_pongo;

import net.coobird.thumbnailator.Thumbnails;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

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

    public BufferedImage redimensionar(String path){
        BufferedImage img = null;
        try{
            img = new BufferedImage(ancho,alto,BufferedImage.TYPE_INT_ARGB);
            img = ImageIO.read(new File(path));
            img = Thumbnails.of(img).forceSize(ancho, alto).asBufferedImage();
            /*con forceSize se fuerza la imagen a esos valores perdiendo calidad,
            en cambio con size solo se fuerza algun valor y se preserva el aspecto*/
        }
        catch (IOException e){
            System.out.println("Error imagen: "+e);
        }

        return img;
    }
}

