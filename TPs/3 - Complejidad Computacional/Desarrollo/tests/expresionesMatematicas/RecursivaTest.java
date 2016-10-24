package expresionesMatematicas;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.junit.Test;

public class RecursivaTest {
	@Test
	public void recursivaTestComplete() throws Exception{
		// Genero el archivo de salida
		int n = 10;
		String miPath = "tests/Salida Esperada/";
		double diff;
		
		double res;
		Calendar tIni, tFin;
		// Creo el objeto polinomio 
		Polinomio p5 = new Polinomio("tests/Entrada/Polinomio_5.in");
		Polinomio p10 = new Polinomio("tests/Entrada/Polinomio_10.in");
		Polinomio p50 = new Polinomio("tests/Entrada/Polinomio_50.in");
		Polinomio p100 = new Polinomio("tests/Entrada/Polinomio_100.in");
		Polinomio p500 = new Polinomio("tests/Entrada/Polinomio_500.in");
		Polinomio p1000 = new Polinomio("tests/Entrada/Polinomio_1000.in");
		Polinomio p2000 = new Polinomio("tests/Entrada/Polinomio_2000.in");
		Polinomio p3000 = new Polinomio("tests/Entrada/Polinomio_3000.in");
		Polinomio p4000 = new Polinomio("tests/Entrada/Polinomio_4000.in");
		Polinomio p5000 = new Polinomio("tests/Entrada/Polinomio_5000.in");
		Polinomio p6000 = new Polinomio("tests/Entrada/Polinomio_6000.in");
		Polinomio p7000 = new Polinomio("tests/Entrada/Polinomio_7000.in");
		Polinomio p8000 = new Polinomio("tests/Entrada/Polinomio_8000.in");
		Polinomio p9000 = new Polinomio("tests/Entrada/Polinomio_9000.in");
		Polinomio p10000 = new Polinomio("tests/Entrada/Polinomio_10000.in");
		

		PrintWriter salida = new PrintWriter(new FileWriter(miPath+"Recursiva.out"));
		
		// METODO 2 - Recursiva
		
	
		
		System.out.println("Programacion Recursiva:");
		salida.println("Programacion Recursiva:");	
		// Polinomio 5
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p5.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("5");
		
		// Polinomio 10
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p10.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("10");

		
		// Polinomio 50
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p50.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("50");	
		
		// Polinomio 100
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p100.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("100");		
		
		
		// Polinomio 500
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p500.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("500");	
		
		// Polinomio 1000
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p1000.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("1000");	
		
		// Polinomio 2000
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p2000.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("2000");	

		// Polinomio 3000
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p3000.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("3000");	
		
		// Polinomio 4000
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p4000.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("4000");	
		
		// Polinomio 5000
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p5000.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("5000");	
		
		// Polinomio 6000
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p6000.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("6000");
		
		// Polinomio 7000
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p7000.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("7000");
		
		// Polinomio 8000
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p8000.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("8000");

		// Polinomio 9000
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p9000.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("9000");

		// Polinomio 10000
		// Inicio la toma de datos
		tIni = new GregorianCalendar();		
		res = p10000.evaluarRecursiva(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		salida.println("10000");
		
		salida.close();
		
		}

}
