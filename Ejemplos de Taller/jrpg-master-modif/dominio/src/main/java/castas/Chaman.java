package castas;
import dominio.*;
import habilidades.*;

public class Chaman extends Casta {

	public Chaman() {
		super.nombre = "Chaman";
		/*
		 * Ver de que forma puedo hacer ademas de agregarlas puedan afectar al personaje
		 * 
		 * */
		
		this.agregarHabilidad(new Velocidad());
		this.agregarHabilidad(new Evasion());
	}

	


}