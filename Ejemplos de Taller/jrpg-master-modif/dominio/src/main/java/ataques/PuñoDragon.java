package ataques;

import dominio.Ataque;

public class Pu�oDragon extends Ataque{

	public Pu�oDragon(){
		this.idAtaque = 5;
		this.nombre = "Pu�o Drag�n";
		this.da�o = 8;
	}

	@Override
	public int aplicarAtaque() {
		return this.da�o;
	}
}
