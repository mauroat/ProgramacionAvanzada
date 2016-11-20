package criterios_aceptacion;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import habilidades.*;
import items.*;
import razas.*;

// OK


/***
 * 
 * 6)	 Como Personaje, Quiero aumentar mis habilidades.  
 * Motivación: Para poder manipular ítems de manera más eficiente.
 * 
 ***/

public class Historia06Test {


	/***
	 * 
	 * 1.	Dado un Personaje, cuando aumenta de nivel y sus habilidades, entonces se incrementará el valor de ciertos atributos.
	 * 
	 ***/
	
	@Test
	public void historia06Criterio01_Test() {
		/*
		 * Creo el personaje con casta Guerrero
		 * */
		
		Personaje p1 = new Orco("Fantino");
		p1.setClase(new Guerrero());
		p1.agregarItem(new BujiasHescher());
		
		
		/*
		 * Verifico el valor de los atributos:
		 * Ataque : 12 + 1
		 * Defensa: 5 + 1
		 * Magia :  3 + 1
		 * Velocidad: 0
		 * Destreza: 0
		 * Potencia: 0
		 * */
		
		Assert.assertEquals(12+1, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5+1, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(3+1, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(0, p1.getPotencia());
		
		/*
		 * Agrego puntos para asignar habilidades
		 * */
		p1.setPuntos(2);
		Assert.assertEquals(2, p1.getPuntos());
		
		/*
		 *Al agregar la casta se añaden dos habilidades
		 * */
		
		//p1.getClase().agregarHabilidad(new Destreza());
		p1.getClase().getHabilidades().get(5).afectar(p1);	
		p1.getClase().getHabilidades().get(3).afectar(p1);	
		
		Assert.assertEquals(2, p1.getClase().getHabilidades().size());
		
		/*
		 * Verifico que los atributos aumentaron, acorde a los puntos que tiene esta habilidad
		 * Ataque : 12 +1+ 1+2
		 * Defensa: 5 + 1
		 * Magia :  3 + 1
		 * Velocidad: 0 
		 * Destreza: 0
		 * Potencia: 0 + 1
		 * */
		
		Assert.assertEquals(12+1+1+2, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5+1, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(3+1, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(1, p1.getPotencia());
		
		/*
		 * Agrego otra habilidad: Inteligencia al personaje p1
		 * */
		p1.setPuntos(1);
		p1.getClase().agregarHabilidad(new Inteligencia());
		p1.getClase().getHabilidades().get(4).afectar(p1);
				
		Assert.assertEquals(3, p1.getClase().getHabilidades().size());
		
		/*
		 * Verifico que los atributos aumentaron, acorde a los puntos que tiene esta habilidad
		 * Ataque : 12 +1+ 1+2+1
		 * Defensa: 5 + 1
		 * Magia :  3 + 1
		 * Velocidad: 0 
		 * Destreza: 0
		 * Potencia: 0 + 1 + 1
		 * */
		
		//Assert.assertEquals(12+1+1+2+1, p1.calcularPuntosDeAtaque());
		Assert.assertEquals(5+1, p1.calcularPuntosDeDefensa());
		Assert.assertEquals(3+1, p1.calcularPuntosDeMagia());
		Assert.assertEquals(0, p1.getVelocidad());
		Assert.assertEquals(0, p1.getDestreza());
		Assert.assertEquals(1+1, p1.getPotencia());
		
	}
	
	
	
}
