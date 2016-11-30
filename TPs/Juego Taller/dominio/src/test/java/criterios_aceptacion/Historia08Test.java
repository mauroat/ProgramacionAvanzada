package criterios_aceptacion;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import habilidades.*;
import razas.*;


/***
 * 
 * 8)	 Como Personaje, Quiero disponer de habilidades de destreza, fuerza e inteligencia.
 * Motivación: Para afectar a mis puntos de ataque, magia y defensa.
 * 
 ***/

public class Historia08Test {


	/***
	 * 
	 * 1.	Dado un Personaje, cuando agrega a su lista de habilidades ‘Destreza’, entonces aumentará su velocidad y potencia. 
	 * 
	 ***/
	
	@Test
	public void historia08Criterio01_Test() {
		/*
		 * Declaración de objeto Personaje. Casta: Hechicero ya que contiene la habilidad Destreza
		 * */
		
		Personaje p1 = new Humano("Humanito");
		p1.setClase(new Hechicero());
		
		/*
		 * Afecto a Personaje con Destreza
		 * */
		p1.setPuntos(1);
		p1.getClase().getHabilidades().get(1).afectar(p1);
		
		/*
		 * Compruebo los valores de los atributos
		 * */
		
		Assert.assertEquals(15, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0+1, p1.getVelocidad());
		Assert.assertEquals(0+1, p1.getDestreza());
		Assert.assertEquals(0, p1.getPotencia());
		
	
	}
	
	
	/***
	 * 
	 * 2.	Dado un Personaje, cuando agrega a su lista de habilidades ‘Fuerza’, entonces aumentará su ataque y potencia.
	 * 
	 ***/
	
	@Test
	public void historia08Criterio02_Test() {
		/*
		 * Declaración de objeto Personaje. Casta: Hechicero ya que contiene la habilidad Fuerza
		 * */
		
		Personaje p1 = new Humano("Humanito");
		p1.setClase(new Guerrero());
		/*
		 * Afecto a Personaje con Fuerza
		 * */
		p1.setPuntos(1);
		p1.getClase().getHabilidades().get(3).afectar(p1);
		
		/*
		 * Compruebo los valores de los atributos
		 * */
		
		Assert.assertEquals(15+1, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0+1, p1.getPotencia());
		
		
	}
	
	/***
	 * 
	 * 3.	Dado un Personaje, cuando agrega a su lista de habilidades ‘Inteligencia’, entonces aumentará su ataque y potencia.
	 * 
	 ***/
	
	@Test
	public void historia08Criterio03_Test() {
		/*
		 * Declaración de objeto Personaje. Casta: Hechicero ya que contiene la habilidad Inteligencia
		 * */
		
		Personaje p1 = new Humano("Humanito");
		p1.setClase(new Hechicero());
		/*
		 * Afecto a Personaje con Inteligencia
		 * */
		p1.setPuntos(1);
		p1.getClase().getHabilidades().get(4).afectar(p1);
		
		/*
		 * Compruebo los valores de los atributos
		 * */
		
		Assert.assertEquals(15+1, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0+1, p1.getPotencia());
		
		
	}
	
	/***
	 * 
	 * 4.	Dado un Personaje, cuando agrega a su lista de habilidades ‘Velocidad’, entonces aumentará su velocidad y su ataque.
	 * 
	 ***/
	
	@Test
	public void historia08Criterio04_Test() {
		/*
		 * Declaración de objeto Personaje. Casta: Hechicero ya que contiene la habilidad Velocidad
		 * */
		
		Personaje p1 = new Humano("Humanito");
		p1.setClase(new Chaman());
		/*
		 * Afecto a Personaje con Inteligencia
		 * */
		p1.setPuntos(1);
		p1.getClase().getHabilidades().get(6).afectar(p1);
		
		/*
		 * Compruebo los valores de los atributos
		 * */
		
		Assert.assertEquals(15+1, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0+1, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0, p1.getPotencia());
		
	}
	
	/***
	 * 
	 * 5.	Dado un Personaje, cuando agrega a su lista de habilidades ‘Evasión’, entonces aumentará su defensa.
	 * 
	 ***/
	
	@Test
	public void historia08Criterio05_Test() {
		/*
		 * Declaración de objeto Personaje. Casta: Hechicero ya que contiene la habilidad Evasion
		 * */
		
		Personaje p1 = new Humano("Humanito");
		p1.setClase(new Chaman());
		/*
		 * Afecto a Personaje con Evasion
		 * */
		p1.setPuntos(1);
		p1.getClase().getHabilidades().get(2).afectar(p1);
		
		/*
		 * Compruebo los valores de los atributos
		 * */
		
		Assert.assertEquals(15, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5+2, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0, p1.getPotencia());
		
	}
	
	/***
	 * 
	 * 6.	Dado un Personaje, cuando agrega a su lista de habilidades ‘Valentía’, entonces aumentará su ataque. 
	 * 
	 ***/
	
	@Test
	public void historia08Criterio06_Test() {
		/*
		 * Declaración de objeto Personaje. Casta: Hechicero ya que contiene la habilidad Evasion
		 * */
		
		Personaje p1 = new Humano("Humanito");
		p1.setClase(new Guerrero());
		/*
		 * Afecto a Personaje con Evasion
		 * */
		p1.setPuntos(1);
		p1.getClase().getHabilidades().get(5).afectar(p1);
		
		/*
		 * Compruebo los valores de los atributos
		 * */
		
		Assert.assertEquals(15+2, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0, p1.getPotencia());
		
	}
}
