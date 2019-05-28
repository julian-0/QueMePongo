package Que_me_pongo;

public class Gratuito implements TipoUsuario {

    public int cantidadMaxima= 5;
    public void agregarPrenda(Prenda prenda,Guardarropa guardarropa){

        if(guardarropa.estaLleno(cantidadMaxima)){
            throw new UsuarioGratuitoNoTieneLugarException("Su guardarropas esta lleno, si desea tener mas lugar puede hacerse socio premium");
        }
        else{
            guardarropa.agregarPrenda(prenda);
        }
    }

}
