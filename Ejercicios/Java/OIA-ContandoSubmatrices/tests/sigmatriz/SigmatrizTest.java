package sigmatriz;

import java.io.FileNotFoundException;

import org.junit.Test;

import junit.framework.Assert;

public class SigmatrizTest {
	//@Test
	public void constructorTest() throws FileNotFoundException{
		Sigmatriz s = new Sigmatriz("tests/Entrada/01.in");
		s.mostrarDataIn();
	}
	
	//@Test
	public void evaluadorTest() throws FileNotFoundException{
		Evaluador e = new Evaluador();
		Sigmatriz s = new Sigmatriz("tests/Entrada/01.in");
		Assert.assertEquals(9, e.evaluarMatriz(s));
	}
	
	//@Test
	public void casoPrueba1() throws FileNotFoundException{
		Sigmatriz s = new Sigmatriz("tests/Entrada/01.in");
		Assert.assertEquals(3, s.resolver());
		s.guardarResultado("tests/Salida esperada/01.out");
	}
	
	//@Test
	public void casoPrueba3() throws FileNotFoundException{
		Sigmatriz s = new Sigmatriz("tests/Entrada/03.in");
		Assert.assertEquals(0, s.resolver());
		s.guardarResultado("tests/Salida esperada/03.out");
	}
	
	
	//@Test
	public void casoPrueba4() throws FileNotFoundException{
		Sigmatriz s = new Sigmatriz("tests/Entrada/04.in");
		Assert.assertEquals(5, s.resolver());
		s.guardarResultado("tests/Salida esperada/04.out");
	}
	
	//@Test
	public void generarCasoFatiga05() throws FileNotFoundException{
		Evaluador e = new Evaluador();
		e.generarCasoPrueba("tests/Entrada/05.in", 160, 160, 2560000, 2560000, 1000);		
	}
	
	//@Test
	public void casoPrueba5() throws FileNotFoundException{
		Sigmatriz s = new Sigmatriz("tests/Entrada/05.in");
		Assert.assertEquals(0, s.resolver());
		s.guardarResultado("tests/Salida esperada/05.out");
	}
	
	//@Test
	public void generarCasoFatiga06() throws FileNotFoundException{
		Evaluador e = new Evaluador();
		e.generarCasoPrueba("tests/Entrada/06.in", 160, 160, 2560000, 2560000, 1000);		
	}
	
	@Test
	public void casoPrueba6() throws FileNotFoundException{
		Sigmatriz s = new Sigmatriz("tests/Entrada/06.in");
		Assert.assertEquals(1000, s.resolver());
		s.guardarResultado("tests/Salida esperada/06.out");
	}
	
}
