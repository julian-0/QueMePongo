import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import org.uqbarproject.jpa.java8.extras.WithGlobalEntityManager;
import org.uqbarproject.jpa.java8.extras.test.AbstractPersistenceTest;
import que_me_pongo.evento.Evento;
import que_me_pongo.evento.EventoJob;
import que_me_pongo.evento.RepositorioEventos;
import que_me_pongo.evento.repetidores.RepeticionDeEvento;
import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.proveedorClima.InstanciaProveedorClima;
import que_me_pongo.proveedorClima.PronosticoClima;
import que_me_pongo.proveedorClima.ProveedorClima;
import que_me_pongo.usuario.TipoUsuario;
import que_me_pongo.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;

import javax.persistence.EntityManager;

@RunWith(MockitoJUnitRunner.class)
public class EventoJobTest extends AbstractPersistenceTest implements WithGlobalEntityManager {
    Usuario usuario = new Usuario("Julian", null, TipoUsuario.PREMIUM);
    Guardarropa guardarropa = new Guardarropa();

    @Mock
    ProveedorClima prov;

    @Before
    public void setProveedorClima() {
        InstanciaProveedorClima.setInstancia(prov);
        EntityManager em = entityManager();
        em.persist(usuario);
        em.persist(guardarropa);
    }

    @Test
    @Ignore
    public void sugiereEventosProximos() throws Exception {
        LocalDateTime ahora = LocalDateTime.now();
        Mockito.when(prov.getPronostico()).thenReturn(Arrays.asList(new PronosticoClima(ahora, 15),
                new PronosticoClima(ahora.plusDays(1), 15),
                new PronosticoClima(ahora.plusDays(2), 15),
                new PronosticoClima(ahora.plusDays(3), 15)));

        Evento evento1 = RepositorioEventos.getInstance().crearEvento(ahora.plusDays(1), usuario, guardarropa, "Ir al campo", new ArrayList(), RepeticionDeEvento.NOREPITE);
        Evento evento2 = RepositorioEventos.getInstance().crearEvento(ahora.plusDays(2), usuario, guardarropa, "Cumpleaños", new ArrayList(), RepeticionDeEvento.NOREPITE);
        Evento evento3 = RepositorioEventos.getInstance().crearEvento(ahora.plusDays(4), usuario, guardarropa, "Casamiento", new ArrayList(), RepeticionDeEvento.NOREPITE);
        Evento evento4 = RepositorioEventos.getInstance().crearEvento(ahora.plusDays(5), usuario, guardarropa, "Bautismo", new ArrayList(), RepeticionDeEvento.NOREPITE);

        EventoJob job = new EventoJob();

        job.execute(null);

        Assert.assertTrue(evento1.getSugirio());
        Assert.assertTrue(evento2.getSugirio());
        Assert.assertFalse(evento3.getSugirio());
        Assert.assertFalse(evento4.getSugirio());
    }

    @Test
    @Ignore
    public void resugiereEventosConCambiosClimaticos() throws Exception {
        LocalDateTime ahora = LocalDateTime.now();

        Mockito.when(prov.getPronostico()).thenReturn(Arrays.asList(new PronosticoClima(ahora, 15),
                new PronosticoClima(ahora.plusDays(1), 15),
                new PronosticoClima(ahora.plusDays(2), 15),
                new PronosticoClima(ahora.plusDays(3), 15)));

        Evento evento1 = Mockito.spy(new Evento(ahora.plusDays(1), usuario, guardarropa, "Ir al campo", new ArrayList()));
        Evento evento2 = Mockito.spy(new Evento(ahora.plusDays(2), usuario, guardarropa, "Cumpleaños", new ArrayList()));
        Evento evento3 = Mockito.spy(new Evento(ahora.plusDays(4), usuario, guardarropa, "Casamiento", new ArrayList()));
        Evento evento4 = Mockito.spy(new Evento(ahora.plusDays(5), usuario, guardarropa, "Bautismo", new ArrayList()));

        RepositorioEventos.getInstance().agendar(evento1);
        RepositorioEventos.getInstance().agendar(evento2);
        RepositorioEventos.getInstance().agendar(evento3);
        RepositorioEventos.getInstance().agendar(evento4);

        EventoJob job = new EventoJob();

        job.execute(null);

        Mockito.when(prov.getPronostico()).thenReturn(Arrays.asList(new PronosticoClima(ahora, 0),
                new PronosticoClima(ahora.plusDays(1), 0),
                new PronosticoClima(ahora.plusDays(2), 0),
                new PronosticoClima(ahora.plusDays(3), 0)));

        job.execute(null);

        Mockito.verify(evento1, Mockito.times(1)).sugerir(Mockito.any(), Mockito.any());
        Mockito.verify(evento2, Mockito.times(1)).sugerir(Mockito.any(), Mockito.any());
        Mockito.verify(evento3, Mockito.times(0)).sugerir(Mockito.any(), Mockito.any());
        Mockito.verify(evento4, Mockito.times(0)).sugerir(Mockito.any(), Mockito.any());

        Mockito.verify(evento1, Mockito.times(1)).resugerir(Mockito.any(), Mockito.any());
        Mockito.verify(evento2, Mockito.times(1)).resugerir(Mockito.any(), Mockito.any());
        Mockito.verify(evento3, Mockito.times(0)).resugerir(Mockito.any(), Mockito.any());
        Mockito.verify(evento4, Mockito.times(0)).resugerir(Mockito.any(), Mockito.any());
    }
}
