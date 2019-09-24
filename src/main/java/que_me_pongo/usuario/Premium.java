package que_me_pongo.usuario;

import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.prenda.Prenda;

public class Premium extends TipoUsuario {

    public void agregarPrenda(Prenda prenda, Guardarropa guardarropa) {
        guardarropa.agregarPrenda(prenda);
    }

    @Override
    public String getTipoUsuario() {
        return getClass().getSimpleName();
    }
}
