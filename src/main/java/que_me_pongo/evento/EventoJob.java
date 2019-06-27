package que_me_pongo.evento;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import que_me_pongo.sugeridor.Sugeridor;

import java.time.LocalDate;

public class EventoJob implements Job {

    //Hace sugerir los eventos proximos
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Ejecuto Job");
        Sugeridor sugeridor = new Sugeridor(5, 10, 3, LocalDate.now());
        RepositorioEventos.getInstance()
                .proximos(LocalDate.now(),7)
                .stream()
                .forEach(evento -> evento.sugerir(sugeridor));
    }
}
