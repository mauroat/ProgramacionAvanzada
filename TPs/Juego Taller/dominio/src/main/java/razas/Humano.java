package razas;

import java.util.List;

import dominio.*;

public class Humano extends Personaje {
	
	public Humano(String nombre){
		super(nombre);
		this.setRaza("HUMANO");
		this.idRaza = 1;
		this.ataque = 15;
		this.defensa = 5;
		this.magia = 0;
	}
	
	public Humano(int idPersonaje, String nombre) {	
		super(nombre);
		this.idPersonaje = idPersonaje;
		this.setRaza("HUMANO");
		this.idRaza = 1;
		this.ataque = 15;
		this.defensa = 5;
		this.magia = 0;
	}
	
	public Humano(int idPersonaje, String nombre, int vida, int energia, int experiencia, int nivel, int puntos, 
			int puedeAgregarAtaque,	int destreza, int velocidad, int potencia, Casta clase, List<Item> mochila)	{
		super(nombre);
		this.idPersonaje = idPersonaje;
		this.nombre = nombre;
		this.vida = vida;
		this.energia = energia;
		this.setRaza("HUMANO");
		this.idRaza = 1;
		this.ataque = 15;
		this.defensa = 5;
		this.magia = 0;
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
	
	public Humano(Personaje p){
		super(p);
		this.setRaza("HUMANO");		
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

