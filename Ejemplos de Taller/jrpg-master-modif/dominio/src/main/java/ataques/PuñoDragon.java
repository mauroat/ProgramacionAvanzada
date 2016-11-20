package ataques;

import dominio.Ataque;

public class PuñoDragon extends Ataque{

	public PuñoDragon(){
		this.idAtaque = 5;
		this.nombre = "Puño Dragón";
		this.daño = 8;
	}

	@Override
	public int aplicarAtaque() {
		return this.daño;
	}
}
