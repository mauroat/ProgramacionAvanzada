package razas;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.Personaje;

public class HumanoTest {

	/* @mauroat - 18-10-16 
	 * */
	@Test
	public void constructorHumano(){
		Personaje h = new Humano("Chespirito");
		h.setClase(new Guerrero());
		Assert.assertEquals("Chespirito", h.getNombre());
		Assert.assertEquals(100, h.getEnergia());
		Assert.assertEquals(100, h.getVida());
		Assert.assertEquals(1, h.getNivel());
		Assert.assertEquals(0, h.getExperiencia());
		Assert.assertEquals(10, h.calcularPuntosDeAtaque());
		Assert.assertEquals(10, h.calcularPuntosDeDefensa());
		Assert.assertEquals(0, h.calcularPuntosDeMagia());
		Assert.assertEquals("Humano", h.getRaza());
		Assert.assertEquals("Guerrero", h.getClase().getNombre());		
	}
}
