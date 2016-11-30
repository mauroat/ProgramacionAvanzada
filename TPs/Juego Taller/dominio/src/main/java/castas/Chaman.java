package castas;
import dominio.*;
import habilidades.*;

public class Chaman extends Casta {

	public Chaman() {
		super.nombre = "CHAMAN";
		/*
		 * Ver de que forma puedo hacer ademas de agregarlas puedan afectar al personaje
		 * 
		 * */
		this.idCasta = 3;
		this.agregarHabilidad(new Velocidad());
		this.agregarHabilidad(new Evasion());
	}

	


}