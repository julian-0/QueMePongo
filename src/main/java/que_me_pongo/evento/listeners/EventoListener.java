package que_me_pongo.evento.listeners;

import que_me_pongo.evento.Evento;

import javax.persistence.Entity;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class EventoListener {
	public abstract void sugerenciasRealizadas(Evento evento);
	public abstract void alertaMeteorologica(Evento evento);
}
