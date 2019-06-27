package que_me_pongo.evento;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import que_me_pongo.sugeridor.Sugeridor;

import java.time.LocalDate;
import java.util.Set;

public class EventoJob implements Job {

    //Hace sugerir los eventos proximos
    public void execute(JobExecutionContext context) throws JobExecutionException {
    		LocalDate date = LocalDate.now();
        Sugeridor sugeridor = new Sugeridor(2, 4, 1, date);
        Set<Evento> proximos = RepositorioEventos.getInstance().proximos(date, 7);
        
        proximos.stream().
        filter(evento -> !evento.sugirio()).
        forEach(evento -> evento.sugerir(sugeridor));
    }
}
