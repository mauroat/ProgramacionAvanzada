package ataques;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import dominio.Personaje;
import razas.Humano;

public class GanchoDeGoroTest {

	/* @mauroat - 18/10/16
	 * Se la construcción y el daño un ataque causado.
	 * En esta caso es 12.
	 * */
	@Test
	public void atacarGanchoDeGoro() {
		Personaje h1 = new Humano("Pablo Lescano");
		Personaje h2 = new Humano("Phil Collins");
		// Lescano agrega el ataque cuerpo a cuerpo y verifico que el daño que causa sea 9
		h1.agregarAtaque(new GanchoDeGoro());
		Assert.assertEquals(12, h1.getAtaque("Gancho de Goro").aplicarAtaque());
		
		// Lescano ataca a Collins con gancho de goro, debe disminuir su energia y disminuir la vida del atacado
		h1.atacar(h2,h1.getAtaque("Gancho de Goro"));
		Assert.assertEquals(100-10-12-5, h1.getEnergia());
		Assert.assertEquals(100-10-12+10-10, h2.getVida());
	}
}
