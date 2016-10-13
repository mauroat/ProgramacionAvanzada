package cuadras;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;

public class Generator {
	public void generarPrueba(int c, int d, int n, String path) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter(new File(path));
		Random r = new Random();
		salida.println(c+" "+d+" "+n);
		for(int i=0; i < c ;i++){
			int valorH = Math.abs(r.nextInt(2000));
			int valorB =  Math.abs(r.nextInt(500));
			int valorA =  Math.abs(r.nextInt(300));
			salida.print(valorA+" "+valorB+" "+valorH);
			salida.println();
		}
		
		salida.close();
	}
}
