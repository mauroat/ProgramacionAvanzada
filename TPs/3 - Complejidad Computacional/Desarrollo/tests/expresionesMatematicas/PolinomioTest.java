package expresionesMatematicas;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Calendar;
import java.util.GregorianCalendar;

public class PolinomioTest {
	//@Test
		public void pruebaMSucesivas() throws Exception
		{
			// Genero el archivo de salida
			String miPath = "tests/Salida Esperada/1 - MSucesivas/";
			PrintWriter salida = new PrintWriter(new FileWriter(miPath+"polinomio_5.out"));
			
			System.out.println("Programacion Multip. Sucesivas:");
			salida.println("Programacion Multip. Sucesivas:");
			// Inicio la toma de datos
			Calendar tIni = new GregorianCalendar();
			Polinomio p = new Polinomio("tests/Entrada/Polinomio_5.in");
			
			// Calculo y muestro el resultado con x=2
			double res = p.evaluarMSucesivas(2);
			
			System.out.println("Resultado: "+res);
			salida.println("Resultado: "+res);
			
			Calendar tFin = new GregorianCalendar();
			long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
			
			System.out.println("Tiempo: "+diff+" milisegundos");
			salida.println("Tiempo: "+diff);
			salida.close();
			
			
		}
		
		//@Test
		public void pruebaRecursiva() throws Exception
		{
			// Genero el archivo de salida
			String miPath = "tests/Salida Esperada/2 - Recursiva/";
			PrintWriter salida = new PrintWriter(new FileWriter(miPath+"polinomio_5.out"));
			
			System.out.println("Programacion recursiva:");
			salida.println("Programacion recursiva:");
			// Inicio la toma de datos
			Calendar tIni = new GregorianCalendar();
			Polinomio p = new Polinomio("tests/Entrada/Polinomio_5.in");
			
			// Calculo y muestro el resultado con x=2
			double res = p.evaluarRecursiva(2);
			
			System.out.println("Resultado: "+res);
			salida.println("Resultado: "+res);
			
			Calendar tFin = new GregorianCalendar();
			long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
			
			System.out.println("Tiempo: "+diff+" milisegundos");
			salida.println("Tiempo: "+diff);
			salida.close();
			
			
		}
		
		//@Test
		public void pruebaRecursivaPar() throws Exception
		{
			// Genero el archivo de salida
			String miPath = "tests/Salida Esperada/3 - RecursivaPar/";
			PrintWriter salida = new PrintWriter(new FileWriter(miPath+"polinomio_5.out"));
			
			System.out.println("Programacion recursiva par:");
			salida.println("Programacion recursiva par:");
			// Inicio la toma de datos
			Calendar tIni = new GregorianCalendar();
			Polinomio p = new Polinomio("tests/Entrada/Polinomio_5.in");
			
			// Calculo y muestro el resultado con x=2
			double res = p.evaluarRecursivaPar(2);
			
			System.out.println("Resultado: "+res);
			salida.println("Resultado: "+res);
			
			Calendar tFin = new GregorianCalendar();
			long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
			
			System.out.println("Tiempo: "+diff+" milisegundos");
			salida.println("Tiempo: "+diff);
			salida.close();		
		}
		
		//@Test
		public void pruebaProgDinamica() throws Exception
		{
			// Genero el archivo de salida
			String miPath = "tests/Salida Esperada/4 - ProgDinamica/";
			PrintWriter salida = new PrintWriter(new FileWriter(miPath+"polinomio_5.out"));
			
			System.out.println("Programacion dinamica:");
			salida.println("Programacion dinamica:");
			// Inicio la toma de datos
			Calendar tIni = new GregorianCalendar();
			Polinomio p = new Polinomio("tests/Entrada/Polinomio_5.in");
			
			// Calculo y muestro el resultado con x=2
			double res = p.evaluarProgDinamica(2);
			
			System.out.println("Resultado: "+res);
			salida.println("Resultado: "+res);
			
			Calendar tFin = new GregorianCalendar();
			long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
			
			System.out.println("Tiempo: "+diff+" milisegundos");
			salida.println("Tiempo: "+diff);
			salida.close();
		}
		
		//@Test
		public void pruebaMejorada() throws Exception
		{
			// Genero el archivo de salida
			String miPath = "tests/Salida Esperada/5 - Mejorada/";
			PrintWriter salida = new PrintWriter(new FileWriter(miPath+"polinomio_5.out"));
			
			System.out.println("Programacion dinámica mejorada:");
			salida.println("Programacion dinámica mejorada:");
			// Inicio la toma de datos
			Calendar tIni = new GregorianCalendar();
			Polinomio p = new Polinomio("tests/Entrada/Polinomio_5.in");
			
			// Calculo y muestro el resultado con x=2
			double res = p.evaluarMejorada(2);
			
			System.out.println("Resultado: "+res);
			salida.println("Resultado: "+res);
			
			Calendar tFin = new GregorianCalendar();
			long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
			
			System.out.println("Tiempo: "+diff+" milisegundos");
			salida.println("Tiempo: "+diff);
			salida.close();
		}
		
		//@Test
		public void pruebaPow() throws Exception
		{
			// Genero el archivo de salida
			String miPath = "tests/Salida Esperada/6 - Pow/";
			PrintWriter salida = new PrintWriter(new FileWriter(miPath+"polinomio_5.out"));
			
			System.out.println("Programacion pow:");
			salida.println("Programacion pow:");
			// Inicio la toma de datos
			Calendar tIni = new GregorianCalendar();
			Polinomio p = new Polinomio("tests/Entrada/Polinomio_5.in");
			
			// Calculo y muestro el resultado con x=2
			double res = p.evaluarPow(2);
			
			System.out.println("Resultado: "+res);
			salida.println("Resultado: "+res);
			
			Calendar tFin = new GregorianCalendar();
			long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
			
			System.out.println("Tiempo: "+diff+" milisegundos");
			salida.println("Tiempo: "+diff);
			salida.close();
		}
		
		//@Test
		public void pruebaHorner() throws Exception
		{
			// Genero el archivo de salida
			String miPath = "tests/Salida Esperada/7 - Horner/";
			PrintWriter salida = new PrintWriter(new FileWriter(miPath+"polinomio_5.out"));
			
			System.out.println("Programacion Algoritmo de Horner:");
			salida.println("Programacion Algoritmo de Horner:");
			// Inicio la toma de datos
			Calendar tIni = new GregorianCalendar();
			Polinomio p = new Polinomio("tests/Entrada/Polinomio_5.in");
			
			// Calculo y muestro el resultado con x=2
			double res = p.evaluarHorner(2);
			
			System.out.println("Resultado: "+res);
			salida.println("Resultado: "+res);
			
			Calendar tFin = new GregorianCalendar();
			long diff = tFin.getTimeInMillis() - tIni.getTimeInMillis();
			
			System.out.println("Tiempo: "+diff+" milisegundos");
			salida.println("Tiempo: "+diff);
			salida.close();
		}
}
