package ataques;

import dominio.Ataque;

public class FuriaSangrienta  extends Ataque{

	public FuriaSangrienta(){
		this.idAtaque = 2;
		this.nombre = "Furia sangrienta";
		this.da�o = 9;
	}

	@Override
	public int aplicarAtaque() {
		return this.da�o;
	}
}
