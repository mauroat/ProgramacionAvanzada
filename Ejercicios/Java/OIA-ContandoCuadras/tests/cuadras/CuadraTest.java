package cuadras;

import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.Assert;

public class CuadraTest {

	//@Test
	public void pruebaConstructor() throws FileNotFoundException{
		Cuadra c = new Cuadra("tests/Entrada/01.in");
		c.mostrarEntrada();
	}
	
	@Test
	public void pruebaResolverCaso1() throws FileNotFoundException{
		Cuadra c = new Cuadra("tests/Entrada/01.in");
		c.resolver();
		Assert.assertEquals(5, c.getResultado());
		c.escribirResultado("tests/Salida esperada/01.out");
	}
	
	@Test
	public void pruebaResolverCaso2() throws FileNotFoundException{
		Cuadra c = new Cuadra("tests/Entrada/02.in");
		c.resolver();
		Assert.assertEquals(7, c.getResultado());
		c.escribirResultado("tests/Salida esperada/02.out");
	}
	
	@Test
	public void pruebaResolverCaso3() throws FileNotFoundException{
		Cuadra c = new Cuadra("tests/Entrada/03.in");
		c.resolver();
		Assert.assertEquals(7, c.getResultado());
		c.escribirResultado("tests/Salida esperada/03.out");
	}
	
	//@Test
	//public void generarPruebaTest() throws FileNotFoundException{
	//	Generator g = new Generator();
	//	g.generarPrueba(1000, 500, 1000, "tests/Entrada/06.in");
	//}
	
	@Test
		public void pruebaResolverCaso6() throws FileNotFoundException{
			Cuadra c = new Cuadra("tests/Entrada/06.in");
			c.resolver();
			Assert.assertEquals(715, c.getResultado());
			c.escribirResultado("tests/Salida esperada/06.out");
		}
}
