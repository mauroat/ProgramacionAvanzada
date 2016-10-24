package expresionesMatematicas;

import org.junit.Test;

import junit.framework.Assert;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class MSucesivasTest {

	@Test
	public void mSucesivasTestComplete() throws Exception{
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
		

		PrintWriter salida = new PrintWriter(new FileWriter(miPath+"MSucesivas.out"));
		
		// METODO 1 - MULTIPLICACIONES SUCESIVAS
		
	
		
		System.out.println("Programacion Multip. Sucesivas:");
		salida.println("Programacion Multip. Sucesivas:");	
		// Polinomio 5
		// Inicio la toma de datos
		salida.println("Polinomio grado 5");
		tIni = new GregorianCalendar();		
		res = p5.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		
		
		// Polinomio 10
		// Inicio la toma de datos
		salida.println("Polinomio grado 10");
		tIni = new GregorianCalendar();		
		res = p10.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");

		
		// Polinomio 50
		// Inicio la toma de datos
		salida.println("Polinomio grado 50");	
		tIni = new GregorianCalendar();		
		res = p50.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");

		
		// Polinomio 100
		// Inicio la toma de datos
		salida.println("Polinomio 100");	
		tIni = new GregorianCalendar();		
		res = p100.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");	
		
		
		// Polinomio 500
		// Inicio la toma de datos
		salida.println("Polinomio 500");
		tIni = new GregorianCalendar();		
		res = p500.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		
		// Polinomio 1000
		// Inicio la toma de datos
		salida.println("Polinomio 1000");
		tIni = new GregorianCalendar();		
		res = p1000.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");	
		
		// Polinomio 2000
		// Inicio la toma de datos
		salida.println("Polinomio 2000");
		tIni = new GregorianCalendar();		
		res = p2000.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
	

		// Polinomio 3000
		// Inicio la toma de datos
		salida.println("Polinomio 3000");
		tIni = new GregorianCalendar();		
		res = p3000.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		
		
		// Polinomio 4000
		// Inicio la toma de datos
		salida.println("Polinomio 4000");
		tIni = new GregorianCalendar();		
		res = p4000.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		
		
		// Polinomio 5000
		// Inicio la toma de datos
		salida.println("Polinomio 5000");
		tIni = new GregorianCalendar();		
		res = p5000.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		
		
		// Polinomio 6000
		// Inicio la toma de datos
		salida.println("Polinomio 6000");
		tIni = new GregorianCalendar();		
		res = p6000.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		
		
		// Polinomio 7000
		// Inicio la toma de datos
		salida.println("Polinomio 7000");
		tIni = new GregorianCalendar();		
		res = p7000.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		
		
		
		// Polinomio 8000
		// Inicio la toma de datos
		salida.println("Polinomio 8000");
		tIni = new GregorianCalendar();		
		res = p8000.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
	
		
		// Polinomio 9000
		// Inicio la toma de datos
		salida.println("Polinomio 9000");
		tIni = new GregorianCalendar();		
		res = p9000.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		
		
		
		// Polinomio 10000
		// Inicio la toma de datos
		salida.println("Polinomio 10000");
		tIni = new GregorianCalendar();		
		res = p10000.evaluarMSucesivas(1);
		tFin = new GregorianCalendar();
		System.out.println("Resultado: "+res);
		salida.println("Resultado: "+res);			
		diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();			
		System.out.println("Tiempo: "+diff+" milisegundos");
		salida.println("Tiempo: "+diff);	
		// Fin de toma de datos
		salida.println("");
		
		
		
		salida.close();
		
		}

	
}
