package criterios_aceptacion;

import java.io.FileNotFoundException;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import items.*;
import razas.*;


/*
 * @mauroat - 07/11/16
 * El error que tengo en el metodo equipo.repartirItems es que los punteros iniciales de p1, p2, etc
 * No se actualizan al decorator. Solo el equipo actualiza su referencia.
 * 
 * */


/***
 * 
 * 11)	Como Personaje, quiero combatir contra otros jugadores.
 * Motivación: Para obtener sus ítems al derrotarlos.
 * 
 ***/

public class Historia11Test {


	/***
	 * 
	 * 1.	Dado un Personaje que pertenece a una Alianza, cuando éstos resultan ganadores de un combate, entonces 
	 * se reparten los ítems de los perdedores entre los integrantes. 
	 * @throws FileNotFoundException 
	 * @throws CloneNotSupportedException 
	 * 
	 ***/
	
	@Test
	public void historia11Criterio01_Test() throws FileNotFoundException, CloneNotSupportedException {
		
		Personaje p1 = new Humano("Humano1");
		p1.setClase(new Guerrero());
		p1.setUbicacion(new Ubicacion(0,1));
		p1.agregarItem(new ArmaduraDeAzorAhai());
		
		Personaje p2 = new Orco("Humano2");
		p2.setClase(new Hechicero());
		p2.setUbicacion(new Ubicacion(0,4));
		p2.agregarItem(new BujiasHescher());
		
		Personaje p3 = new Elfo("Humano3");
		p3.setClase(new Chaman());
		p3.setUbicacion(new Ubicacion(1,4));
		p3.agregarItem(new PocionBruta());
		
		Personaje p4 = new Elfo("Humano4");
		p4.setClase(new Hechicero());
		p4.setUbicacion(new Ubicacion(1,6));
		p4.agregarItem(new PocionSabiduria());

		
		/*
		 * Armo las alianzas: 
		 *  
		 * */
	
		p1.formarAlianzaCon(p2);
		p3.formarAlianzaCon(p4);
		

		
		/*
		 * Se preparan los equipos a pelear
		 * */
		Equipo e1 = new Equipo(p1);
		Equipo e2 = new Equipo(p3);
		
		
		/*
		 * Compruebo que esten todos vivos
		 * */
		Assert.assertEquals(true, e1.quedaAlgunoVivo());
		Assert.assertEquals(true, e2.quedaAlgunoVivo());
		
		/*
		 * Se ataca por turnos. La idea es que cuando un equipo se queda sin peleadores, 
		 * se sumen los niveles de todos sus integrantes y se los multiplique por 10.
		 * Ese numero sera dividido por la cantidad de peleadores del equipo ganador y se le 
		 * sumara a cada uno a su experiencia. 
		 * 
		 * */
		/*
		 * Arranca el combate
		 * */
			
		Random r = new Random();
		int aux = r.nextInt(2);
		
		/*
		 * Defino quien ataca primero
		 * */
		while(e1.quedaAlgunoVivo() && e2.quedaAlgunoVivo() ){
			if(aux == 1){
				e1.atacar(e2);
				e2.atacar(e1);
			} else {
				e2.atacar(e1);
				e1.atacar(e2);
			}		
		}

		if(e1.quedaAlgunoVivo()){
			e1.repartirExperiencia(e2.calcularExperiencia());
			e1.repartirItem(e2);		
		}	else if(e2.quedaAlgunoVivo()){
			e2.repartirExperiencia(e1.calcularExperiencia());
			e2.repartirItem(e1);
		}
		
		
		
		Assert.assertEquals(0, p3.getMochila().size());
		Assert.assertEquals(0, p4.getMochila().size());
		
	}
	
	
	/***
	 * 
	 * 2.	Dado un Personaje, cuando finaliza el combate contra otro Personaje y resulta ganador, entonces se le 
	 * entrega el mejor ítem de aquel Personaje Usuario derrotado. 
	 * @throws FileNotFoundException 
	 * @throws CloneNotSupportedException 
	 * 
	 ***/
	
