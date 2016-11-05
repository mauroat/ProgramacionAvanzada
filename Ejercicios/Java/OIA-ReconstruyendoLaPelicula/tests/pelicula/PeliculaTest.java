package pelicula;

import java.io.FileNotFoundException;

import org.junit.Test;

public class PeliculaTest {

	@Test
	public void ConstructorTest() throws FileNotFoundException{
		Pelicula p = new Pelicula("tests/pelicula/Entrada/01-CasoEstandar.in");
		p.mostrarEntrada();
	}
	
}
