import Que_me_pongo.evento.Evento;
import Que_me_pongo.evento.EventoJob;
import Que_me_pongo.usuario.Premium;
import Que_me_pongo.usuario.Usuario;

import org.junit.Test;
import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.time.LocalDate;
import java.util.ArrayList;

public class EventoMain {

    public static void main(String[] args) throws SchedulerException{
        Usuario usuario = new Usuario(new Premium());

        usuario.agregarEvento(new Evento(LocalDate.parse("2019-05-31"), usuario,"Ir al campo", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDate.parse("2019-05-30"), usuario,"Cumpleaños", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDate.parse("2019-05-29"), usuario,"Casamiento", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDate.parse("2019-07-29"), usuario,"Bautismo", new ArrayList()));

        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
        System.out.println("Iniciando Scheduler");
        scheduler.start();

        // Creacion una instacia de JobDetail
        JobDetail jobDetail = JobBuilder.newJob(EventoJob.class).build();

        // Creacion de un Trigger donde indicamos que el Job se ejecutara de inmediato y a partir de ahi en lapsos de 24 horas para siempre.
        Trigger trigger = TriggerBuilder
                .newTrigger()
                .withIdentity("CronTrigger")
                .withSchedule(SimpleScheduleBuilder
                        .simpleSchedule()
                        .withIntervalInHours(24)    //Se ejecuta cada 24 horas
                        .repeatForever())           //Se ejecuta para siempre
                .build();

        // Registro dentro del Scheduler
        scheduler.scheduleJob(jobDetail, trigger);
    }

    @Test
    public void pruebaEventos() throws SchedulerException, InterruptedException {
        Usuario usuario = new Usuario(new Premium());

        usuario.agregarEvento(new Evento(LocalDate.parse("2019-05-31"), usuario,"Ir al campo", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDate.parse("2019-05-30"), usuario,"Cumpleaños", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDate.parse("2019-05-29"), usuario,"Casamiento", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDate.parse("2019-07-29"), usuario,"Bautismo", new ArrayList()));

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
                        .withIntervalInSeconds(5)   //Cada cuanto se va a ejecutar
                        .withRepeatCount(0))        //Cantidad de veces a ejecutar (ademas de la primera)
                .build();

        // Registro dentro del Scheduler
        scheduler.scheduleJob(jobDetail, trigger);

        // Damos tiempo (en milisegundos) a que el Trigger registrado termine su periodo de vida dentro del scheduler
        Thread.sleep(10000); //10 seg

        // Detenemos la ejecución de la instancia de Scheduler
        scheduler.shutdown();
    }

}