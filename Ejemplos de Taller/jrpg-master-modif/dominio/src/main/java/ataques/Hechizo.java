package ataques;

import dominio.Ataque;

public class Hechizo  extends Ataque{

	public Hechizo(){
		this.idAtaque = 4;
		this.nombre = "Hechizo";
		this.da�o = 6;
	}

	@Override
	public int aplicarAtaque() {
		return this.da�o;
	}
}
