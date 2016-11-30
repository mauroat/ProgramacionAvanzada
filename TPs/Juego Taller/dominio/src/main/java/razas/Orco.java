package razas;

import java.util.List;

import dominio.*;

public class Orco extends Personaje{

	public Orco(String username) {
		super(username);
		this.setRaza("ORCO");
		this.idRaza = 2;
		this.ataque = 12;
		this.defensa = 5;
		this.magia = 3;
	}
	
	public Orco(int idPersonaje, String nombre) {	
		super(nombre);
		this.idPersonaje = idPersonaje;
		this.setRaza("ORCO");
		this.idRaza = 1;
		this.ataque = 15;
		this.defensa = 5;
		this.magia = 0;
	}
	
	public Orco(int idPersonaje, String nombre, int vida, int energia, int experiencia, int nivel, int puntos, 
			int puedeAgregarAtaque,	int destreza, int velocidad, int potencia, Casta clase, List<Item> mochila)	{
		super(nombre);
		this.idPersonaje = idPersonaje;
		this.nombre = nombre;
		this.vida = vida;
		this.energia = energia;
		this.setRaza("ORCO");
		this.idRaza = 2;
		this.ataque = 12;
		this.defensa = 5;
		this.magia = 3;
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
	
	public Orco(Personaje p) {
		super(p);
		this.setRaza("ORCO");
	}
	
	@Override
	public  boolean puedeAtacar() {
		return this.energia > (10 + this.calcularPuntosDeAtaque());
	}
	
		
	@Override
	protected Personaje clone() throws CloneNotSupportedException {
		Personaje aux = new Orco(this);
		return aux;
	}
	
	@Override
	public String getRaza() {
		return raza;
	}

}
