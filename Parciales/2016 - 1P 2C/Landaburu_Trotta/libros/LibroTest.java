package libros;

import java.io.FileNotFoundException;




import org.junit.Assert;
import org.junit.Test;

public class LibroTest {

	//@Test
	public void constructorTest() throws FileNotFoundException{
		Libro l = new Libro ("Preparacion de la prueba/Lotes de prueba/Entrada/01.in");
		l.mostrarEntrada();	
	}
	
	
	@Test
	public void pruebaCaso01() throws FileNotFoundException{
		Libro l = new Libro ("Preparacion de la prueba/Lotes de prueba/Entrada/01 - CasoEjemplo.in");
		l.resolver();
		Assert.assertEquals(1, l.getResultado());
		l.guardarResultado("Ejecucion de la prueba/Salida esperada/01 - CasoEjemplo.out");
	}
	
	@Test
	public void pruebaCaso02() throws FileNotFoundException{
		Libro l = new Libro ("Preparacion de la prueba/Lotes de prueba/Entrada/02 - MenorCantidadDeElementos.in");
		l.resolver();
		Assert.assertEquals(1, l.getResultado());
		l.guardarResultado("Ejecucion de la prueba/Salida esperada/02 - MenorCantidadDeElementos.out");
	}
	
	@Test
	public void pruebaCaso03() throws FileNotFoundException{
		Libro l = new Libro ("Preparacion de la prueba/Lotes de prueba/Entrada/03 - TodosElementosIguales.in");
		l.resolver();
		Assert.assertEquals(4, l.getResultado());
		l.guardarResultado("Ejecucion de la prueba/Salida esperada/03 - TodosElementosIguales.out");
	}
	
	@Test
	public void pruebaCaso04() throws FileNotFoundException{
		Libro l = new Libro ("Preparacion de la prueba/Lotes de prueba/Entrada/04 - FatigaEstandar.in");
		l.resolver();
		Assert.assertEquals(828, l.getResultado());
		l.guardarResultado("Ejecucion de la prueba/Salida esperada/04 - FatigaEstandar.out");
	}
	
	@Test
	public void pruebaCaso05() throws FileNotFoundException{
		Libro l = new Libro ("Preparacion de la prueba/Lotes de prueba/Entrada/05 - FatigaCapicua.in");
		l.resolver();
		Assert.assertEquals(1000, l.getResultado());
		l.guardarResultado("Ejecucion de la prueba/Salida esperada/05 - FatigaCapicua.out");
	}
	
	@Test
	public void pruebaCaso06() throws FileNotFoundException{
		Libro l = new Libro ("Preparacion de la prueba/Lotes de prueba/Entrada/06 - Capicua.in");
		l.resolver();
		Assert.assertEquals(3, l.getResultado());
		l.guardarResultado("Ejecucion de la prueba/Salida esperada//06 - Capicua.out");
	}
	
}
