package ataques;

import dominio.*;

public class CuerpoACuerpo  extends Ataque {

	public CuerpoACuerpo(){
		this.idAtaque = 1;
		this.nombre = "Cuerpo a cuerpo";
		this.da�o = 5;
	}

	@Override
	public int aplicarAtaque() {
		return this.da�o;
	}
	
	
	
}
