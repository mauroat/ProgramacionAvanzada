package transmitiendo;

import java.io.IOException;

import org.junit.Test;

public class TransmitiendoTests {

	@Test
	public void constructorTest() throws IOException{
		String path = "01.in";
		Transmitiendo t = new Transmitiendo(path);
		//t.verVecinos();
		t.resolver();
		t.grabarResultado("01.out");
	}
	
}
