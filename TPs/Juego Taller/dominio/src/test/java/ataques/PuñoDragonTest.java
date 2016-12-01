package ataques;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import dominio.*;
import razas.*;

public class Pu�oDragonTest {
	/* @mauroat - 18/10/16
	 * Se la construcci�n y el da�o un ataque causado.
	 * En esta caso es 8.
	 * */
	@Test
	public void atacarPu�oDragon() {
		Personaje h1 = new Humano("Goku");
		Personaje h2 = new Humano("Majin Boo");
		// Lescano agrega el ataque cuerpo a cuerpo y verifico que el da�o que causa sea 9
		h1.agregarAtaque(new Pu�oDragon());
		Assert.assertEquals(8, h1.getAtaque("Pu�o Drag�n").aplicarAtaque());
		
		// Lescano ataca a Collins con furia sangrienta, debe disminuir su energia y disminuir la vida del atacado
		h1.atacar(h2,h1.getAtaque("Pu�o Drag�n"));
		Assert.assertEquals(77, h1.getEnergia());
		Assert.assertEquals(82, h2.getVida());
	}
}
