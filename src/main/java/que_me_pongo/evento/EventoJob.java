package que_me_pongo.evento;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.google.common.base.Optional;
import com.google.common.collect.Iterables;

import que_me_pongo.configuraciones.Configuraciones;
import que_me_pongo.proveedorClima.PronosticoClima;
import que_me_pongo.proveedorClima.ProveedorClima;
import que_me_pongo.sugeridor.Sugeridor;

import java.util.List;
import java.time.LocalDate;
import java.util.Set;

public class EventoJob implements Job {
    //Hace sugerir los eventos proximos
    public void execute(JobExecutionContext context) throws JobExecutionException {

        List<PronosticoClima> pronosticos = Configuraciones.get(ProveedorClima.class).getPronostico();
    		LocalDate date = LocalDate.now();
        Sugeridor sugeridor = new Sugeridor(2, 4, 1);
        Set<Evento> proximos = RepositorioEventos.getInstance().proximos(date, 3);
        
        proximos.stream()
        .filter(evento -> evento.getSugirio() && evento.chequearPronostico(pronosticoDeEvento(evento, pronosticos)))
        .forEach(evento -> evento.resugerir(sugeridor, pronosticoDeEvento(evento, pronosticos)));
        
        proximos.stream().
        filter(evento -> !evento.getSugirio()).
        forEach(evento -> evento.sugerir(sugeridor, pronosticoDeEvento(evento, pronosticos)));
    }
    
    private PronosticoClima pronosticoDeEvento(Evento evento, List<PronosticoClima> pronosticos) {
    	Optional<PronosticoClima> resultado = Iterables.tryFind(pronosticos, 
    			pronostico -> pronostico.getFechaPronostico().isEqual(evento.getFecha()) || 
    										pronostico.getFechaPronostico().isAfter(evento.getFecha()));
    	//TODO Cambiar por una mejor excepcion
    	if(!resultado.isPresent()) 
    		throw new RuntimeException();
    	
    	return resultado.get();
    }
}
