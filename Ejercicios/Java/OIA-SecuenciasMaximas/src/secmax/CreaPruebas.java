package secmax;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.concurrent.ThreadLocalRandom;

public class CreaPruebas {
	
	public CreaPruebas(String path, int cantidad) throws FileNotFoundException{
		PrintWriter salida = new PrintWriter(new File(path));
		salida.println(cantidad);
		for (int i = 0; i < cantidad;i++)
			salida.println(ThreadLocalRandom.current().nextInt(0, 1000000));
		salida.close();
	}
}
