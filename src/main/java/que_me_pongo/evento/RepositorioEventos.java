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
    public Set<Evento> proximos(LocalDateTime fecha, int cantDias){
    	return entityManager().
				   createQuery("FROM Evento WHERE fecha BETWEEN :hoy AND :limite", Evento.class).
				   setParameter("hoy", fecha).
				   setParameter("limite", fecha.plusDays(cantDias)).
				   getResultList().
				   stream().
				   collect(Collectors.toSet());
    }

    public Set<Evento> getEventos() {
        return entityManager().
        			 createQuery("FROM Evento", Evento.class).
        			 getResultList().stream().
        			 collect(Collectors.toSet());
    }


    public Set<Evento> filtrarEventos(Date desde, Date hasta){
    		return entityManager().
				   createQuery("FROM Evento WHERE fecha BETWEEN :desde AND :hasta", Evento.class).
				   setParameter("hoy", desde).
				   setParameter("hasta", hasta).
				   getResultList().
				   stream().
				   collect(Collectors.toSet());
    }
    
}