package ataques;

import dominio.Ataque;

public class GanchoDeGoro  extends Ataque {

	public GanchoDeGoro(){
		this.idAtaque = 3;
		this.nombre = "Gancho de Goro";	
		this.da�o = 12;
	}

	@Override
	public int aplicarAtaque() {
		return this.da�o;
	}
}
