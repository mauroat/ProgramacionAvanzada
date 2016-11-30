package castas;
import dominio.*;
import habilidades.*;


public class Hechicero extends Casta {
	/*
	 * Ver de que forma puedo hacer ademas de agregarlas puedan afectar al personaje
	 * 
	 * */
	public Hechicero(){
		super.nombre = "HECHICERO";
		this.idCasta = 2;
		this.agregarHabilidad(new Destreza());
		this.agregarHabilidad(new Inteligencia());
	}
	
	

}
