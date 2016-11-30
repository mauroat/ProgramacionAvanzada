package cliente;

import java.io.Serializable;

import dominio.Personaje;
import utilities.Loggin;

public class MensajePersonaje extends Mensaje implements Serializable, Cloneable {

	private static final long serialVersionUID = 3341436791467937324L;
	private int idPersonaje;
	private Personaje personaje;
	private String raza;
	private String casta;
	private int mundo;
	private float posX;
	private float posY;
	private int direccion;
	private int frame;
	private int ancho;
	private int alto;
	private boolean colision;
	private boolean enCombate;
	private int idPersonajeColision;
	

	public Personaje getPersonaje() {
		return personaje;
	}

	public void setPersonaje(Personaje personaje) {
		this.personaje = personaje;
	}

	public MensajePersonaje() {

	}

	public MensajePersonaje(int idPersonaje, String raza, String casta, int mundo, float posX, float posY, Personaje personaje) {
		this.idPersonaje = idPersonaje;
		this.raza = raza;
		this.casta = casta;
		this.mundo = mundo;
		this.posX = posX;
		this.posY = posY;
		this.personaje = personaje;
		this.enCombate = false;
		this.colision = false;
		
	}

	public int getIdPersonaje() {
		return idPersonaje;
	}

	public void setIdPersonaje(int idPersonaje) {
		this.idPersonaje = idPersonaje;
	}

	public String getRaza() {
		return raza;
	}

	public void setRaza(String raza) {
		this.raza = raza;
	}

	public String getCasta() {
		return casta;
	}

	public void setCasta(String casta) {
		this.casta = casta;
	}

	public int getMundo() {
		return mundo;
	}

	public void setMundo(int mundo) {
		this.mundo = mundo;
	}

	public float getPosX() {
		return posX;
	}

	public void setPosX(float posX) {
		this.posX = posX;
	}

	
	
	public boolean isColision() {
		return colision;
	}

	public void setColision(boolean colision) {
		this.colision = colision;
	}

	public boolean isEnCombate() {
		return enCombate;
	}

	public void setEnCombate(boolean enCombate) {
		this.enCombate = enCombate;
	}

	public int getIdPersonajeColision() {
		return idPersonajeColision;
	}

	public void setIdPersonajeColision(int idPersonajeColision) {
		this.idPersonajeColision = idPersonajeColision;
	}

	public float getPosY() {
		return posY;
	}

	public void setPosY(float posY) {
		this.posY = posY;
	}
	

	public int getDireccion() {
		return direccion;
	}

	public void setDireccion(int direccion) {
		this.direccion = direccion;
	}

	public int getFrame() {
		return frame;
	}

	public void setFrame(int frame) {
		this.frame = frame;
	}

	public Object clone() {
		Object obj = null;
		try {
			obj = super.clone();
		} catch (CloneNotSupportedException e) {
			Loggin.getInstance().error(e.getMessage());
		}
		return obj;
	}

	public int getAncho() {
		return ancho;
	}

	public void setAncho(int ancho) {
		this.ancho = ancho;
	}

	public int getAlto() {
		return alto;
	}

	public void setAlto(int alto) {
		this.alto = alto;
	}	
}

