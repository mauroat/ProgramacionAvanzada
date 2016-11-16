package corridas;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Arrays;

import grafo.GrafoNDNP;

public class Corridas {
	final static String FILE_NAME = "GR_N1000_ADY75";
	
	final static String DIR_ENTRADA = "src/corridas/Entrada/";
	final static String DIR_SALIDA = "src/corridas/Salida/";
	final static String DIR_COLOR = "src/corridas/Coloreo/";
	
	final static int CANTIDAD_CORRIDAS = 1000; 
			
	public static void main(String[] args) {
		GrafoNDNP grafo = new GrafoNDNP(DIR_ENTRADA + FILE_NAME + ".in");
		corrida("SA", grafo);
		corrida("Matula", grafo);
		corrida("WP", grafo);
	}
	
	public static void corrida(String directorio, GrafoNDNP grafo){
		int[] frecuenciaDeColor = new int[grafo.getCantidadNodos()];
		Arrays.fill(frecuenciaDeColor, 0);
		boolean first = true;
		int corridaMenorColor = CANTIDAD_CORRIDAS;
		int menorColor = grafo.getCantidadNodos();
		String pathSalida = DIR_SALIDA + directorio + "/" + FILE_NAME + ".out";
				
		for (int i = 0; i < CANTIDAD_CORRIDAS; i++) {
			System.out.println(i);
			switch (directorio) {
				case "SA":
					grafo.secuencialAleatorio();
					break;
				case "Matula":
					grafo.matula();
					break;
				case "WP":
					grafo.welshPowell();
					break;
				default:
					break;
			}
			
			if (first) {
				corridaMenorColor = i;
				menorColor = grafo.getCantidadColores();
				grafo.exportar(pathSalida);
				first = false;
			}else{
				if (menorColor > grafo.getCantidadColores()) {
					corridaMenorColor = i;
					menorColor = grafo.getCantidadColores();
					grafo.exportar(pathSalida);
				}
			}
			frecuenciaDeColor[grafo.getCantidadColores()]++;
		}
		exportarGrafo(directorio, frecuenciaDeColor, corridaMenorColor);
	}
	
	public static void exportarGrafo(String directorio, int[] frecuenciaDeColor, int corridaMenorColor){
		FileWriter fw = null;
		PrintWriter pw = null;
		String ruta = DIR_COLOR + directorio + "/" + FILE_NAME + ".graf";
		System.out.println(ruta);
		try {
			fw = new FileWriter(ruta);
			pw = new PrintWriter(fw);
			pw.println(corridaMenorColor);
			for (int i = 0; i < frecuenciaDeColor.length; i++) {
				pw.println(frecuenciaDeColor[i]);
			}			
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (fw != null) {
					fw.close();
				}
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
	}
}
