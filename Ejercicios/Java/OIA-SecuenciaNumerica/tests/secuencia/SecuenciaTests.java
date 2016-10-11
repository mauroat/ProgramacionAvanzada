package secuencia;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class SecuenciaTests {
	@Test
	public void test1() throws IOException{
				
		Secuencia s = new Secuencia ("tests/Entrada/03.in");
		Calendar tIni = new GregorianCalendar();
		s.secuencia();
		Calendar tFin = new GregorianCalendar();
		//long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
		//System.out.println(diff);
		s.guardarResultado("tests/Salida esperada/03.out");
	}
	
	
}
