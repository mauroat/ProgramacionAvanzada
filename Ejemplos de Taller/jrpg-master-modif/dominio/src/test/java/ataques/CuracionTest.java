package ataques;

import org.junit.Assert;
import org.junit.Test;

import dominio.*;
import razas.*;

public class CuracionTest {
	
	@Test
	public void atacarConCuracion() {
		Personaje h1 = new Humano("Pablo Lescano");
		Personaje h2 = new Humano("Phil Collins");
		
		h1.agregarAtaque(new Curacion());
		Assert.assertEquals(-20, h1.getAtaque("Curacion").aplicarAtaque());
		
		// Lescano ataca a Collins con cuerpo a cuerpo, debe disminuir su energia y 
		// disminuir la vida del atacado
	
		h1.atacar(h2,h1.getAtaque("Curacion"));
		Assert.assertEquals(100-10, h1.getEnergia());
		Assert.assertEquals(100+20, h2.getVida());
		
	}
	
}
