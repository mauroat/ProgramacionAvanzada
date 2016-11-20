package criterios_aceptacion;

import java.io.FileNotFoundException;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import habilidades.*;
import items.*;
import razas.*;

// OK
//El primero falla. No me doy cuenta en que...


/***
 * 
 * 5)	 Como Personaje, quiero subir de nivel.  
 * Motivación: Para poder asignar puntos adicionales a mis habilidades.
 * 
 ***/

public class Historia05Test {

	
	/***
	 * 
	 * 1.	Dado un Personaje, cuando éste aumente su nivel, entonces se le otorgarán puntos adicionales para poder agregar a 
	 * sus habilidades.
	 * @throws FileNotFoundException 
	 * @throws CloneNotSupportedException 
	 * 
	 ***/
	
	@Test
	public void historia05Criterio01_Test() throws FileNotFoundException, CloneNotSupportedException {
		Personaje p1 = new Humano("CarlosTevez");
		p1.setClase(new Guerrero());
		p1.setUbicacion(new Ubicacion(0,1));
		
		Personaje p2 = new Humano("Dalessandra");
		p2.setClase(new Guerrero());
		p2.setUbicacion(new Ubicacion(0,4));
		
		Personaje p3 = new Humano("Romagnola");
		p3.setClase(new Guerrero());
		p3.setUbicacion(new Ubicacion(1,4));
		
		Personaje p4 = new Humano("Romagnola");
		p4.setClase(new Guerrero());
		p4.setUbicacion(new Ubicacion(1,6));
		
		p2.formarAlianzaCon(p3);
		p3.formarAlianzaCon(p4);
		
		
		
		/*
		 * Creacion de equipos
		 * */
		Equipo e1 = new Equipo(p1);
		Equipo e2 = new Equipo(p2);
		
		/*
		 * Arranca el combate
		 * */
			
		Random r = new Random();
		int aux = r.nextInt(2);
		
		/*
		 * Equipo 1 ataca siempre al equipo 2
		 * */
		while(e2.quedaAlgunoVivo()){			
			e1.atacar(e2);			
		}

		if(e2.quedaAlgunoVivo()){
			e2.repartirExperiencia(e1.calcularExperiencia());
			e2.repartirItem(e1);
		}
		
		
		
		/*
		 * El personaje p1 sube de nivel
		 * */
		Assert.assertEquals(1, p1.getNivel());
		
		
		/*
		 * El personaje p1 acumula 2 puntos por subir de nivel
		 * */
		
		Assert.assertEquals(2, p1.getPuntos());
		
	}
	
	/***
	 *
	 * 2.	Dado un Personaje, cuando éste aumente su nivel y acumule puntos de habilidades, entonces se le permitirá mejorar 
	 * las habilidades existentes asignándoles puntos especiales.
	 * @throws FileNotFoundException 
	 * @throws CloneNotSupportedException 
	 * 
	 ***/
	
	@Test
	public void historia05Criterio02_Test() throws FileNotFoundException, CloneNotSupportedException {
		Personaje p1 = new Humano("CarlosTevez");
		p1.setClase(new Guerrero());
		p1.setUbicacion(new Ubicacion(0,1));
		
		Personaje p2 = new Humano("Dalessandra");
		p2.setClase(new Hechicero());
		p2.setUbicacion(new Ubicacion(1,1));
		
		Personaje p3 = new Humano("Romagnola");
		p3.setClase(new Chaman());	
		p3.setUbicacion(new Ubicacion(1,0));
		
		/*
		 * Controlo que el personaje p1 tenga 2 habilidades: Incorporadas gracias a la casta Guerrero
		 * */
		Assert.assertEquals(2, p1.getClase().getHabilidades().size());
		
		/*
		 * Formo alianzas y empiezo combate
		 * */
		
		p2.formarAlianzaCon(p3);
		
		Equipo e1 = new Equipo(p1);
		Equipo e2 = new Equipo(p2);
		
		
		
		/*
		 * Ataco para subir de nivel
		 * */
		
		while(e2.quedaAlgunoVivo()){			
			e1.atacar(e2);			
		}

		if(e2.quedaAlgunoVivo()){
			e2.repartirExperiencia(e1.calcularExperiencia());
			e2.repartirItem(e1);
		}
		
		
		/*
		 * El personaje p1 sube de nivel
		 * */
		Assert.assertEquals(2, p1.getNivel());		
		
		/*
		 * El personaje p1 acumula 2 puntos por subir de nivel
		 */
		
		Assert.assertEquals(2, p1.getPuntos());
		
		/*
		 * El personaje p1 asigna estos puntos a sus habilidades: Fuerza y Valentia
		 */
		
		p1.getClase().getHabilidades().get(3).afectar(p1);
		p1.getClase().getHabilidades().get(5).afectar(p1);
		
		/*
		 * El personaje p1 queda sin puntos disponibles
		 */
		Assert.assertEquals(0, p1.getPuntos());
		
		/*
		 * El personaje p1 tiene la habilidad Fuerza y Valentia con 1 puntos c/u
		 */
		Assert.assertEquals(1, p1.getClase().getHabilidades().get(3).getPuntos());
		Assert.assertEquals(1, p1.getClase().getHabilidades().get(5).getPuntos());
		
		
		/*
		 * El personaje p1 mejoró sus atributos gracias a su nueva habilidad
		 * */
		Assert.assertEquals(15+1+2, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0+1, p1.getPotencia());
	}

	
	
