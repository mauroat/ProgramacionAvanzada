package criterios_aceptacion;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import habilidades.*;
import items.*;
import razas.*;


// Agregado criterio 3 y 4, faltan 1 y 2
// criterio 3 parecido al criterio 1 de historia de usuario 7

/***
 * 
 * 2)	 Como Jugador, Quiero ingresar a un mundo.
 *  Motivación: Para adquirir experiencia, ítems y habilidades nuevas. 
 * 
 ***/
public class Historia02Test {

	/***
	 * 
	 * 1.	Dado un Jugador, cuando quiera ingresar a alguna partida, entonces seleccionará uno de los mundos 
	 * 
	 ***/
	public void historia02Criterio01_Test(){
		/*
		 * Criterio no testeable
		 * */
	}
	

	/***
	 * 
	 * 2.	Dado un Jugador, cuando se encuentre en alguna partida o buscando alguna para ingresar, entonces se le mostrará 
	 * aquellas partidas o mundos disponibles a las cuales le sea posible unirse.
	 * 
	 ***/
	public void historia02Criterio02_Test(){
		/*
		 * Criterio no testeable
		 * */
	}
	

	/***
	 * 
	 * 3.	Dado un Jugador, cuando su personaje gane una batalla contra otro personaje Jugador o un personaje genérico,
	 * entonces obtendrá experiencia e ítems.
	 * @ 
	 * @throws CloneNotSupportedException 
	 * 
	 ***/
	public void historia02Criterio03_Test() throws CloneNotSupportedException{
		/**
		 * 2.3.1.	Personaje contra Personaje
		*/
		
		/*
		 * Creo los personajes 
		 * */


		Personaje p1 = new Humano("Skay");
		p1.setClase(new Hechicero());
		
		
		/*
		 * Equipo al p2 con 2 items: el mas prioritario es Pocion Sabiduria 
		 * */
		
		Personaje p2 = new Elfo("Semilla");
		p2.setClase(new Chaman());
		p2.agregarItem(new RunaDeMagia());
		p2.agregarItem(new PocionSabiduria());
		
				
		/*
		 * Como recien está creado, su experiencia es 0
		 * */
		Assert.assertEquals(0, p1.getExperiencia());
		
		/*
		 * Combate entre p1 y p2 
		 */
		
		while(p2.estaVivo()){
			p1.atacar(p2);
			p1.atacar(p2);
			p1.atacar(p2);
			p1.serEnergizado();			
		}
		
		/*
		 * Cuando muera p2, le desequipo su mejor item y se lo agrego a p1
		 * */
		if(!p2.estaVivo()){
			p1.agregarItem(p2.dejarItem());	
		}
		
		/*
		 * La lista de items del personaje p1 ahora es 1 ya que obtuvo el mejor item del eliminado
		 * */
		
		Assert.assertEquals(1, p1.getMochila().size());
		Assert.assertEquals("Pocion sabiduria", p1.getMochila().get(0).getNombre());
		
		/*
		 * Luego de atacar, su experiencia ya no es 0
		 * */
		Assert.assertNotEquals(0, p1.getExperiencia());
		
		/**
		 * 2.3.2.	Personaje contra Generico
		*/
		
		Personaje p3 = new Humano("Pipi");
		p3.setClase(new Hechicero());
		
		Generico g1 = new Generico();
		
		/*
		 * Ataco al generico hasta matarlo
		 * */
		
		while(g1.estaVivo()){
			p3.atacar(g1);
			p3.atacar(g1);
			p3.atacar(g1);
			p3.serEnergizado();			
		}
		
		/*
		 * Si está muerto, me equipo con su item
		 * */
		
		if(!g1.estaVivo()){			
			p3.agregarItem(g1.dejarItem());			
		}
		
		/*
		 * Controlo que la lista de p3 tenga un item
		 * */
		
		Assert.assertEquals(1, p3.getMochila().size());
		
	}
	

	/***
	 * 
	 * 4.	Dado un Jugador, cuando su personaje acumule la experiencia necesaria para aumentar de nivel, entonces podrá agregar 
	 * nuevas habilidades.
	 * 
	 ***/
	@Test
	public void historia02Criterio04_Test() {
		/*
		 * Se crean 2 personajes 
		 * La casta Guerrero le agrega fuerza a estos personajes
		 * */
		
		
		Personaje p1 = new Orco("Usuario 1");
		p1.setClase(new Guerrero());
		
		Personaje p2 = new Elfo("Usuario 2");
		p2.setClase(new Guerrero());
		

		/*
		 * Compruebo mis atributos iniciales
		 * */
		
		Assert.assertEquals(12, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(3, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0, p1.getPotencia());
		
		/*
		 * Ataco para obtener experiencia 
		 * */
		
		while(p2.estaVivo() && p1.getNivel()<=5){
			p1.atacar(p2);
			p1.atacar(p2);
			p1.atacar(p2);
			p1.atacar(p2);
			p1.serEnergizado();	
		}
		
		/*
		 * El personaje p1 sube de nivel
		 * */
		Assert.assertEquals(2, p1.getNivel());
		
		
		/*
		 * El personaje p1 acumula 2 puntos por subir de nivel
		 * */
		
		Assert.assertEquals(2, p1.getPuntos());
		
		/*
		 * El personaje agrega una nueva habilidad a su lista. Ésta es ingresada con 1 punto.
		 * */
		p1.getClase().agregarHabilidad(new Inteligencia());
		p1.getClase().getHabilidades().get(4).afectar(p1);
		
		p1.getClase().agregarHabilidad(new Valentia());
		p1.getClase().getHabilidades().get(5).afectar(p1);
		
		/*
		 * Compruebo que mi lista tiene dos habilidades y que las mismas tienen 1 punto cada una.
		 * */
		Assert.assertEquals(3, p1.getClase().getHabilidades().size());
	
		/*
		 * Fuerza: 0 puntos
		 * Inteligencia: 1 punto
		 * Valentia: 1 punto
		 * */
		
		Assert.assertEquals(0, p1.getClase().getHabilidades().get(3).getPuntos());
		Assert.assertEquals(1, p1.getClase().getHabilidades().get(4).getPuntos());
		Assert.assertEquals(1, p1.getClase().getHabilidades().get(5).getPuntos());
		
		
		/*
		 * Por ultimo compruebo que mis atributos base han cambiado
		 * */
			
		Assert.assertEquals(12+2+1, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(3, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0+1, p1.getPotencia());
	}
}
