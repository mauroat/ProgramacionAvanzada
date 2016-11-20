package ataques;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import dominio.*;
import razas.*;

public class CuerpoACuerpoTest {

	/* @mauroat - 18/10/16
	 * Se la construcción y el daño un ataque causado.
	 * En esta caso es 5.
	 * */
	@Test
	public void atacarConCuerpoACuerpo() {
		Personaje h1 = new Humano("Pablo Lescano");
		Personaje h2 = new Humano("Phil Collins");
		
		h1.agregarAtaque(new CuerpoACuerpo());
		Assert.assertEquals(5, h1.getAtaque("Cuerpo a cuerpo").aplicarAtaque());
		
		// Lescano ataca a Collins con cuerpo a cuerpo, debe disminuir su energia y disminuir la vida del atacado
		h1.atacar(h2,h1.getAtaque("Cuerpo a cuerpo"));
		Assert.assertEquals(100-10-5, h1.getEnergia());
		Assert.assertEquals(100-10-5+10, h2.getVida());
	}
	
}