	@Test
	public void historia11Criterio02_Test() throws FileNotFoundException, CloneNotSupportedException {
		Personaje p1 = new Humano("Humano1");
		p1.setClase(new Guerrero());
		p1.agregarItem(new EspadaDeAragorn());
		p1.agregarItem(new ArmaduraDeAzorAhai());
		
		
		Personaje p2 = new Orco("Humano2");		
		p2.setClase(new Hechicero());
		p2.agregarItem(new PocionSabiduria());
		p2.agregarItem(new BujiasHescher());
		
		/*
		 * Se preparan los equipos a pelear
		 * */
		Equipo e1 = new Equipo(p1);
		Equipo e2 = new Equipo(p2);
		
		/*
		 * Compruebo que esten todos vivos
		 * */
		Assert.assertEquals(true, e1.quedaAlgunoVivo());
		Assert.assertEquals(true, e2.quedaAlgunoVivo());
		
		/*
		 * Se ataca por turnos. La idea es que cuando un equipo se queda sin peleadores, 
		 * se sumen los niveles de todos sus integrantes y se los multiplique por 10.
		 * Ese numero sera dividido por la cantidad de peleadores del equipo ganador y se le 
		 * sumara a cada uno a su experiencia. 
		 * 
		 * */
		
		/*
		 * Arranca el combate
		 * */
			
		Random r = new Random();
		int aux = r.nextInt(2);
		
		/*
		 * Defino quien ataca primero
		 * */
		while(e1.quedaAlgunoVivo() && e2.quedaAlgunoVivo() ){
			if(aux == 1){
				e1.atacar(e2);
				e2.atacar(e1);
			} else {
				e2.atacar(e1);
				e1.atacar(e2);
			}		
		}

		if(e1.quedaAlgunoVivo()){
			e1.repartirExperiencia(e2.calcularExperiencia());
			e1.repartirItem(e2);		
		}	else if(e2.quedaAlgunoVivo()){
			e2.repartirExperiencia(e1.calcularExperiencia());
			e2.repartirItem(e1);
		}
		
		/*
		 * Comrpuebo que el personaje del equipo ganador obtuvo un item y el perdedor perdio uno
		 * 
		 * */
		Assert.assertEquals(3, p1.getMochila().size());
		Assert.assertEquals(1, p2.getMochila().size());
		

	}
	
	/***
	 * 
	 * 3.	Dado un Personaje, cuando finaliza el combate contra un Personaje Genérico y resulta ganador, 
	 * entonces se le entrega el mejor ítem del Personaje Genérico.
	 * @throws FileNotFoundException 
	 * @throws CloneNotSupportedException 
	 * 
	 ***/
	
	@Test
	public void historia11Criterio03_Test() throws FileNotFoundException, CloneNotSupportedException {
		Personaje p1 = new Humano("Humano1");
		p1.setClase(new Guerrero());
		p1.setUbicacion(new Ubicacion(0,1));
		
		Personaje p2 = new Orco("Humano2");
		p2.setClase(new Hechicero());		
		p2.setUbicacion(new Ubicacion(0,4));
		
		Generico g = new Generico("Terminator");
		
		
		/*
		 * Armo la alianza: 
		 *  
		 * */
		p1.formarAlianzaCon(p2);
		
		/*
		 * Se prepara un equipo para pelear
		 * */
		Equipo e1 = new Equipo(p1);
		
		/*
		 * Compruebo que esten todos vivos
		 * */
		Assert.assertEquals(true, e1.quedaAlgunoVivo());
		Assert.assertEquals(true, g.estaVivo());
		
		/*
		 * Se ataca por turnos. La idea es que cuando un equipo se queda sin peleadores, 
		 * se sumen los niveles de todos sus integrantes y se los multiplique por 10.
		 * Ese numero sera dividido por la cantidad de peleadores del equipo ganador y se le 
		 * sumara a cada uno a su experiencia. 
		 * 
		 * */
		
		Random r = new Random();
		int aux = r.nextInt(2);
		
		/*
		 * Defino quien ataca primero
		 * */
		while(e1.quedaAlgunoVivo() && g.estaVivo() ){
			if(aux == 1){
				e1.atacar(g);
				g.atacar(e1.obtenerProximaVictima());	
			} else {
				g.atacar(e1.obtenerProximaVictima());	
				e1.atacar(g);
			}		
		}

		if(e1.quedaAlgunoVivo()){
			
			e1.repartirExperiencia(g.getNivel()*10);
			e1.repartirItem(g);
			
		}	else if(g.estaVivo()){
			
				e1.desequiparEquipo();
			}
		
	
		/*
		 * Este assert puede fallar ya que los items se dan en forma aleatoria
		 * 
		 * */
		Assert.assertEquals(1, p1.getMochila().size());
		

		
	}
}
