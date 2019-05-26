package Que_me_pongo;

public class Gratuito implements TipoUsuario {

    public void agregarPrenda(Prenda prenda,Guardarropa guardarropa){

        if(guardarropa.estaLleno()){
            System.out.print("Su guardarropas esta lleno, si desea tener mas lugar puede hacerse socio premium y disfrutar de todos sus beneficios");
        }
        else{
            guardarropa.agregarPrenda(prenda);
        }
    }

}
