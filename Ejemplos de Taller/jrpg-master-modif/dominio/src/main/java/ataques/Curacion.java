package ataques;

import dominio.*;

public class Curacion extends Ataque{
	
	public Curacion(){
		this.idAtaque = 7;
		this.nombre = "Curacion";
		this.da�o = -20;
	}

	@Override
	public int aplicarAtaque() {
		return this.da�o;
	}
}
