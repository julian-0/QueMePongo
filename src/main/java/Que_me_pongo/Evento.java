package Que_me_pongo;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class Evento {
    LocalDate fecha;
    Usuario usuario;
    String lugar;

    public Evento(LocalDate fecha,Usuario usuario,String lugar) {
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

    public boolean esProximo(LocalDate f, int cantDias){
        return f.isEqual(fecha) || (f.isAfter(fecha) && fecha.isBefore(f.plusDays(cantDias)));
    }
}
