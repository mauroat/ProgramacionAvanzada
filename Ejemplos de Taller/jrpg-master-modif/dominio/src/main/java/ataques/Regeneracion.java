package ataques;

import dominio.Ataque;

/*
 *	 este ataque incrementa la energia del que lo use
 * 
 */
 

public class Regeneracion  extends Ataque{

	public Regeneracion(){
		this.idAtaque = 6;
		this.nombre = "Regeneraci�n";
		this.da�o = 30;
	}

	@Override
	public int aplicarAtaque() {
		return da�o;
	}
	
	
}
