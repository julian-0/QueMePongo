package que_me_pongo.evento;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

import org.uqbar.commons.model.annotations.Observable;
import org.uqbar.commons.model.annotations.Transactional;
import que_me_pongo.Atuendo;
import que_me_pongo.LocalDateTimeAttributeConverter;
import que_me_pongo.evento.listeners.EventoListener;
import que_me_pongo.evento.repetidores.RepeticionDeEvento;
import que_me_pongo.guardarropa.Guardarropa;
import que_me_pongo.prenda.Categoria;
import que_me_pongo.prenda.Material;
import que_me_pongo.prenda.Prenda;
import que_me_pongo.proveedorClima.PronosticoClima;
import que_me_pongo.sugeridor.Sugeridor;
import que_me_pongo.usuario.Usuario;

import javax.persistence.*;

@Observable @Entity
public class Evento {

    @Id @GeneratedValue
    private long id;

    @Convert(converter = LocalDateTimeAttributeConverter.class)
    private LocalDateTime fecha;

    @ManyToOne
    private Usuario usuario;

    @ManyToOne
    private Guardarropa guardarropa;

    private String descripcion;

    @ManyToOne
    private PronosticoClima pronostico;

    @ManyToMany
    private Deque<Atuendo> sugerencias, rechazados;

    @ManyToOne
    private Atuendo aceptado;

    @OneToMany @JoinColumn(name = "evento_id")
    private Collection<EventoListener> listenersSugerir;

    @Enumerated(EnumType.STRING)
    private RepeticionDeEvento repetidor;

    @ElementCollection(targetClass = Categoria.class)
  	@CollectionTable(name = "aumento_abrigo", joinColumns = @JoinColumn(name = "id_evento"))
  	@Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Categoria> aumentoAbrigo;
    
    @ElementCollection(targetClass = Categoria.class)
  	@CollectionTable(name = "reduccion_abrigo", joinColumns = @JoinColumn(name = "id_evento"))
  	@Column(name = "categoria", nullable = false)
    @Enumerated(EnumType.STRING)
    private Set<Categoria> reduccionAbrigo;

    public Evento() {
    }

    public Evento(LocalDateTime fecha, Usuario usuario, Guardarropa guardarropa, String descripcion, Collection<EventoListener> notificadores) {
    	settearEstadoInicial(fecha, usuario, guardarropa, descripcion, notificadores);
    	this.repetidor = RepeticionDeEvento.NOREPITE;
    }
    
    public Evento(LocalDateTime fecha,Usuario usuario,Guardarropa guardarropa,String descripcion,Collection<EventoListener> notificadores, RepeticionDeEvento tiempoParaRepetir) {
      settearEstadoInicial(fecha, usuario, guardarropa, descripcion, notificadores);
      this.repetidor = tiempoParaRepetir; 
    }
    
    public LocalDateTime getFecha() {
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

    public Deque<Atuendo> getSugerencias() { return sugerencias; }

    public Collection<EventoListener> getListenersSugerir(){
    	return this.listenersSugerir;
    }
    
    public Evento addListenersSugerir(EventoListener listener) {
    	listenersSugerir.add(listener);
    	return this;
    }

    public void obtenerSugerencias(Sugeridor sugeridor, PronosticoClima pronostico){
        sugerencias = new LinkedList<Atuendo>(
                sugeridor.sugerir(guardarropa.atuendos(fecha.toLocalDate()), pronostico, usuario));
        rechazados = new LinkedList<Atuendo>();
        aceptado = null;
        this.pronostico = pronostico;
    }

    //Le va a cargar una lista de atuendos al usuario en su lista atuendos pendientes
    public void sugerir(Sugeridor sugeridor, PronosticoClima pronostico){
        this.obtenerSugerencias(sugeridor,pronostico);
        listenersSugerir.forEach(listener -> listener.sugerenciasRealizadas(this));
        repetidor.generarRepeticion(this);
    }

    public void resugerir(Sugeridor sugeridor, PronosticoClima pronostico){
        this.obtenerSugerencias(sugeridor,pronostico);
        listenersSugerir.forEach(listener -> listener.alertaMeteorologica(this));
    }
    
    public void rechazarSugerencia() {
    	validarExistenSugerencias();
    	validarNoAceptado();
    	
    	//removeFirst tira su propia excepcion si está vacia, tal vez atajarlo antes y tirar una nuestra
    	rechazados.add(sugerencias.removeFirst());
    }
    
    public void aceptarSugerencia(Set<Categoria> aumentarAbrigo, Set<Categoria> reducirAbrigo) {
    	validarExistenSugerencias();
    	validarNoAceptado();
    	aceptado = sugerencias.removeFirst();
    	usuario.ajustarPreferencias(aumentarAbrigo, reducirAbrigo);
    	guardarropa.reservarAtuendo(fecha.toLocalDate(), aceptado);
    	this.aumentoAbrigo = aumentarAbrigo;
    	this.reduccionAbrigo = reducirAbrigo;
    }
    
    public void deshacerDecision() {
    	validarExistenSugerencias();
    	if(aceptado != null) {
    		sugerencias.addFirst(aceptado);
    		guardarropa.liberarAtuendo(fecha.toLocalDate(), aceptado);
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
        return fConsultada.isEqual(fecha.toLocalDate()) || (fConsultada.isBefore(fecha.toLocalDate()) && fecha.toLocalDate().isBefore(fConsultada.plusDays(cantDias)));
    }
    
    public boolean getSugirio() {
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
    
    private void settearEstadoInicial(LocalDateTime fecha,Usuario usuario, Guardarropa guardarropa,String descripcion,Collection<EventoListener> notificadores) {
    	this.fecha = Objects.requireNonNull(fecha);
      this.usuario = Objects.requireNonNull(usuario);
      this.descripcion = Objects.requireNonNull(descripcion);
      this.guardarropa = Objects.requireNonNull(guardarropa);
      if(notificadores == null)
      	this.listenersSugerir = new ArrayList<EventoListener>();
      else
      	this.listenersSugerir = notificadores;   
    }
    
    public void generarRepeticion(LocalDateTime nuevaFecha) {
    	RepositorioEventos.getInstance().crearEvento(nuevaFecha, this.usuario, this.guardarropa, this.descripcion, this.listenersSugerir, this.repetidor);
    }

    public boolean chequearPronostico(PronosticoClima nuevoPronostico) {
			return pronostico.difiere(nuevoPronostico);
		}

    public boolean estaEntreFechas(Date desde, Date hasta){
        ZonedDateTime zdt = fecha.atZone(ZoneId.systemDefault());
        Date fechaConvertida = Date.from(zdt.toInstant());

        return fechaConvertida.after(desde) && fechaConvertida.before(hasta);
    }
}