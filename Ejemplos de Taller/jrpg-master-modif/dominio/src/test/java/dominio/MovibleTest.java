package dominio;

import org.junit.Assert;
import org.junit.Test;

import castas.*;
import razas.*;
import dominio.*;

public class MovibleTest {

	@Test
	public void posicionamientoInicialPersonajeTest(){
		Personaje p1 = new Humano("Gate");
		p1.setClase(new Hechicero());	
		p1.setUbicacion(new Ubicacion(1.0,2.0));
		
		Assert.assertEquals(new Ubicacion(1.0,2.0), p1.getUbicacion());
	}
	
	@Test
	public void posicionamientoEInteraccionTest(){
		/*
		 * Creación de objetos
		 * */
		
		Personaje p1 = new Humano("Gate");
		p1.setClase(new Hechicero());
		p1.setUbicacion(new Ubicacion(3.0,4.0));
		
		Personaje p2 = new Orco("Teto");
		p2.setClase(new Guerrero());
		p2.setUbicacion(new Ubicacion(2.0,3.0));
		
		Personaje p3 = new Elfo("Teto");
		p3.setClase(new Chaman());
		p3.setUbicacion(new Ubicacion(13.0,24.0));
		
		/*
		 * Creo variables de posicionamiento
		 * */
		//Circulo posP1 = new Circulo(p1.getPosicion(),1);
		//Circulo posP2 = new Circulo(p2.getPosicion(),1);
		
		/*
		 * @mauroat - 26/10/16
		 * ESTE METODO DEBERÁ MODIFICARSE EN EL FUTURO
		 * Si puedo interactuar es porque la posición de p1, con un radio de 2, contiene a la posicion de p2.
		 * En cambio p1 no podrá interacturar con p3 ya que su distancia es mayor.
		 * 
		 * */
		Assert.assertEquals(true, p1.interactuarConOtroPersonaje(p2));
		
		Assert.assertEquals(false, p1.interactuarConOtroPersonaje(p3));

		
		
	}
}
