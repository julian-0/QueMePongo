import Que_me_pongo.Evento;
import Que_me_pongo.EventoJob;
import Que_me_pongo.Premium;
import Que_me_pongo.Usuario;
import org.junit.Assert;
import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EventoTest {

    public static void main(String[] args) throws SchedulerException, InterruptedException, ParseException {
        Usuario usuario = new Usuario(new Premium());

        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        Date d = sdf.parse("31/05/2019");

        usuario.agregarEvento(new Evento(sdf.parse("31/05/2019"), usuario,"Ir al campo"));
        usuario.agregarEvento(new Evento(sdf.parse("30/05/2019"), usuario,"Cumpleaños"));
        usuario.agregarEvento(new Evento(sdf.parse("29/05/2019"), usuario,"Casamiento"));
        usuario.agregarEvento(new Evento(sdf.parse("29/07/2019"), usuario,"Bautismo"));

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        System.out.println("Iniciando Scheduler");
        scheduler.start();

        // Creacion una instacia de JobDetail
        JobDetail jobDetail = JobBuilder.newJob(EventoJob.class).build();

        // Creacion de un Trigger donde indicamos que el Job se ejecutara de inmediato y a partir de ahi en lapsos de 5 segundos .
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("CronTrigger")
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInSeconds(5)
                        //.repeatForever())
                        .withRepeatCount(2))
                .build();
        //new SimpleTrigger("EventoTrigger",Scheduler.DEFAULT_GROUP,10, 5000);

        // Registro dentro del Scheduler
        scheduler.scheduleJob(jobDetail, trigger);

        // Damos tiempo a que el Trigger registrado termine su periodo de vida dentro del scheduler
        Thread.sleep(60000);

        // Detenemos la ejecución de la instancia de Scheduler
        scheduler.shutdown();

        /*
        {
            try {
                // Creacion de una instacia de Scheduler
                Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
                System.out.println("Iniciando Scheduler");
                scheduler.start();

                // Creacion una instacia de JobDetail
                JobDetail jobDetail = JobBuilder.newJob(EventoJob.class).build();

                // Creacion de un Trigger donde indicamos que el Job se ejecutara de inmediato y a partir de ahi en lapsos de 5 segundos .
                Trigger trigger = TriggerBuilder
                        .newTrigger()
                        .withIdentity("CronTrigger")
                        .withSchedule(SimpleScheduleBuilder
                                .simpleSchedule()
                                .withIntervalInSeconds(5)
                                //.repeatForever())
                                .withRepeatCount(10))
                        .build();
                //new SimpleTrigger("EventoTrigger",Scheduler.DEFAULT_GROUP,10, 5000);

                // Registro dentro del Scheduler
                scheduler.scheduleJob(jobDetail, trigger);

                // Damos tiempo a que el Trigger registrado termine su periodo de vida dentro del scheduler
                Thread.sleep(60000);

                // Detenemos la ejecución de la instancia de Scheduler
                scheduler.shutdown();

            } catch (SchedulerException | InterruptedException e) {
                e.printStackTrace();
            }
        }
*/
    }


}