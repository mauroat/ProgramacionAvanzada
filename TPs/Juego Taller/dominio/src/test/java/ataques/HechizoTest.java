package ataques;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import dominio.*;
import razas.*;


public class HechizoTest {
	/* @mauroat - 18/10/16
	 * Se la construcción y el daño un ataque causado.
	 * En esta caso es 6.
	 * */
	@Test
	public void atacarHechizo() {
		Personaje e1 = new Elfo("Enrique Bunbury");
		Personaje e2 = new Elfo("Mago goma");
		// Lescano agrega el ataque cuerpo a cuerpo y verifico que el daño que causa sea 9
		e1.agregarAtaque(new Hechizo());
		Assert.assertEquals(6, e1.getAtaque("Hechizo").aplicarAtaque());
		
		// Lescano ataca a Collins con gancho de goro, debe disminuir su energia y disminuir la vida del atacado
		e1.atacar(e2,e1.getAtaque("Hechizo"));
		Assert.assertEquals(100-5-6-1, e1.getEnergia());
		Assert.assertEquals(100-5-6+5-2, e2.getVida());
	}
}
