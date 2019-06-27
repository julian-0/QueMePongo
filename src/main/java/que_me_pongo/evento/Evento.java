package que_me_pongo.evento;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Objects;
import java.util.Set;

import que_me_pongo.evento.listeners.EventoListener;
import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.prenda.Categoria;
import que_me_pongo.prenda.Prenda;
import que_me_pongo.sugeridor.Sugeridor;
import que_me_pongo.usuario.Usuario;

public class Evento {
    private LocalDate fecha;
    private Usuario usuario;
    private Guardarropa guardarropa;
    private String descripcion;
    private Deque<List<Prenda>> sugerencias, rechazados;
    private List<Prenda> aceptado;
    private Collection<EventoListener> listenersSugerir;
    private Set<Categoria> aumentoAbrigo, reduccionAbrigo;

    //Se crea un evento y se carga en el repo de eventos
    public Evento(LocalDate fecha,Usuario usuario, Guardarropa guardarropa,String descripcion,Collection<EventoListener> notificadores) {
    	settearEstadoInicial(fecha, usuario, guardarropa, descripcion, notificadores);
    }
    
    public Evento(LocalDate fecha,Usuario usuario,String descripcion,Collection<EventoListener> notificadores, EventoListener tiempoParaRepetir) {
      settearEstadoInicial(fecha, usuario, guardarropa, descripcion, notificadores);
      this.listenersSugerir.add(tiempoParaRepetir);
    }
    
    public LocalDate getFecha() {
    	return this.fecha;
    }
    
    public Usuario getUsuario() {
    	return this.usuario;
    }
    
    public Guardarropa getGuardarropa() {
    	return this.guardarropa;
    }
    
    public String getDescripcion() {
    	return this.descripcion;
    }
    
    public Collection<EventoListener> getListenersSugerir(){
    	return this.listenersSugerir;
    }
    
    public Evento addListenersSugerir(EventoListener listener) {
    	listenersSugerir.add(listener);
    	return this;
    }

    //Le va a cargar una lista de atuendos al usuario en su lista atuendos pendientes
    public void sugerir(Sugeridor sugeridor){
        sugerencias = new LinkedList<List<Prenda>>(sugeridor.sugerir(usuario.atuendos()));
        rechazados = new LinkedList<List<Prenda>>();
        listenersSugerir.forEach(listener -> listener.accionRealizada(this));
    }
    
    public void rechazarSugerencia() {
    	validarExistenSugerencias();
    	validarNoAceptado();
    	
    	//removeFirst tira su propia exception si está vacia, tal vez atajarlo antes y tirar una nuestra
    	rechazados.add(sugerencias.removeFirst());
    }
    
    public void aceptarSugerencia(Set<Categoria> aumentarAbrigo, Set<Categoria> reducirAbrigo) {
    	validarExistenSugerencias();
    	validarNoAceptado();
    	aceptado = sugerencias.removeFirst();
    	usuario.ajustarPreferencias(aumentarAbrigo, reducirAbrigo);
    	guardarropa.reservarAtuendo(fecha, aceptado);
    	this.aumentoAbrigo = aumentarAbrigo;
    	this.reduccionAbrigo = reducirAbrigo;
    }
    
    public void deshacerDecision() {
    	validarExistenSugerencias();
    	if(aceptado != null) {
    		sugerencias.addFirst(aceptado);
    		guardarropa.liberarAtuendo(fecha, aceptado);
    		aceptado = null;
    		usuario.ajustarPreferencias(reduccionAbrigo, aumentoAbrigo);
    	}
    	else if(!rechazados.isEmpty()) {
    		sugerencias.addFirst(rechazados.removeLast());
    	}
    	else
    		throw new NoHistorialException();
    }

    /*Verdadero si las fechas coinciden o la fecha del evento es posterior a la consultada
    y ademas esta dentro del rango de cantDias
    */
    public boolean esProximo(LocalDate fConsultada, int cantDias){
        return fConsultada.isEqual(fecha) || (fConsultada.isBefore(fecha) && fecha.isBefore(fConsultada.plusDays(cantDias)));
    }
    
    public boolean sugirio() {
    	return sugerencias != null;
    }
    
    private void validarNoAceptado() {
    	if(aceptado != null)
    		throw new AtuendoYaDecididoException();
    }
    
    private void validarExistenSugerencias() {
    	if(sugerencias == null)
    		throw new SinSugerenciasException();
    }
    
    private void settearEstadoInicial(LocalDate fecha,Usuario usuario, Guardarropa guardarropa,String descripcion,Collection<EventoListener> notificadores) {
    	this.fecha = Objects.requireNonNull(fecha);
      this.usuario = Objects.requireNonNull(usuario);
      this.descripcion = Objects.requireNonNull(descripcion);
      this.guardarropa = Objects.requireNonNull(guardarropa);
      if(notificadores == null)
      	this.listenersSugerir = new ArrayList<EventoListener>();
      else
      	this.listenersSugerir = notificadores;
      RepositorioEventos.getInstance().agendar(this);   
    }
    
}