package que_me_pongo.usuario;

import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.prenda.Prenda;

public class Premium implements TipoUsuario {

    public void agregarPrenda(Prenda prenda,Guardarropa guardarropa){

        guardarropa.agregarPrenda(prenda);

    }

}
