package que_me_pongo.evento;

import org.uqbar.commons.model.annotations.Observable;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Observable
public class FiltradorDeEventos {
    public static FiltradorDeEventos instancia;
    public Date desde;
    public Date hasta;
    public Set<Evento> eventos = new HashSet<>();
    public RepositorioEventos repo;

    public static FiltradorDeEventos getInstance(){
        if(instancia == null){
            instancia = new FiltradorDeEventos();
        }
        return instancia;
    }

    public FiltradorDeEventos(){
        this.repo=RepositorioEventos.getInstance();
    }

    public void filtrarEventos(){
        eventos = repo.filtrarEventos(desde,hasta);
    }

    public Date getDesde() { return desde; }

    public Date getHasta() { return hasta; }

    public Set<Evento> getEventos() { return eventos; }

    public void setDesde(Date desde) { this.desde = desde; }

    public void setHasta(Date hasta) { this.hasta = hasta; }
}
