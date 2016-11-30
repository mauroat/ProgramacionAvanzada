package criterios_aceptacion;


import dominio.*;
import habilidades.*;
import items.*;

import razas.*;
import castas.*;
import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

/***
 * 
 * 3)	 Atacar personajes. 
 * Motivación: Para aumentar mi experiencia.  
 * 
 ***/


public class Historia03Test {

	/***
	 * 
	 * 1.	Dado un Orco, cuando gane alguna batalla contra otro Personaje Jugador o Personaje Genérico, entonces el Orco aumentará su 
	 * 		experiencia acorde al nivel del derrotado.
	 * 
	 ***/

	@Test
	public void historia03Criterio01_Test() {
		
		/****************************************************
		 * 3.1.1 - Orco contra otro personaje
		 * ****************************************************/
		
		// Creación de personajes
		Personaje o1 = new Orco ("Orquito1");
		o1.setClase(new Guerrero());
		o1.agregarItem(new EspadaDeJuanNieve());
				
		
		Personaje o2 = new Orco ("Orquito2");		
		o2.setClase(new Guerrero());		
		o2.agregarItem(new DagaDeDragon());
		
		// Verifico mi experiencia inicial
		Assert.assertEquals(0, o1.getExperiencia());
		Assert.assertEquals(12+5, o1.calcularPuntosDeAtaque());
		
		// Verifico el nivel del Orco 2
		Assert.assertEquals(1, o2.getNivel());
		
		// Orco 1 ataca a Orco 2 hasta matarlo
		while(o2.estaVivo()){
			o1.atacar(o2);
			o1.atacar(o2);
			o1.atacar(o2);
			o1.atacar(o2);
			o1.serEnergizado();		
		}
		
		/*
		 * El Orco 2 muere
		 * */
		
		Assert.assertEquals(false, o2.estaVivo());
		
		/*
		 * El atacante debe ganar 8 puntos de experiencia por cada ataque realizado y 10*Nivel del oponente en caso de matar 
		 * al oponente.
		 * */ 
		Assert.assertEquals(8*8 + 10, o1.getExperiencia());
		
		
		/****************************************************
		 * 3.1.2 - Orco contra Genérico
		 * ****************************************************/
		
		// Creación de personajes
		Personaje o3 = new Orco ("Orquito3");
		o3.setClase(new Hechicero());
		o3.agregarItem(new EspadaDeJuanNieve());
		o3.agregarItem(new DagaDeDragon());
	
		Generico g1 = new Generico ("Generico 1");
		
		
		// Verifico mi experiencia inicial
		Assert.assertEquals(0, o3.getExperiencia());
		
		/*
		 * Verifico los puntos de ataque del orco: 12 + 5 + 4
		 * */
		
		Assert.assertEquals(12+4+5, o3.calcularPuntosDeAtaque());
		
		// Orco 3 ataca a Genérico 1 hasta matarlo
		while(g1.estaVivo()){
			o3.atacar(g1);
			o3.atacar(g1);
			o3.atacar(g1);
			o3.atacar(g1);
			o3.serEnergizado();		
		}
		
		if(!g1.estaVivo()){
			o3.agregarItem(g1.dejarItem());
		}
		
		/*
		 * Chequeo que el Orco 2 esté muerto 
		 * */

		Assert.assertEquals(false, g1.estaVivo());
		
		/*
		 * El atacante debe ganar 8 puntos de experiencia por cada ataque realizado y 10*Nivel del oponente en caso de matar 
		 * al oponente y ademas sumar un item
		 * El genérico tiene un nivel random entre 1 y 6, por lo tanto no es posible incluirlo en un Assert
		 * */ 
		
		Assert.assertEquals(3, o3.getMochila().size());
		//Assert.assertEquals(, o1.getExperiencia());
		
	}
	
	/***
	 * 
	 * 2.	Dado un Humano, cuando gane alguna batalla contra otro Personaje Jugador o Personaje Genérico, entonces el Orco aumentará su 
	 * 		experiencia acorde al nivel del derrotado.
	 * 
	 ***/

