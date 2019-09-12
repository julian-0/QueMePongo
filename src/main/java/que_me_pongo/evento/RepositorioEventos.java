package que_me_pongo.evento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;

import que_me_pongo.evento.listeners.EventoListener;
import que_me_pongo.evento.repetidores.RepeticionDeEvento;
import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.usuario.Usuario;

public class RepositorioEventos implements WithGlobalEntityManager{

    private Set<Evento> eventos = new HashSet<Evento>();

    public static RepositorioEventos instancia;

    private RepositorioEventos(){}

    public static RepositorioEventos getInstance(){
        if(instancia == null){
            instancia = new RepositorioEventos();
        }
        return instancia;
    }

    public Evento agendar(Evento evento){//Agrega un evento
        entityManager().persist(evento);
        return evento;
    }
    
    public Evento crearEvento(LocalDateTime fecha,Usuario usuario,Guardarropa guardarropa,String descripcion,Collection<EventoListener> notificadores, RepeticionDeEvento tiempoParaRepetir) {
    	Evento nuevo = new Evento(fecha, usuario, guardarropa, descripcion, notificadores, tiempoParaRepetir);
    	return agendar(nuevo);
    }

    //Filtra los eventos cuya fecha esta cantDias cerca
    public Set<Evento> proximos(LocalDate fecha, int cantDias){
        return this.getEventos()
                .stream()
                .filter(evento -> evento.esProximo(fecha,cantDias))
                .collect(Collectors.toSet());
    }

    public Set<Evento> getEventos() {
        return entityManager().
        			 createQuery("FROM Evento", Evento.class).
        			 getResultList().stream().
        			 collect(Collectors.toSet());
    }


    public Set<Evento> filtrarEventos(Date desde, Date hasta){
        return getEventos().stream().filter(e -> e.estaEntreFechas(desde,hasta)).collect(Collectors.toSet());
    }
}