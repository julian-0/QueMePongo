package Que_me_pongo;

import java.util.Date;
import java.util.HashSet;
import java.util.List;
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

    public void agendar(Evento evento){//Agrega un evento
        this.eventos.add(evento);
    }

    public void proximos(Date fecha, int cantDias){ //Filtra los eventos cuya fecha esta cantDias cerca y los hace sugerir
        this.eventos
                .stream()
                .filter(evento -> evento.esProximo(fecha,cantDias))
                .forEach(evento -> evento.sugerir());
    }
}
