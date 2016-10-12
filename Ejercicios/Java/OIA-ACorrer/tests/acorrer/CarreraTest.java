package acorrer;

import java.io.FileNotFoundException;

import org.junit.Test;

public class CarreraTest {

	@Test
	public void constructorTest() throws FileNotFoundException{
		Carrera cc = new Carrera("01.in");
		cc.mostrarEntrada();
		cc.asignarPodio();
		cc.escribirResultado("01.out");
		
	}
	
}
