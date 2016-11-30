package castas;

import org.junit.Assert;
import org.junit.Test;

import dominio.Personaje;
import habilidades.Destreza;
import razas.Orco;

public class ChamanTest {
	
	@Test
	public void guerreroConstructorTest(){
		Personaje p = new Orco("Willy");
		p.setClase(new Chaman());
		Assert.assertEquals("CHAMAN", p.getNombreClase());
	}
	
	@Test
	public void guerreroAgregarAtaques(){
		Personaje p = new Orco("Willy");
		p.setClase(new Chaman());
		p.getClase().agregarHabilidad(new Destreza());
		p.getClase().getHabilidades();
	}
	
	@Test
	public void guerreroAgregarAtaques2(){
		Personaje p = new Orco("Willy");
		p.setClase(new Chaman());
		p.getClase().agregarHabilidad(new Destreza());
		p.getClase().getIdCasta();
	}
	
}
