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
		Assert.assertEquals(15, h.calcularPuntosDeAtaque());
		Assert.assertEquals(5, h.calcularPuntosDeDefensa());
		Assert.assertEquals(0, h.calcularPuntosDeMagia());
		Assert.assertEquals("HUMANO", h.getRaza());
		Assert.assertEquals("GUERRERO", h.getClase().getNombre());		
	}
	
	@Test
	public void crearOrcoConOtrosParametros(){
		Personaje e = new Humano(1,"Agente Smith",100,100,0,1,0,0,0,0,0, new Guerrero(), null);
			
		Assert.assertEquals("Agente Smith", e.getNombre());
		Assert.assertEquals(100, e.getEnergia());
		Assert.assertEquals(100, e.getVida());
		Assert.assertEquals(1, e.getNivel());
		Assert.assertEquals(0, e.getExperiencia());
		Assert.assertEquals(15, e.calcularPuntosDeAtaque());
		Assert.assertEquals(5, e.calcularPuntosDeDefensa());
		Assert.assertEquals(0, e.calcularPuntosDeMagia());
		Assert.assertEquals(0, e.getDestreza());
		Assert.assertEquals(0, e.getPuedeAgregarAtaque());
		Assert.assertEquals(0, e.getVelocidad());
		
		Assert.assertEquals("HUMANO", e.getRaza());
		Assert.assertEquals("GUERRERO", e.getClase().getNombre());
	}
}
