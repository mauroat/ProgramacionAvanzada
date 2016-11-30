package razas;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;


public class ElfoTest {

	/* @mauroat - 18-10-16 
	 * */
	@Test
	public void constructorElfo(){
		Personaje e = new Elfo("Agente Smith");
		e.setClase(new Guerrero());
	
		Assert.assertEquals("Agente Smith", e.getNombre());
		Assert.assertEquals(100, e.getEnergia());
		Assert.assertEquals(100, e.getVida());
		Assert.assertEquals(1, e.getNivel());
		Assert.assertEquals(0, e.getExperiencia());
		Assert.assertEquals(5+1, e.calcularPuntosDeAtaque());
		Assert.assertEquals(5-1, e.calcularPuntosDeDefensa());
		Assert.assertEquals(10, e.calcularPuntosDeMagia());
		Assert.assertEquals("ELFO", e.getRaza());
		Assert.assertEquals("GUERRERO", e.getClase().getNombre());	
	}
	
	@Test
	public void crearElfoConOtrosParametros(){
		Personaje e = new Elfo(1,"Agente Smith",100,100,0,1,0,0,0,0,0, new Guerrero(), null);
			
		Assert.assertEquals("Agente Smith", e.getNombre());
		Assert.assertEquals(100, e.getEnergia());
		Assert.assertEquals(100, e.getVida());
		Assert.assertEquals(1, e.getNivel());
		Assert.assertEquals(0, e.getExperiencia());
		Assert.assertEquals(5+1, e.calcularPuntosDeAtaque());
		Assert.assertEquals(5-1, e.calcularPuntosDeDefensa());
		Assert.assertEquals(10, e.calcularPuntosDeMagia());
		Assert.assertEquals(0, e.getDestreza());
		Assert.assertEquals(0, e.getPuedeAgregarAtaque());
		Assert.assertEquals(0, e.getVelocidad());
		
		Assert.assertEquals("ELFO", e.getRaza());
		Assert.assertEquals("GUERRERO", e.getClase().getNombre());
	}
	
}
