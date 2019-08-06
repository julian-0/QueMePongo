import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;

import que_me_pongo.configuraciones.Configuraciones;
import que_me_pongo.evento.Evento;
import que_me_pongo.evento.EventoJob;
import que_me_pongo.evento.RepositorioEventos;
import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.proveedorClima.PronosticoClima;
import que_me_pongo.proveedorClima.ProveedorClima;
import que_me_pongo.usuario.Premium;
import que_me_pongo.usuario.Usuario;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;

@RunWith(MockitoJUnitRunner.class)
public class EventoJobTest {
	Usuario usuario = new Usuario(new Premium());
	Guardarropa guardarropa = new Guardarropa();
	
	@Mock
	ProveedorClima prov;
	
	@Before
	public void setProveedorClima() {
		Configuraciones.set(ProveedorClima.class, prov);
	}
	
	@Test
	public void sugiereEventosProximos() throws Exception {
		LocalDateTime ahora = LocalDateTime.now();
		Mockito.when(prov.getPronostico()).thenReturn(Arrays.asList(new PronosticoClima(ahora, 15),
				new PronosticoClima(ahora.plusDays(1), 15),
				new PronosticoClima(ahora.plusDays(2), 15),
				new PronosticoClima(ahora.plusDays(3), 15)));
		
		Evento evento1 = new Evento(ahora.plusDays(1), usuario, guardarropa,"Ir al campo", new ArrayList());
    Evento evento2 = new Evento(ahora.plusDays(2), usuario, guardarropa,"Cumpleaños", new ArrayList());
    Evento evento3 = new Evento(ahora.plusDays(4), usuario, guardarropa,"Casamiento", new ArrayList());
    Evento evento4 = new Evento(ahora.plusDays(5), usuario, guardarropa,"Bautismo", new ArrayList());
    
    EventoJob job = new EventoJob();
    
  	job.execute(null);
  	
  	Assert.assertTrue(evento1.getSugirio());
    Assert.assertTrue(evento2.getSugirio());
    Assert.assertFalse(evento3.getSugirio());
    Assert.assertFalse(evento4.getSugirio());
	}
	
	@Test
	public void resugiereEventosConCambiosClimaticos() throws Exception {
		LocalDateTime ahora = LocalDateTime.now();
		
		Mockito.when(prov.getPronostico()).thenReturn(Arrays.asList(new PronosticoClima(ahora, 15),
				new PronosticoClima(ahora.plusDays(1), 15),
				new PronosticoClima(ahora.plusDays(2), 15),
				new PronosticoClima(ahora.plusDays(3), 15)));
		
		Evento evento1 = Mockito.spy(new Evento(ahora.plusDays(1), usuario, guardarropa,"Ir al campo", new ArrayList()));
		Evento evento2 = Mockito.spy(new Evento(ahora.plusDays(2), usuario, guardarropa,"Cumpleaños", new ArrayList()));
		Evento evento3 = Mockito.spy(new Evento(ahora.plusDays(4), usuario, guardarropa,"Casamiento", new ArrayList()));
		Evento evento4 = Mockito.spy(new Evento(ahora.plusDays(5), usuario, guardarropa,"Bautismo", new ArrayList()));
		
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
  	
  	Mockito.verify(evento1, Mockito.times(2)).sugerir(Mockito.any(), Mockito.any());
  	Mockito.verify(evento2, Mockito.times(2)).sugerir(Mockito.any(), Mockito.any());
  	Mockito.verify(evento3, Mockito.times(0)).sugerir(Mockito.any(), Mockito.any());
  	Mockito.verify(evento4, Mockito.times(0)).sugerir(Mockito.any(), Mockito.any());
	}
}
