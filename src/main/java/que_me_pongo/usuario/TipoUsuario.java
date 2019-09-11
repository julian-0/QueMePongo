package que_me_pongo.usuario;

import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.prenda.Prenda;

public abstract class TipoUsuario {

    public abstract String getTipoUsuario();

    public abstract void agregarPrenda(Prenda prenda, Guardarropa guardarropa);

}
