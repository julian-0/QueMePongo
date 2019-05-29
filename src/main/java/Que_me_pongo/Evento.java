package Que_me_pongo;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;

public class Evento {
    LocalDate fecha;
    Usuario usuario;
    String lugar;

    //Se crea un evento y se carga en el repo de eventos
    public Evento(LocalDate fecha,Usuario usuario,String lugar) {
        this.fecha = fecha;
        this.usuario = usuario;
        this.lugar = lugar;

        RepositorioEventos.getInstance().agendar(this);
    }

    //Le va a cargar una lista de atuendos al usuario en su lista atuendos pendientes
    public void sugerir(){

        Set<List<Prenda>> atuendos = Sugeridor.sugerir(usuario.atuendos(), Configuraciones.get(ProveedorClima.class).getTemp(LocalDate.now()));
        usuario.recolectarAtuendos(atuendos);
        System.out.println("Soy un evento y me ejecute xq estoy proximo, lugar: "+ this.lugar);
    }

    /*Verdadero si las fechas coinciden o la fecha del evento es posterior a la consultada
    y ademas esta dentro del rango de cantDias
    */
    public boolean esProximo(LocalDate fConsultada, int cantDias){
        return fConsultada.isEqual(fecha) || (fConsultada.isBefore(fecha) && fecha.isBefore(fConsultada.plusDays(cantDias)));
    }
}