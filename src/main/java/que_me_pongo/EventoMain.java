package que_me_pongo;

import que_me_pongo.evento.Evento;
import que_me_pongo.evento.EventoJob;
import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.prenda.Material;
import que_me_pongo.prenda.Prenda;
import que_me_pongo.prenda.TipoDePrendaFactory;
import que_me_pongo.usuario.Premium;
import que_me_pongo.usuario.Usuario;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.awt.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EventoMain {

    public static void main(String[] args) throws SchedulerException{
        Usuario usuario = new Usuario(new Premium());

        Prenda remera = new Prenda(TipoDePrendaFactory.remeraMangaCorta(), Material.SEDA, Color.BLACK, null,null);
        Prenda remeraB = new Prenda(TipoDePrendaFactory.remeraMangaCorta(),Material.ALGODON, Color.WHITE, null,null);
        Prenda pantalonA = new Prenda(TipoDePrendaFactory.shorts(),Material.ALGODON, Color.BLACK, null,null);
        Prenda pantalonB = new Prenda(TipoDePrendaFactory.shorts(),Material.ALGODON, Color.PINK, null,null);
        Prenda accesorioA = new Prenda(TipoDePrendaFactory.anteojos(),Material.PLASTICO, Color.ORANGE, null,null);
        Prenda zapatoA = new Prenda(TipoDePrendaFactory.zapatosDeTacon(),Material.CUERO, Color.BLUE, null,null);
        Prenda zapatoB = new Prenda(TipoDePrendaFactory.zapatosDeTacon(),Material.CUERO, Color.GREEN, null,null);

        Guardarropa guardarropa = new Guardarropa();
        guardarropa.agregarPrenda(remera);
        guardarropa.agregarPrenda(remeraB);
        guardarropa.agregarPrenda(pantalonA);
        guardarropa.agregarPrenda(pantalonB);
        guardarropa.agregarPrenda(accesorioA);
        guardarropa.agregarPrenda(zapatoA);
        guardarropa.agregarPrenda(zapatoB);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        usuario.agregarEvento(new Evento(LocalDateTime.parse("2019-08-05 12:30",formatter), usuario, guardarropa,"Ir al campo", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDateTime.parse("2019-05-06 12:30",formatter), usuario, guardarropa,"Cumpleaños", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDateTime.parse("2019-05-07 12:30",formatter), usuario, guardarropa,"Casamiento", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDateTime.parse("2019-09-29 12:30",formatter), usuario, guardarropa,"Bautismo", new ArrayList()));

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

    //@Test
    public void pruebaEventos() throws SchedulerException, InterruptedException {
        Usuario usuario = new Usuario(new Premium());
        Guardarropa guardarropa = new Guardarropa();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        usuario.agregarEvento(new Evento(LocalDateTime.parse("2019-08-05 12:30",formatter), usuario, guardarropa,"Ir al campo", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDateTime.parse("2019-08-06 12:30",formatter), usuario, guardarropa,"Cumpleaños", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDateTime.parse("2019-08-07 12:30",formatter), usuario, guardarropa,"Casamiento", new ArrayList()));
        usuario.agregarEvento(new Evento(LocalDateTime.parse("2019-09-29 12:30",formatter), usuario, guardarropa,"Bautismo", new ArrayList()));

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

