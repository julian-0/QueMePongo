package que_me_pongo.evento;

import java.time.LocalDate;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import que_me_pongo.evento.listeners.EventoListener;
import que_me_pongo.prenda.Categoria;
import que_me_pongo.prenda.Prenda;
import que_me_pongo.sugeridor.Sugeridor;
import que_me_pongo.usuario.Usuario;

public class Evento {
    private LocalDate fecha;
    private Usuario usuario;
    private String descripcion;
    private Deque<List<Prenda>> sugerencias, rechazados;
    private List<Prenda> aceptado;
    private Collection<EventoListener> listenersSugerir;

    //Se crea un evento y se carga en el repo de eventos
    public Evento(LocalDate fecha,Usuario usuario,String descripcion,Collection<EventoListener> listenersSugerir) {
        this.fecha = fecha;
        this.usuario = usuario;
        this.descripcion = descripcion;
        this.listenersSugerir = listenersSugerir;

        RepositorioEventos.getInstance().agendar(this);
        
    }
    
    public Evento(LocalDate fecha,Usuario usuario,String descripcion,Collection<EventoListener> listenersSugerir, EventoListener tiempoParaRepetir) {
      this.fecha = fecha;
      this.usuario = usuario;
      this.descripcion = descripcion;
      this.listenersSugerir = listenersSugerir;
      this.listenersSugerir.add(tiempoParaRepetir);
      RepositorioEventos.getInstance().agendar(this);
      
  }
    
    public LocalDate getFecha() {
    	return this.fecha;
    }
    
    public Usuario getUsuario() {
    	return this.usuario;
    }
    
    public String getDescripcion() {
    	return this.descripcion;
    }
    
    public Collection<EventoListener> getListenersSugerir(){
    	return this.listenersSugerir;
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
    	//Pasar info al usuario
    }
    
    public void deshacerDecision() {
    	validarExistenSugerencias();
    	if(aceptado != null) {
    		sugerencias.addFirst(aceptado);
    		aceptado = null;
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
    
    private void validarNoAceptado() {
    	if(aceptado != null)
    		throw new AtuendoYaDecididoException();
    }
    
    private void validarExistenSugerencias() {
    	if(sugerencias == null)
    		throw new SinSugerenciasException();
    }
    
}