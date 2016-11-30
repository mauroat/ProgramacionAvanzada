package criterios_aceptacion;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import habilidades.*;
import items.*;
import razas.*;



/***
 * 
 * 7) Como Personaje, Quiero equipar Items. 
 * Motivacion: Para poder potenciar mis habilidades.
 * 
 ***/

public class Historia07Test {


	/***
	 * 
	 * 1.	Dado un Personaje, cuando éste mata a otro Personaje Jugador o Personaje Genérico, entonces éste se equipará con 
	 * el mejor item de su enemigo.
	 * @throws CloneNotSupportedException 
	 * 
	 ***/
	
	@Test
	public void historia07Criterio01_Test() throws CloneNotSupportedException{
	
		/**
		 * 7.1.1.	Personaje con otro personaje
		 * */
		Personaje p1 = new Humano("Fito");
		p1.setClase(new Hechicero());
		
		
		Personaje p2 = new Elfo("Chano");
		p2.setClase(new Chaman());
		p2.agregarItem(new RunaDeMagia());
		p2.agregarItem(new PocionSabiduria());
		
		
		// IMPLEMENTAR ATAQUE ENTRE EQUIPOS
		/*
		 * Combate entre p1 y p2 
		 */
		
		while(p2.estaVivo()){
			p1.atacar(p2);
			p1.atacar(p2);
			p1.atacar(p2);
			p1.serEnergizado();			
		}
		
		if(!p2.estaVivo())
			p1.agregarItem(p2.dejarItem());

		/*
		 * Personaje p1 agrega 1 item y p2 deja 1 item 
		 * */
		
		Assert.assertEquals(1, p1.getMochila().size());
		Assert.assertEquals("Pocion sabiduria", p1.getMochila().get(0).getNombre());
		
		Assert.assertEquals(1, p2.getMochila().size());
		
		
		
		/**
		 * 7.1.2.	Personaje con genérico
		 * */
		Personaje p3 = new Humano("Fito");
		p3.setClase(new Hechicero());
		p3.agregarItem(new EspadaDeJuanNieve());
		
		Generico g1 = new Generico();
		
	
		/*
		 * Los genericos se generan con un item aleatorio por defecto
		 * */
		Assert.assertNotEquals(null, g1.getItem());
				
		/*
		 * Combate entre p1 y p2 
		 */
		
		while(g1.estaVivo()){
			p3.atacar(g1);
			p3.atacar(g1);
			p3.atacar(g1);
			p3.serEnergizado();			
		}
		
		if(!g1.estaVivo())	
			p1.agregarItem(g1.dejarItem());
			
		
		
		Assert.assertEquals(2, p1.getMochila().size());
		
	}
	
	
	/***
	 * 
	 * 2.	Dado un Personaje, cuando encuentre un ítem al recorrer el mundo donde se encuentra, entonces podrá equiparse con 
	 * el mismo o agregarlo a su inventario.
	 * 
	 ***/
	
	@Test
	public void historia07Criterio02_Test() {
		// No testeable
		
	}
	
	/***
	 * 
	 * 3.	Dado un Personaje, cuando selecciona el ítem nuevo para equiparse, entonces éste será más fuerte con la combinación 
	 * de ítem y habilidad.	
	 * 
	 ***/
	
	@Test
	public void historia07Criterio03_Test() {
		
		
		/*
		 * Creo un objeto personaje estandar
		 * */
		Personaje p1 = new Humano("Pepito");
		p1.setClase(new Guerrero());
		
		/*
		 * Verifico el valor de los atributos:
		 * Ataque : 10
		 * Defensa: 10
		 * Magia :  0
		 * Velocidad: 0
		 * Destreza: 0
		 * Potencia: 0
		 * */
		
		Assert.assertEquals(15, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0, p1.getPotencia());
		
		/*
		 * Equipo al p1 con un item
		 * */
		p1.agregarItem(new PocionSabiduria());
	
		
		/*
		 * Verifico el nuevo valor de sus atributos:
		 * Ataque : 10
		 * Defensa: 10+2
		 * Magia :  0+3
		 * Velocidad: 0
		 * Destreza: 0
		 * Potencia: 0
		 * */
		
		Assert.assertEquals(15, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5+2, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0+3, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0, p1.getPotencia());
		
		/*
		 * Agrego una habilidad al personaje
		 * */
		p1.setPuntos(1);
		p1.getClase().agregarHabilidad(new Valentia());		
		p1.getClase().getHabilidades().get(5).afectar(p1);
		
		/*
		 * Verifico el nuevo valor de sus atributos:
		 * Ataque : 10+2
		 * Defensa: 10+2
		 * Magia :  0+3
		 * Velocidad: 0
		 * Destreza: 0
		 * Potencia: 0
		 * */
		
		Assert.assertEquals(15+2, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5+2, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0+3, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0, p1.getPotencia());
		
		
	}
}
