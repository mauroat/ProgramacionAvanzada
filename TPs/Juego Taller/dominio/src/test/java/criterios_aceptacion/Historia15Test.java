package criterios_aceptacion;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import items.*;
import razas.*;


/***
 * 
 * 15)	 Como Personaje, quiero dirigirme al lugar seguro.
 * Motivación: Para recuperar salud.
 * 
 ***/
public class Historia15Test {

	
	
	/***
	 * 
	 * 1.	Dado un Personaje, cuando se encuentre con la salud baja, entonces podrá dirigirse a la zona segura donde 
	 * su salud comenzará a incrementarse. 
	 * 
	 ***/
	
	@Test
	public void historia15Criterio01_Test() {
		
		/*
		 * Creo los personajes
		 * */
		
		Personaje p1 = new Elfo("gato");
		p1.setClase(new Guerrero());
		p1.agregarItem(new DagaDeDragon());
		p1.agregarItem(new EspadaDeJuanNieve());
		
		Personaje p2 = new Elfo("perro");	
		p2.setClase(new Chaman());
		
		/*
		 * Verifico experiencia y nivel
		 * */
		Assert.assertEquals(0, p1.getExperiencia());
		Assert.assertEquals(1, p1.getNivel());
		
		/*
		 * Ataco repetidamente para ganar experiencia y subir de nivel
		 * */
		p1.atacar(p2);
		p1.atacar(p2);
		p1.atacar(p2);
		p1.atacar(p2);
		
		Assert.assertEquals(8*7, p2.getVida());
		
		/*
		 * El personaje p2 se dirige (?) a una zona segura y es curado
		 * */
		
		p2.serCurado();
		
		Assert.assertEquals(100, p2.getVida());
		
	}
}
