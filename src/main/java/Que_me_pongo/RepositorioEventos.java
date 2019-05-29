package Que_me_pongo;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

public class RepositorioEventos {

    private Set<Evento> eventos = new HashSet<Evento>();

    public static RepositorioEventos instancia;

    private RepositorioEventos(){}

    public static RepositorioEventos getInstance(){
        if(instancia == null){
            instancia = new RepositorioEventos();
        }
        return instancia;
    }

    //Agrega un evento en el set
    public void agendar(Evento evento){//Agrega un evento
        this.eventos.add(evento);
    }

    //Filtra los eventos cuya fecha esta cantDias cerca
    public Set<Evento> proximos(LocalDate fecha, int cantDias){
        return this.eventos
                .stream()
                .filter(evento -> evento.esProximo(fecha,cantDias))
                .collect(Collectors.toSet());
    }
}