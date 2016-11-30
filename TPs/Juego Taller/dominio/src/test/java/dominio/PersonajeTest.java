package dominio;

import org.junit.Assert;
import org.junit.Test;

import ataques.*;
import castas.*;
import habilidades.Fuerza;
import habilidades.Velocidad;
import items.*;
import razas.*;


public class PersonajeTest {

	 @Test
	 public void constructorTest(){
		Personaje h = new Humano("Humanito 1");
		h.setClase(new Guerrero());
		
		Personaje e = new Elfo("Elfito 2");
		e.setClase(new Hechicero());
		
		Personaje o = new Orco("Orquito 3");	
		o.setClase(new Chaman());
		
		// Humano - Valores estandar de Ataque (10) , Defensa (10) y Magia (0)
		Assert.assertEquals("Humanito 1", h.getNombre());
		Assert.assertEquals("HUMANO",h.getRaza());
		Assert.assertEquals("GUERRERO", h.getClase().getNombre());
		Assert.assertEquals(100,h.getVida());
		Assert.assertEquals(0,h.getExperiencia());
		Assert.assertEquals(15,h.calcularPuntosDeAtaque());
		Assert.assertEquals(5,h.calcularPuntosDeDefensa());
		Assert.assertEquals(0,h.calcularPuntosDeMagia());
		Assert.assertEquals(0, h.getMochila().size());
			
		// Elfo - Valores estandar de Ataque (5) , Defensa (5) y Magia (10)		
		Assert.assertEquals("Elfito 2", e.getNombre());
		Assert.assertEquals("ELFO", e.getRaza());
		Assert.assertEquals("HECHICERO", e.getClase().getNombre());
		Assert.assertEquals(1, e.getNivel());
		Assert.assertEquals(6,e.calcularPuntosDeAtaque());
		Assert.assertEquals(4,e.calcularPuntosDeDefensa());
		Assert.assertEquals(10,e.calcularPuntosDeMagia());
		Assert.assertEquals(0, h.getMochila().size());
		
		// Orco - Valores estandar de Ataque (12) , Defensa (5) y Magia (3)
		Assert.assertEquals("Orquito 3", o.getNombre());
		Assert.assertEquals("ORCO", o.getRaza());
		Assert.assertEquals("CHAMAN", o.getClase().getNombre());
		Assert.assertEquals(5,o.calcularPuntosDeDefensa());
		Assert.assertEquals(3,o.calcularPuntosDeMagia());
		Assert.assertEquals(12, o.calcularPuntosDeAtaque());
		Assert.assertEquals(0, h.getMochila().size()); 
	 }
	 
	 @Test
	 public void personajeEquipadoYDesequipadoTest(){
		 Personaje h = new Humano("Humanito 1");
		 h.agregarItem(new EspadaDeAragorn());
		 h.agregarItem(new ArmaduraDeAzorAhai());
		 
		 /*
		  * Compruebo de tener 2 items en mi mochila
		  * */
		 Assert.assertEquals(2, h.getMochila().size());
		 
		 /*
		  * Quito el item y compruebo que sea el de mayor prioridad
		  * */
		 Item item = h.dejarItem();
		 Assert.assertEquals(1, h.getMochila().size());
		 Assert.assertEquals("Espada de Aragorn", item.getNombre());
	 }
	
