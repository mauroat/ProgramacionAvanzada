package composite;

import org.junit.Test;

import junit.framework.Assert;

public class FiguraTest {

	@Test
	public void pruebaFigura(){
		Figura f1 = new Rectangulo(2,4);	// Una clase más general puede declararse como una clase más específica.
		Figura f2 = new Circulo(5);
		Figura f3 = new Triangulo(3,2);
		
		Assert.assertEquals(8, f1.calcularArea(),1);
		Assert.assertEquals(Math.PI*Math.pow(5, 2),f2.calcularArea());
		Assert.assertEquals((3*2)/2, f3.calcularArea(),1);
	}
}
