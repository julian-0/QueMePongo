package Que_me_pongo;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Evento {
    Date fecha;
    Usuario usuario;
    String lugar;

    public void sugerir(){ //Le va a cargar una lista de atuendos al usuario en su lista atuendos pendientes?

        Set<List<Prenda>> atuendos = usuario.atuendos();
        usuario.recolectarAtuendos(atuendos);
    }

    public boolean esProximo(Date f, int cantDias){
        long difEnMilisegundos = fecha.getTime() - f.getTime();
        long difEnDias = TimeUnit.DAYS.convert(difEnMilisegundos, TimeUnit.MILLISECONDS);

        return difEnDias <= cantDias;
    }
}
