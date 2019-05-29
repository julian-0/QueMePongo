package Que_me_pongo.evento;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;

public class EventoJob implements Job {

    //Hace sugerir los eventos proximos
    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Ejecuto Job");

        RepositorioEventos.getInstance()
                .proximos(LocalDate.now(),7)
                .stream()
                .forEach(evento -> evento.sugerir());
    }
}