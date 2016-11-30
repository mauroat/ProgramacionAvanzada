package razas;

import java.util.List;

import dominio.*;
public class Elfo extends Personaje{

	public Elfo(String nombre) {	
		super(nombre);
		this.setRaza("ELFO");
		this.idRaza = 3;
		this.ataque = 6;
		this.defensa = 4;
		this.magia = 10;
	}
	
	public Elfo(int idPersonaje, String nombre) {	
		super(nombre);
		this.idPersonaje = idPersonaje;
		this.setRaza("ELFO");
		this.idRaza = 3;
		this.ataque = 6;
		this.defensa = 4;
		this.magia = 10;
	}
	
	public Elfo(int idPersonaje, String nombre, int vida, int energia, int experiencia, int nivel, int puntos, 
			int puedeAgregarAtaque,	int destreza, int velocidad, int potencia, Casta clase, List<Item> mochila)	{
		super(nombre);
		this.idPersonaje = idPersonaje;
		this.nombre = nombre;
		this.vida = vida;
		this.energia = energia;
		this.setRaza("ELFO");
		this.idRaza = 3;
		this.ataque = 6;
		this.defensa = 4;
		this.magia = 10;
		this.experiencia = experiencia;
		this.nivel = nivel;	
		this.puntos = puntos;
		this.puedeAgregarAtaque = puedeAgregarAtaque;
		this.destreza = destreza;
		this.velocidad = velocidad;
		this.potencia = potencia;
		this.clase = clase;	
		this.mochila = mochila;
	}
	
	
	public Elfo(Personaje p) {
		super(p);
		this.setRaza("ELFO");
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