	 @Test
	 public void clonarPersonajeSimpleConAtaquesYEquipamientoTest() throws CloneNotSupportedException{
		/*
		 * Armo un personaje con experiencia
		 * */
		Personaje p1 = new Humano("Lero");
		p1.setClase(new Hechicero());
		p1.setNivel(3);
		p1.setExperiencia(440);
		p1.setPuedeAgregarAtaque(1);
		p1.agregarAtaque(new CuerpoACuerpo());
		p1.agregarItem(new ArmaduraDeAzorAhai());
			
		/*
		 * Clono y comparo
		 * */
		Personaje p2 = new Humano(p1);
		
		/*			
		 * Atributos propios de personaje
		 * */
		Assert.assertEquals(p1.getVida(), p2.getVida());
		Assert.assertEquals(p1.getExperiencia(), p2.getExperiencia());
		/*
		 * Atributos básicos
		 * */
		Assert.assertEquals(p1.calcularPuntosDeAtaque(), p2.calcularPuntosDeAtaque());
		Assert.assertEquals(p1.calcularPuntosDeDefensa(), p2.calcularPuntosDeDefensa());
		Assert.assertEquals(p1.calcularPuntosDeMagia(), p2.calcularPuntosDeMagia());
		Assert.assertEquals(p1.getPotencia(), p2.getPotencia());
		Assert.assertEquals(p1.getVelocidad(), p2.getVelocidad());
		Assert.assertEquals(p1.getDestreza(), p2.getDestreza());
		
		/*
		 * Casta y Raza
		 * */
		Assert.assertEquals(p1.getRaza(), p2.getRaza());
		Assert.assertEquals(p1.getClase(), p2.getClase());
		
		/*
		 * Mapa de ataques
		 * */		
		Assert.assertEquals(p1.getAtaques(), p2.getAtaques());
		
		/*
		 * Items
		 * */
		Assert.assertEquals(1, p1.mochila.size());
		Assert.assertEquals(p1.mochila.size(), p2.mochila.size());
		
		/*
		 * Habilidades
		 * */
		Assert.assertEquals(p1.getClase().getHabilidades(), p2.getClase().getHabilidades());		
		
		/*
		 * Puntajes
		 * */
		Assert.assertEquals(p1.getPuedeAgregarAtaque(), p2.getPuedeAgregarAtaque());
		Assert.assertEquals(p1.getPuntos(), p2.getPuntos());
			
	}
	 
	 @Test
		public void clonarPersonajeConItemsConAtaquesConHabilidadesTest() throws CloneNotSupportedException{
			/*
			 * Armo un personaje con habilidades, ataques e items
			 * */
			Personaje p1 = new Humano("Lero");
			p1.setClase(new Hechicero());
			p1.setNivel(3);
			p1.setExperiencia(440);
			p1.setPuedeAgregarAtaque(1);
			p1.agregarAtaque(new CuerpoACuerpo());
			p1.setPuntos(2);
			p1.getClase().agregarHabilidad(new Velocidad());
			p1.getClase().getHabilidades().get(6).afectar(p1);
			p1.getClase().agregarHabilidad(new Fuerza());
			p1.getClase().getHabilidades().get(3).afectar(p1);
			p1.agregarItem(new ArmaduraDeAzorAhai());
			
			
			/*
			 * Clono y comparo
			 * */
			Personaje p2 = p1;
			
			
			/*
			 * Atributos propios de personaje
			 * */
			Assert.assertEquals(p1.getVida(), p2.getVida());
			Assert.assertEquals(p1.getExperiencia(), p2.getExperiencia());
			/*
			 * Atributos básicos
			 * */
			Assert.assertEquals(p1.calcularPuntosDeAtaque(), p2.calcularPuntosDeAtaque());
			Assert.assertEquals(p1.calcularPuntosDeDefensa(), p2.calcularPuntosDeDefensa());
			Assert.assertEquals(p1.calcularPuntosDeMagia(), p2.calcularPuntosDeMagia());
			Assert.assertEquals(p1.getPotencia(), p2.getPotencia());
			Assert.assertEquals(p1.getVelocidad(), p2.getVelocidad());
			Assert.assertEquals(p1.getDestreza(), p2.getDestreza());
			/*
			 * Casta y Raza
			 * */
			Assert.assertEquals(p1.getRaza(), p2.getRaza());
			Assert.assertEquals(p1.getClase(), p2.getClase());
			/*
			 * Mapa de ataques
			 * */		
			Assert.assertEquals(p1.getAtaques(), p2.getAtaques());
			/*
			 * Habilidades
			 * */
			Assert.assertEquals(p1.getClase().getHabilidades(), p2.getClase().getHabilidades());		
			/*
			 * Puntajes
			 * */
			Assert.assertEquals(p1.getPuedeAgregarAtaque(), p2.getPuedeAgregarAtaque());
			Assert.assertEquals(p1.getPuntos(), p2.getPuntos());
			
			//p2.setNivel(5);
		} 
	 
	 
}
