package juego;

import java.awt.Graphics;
import java.io.IOException;
import java.util.Iterator;

import com.google.gson.Gson;

import cliente.Cliente;
import cliente.Mensaje;
import cliente.MensajePersonaje;
import entidades.PersonajeGrafico;
import gui.PantallaCombate;
import mapa.Mapa;
import utilities.Loggin;

public class EstadoJuego extends Estado {

	private PersonajeGrafico personaje;
	private Mapa mapa;
	private final Gson gson = new Gson();
	final boolean colision = false;
	private int spawnX;
	private int spawnY;

	public EstadoJuego(Juego juego) {
		super(juego);
		mapa = new Mapa(juego);
		setearUbicacionRandom();
	    personaje = new PersonajeGrafico(juego, mapa, 64, 64, spawnX, spawnY, 100); 
	 
		try {
			juego.getPersonaje().setComando("conectado");
			juego.getCliente().getSalida().writeObject(gson.toJson(juego.getPersonaje()));
			
		} catch (IOException e) {
			Loggin.getInstance().error("Error en EstadoJuego "+e.getMessage());
		}
	}

	private void setearUbicacionRandom() {
		int[][] ubicaciones = {{0,0},{-768,352},{-1568,752},{-800,1136},{0,1536},{768,352},{1368,752},{800,1136}};
		int fila = (int) (Math.random() * 7) + 1;
		this.spawnX = ubicaciones[fila][0];
		this.spawnY = ubicaciones[fila][1];
	}

	@Override
	public void actualizar() {
		mapa.actualizar();
		personaje.actualizar();
	}

	public void graficar(Graphics g) {
		g.drawImage(Grafico.fondo, 0, 0, juego.getAncho(), juego.getAlto(), null); 
		mapa.graficar(g);
		personaje.graficar(g);

		Iterator it = juego.getEscuchaMensajes().getPersonajes().keySet().iterator();
		int key;
		MensajePersonaje personajeActual;
		PersonajeGrafico personajeAux;
		
		while (it.hasNext()) {
			key = (int) it.next();
			personajeActual = juego.getEscuchaMensajes().getPersonajes().get(key);
			
			if(evaluarColision(juego.getPersonaje(),personajeActual) && !juego.getPersonaje().isEnCombate())	{
				juego.getPersonaje().setEnCombate(true);
				PantallaCombate pc = new PantallaCombate(juego,personajeActual);
				pc.setVisible(true);				
			}
				
			if (personajeActual.getIdPersonaje() != juego.getPersonaje().getIdPersonaje()) {
				g.drawImage(personaje.obtenerAnimacion(personajeActual.getRaza()).get(personajeActual.getDireccion())[personajeActual.getFrame()], (int) (personajeActual.getPosX() - juego.getCamara().getxOffset() ), (int) (personajeActual.getPosY() - juego.getCamara().getyOffset()), 64, 64, null); 
			} 
		}
		
		g.drawImage(Grafico.marco, 0, 0, juego.getAncho(), juego.getAlto(), null);
	}
  
	public boolean evaluarColision(MensajePersonaje p, MensajePersonaje aux)
	{
		int tolerancia = 3;
		
		if(aux.getIdPersonaje()!=p.getIdPersonaje() && aux.getPosX() + aux.getAncho() +tolerancia >= p.getPosX() && aux.getPosX() < p.getPosX() + p.getAncho() +tolerancia)
		{
		//Colisiones verticales
			if(aux.getPosY() < p.getPosY() + p.getAlto())
				return true;
		}
		//Colisión de a con b
		if(aux.getIdPersonaje()!=p.getIdPersonaje() && aux.getPosX() <= p.getPosX() +tolerancia && aux.getPosX() + aux.getAncho() +tolerancia >= p.getPosX() + p.getAncho())
		{
			if(aux.getPosY() <= p.getPosY() && aux.getPosY() + aux.getAlto() >= p.getPosY() + p.getAlto())
				return true;
		}
		//Colisión b con a
		if(aux.getIdPersonaje()!=p.getIdPersonaje() && p.getPosX() <= aux.getPosX() + tolerancia && p.getPosX() + p.getAncho() +tolerancia >= aux.getPosX() + aux.getAncho())
		{
			if(p.getPosY() <= aux.getPosY() +tolerancia && p.getPosY() + p.getAlto() +tolerancia >= aux.getPosY() + aux.getAlto())
				return true;
		}
		
		return false;
	}
	
	
	public PersonajeGrafico getPersonaje() {
		return personaje;
	}
}