	/***
	 * 
	 *3.	Dado un Personaje equipado con items, cuando éste aumente su nivel podrá asignar puntos adicionales a sus habilidades, 
	 * entonces podrá mejorar sus atributos para el manejo de ciertos items.
	 * 
	 ***/

	
	@Test
	public void historia05Criterio03_Test() {
		Personaje p1 = new Humano("JRR10");
		p1.setClase(new Guerrero());
		p1.agregarItem(new PocionBruta());
		p1.getClase().agregarHabilidad(new Inteligencia());
				
		Personaje p2 = new Humano("Yepes");
		
		
		/*
		 * Controlo que el personaje p1 tenga una habilidad nueva + las 2 que vienen por defecto en la casta
		 * */
		Assert.assertEquals(3, p1.getClase().getHabilidades().size());
		
		
		/*
		 * Ataco para subir de nivel
		 * */
		
		while(p2.estaVivo() && p1.getNivel()<=3){
			p1.atacar(p2);
			p1.atacar(p2);
			p1.atacar(p2);
			p1.atacar(p2);
			if(p1.getEnergia() < 10 + p1.calcularPuntosDeAtaque()){
				p1.serEnergizado();
			}			
		}
		
		
		/*
		 * El personaje p1 sube de nivel
		 * */
		Assert.assertEquals(2, p1.getNivel());		
		
		/*
		 * El personaje p1 acumula 6 puntos por subir de nivel
		 */
		
		Assert.assertEquals(2, p1.getPuntos());
		
		/*
		 * El personaje p1 asigna estos puntos a sus habilidades
		 */
		
		p1.getClase().getHabilidades().get(4).afectar(p1);
		
		/*
		 * El personaje p1 queda con un punto disponible
		 */
		Assert.assertEquals(1, p1.getPuntos());
		
		/*
		 * El personaje p1 tiene la habilidad Destreza con 2 puntos
		 */
		Assert.assertEquals(1, p1.getClase().getHabilidades().get(4).getPuntos());
		
		
		/*
		 * El personaje p1 mejoró sus atributos gracias a su nueva habilidad
		 * */
		Assert.assertEquals(15-2+1, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5+4, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(0+2, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0+1, p1.getPotencia());	
		
	}
	
	
	/***
	 * 
	 * 4.	Dado un Personaje, cuando éste aumente su nivel y alcance uno determinado, entonces podrá agregar una nueva habilidad 
	 * que afecte sus atributos. 
	 * 
	 ***/
	
	@Test
	public void historia05Criterio04_Test() {
		/*
		 * Se crean 2 usuarios y dos personajes en base a estos usuarios 
		 * */
		
	//	Usuario u1 = new Usuario("Usuario 1");
	//	Usuario u2 = new Usuario("Usuario 2",);
		
		Personaje p1 = new Orco("Orco 1");
		p1.setClase(new Guerrero());
		
		Personaje p2 = new Elfo("Elfo 2");
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
			if(p1.getEnergia()<10+p1.calcularPuntosDeAtaque()){
				p1.serEnergizado();
			}
			
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