	@Test
	public void historia03Criterio02_Test() {
		/****************************************************
		 * 3.2.1 - Humano contra otro personaje
		 * ****************************************************/
		
		/*
		 * Creación de personajes
		 * */
		
		Personaje p1 = new Humano ("Humanito1");
		p1.setClase(new Guerrero());
		p1.agregarItem(new EspadaDeJuanNieve());
		p1.agregarItem(new DagaDeDragon());
		
		Personaje p2 = new Humano ("Humanito2");
		p2.setClase(new Guerrero());

		
		/*
		 * Verifico mis valores iniciales
		 * */
		
		Assert.assertEquals(0, p1.getExperiencia());
		Assert.assertEquals(15+4+5, p1.calcularPuntosDeAtaque());
		
		/*
		 * Verifico el nivel del humano 2
		 * */
		
		Assert.assertEquals(1, p2.getNivel());
		
		/*
		 * Humano 1 ataca a Humano 2 hasta matarlo
		 * */
		
		while(p2.estaVivo()){
			p1.atacar(p2);
			p1.atacar(p2);
			p1.atacar(p2);
			p1.atacar(p2);
			p1.serEnergizado();		
		}
		
		
		/*
		 * El Humano 2 muere
		 * */

		Assert.assertEquals(false, p2.estaVivo());
		
		/*
		 * El atacante debe ganar 8 puntos de experiencia por cada ataque realizado y 10*Nivel del oponente en caso de matar 
		 * al oponente.
		 * */ 
		Assert.assertEquals(40 + 10, p1.getExperiencia());
		
		
		/****************************************************
		 * 3.1.2 - Orco contra Genérico
		 * ****************************************************/
		
		/*
		 * Creación de personajes
		 * */
		Personaje p3 = new Humano ("Orquito3");
		p3.setClase(new Hechicero());
		p3.agregarItem(new EspadaDeJuanNieve());
		p3.agregarItem(new DagaDeDragon());
	
		Generico g1 = new Generico ("Generico 1");
		

		/*
		 * Verifico mi experiencia inicial
		 * */
 
		Assert.assertEquals(0, p3.getExperiencia());
		
		/*
		 * Verifico los puntos de ataque del humano: 10 + 5 + 4
		 * */
		
		Assert.assertEquals(15+4+5, p3.calcularPuntosDeAtaque());
		
		// Humano 3 ataca a Genérico 1 hasta matarlo
		while(g1.estaVivo()){
			p3.atacar(g1);
			p3.atacar(g1);
			p3.atacar(g1);
			p3.atacar(g1);
			p3.serEnergizado();		
		}
		
		/*
		 * Chequeo que el Generico esté muerto 
		 * */
		
		Assert.assertEquals(false, g1.estaVivo());
		
		/*
		 * El atacante debe ganar 8 puntos de experiencia por cada ataque realizado y 10*Nivel del oponente en caso de matar 
		 * al oponente.
		 * El genérico tiene un nivel random entre 1 y 6, por lo tanto no es posible incluirlo en un Assert
		 * */ 
		
		//Assert.assertEquals(, o1.getExperiencia());
		
		
	}
	
	/***
	 * 
	 * 3.	Dado un Elfo, cuando gane alguna batalla contra otro Personaje Jugador o Personaje Genérico, entonces el Orco aumentará su 
	 * 		experiencia acorde al nivel del derrotado.
	 * 
	 ***/
	
	@Test
	public void historia03Criterio03_Test() {
		/****************************************************
		 * 3.2.1 - Humano contra otro personaje
		 * ****************************************************/
		
		/*
		 * Creación de personajes
		 * */
		
		Personaje e1 = new Elfo ("Elfito1");
		e1.setClase(new Guerrero());
		e1.agregarItem(new EspadaDeJuanNieve());
		e1.agregarItem(new DagaDeDragon());
		
		Personaje e2 = new Elfo ("Elfito2");
		e2.setClase(new Guerrero());
		
		
		/*
		 * Verifico mi experiencia inicial
		 * */
		
		Assert.assertEquals(0, e1.getExperiencia());
		Assert.assertEquals(6+4+5, e1.calcularPuntosDeAtaque());
		
		/*
		 * Verifico el nivel del Elfo 2
		 * */
		
		Assert.assertEquals(1, e2.getNivel());
		
		/*
		 * Elfo  1 ataca a Elfo 2 hasta matarlo
		 * */
		
		while(e2.estaVivo()){
			e1.atacar(e2);
			e1.atacar(e2);
			e1.atacar(e2);
			e1.atacar(e2);
			e1.serEnergizado();		
		}
		
		
		/*
		 * El Elfo 2 muere
		 * */

		Assert.assertEquals(false, e2.estaVivo());
		
		
		/*
		 * El Elfo 1 obtiene el mejor item del elfo 2. En esta caso ninguno
		 * */
		
		e1.agregarItem(e2.dejarItem());
		
		/*
		 * El atacante debe ganar 8 puntos de experiencia por cada ataque realizado y 10*Nivel del oponente en caso de matar 
		 * al oponente.
		 * */ 
		Assert.assertEquals(8*9 + 10, e1.getExperiencia());
		Assert.assertEquals(2, e1.getMochila().size());
		
		/****************************************************
		 * 3.1.2 - Orco contra Genérico
		 * ****************************************************/
		
		/*
		 * Creación de personajes
		 * */
		Personaje p3 = new Orco ("Orquito3");
		p3.setClase(new Hechicero());		
		p3.agregarItem(new EspadaDeJuanNieve());
		p3.agregarItem(new DagaDeDragon());
		
		Generico g1 = new Generico ("Generico 1");
		
			
		/*
		 * Verifico mi experiencia inicial
		 * */
 
		Assert.assertEquals(0, p3.getExperiencia());
		
		/*
		 * Verifico los puntos de ataque del Orco: 12 + 5 + 4
		 * */
		
		Assert.assertEquals(12+4+5, p3.calcularPuntosDeAtaque());
		
		// Humano 3 ataca a Genérico 1 hasta matarlo
		while(g1.estaVivo()){
			p3.atacar(g1);
			p3.atacar(g1);
			p3.atacar(g1);
			p3.atacar(g1);
			p3.serEnergizado();		
		}
		
		/*
		 * Chequeo que el Generico esté muerto 
		 * */
		
		Assert.assertEquals(false, g1.estaVivo());
		
		/*
		 * El atacante debe ganar 8 puntos de experiencia por cada ataque realizado y 10*Nivel del oponente en caso de matar 
		 * al oponente.
		 * El genérico tiene un nivel random entre 1 y 6, por lo tanto no es posible incluirlo en un Assert
		 * */ 
		
		//Assert.assertEquals(, o1.getExperiencia());
	}
	

}
