package razas;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import dominio.*;

public class OrcoTest {

	/* @mauroat - 18-10-16 
	 * */
	@Test
	public void constructorOrco(){
		Personaje o = new Orco("Marciano Aguirre");
		o.setClase(new Guerrero());
		Assert.assertEquals("Marciano Aguirre", o.getNombre());
		Assert.assertEquals(100, o.getEnergia());
		Assert.assertEquals(100, o.getVida());
		Assert.assertEquals(1, o.getNivel());
		Assert.assertEquals(0, o.getExperiencia());
		Assert.assertEquals(12, o.calcularPuntosDeAtaque());
		Assert.assertEquals(5, o.calcularPuntosDeDefensa());
		Assert.assertEquals(3, o.calcularPuntosDeMagia());
		Assert.assertEquals("ORCO", o.getRaza());
		Assert.assertEquals("GUERRERO", o.getClase().getNombre());	
	}

	@Test
	public void crearOrcoConOtrosParametros(){
		Personaje e = new Orco(1,"Agente Smith",100,100,0,1,0,0,0,0,0, new Guerrero(), null);
			
		Assert.assertEquals("Agente Smith", e.getNombre());
		Assert.assertEquals(100, e.getEnergia());
		Assert.assertEquals(100, e.getVida());
		Assert.assertEquals(1, e.getNivel());
		Assert.assertEquals(0, e.getExperiencia());
		Assert.assertEquals(12, e.calcularPuntosDeAtaque());
		Assert.assertEquals(5, e.calcularPuntosDeDefensa());
		Assert.assertEquals(3, e.calcularPuntosDeMagia());
		Assert.assertEquals(0, e.getDestreza());
		Assert.assertEquals(0, e.getPuedeAgregarAtaque());
		Assert.assertEquals(0, e.getVelocidad());
		
		Assert.assertEquals("ORCO", e.getRaza());
		Assert.assertEquals("GUERRERO", e.getClase().getNombre());
	}
	
	
}
