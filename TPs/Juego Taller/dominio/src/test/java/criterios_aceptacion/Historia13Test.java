package criterios_aceptacion;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import items.*;
import razas.*;



/***
 * 
 * 13)	 Como Personaje, puedo morir en combate.
 *  Motivación: Reaparecer en una zona segura.
 * 
 ***/
public class Historia13Test {

	
	
	/***
	 * 
	 * 1.	Dado un Personaje, cuando éste es derrotado en combate, entonces reaparece en el lugar seguro designado en el mundo 
	 * donde se encuentra. 
	 * 
	 ***/
	
	@Test
	public void historia13Criterio01_Test() {
		
		/*
		 * Creo los personajes
		 * */
		
		Personaje p1 = new Orco("gato");
		p1.setClase(new Guerrero());
		p1.agregarItem(new DagaDeDragon());
		p1.agregarItem(new EspadaDeJuanNieve());
		
		Personaje p2 = new Humano("perro");
		p2.setClase(new Chaman());
		
	
		
		/*
		 * Verifico experiencia y nivel
		 * */
		
		Assert.assertEquals(0, p1.getExperiencia());
		Assert.assertEquals(1, p1.getNivel());
		
		/*
		 * Ataco repetidamente para ganar experiencia y subir de nivel
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
		 * Verifico que el personaje p2 esté muerto
		 * */
		Assert.assertEquals(false, p2.estaVivo());
		
		/*
		 * El personaje debe ser revivido y debe reaparecer en otra posición del mapa.
		 * */
		
		p2.serCurado();
		
		// Método a definir e implementar
		p2.reubicar();
		
		Assert.assertEquals(100, p2.getVida());
	}
}
