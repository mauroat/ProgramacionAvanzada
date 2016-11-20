package ataques;

import dominio.Ataque;

/*
 *	 este ataque incrementa la energia del que lo use
 * 
 */
 

public class Regeneracion  extends Ataque{

	public Regeneracion(){
		this.idAtaque = 6;
		this.nombre = "Regeneración";
		this.daño = 30;
	}

	@Override
	public int aplicarAtaque() {
		return daño;
	}
	
	
}
