package juego;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import cliente.Cliente;
import cliente.EscuchaMensajes;
import cliente.MensajePersonaje;
import utilities.Loggin;

public class Juego implements Runnable {
	
	private Pantalla pantalla;
	private final String NOMBRE;
	private final int ANCHO;
	private final int ALTO;

	private Thread hilo;
	private boolean estaEnEjecucion;

	private BufferStrategy bs; // Estrategia para graficar mediante buffers (Primero se "grafica" en el/los buffer/s y finalmente en el canvas)
	private Graphics g;
	private String raza;
	private String nickName;

	// Estados
	private Estado estadoJuego;

	// HandlerMouse
	private Mouse handlerMouse;
	
	// Camara
	private Camara camara;

	// Conexion
	private Cliente cliente;
	private EscuchaMensajes escuchaMensajes;
	private MensajePersonaje paquetePersonaje;
	private String tipoMapa; 
	   
	  public Juego(final String nombre, final int ancho, final int alto, String tipoMapa, String raza) { 
	    this.NOMBRE = nombre; 
	    this.ALTO = alto; 
	    this.ANCHO = ancho;
	    this.setRaza(raza); 
	    this.setTipoMapa(tipoMapa); 
	 
	    handlerMouse = new Mouse(); 
	  } 
	
	public Juego(final String nombre, final int ancho, final int alto, Cliente cliente, MensajePersonaje pp, String tipoMapa) {
		this.NOMBRE = nombre;
		this.ALTO = alto;
		this.ANCHO = ancho;
		this.cliente = cliente;
		this.raza = pp.getRaza();
	    this.setTipoMapa(tipoMapa); 
	    this.nickName = pp.getNick();
		pp.setDireccion(6);
		pp.setFrame(0);
		this.paquetePersonaje = pp;
		escuchaMensajes = new EscuchaMensajes(cliente);
		escuchaMensajes.start();
		handlerMouse = new Mouse();
	}

	public void iniciar() { // Carga lo necesario para iniciar el juego
		pantalla = new Pantalla(NOMBRE, ANCHO, ALTO, cliente);

		pantalla.getCanvas().addMouseListener(handlerMouse);

		Grafico.cargar();

		estadoJuego = new EstadoJuego(this);
		Estado.setEstado(estadoJuego);
		
		camara = new Camara(this, 0, 0);
	}

	private void actualizar() { // Actualiza los objetos y sus posiciones
		handlerMouse.actualizar();

		if (Estado.getEstado() != null) {
			Estado.getEstado().actualizar();
		}
	}

	private void graficar() { // Grafica los objetos y sus posiciones
		bs = pantalla.getCanvas().getBufferStrategy();
		if (bs == null) { // Seteo una estrategia para el canvas en caso de que no tenga una
			pantalla.getCanvas().createBufferStrategy(3);
			return;
		}

		g = bs.getDrawGraphics(); // Permite graficar el buffer mediante g

		g.clearRect(0, 0, ANCHO, ALTO); // Limpiamos la pantalla

		// Graficado de imagenes
		
		if (Estado.getEstado() != null) {
			estadoJuego.graficar(g);
		}

		// Fin de graficado de imagenes

		bs.show(); // Hace visible el próximo buffer disponible
		g.dispose();
	}

	@Override
	public void run() { // Hilo principal del juego
		iniciar();

		int fps = 60; // Cantidad de actualizaciones por segundo que se desean
		double tiempoPorActualizacion = 1000000000 / fps; // Cantidad de nanosegundos en FPS deseados
		double delta = 0;
		long ahora;
		long ultimoTiempo = System.nanoTime();
		long timer = 0; // Timer para mostrar fps cada un segundo
		int actualizaciones = 0; // Cantidad de actualizaciones que se realizan realmente

		while (estaEnEjecucion) {
			ahora = System.nanoTime();
			delta += (ahora - ultimoTiempo) / tiempoPorActualizacion;  
	        timer += ahora - ultimoTiempo;  
	        ultimoTiempo = ahora; 

			if (delta >= 1) {
				actualizar();
				graficar();
				actualizaciones++;
				delta--;
			}

			if (timer >= 1000000000) { 
		        pantalla.getFrame(); 
				actualizaciones = 0;
				timer = 0;
			}
		}

		stop();
	}

	public synchronized void start() { // Inicia el juego
		if (estaEnEjecucion)
			return;
		estaEnEjecucion = true;
		hilo = new Thread(this);
		hilo.start();
	}

	public synchronized void stop() { // Detiene el juego
		if (!estaEnEjecucion)
			return;
		try {
			estaEnEjecucion = false;
			hilo.join();
		} catch (InterruptedException e) {
			Loggin.getInstance().error("Error Juego: "+e.getMessage());
		}
	}

	public int getAncho() {
		return ANCHO;
	}

	public int getAlto() {
		return ALTO;
	}

	public Mouse getHandlerMouse() {
		return handlerMouse;
	}
	
	public Camara getCamara() {
		return camara;
	}
	
	public EstadoJuego getEstadoJuego() {
		return (EstadoJuego) estadoJuego;
	}
	
	public Cliente getCliente() {
		return cliente;
	}
	
	public EscuchaMensajes getEscuchaMensajes() {
		return escuchaMensajes;
	}
	
	public MensajePersonaje getPersonaje() {
		return paquetePersonaje;
	}

    public String getTipoMapa() { 
      return tipoMapa; 
    } 
 
    public void setTipoMapa(String tipoMapa) { 
      this.tipoMapa = tipoMapa; 
    }

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	} 
}
