package razas;

import dominio.*;
public class Elfo extends Personaje{

	public Elfo(String nombre) {	
		super(nombre);
		this.setRaza("Elfo");
		this.ataque = 6;
		this.defensa = 4;
		this.magia = 10;
	}
	
	public Elfo(Personaje p) {
		super(p);
		this.setRaza("Elfo");
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
		Personaje aux = new Elfo(this);
		return aux;	
	}

	
	
	
}
