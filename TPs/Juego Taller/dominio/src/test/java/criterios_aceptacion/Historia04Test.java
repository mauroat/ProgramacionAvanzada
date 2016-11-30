package criterios_aceptacion;

import org.junit.Assert;
import org.junit.Test;

import dominio.Personaje;
import razas.Humano;


/***
 * 
 * 4)	 Como Personaje, quiero acumular experiencia. 
 * Motivación: Para poder subir de nivel.
 * 
 ***/


public class Historia04Test {

	/***
	 * 
	 * 1.	Dado un Personaje, cuando acumule la cantidad de experiencia necesaria, entonces se incrementará su nivel. 
	 * 
	 ***/

	@Test
	public void historia04Criterio01_Test() {
		Personaje p1 = new Humano("CarlosTevez");
		Personaje p2 = new Humano("Dalessandra");
		Personaje p3 = new Humano("Romagnola");
		
		/*
		 * El personaje p1 ataca a p2 y p3. En consecuencia, aumenta su experiencia. 
		 * */
		
		for (int i = 0; i<9;i++)			
			p1.atacar(p2);
		p1.serEnergizado();
		for (int i = 0; i<6;i++)			
			p1.atacar(p2);
		p1.serEnergizado();
		for (int i = 0; i<6;i++)			
			p1.atacar(p3);
		p1.serEnergizado();
		for (int i = 0; i<6;i++)			
			p1.atacar(p3);
		p1.serEnergizado();
		
		/*
		 * El personaje p1 sube de nivel
		 * */
		
		Assert.assertEquals(2, p1.getNivel());
		
	}
	
	

}
