package ataques;

import dominio.*;

public class CuerpoACuerpo  extends Ataque {

	public CuerpoACuerpo(){
		this.idAtaque = 1;
		this.nombre = "Cuerpo a cuerpo";
		this.daño = 5;
	}

	@Override
	public int aplicarAtaque() {
		return this.daño;
	}
	
	
	
}
