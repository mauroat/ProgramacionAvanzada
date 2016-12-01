package ataques;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

import dominio.*;
import razas.*;

public class RegeneracionTest {
	/* @mauroat - 18/10/16
	 * Se la construcci�n y el da�o un ataque causado.
	 * En esta caso es -7.
	 * */
	@Test
	public void atacarRegeneracion() {
		Personaje e1 = new Elfo("Ditto");
		Personaje e2 = new Elfo("Pikachu");
		// Ditto agrega el ataque Regeneracion y verifico que el da�o que causa sea 30
		e1.agregarAtaque(new Regeneracion());
		Assert.assertEquals(30, e1.getAtaque("Regeneraci�n").aplicarAtaque());
		
		// Ditto usa el ataque regeneracion. Pikachu no se ve afectado.
		e1.atacar(e2,e1.getAtaque("Regeneraci�n"));
		Assert.assertEquals(100+30, e1.getEnergia());
		Assert.assertEquals(100, e2.getVida());
	}
}
