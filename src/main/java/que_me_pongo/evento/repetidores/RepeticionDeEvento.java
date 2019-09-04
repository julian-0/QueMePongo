package que_me_pongo.evento.repetidores;

import java.time.LocalDateTime;

import que_me_pongo.evento.Evento;
import que_me_pongo.evento.listeners.EventoListener;

public interface RepeticionDeEvento {
	public LocalDateTime getNuevaFecha(LocalDateTime fecha);
}
