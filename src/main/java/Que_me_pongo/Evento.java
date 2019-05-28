package Que_me_pongo;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Evento {
    Date fecha;
    Usuario usuario;
    String lugar;

    public Evento(Date fecha,Usuario usuario,String lugar) {
        this.fecha = fecha;
        this.usuario = usuario;
        this.lugar = lugar;

        RepositorioEventos.getInstance().agendar(this);
    }

    public void sugerir(){ //Le va a cargar una lista de atuendos al usuario en su lista atuendos pendientes?

        Set<List<Prenda>> atuendos = usuario.atuendos();
        usuario.recolectarAtuendos(atuendos);
        System.out.println("Soy un evento y me ejecute xq estoy proximo, lugar: "+ this.lugar);
    }

    public boolean esProximo(Date f, int cantDias){
        long difEnMilisegundos = fecha.getTime() - f.getTime();
        long difEnDias = TimeUnit.DAYS.convert(difEnMilisegundos, TimeUnit.MILLISECONDS);

        return difEnDias <= cantDias;
    }
}
