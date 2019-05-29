package Que_me_pongo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.time.LocalDate;

public class EventoJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        System.out.println("Ejecuto Job");
        RepositorioEventos.getInstance().proximos(LocalDate.now(),7);
    }
}
