package que_me_pongo;

import que_me_pongo.evento.EventoJob;
import que_me_pongo.evento.RepositorioEventos;
import que_me_pongo.evento.repetidores.RepeticionDeEvento;
import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.prenda.Material;
import que_me_pongo.prenda.Prenda;
import que_me_pongo.prenda.TipoDePrendaFactory;
import que_me_pongo.proveedorClima.ClimaOpenWeather;
import que_me_pongo.proveedorClima.InstanciaProveedorClima;
import que_me_pongo.usuario.Premium;
import que_me_pongo.usuario.Usuario;

import org.quartz.*;
import org.quartz.impl.StdSchedulerFactory;

import java.awt.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class EventoMain {

    public static void main(String[] args) throws SchedulerException{
    		InstanciaProveedorClima.setInstancia(new ClimaOpenWeather());
        Usuario usuario = new Usuario("Julian","jm.ord98@gmail.com",new Premium());

        Prenda remera = new Prenda(TipoDePrendaFactory.getInstance().remeraMangaCorta(), Material.SEDA, Color.BLACK, null,null);
        Prenda remeraB = new Prenda(TipoDePrendaFactory.getInstance().remeraMangaCorta(),Material.ALGODON, Color.WHITE, null,null);
        Prenda pantalonA = new Prenda(TipoDePrendaFactory.getInstance().shorts(),Material.ALGODON, Color.BLACK, null,null);
        Prenda pantalonB = new Prenda(TipoDePrendaFactory.getInstance().shorts(),Material.ALGODON, Color.PINK, null,null);
        Prenda accesorioA = new Prenda(TipoDePrendaFactory.getInstance().anteojos(),Material.PLASTICO, Color.ORANGE, null,null);
        Prenda zapatoA = new Prenda(TipoDePrendaFactory.getInstance().zapatosDeTacon(),Material.CUERO, Color.BLUE, null,null);
        Prenda zapatoB = new Prenda(TipoDePrendaFactory.getInstance().zapatosDeTacon(),Material.CUERO, Color.GREEN, null,null);

        Guardarropa guardarropa = new Guardarropa();
        guardarropa.agregarPrenda(remera);
        guardarropa.agregarPrenda(remeraB);
        guardarropa.agregarPrenda(pantalonA);
        guardarropa.agregarPrenda(pantalonB);
        guardarropa.agregarPrenda(accesorioA);
        guardarropa.agregarPrenda(zapatoA);
        guardarropa.agregarPrenda(zapatoB);
        LocalDateTime ahora = LocalDateTime.now();

        RepositorioEventos.getInstance().crearEvento(ahora.plusDays(1), usuario, guardarropa,"Ir al campo", new ArrayList(), RepeticionDeEvento.NOREPITE);
        RepositorioEventos.getInstance().crearEvento(ahora.plusDays(1), usuario, guardarropa,"Cumpleaños", new ArrayList(), RepeticionDeEvento.DIARIO);
        RepositorioEventos.getInstance().crearEvento(ahora.plusDays(4), usuario, guardarropa,"Casamiento", new ArrayList(), RepeticionDeEvento.NOREPITE);
        RepositorioEventos.getInstance().crearEvento(ahora.plusDays(5), usuario, guardarropa,"Bautismo", new ArrayList(), RepeticionDeEvento.NOREPITE);



        Scheduler scheduler = StdSchedulerFactory.getDefaultScheduler();
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

}

