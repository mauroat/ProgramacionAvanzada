package castas;

import org.junit.Assert;
import org.junit.Test;

import dominio.Personaje;
import habilidades.Destreza;
import razas.Orco;

public class HechiceroTest {
	
	@Test
	public void guerreroConstructorTest(){
		Personaje p = new Orco("Willy");
		p.setClase(new Hechicero());
		Assert.assertEquals("HECHICERO", p.getNombreClase());
	}
	
	@Test
	public void guerreroAgregarAtaques(){
		Personaje p = new Orco("Willy");
		p.setClase(new Hechicero());
		p.getClase().agregarHabilidad(new Destreza());
		p.getClase().getHabilidades();
	}
}
