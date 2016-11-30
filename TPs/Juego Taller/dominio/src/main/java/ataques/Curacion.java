package ataques;

import dominio.*;

public class Curacion extends Ataque{
	
	public Curacion(){
		this.idAtaque = 7;
		this.nombre = "Curacion";
		this.daño = -20;
	}

	@Override
	public int aplicarAtaque() {
		return this.daño;
	}
}
