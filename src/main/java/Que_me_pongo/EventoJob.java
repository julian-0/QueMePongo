package Que_me_pongo;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class EventoJob implements Job {

    public void execute(JobExecutionContext context) throws JobExecutionException {
        RepositorioEventos.getInstance().proximos(new Date(),7);
    }
}
