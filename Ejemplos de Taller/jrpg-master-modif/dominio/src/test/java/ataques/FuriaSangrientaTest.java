package ataques;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import dominio.Personaje;
import razas.Humano;

public class FuriaSangrientaTest {

	/* @mauroat - 18/10/16
	 * Se la construcción y el daño un ataque causado.
	 * En esta caso es 9.
	 * */
	@Test
	public void atacarConFuriaSangrienta() {
		Personaje h1 = new Humano("Pablo Lescano");
		Personaje h2 = new Humano("Phil Collins");
		// Lescano agrega el ataque cuerpo a cuerpo y verifico que el daño que causa sea 9
		h1.agregarAtaque(new FuriaSangrienta());
		Assert.assertEquals(9, h1.getAtaque("Furia sangrienta").aplicarAtaque());
		
		// Lescano ataca a Collins con furia sangrienta, debe disminuir su energia y disminuir la vida del atacado
		h1.atacar(h2,h1.getAtaque("Furia sangrienta"));
		Assert.assertEquals(100-10-9, h1.getEnergia());
		Assert.assertEquals(100-10-9+10, h2.getVida());
	}
	
}
