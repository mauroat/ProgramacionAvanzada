package dominio;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;
import razas.*;
import items.*;


public class PeleadorTest {

	
	/*	@mauroat - 18-10-16:
	 *  Este test verifica que cuando un personaje ataca agote su energía, disminuya la vida del atacado y 
	 *  en caso de matarlo sume experiencia.
	 *  El personaje podrá atacar siempre cuando su energía sea >= a sus puntos de ataque y el atacado esté vivo. 
	 * */
	
	@Test
	public void peleadorTest() {
		Personaje orco = new Orco("Orcazo");
		Personaje elfo = new Elfo("Elfazo");
		
		
		/* Con el Orco ataca 15 veces al humano. Debería dejar de atacar cuando el Orco se quede sin energia */		
		for (int i = 0; i<15;i++)
			orco.atacar(elfo);
		
		
		Assert.assertEquals(44, elfo.getVida());		

		
		/* Ahora energizo al Orco y termino de rematar al humano. Deberia aumentar la experiencia del Orco*/
		orco.serEnergizado();
		for (int i = 0; i<6;i++){
			orco.atacar(elfo);
		}
			
		orco.serEnergizado();
		
		Assert.assertEquals(106, orco.getExperiencia());
		Assert.assertEquals(-4, elfo.getVida());

	}
	
	/*	@mauroat - 18-10-16:
	 *  Este test verifica que cuando un personaje mate varios enemigos aumente experiencia y suba de nivel. 
	 *  La experiencia necesaria para pasar al nivel 2 es 220.
	 * */
	
	@Test
	public void peleadorTestSubirNivel() {
		Personaje orco = new Orco("Orcazo");
		Personaje humano1 = new Humano("Humanazo");
		Personaje elfo1 = new Elfo("Elfazo");
		Personaje elfo2 = new Elfo("Elfazito");
			
		for (int i = 0; i<9;i++)			
			orco.atacar(humano1);
		orco.serEnergizado();
		for (int i = 0; i<6;i++)			
			orco.atacar(elfo1);
		orco.serEnergizado();
		for (int i = 0; i<6;i++)			
			orco.atacar(elfo2);
		orco.serEnergizado();
		for (int i = 0; i<6;i++)			
			orco.atacar(elfo2);
		orco.serEnergizado();
		for (int i = 0; i<6;i++)			
			orco.atacar(elfo2);
		
		
		Assert.assertEquals(210, orco.getExperiencia());
		Assert.assertEquals(2, orco.getNivel());
		Assert.assertEquals(2, orco.getPuntos());
	}
	
	/*	@mauroat - 18-10-16:
	 *  Este test verifica que un personaje aumente la lista de items a medida que se va equipando 
	 */
	
	@Test
	public void peleadorDejarMejorItem() {
	
		/* @mauroat - 18/10/16
		 * ERROR: Los items se equipan, sin embargo la lista siempre es reemplazada por el último item y no son agregados.
		 * @mauroat - 19/10/16
		 * Solucionado.
		 * */
				
		// Sin items
		Personaje humano = new Humano("Indio Solari");
		Personaje item;

		Assert.assertEquals(0, humano.getMochila().size());
		
		// Agrego 3 items
		humano.agregarItem(new BastonDeSaruman());
		humano.agregarItem(new BujiasHescher());
		humano.agregarItem(new ArmaduraDeAzorAhai());
		
		
		Assert.assertEquals(3, humano.getMochila().size());	
		
		// Obtengo mejor item del peleador: Bujia Hescher
		Assert.assertEquals("Bujias Hescher", humano.dejarItem().getNombre());
				
	}

	@Test
	public void peleadorDejarMejorItemDistintasPosiciones() {
				
		// El mejor al comienzo
		Personaje humano = new Humano("Indio Solari");
		humano.setClase(new Guerrero());
		
		humano.agregarItem(new BujiasHescher());
		humano.agregarItem(new BastonDeSaruman());
		humano.agregarItem(new BujiasHescher());
		humano.agregarItem(new ArmaduraDeAzorAhai());
		
		
		Assert.assertEquals("Bujias Hescher", humano.dejarItem().getNombre());
		
		// El mejor al comienzo
		Personaje orco = new Orco("Indio Solari");
		
		orco.agregarItem(new BastonDeSaruman());
		orco.agregarItem(new BujiasHescher());
		orco.agregarItem(new ArmaduraDeAzorAhai());

		Assert.assertEquals("Bujias Hescher", orco.dejarItem().getNombre());
		Assert.assertEquals(3, humano.getMochila().size());
		
		// El mejor al final
		Personaje elfo = new Elfo("Indio Solari");
				
		elfo.agregarItem(new BastonDeSaruman());
		elfo.agregarItem(new BujiasHescher());
		elfo.agregarItem(new ArmaduraDeAzorAhai());

		Assert.assertEquals("Bujias Hescher", elfo.dejarItem().getNombre());
		Assert.assertEquals(2, elfo.getMochila().size());
	}
	
	
	@Test
	public void peleadorDejarMejorItemSiEstaRepetido() {
			
		// Sin items
		Personaje humano = new Humano("Indio Solari");
		humano.setClase(new Guerrero());
		
		
		Assert.assertEquals(0, humano.getMochila().size());
			
		// 4 items - 2 repetidos
		humano.agregarItem(new BujiasHescher());
		humano.agregarItem(new BastonDeSaruman());
		humano.agregarItem(new BujiasHescher());
		humano.agregarItem(new ArmaduraDeAzorAhai());
		
		Assert.assertEquals(4, humano.getMochila().size());
		
		
		// Obtengo mejor item del peleador: Bujia Hescher
		Assert.assertEquals("Bujias Hescher", humano.dejarItem().getNombre());
			
	}
	
	
	
	@Test
	public void peleadorSeDesequipaConMejorItemYOtroSeEquipa() {
		
		
		Personaje humano = new Humano("Indio Solari");
		humano.setClase(new Guerrero());
		
		Personaje orco = new Orco ("Pipita");
		orco.setClase(new Chaman());
		
		
		/*
		 * El item de mayor prioridad es BujiaHescher
		 * */
		
		humano.agregarItem(new BujiasHescher());
		humano.agregarItem(new BastonDeSaruman());
		humano.agregarItem(new ArmaduraDeAzorAhai());
		
		orco.agregarItem(humano.dejarItem());
		
		Assert.assertEquals(2, humano.getMochila().size());
		
		Assert.assertEquals(1, orco.getMochila().size());

	}
	/*
	 * La idea aqui es que cuando un personaje tenga mas defensa que ataque su oponente, su vida no 
	 * aumente mas alla de 100	 * 
	 * */
	@Test
	public void evitarInanicionEnVida(){
		Personaje p1 = new Humano("Pipito");
		p1.setClase(new Chaman());
		
		
		Personaje p2 = new Elfo("Pipito");
		p2.setClase(new Chaman());
		p2.agregarItem(new PocionBruta());
		p2.agregarItem(new PocionBruta());
		p2.agregarItem(new PocionBruta());
		p2.agregarItem(new PocionBruta());
		
		
		/*
		 * Humano tiene +5 de defensa
		 * Elfo tiene +6 de ataque
		 * Le agrego 4 pociones brutas que disminuyen en 2 el ataque, entonces su ataque sera -8+6 = -2
		 * */
		
		p2.atacar(p1);
		Assert.assertEquals(100, p1.getVida());
	}
	

}
