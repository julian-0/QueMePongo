package que_me_pongo.evento.repetidores;

import que_me_pongo.evento.Evento;
import que_me_pongo.evento.listeners.EventoListener;

public interface RepeticionDeEvento {
	public void repetir(Evento evento);
	
	static public RepeticionDeEvento noRepite() {
		return (ev) -> {}; 
	}
	
	static public RepeticionDeEvento semanal() {
		return (ev) -> new Evento(ev.getFecha().plusWeeks(1), ev.getUsuario(), ev.getGuardarropa(), ev.getDescripcion(), ev.getListenersSugerir());
	}
	
	static public RepeticionDeEvento diario() {
		return (ev) -> new Evento(ev.getFecha().plusDays(1), ev.getUsuario(), ev.getGuardarropa(), ev.getDescripcion(), ev.getListenersSugerir());
	}
	
	static public RepeticionDeEvento mensual() {
		return (ev) -> new Evento(ev.getFecha().plusMonths(1), ev.getUsuario(), ev.getGuardarropa(), ev.getDescripcion(), ev.getListenersSugerir());
	}
	
	static public RepeticionDeEvento anual() {
		return (ev) -> new Evento(ev.getFecha().plusYears(1), ev.getUsuario(), ev.getGuardarropa(), ev.getDescripcion(), ev.getListenersSugerir());
	}
}
