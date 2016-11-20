package criterios_aceptacion;

import java.io.FileNotFoundException;
import java.util.Random;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import razas.*;


// Falta ultimo assert en criterio 1

/***
 * 
 * 10)	 Como Personaje, quiero aliarme con otro personaje.
 * Motivación: Para combatir junto a él y aumentar la experiencia que recolectamos en ese tiempo.
 * 
 ***/

public class Historia10Test {


	/***
	 * 
	 * 1.	Dado un Personaje que pertenece a una Alianza, cuando resulten ganadores de un combate contra otra 
	 * Alianza de Personajes Usuarios, entonces el sistema incrementará la experiencia de todos ellos.
	 * @throws FileNotFoundException 
	 * @throws CloneNotSupportedException 
	 * 
	 ***/
	
	@Test
	public void historia10Criterio01_Test() throws FileNotFoundException, CloneNotSupportedException {
		/*
		 * Creo los objetos personaje
		 * */
		
		Personaje p1 = new Humano("Humano1");
		p1.setClase(new Guerrero());
		p1.setUbicacion(new Ubicacion(0,1));
		
		Personaje p2 = new Orco("Humano2");
		p2.setClase(new Hechicero());
		p2.setUbicacion(new Ubicacion(0,4));
		
		Personaje p3 = new Elfo("Humano3");
		p3.setClase(new Chaman());
		p3.setUbicacion(new Ubicacion(1,4));
		
		Personaje p4 = new Elfo("Humano4");
		p4.setClase(new Hechicero());
		p4.setUbicacion(new Ubicacion(1,6));
		

		
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
		};
		
		/*
		 * Todo el equipo 4 esta muerto
		 * */
		Assert.assertEquals(false, p3.estaVivo());
		Assert.assertEquals(false, p4.estaVivo());
		
		/*
		 * p1 obtuvo 100 de experiencia por los ataques realizados 
		 * p2 obtuvo 110 de experiencia por los ataques realizados 
		 * El ultimo peleador vivo del equipo 2 era de nivel 2, por ende la experiencia 
		 * a repartir sera (2)*10 /2 = 10
		 * 
		 * */
		Assert.assertEquals(100+10, p1.getExperiencia());
		Assert.assertEquals(100+10, p2.getExperiencia());
		

	}
	

	
	/***
	 * 
	 * 2.	Dado un Personaje que pertenece a una Alianza, cuando resulten ganadores de un combate contra un 
	 * Personaje Usuario, entonces el sistema incrementará la experiencia de todos ellos. 
	 * @throws FileNotFoundException 
	 * @throws CloneNotSupportedException 
	 * 
	 ***/
	
	@Test
	public void historia10Criterio02_Test() throws FileNotFoundException, CloneNotSupportedException {
		Personaje p1 = new Humano("Humano1");
		p1.setClase(new Guerrero());
		p1.setUbicacion(new Ubicacion(0,1));
		
		Personaje p2 = new Orco("Humano2");
		p2.setClase(new Hechicero());
		p2.setUbicacion(new Ubicacion(0,4));
		
		Personaje p3 = new Elfo("Humano3");
		p3.setClase(new Chaman());
		p3.setUbicacion(new Ubicacion(1,4));
		
		

		
		/*
		 * Armo las alianzas: 
		 *  
		 * */
		p1.formarAlianzaCon(p2);
				
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
		};
		
		
		/*
		 * Todo el equipo 2 esta muerto
		 * */
		Assert.assertEquals(false, p3.estaVivo());
	
		
		/*
		 * p1 obtuvo 74 de experiencia por los ataques realizados 
		 * p2 obtuvo 64 de experiencia por los ataques realizados 
		 * p3 era de nivel 1, por ende la experiencia 
		 * a repartir sera (1)*10 /2 = 5
		 * 
		 * */
		Assert.assertEquals(40+5, p1.getExperiencia());
		Assert.assertEquals(50+5, p2.getExperiencia());
		
		
	}
	
	/***
	 * 
	 * 3. 	Dado un Personaje que pertenece a una Alianza, cuando resulten ganadores de un combate contra un 
	 * Personaje Genérico, entonces el sistema incrementará la experiencia de todos ellos.
	 * @throws FileNotFoundException 
	 * @throws CloneNotSupportedException 
	 * 
	 ***/
	
	@Test
	public void historia10Criterio03_Test() throws FileNotFoundException, CloneNotSupportedException {
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
		 * Personaje generico esta muerto
		 * */
		Assert.assertEquals(false, g.estaVivo());
	
		
		/*
		 * La experiencia no se puede comprobar ya que los genericos se crean con nivel random
		 * */
	
	
		
		
	}
	
}
