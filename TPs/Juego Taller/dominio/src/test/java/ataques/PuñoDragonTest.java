package ataques;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import dominio.*;
import razas.*;

public class PuñoDragonTest {
	/* @mauroat - 18/10/16
	 * Se la construcción y el daño un ataque causado.
	 * En esta caso es 8.
	 * */
	@Test
	public void atacarPuñoDragon() {
		Personaje h1 = new Humano("Goku");
		Personaje h2 = new Humano("Majin Boo");
		// Lescano agrega el ataque cuerpo a cuerpo y verifico que el daño que causa sea 9
		h1.agregarAtaque(new PuñoDragon());
		Assert.assertEquals(8, h1.getAtaque("Puño Dragón").aplicarAtaque());
		
		// Lescano ataca a Collins con furia sangrienta, debe disminuir su energia y disminuir la vida del atacado
		h1.atacar(h2,h1.getAtaque("Puño Dragón"));
		Assert.assertEquals(77, h1.getEnergia());
		Assert.assertEquals(82, h2.getVida());
	}
}
