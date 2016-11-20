package castas;

import org.junit.Assert;
import org.junit.Test;

import dominio.*;
import habilidades.*;
import razas.*;

public class GuerreroTest {

	@Test
	public void guerreroConstructorTest(){
		Personaje p = new Orco("Willy");
		p.setClase(new Guerrero());
		Assert.assertEquals("Guerrero", p.getNombreClase());
	}
	
	@Test
	public void guerreroAgregarAtaques(){
		Personaje p = new Orco("Willy");
		p.setClase(new Guerrero());
		p.getClase().agregarHabilidad(new Destreza());
		p.getClase().getHabilidades();
	}
}
