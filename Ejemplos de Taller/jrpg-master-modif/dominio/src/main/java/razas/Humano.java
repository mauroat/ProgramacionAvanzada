package razas;

import dominio.*;

public class Humano extends Personaje {
	
	public Humano(String nombre){
		super(nombre);
		this.setRaza("Humano");
		this.ataque = 15;
		this.defensa = 5;
		this.magia = 0;
	}
	
	public Humano(Personaje p){
		super(p);
		this.setRaza("Humano");		
	}
	
	@Override
	public boolean puedeAtacar() {
		return energia > (10 + this.calcularPuntosDeAtaque());
	}


	@Override
	public String getRaza() {
		return raza;
	}

	@Override
	protected Personaje clone() throws CloneNotSupportedException {
		Personaje aux = new Humano(this);
		return aux;
	}

	
	
	
}

