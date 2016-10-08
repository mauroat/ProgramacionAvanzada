package probador;

import java.io.FileNotFoundException;
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		/* CASO 1 */
		String pathIn = "./Lote de Prueba PP/Entrada/Caso01_muchasUbicacionesPosibles.in";
		String pathOut = "./Lote de Prueba PP/Salida esperada/Caso01_muchasUbicacionesPosibles.out";
		
		Terreno terreno = new Terreno(pathIn);
		Casa casa = new Casa(pathIn, pathOut);
		
		System.out.println(terreno.comprobarUbicacion(casa));
		
		/* CASO 2 */
		pathIn = "./Lote de Prueba PP/Entrada/Caso02_unaSolaUbicacionDosOrientacionesPosibles.in";
		pathOut = "./Lote de Prueba PP/Salida esperada/Caso02_unaSolaUbicacionDosOrientacionesPosibles.out";
		
		terreno = new Terreno(pathIn);
		casa = new Casa(pathIn, pathOut);
		
		System.out.println(terreno.comprobarUbicacion(casa));
		
		/* CASO 3 */
		pathIn = "./Lote de Prueba PP/Entrada/Caso03_unaSolaUbicacion4OrientacionesPosibles.in";
		pathOut = "./Lote de Prueba PP/Salida esperada/Caso03_unaSolaUbicacion4OrientacionesPosibles.out";
		
		terreno = new Terreno(pathIn);
		casa = new Casa(pathIn, pathOut);
		
		System.out.println(terreno.comprobarUbicacion(casa));
		
		/* CASO 4 */
		pathIn = "./Lote de Prueba PP/Entrada/Caso04_parcelasConsecutivas.in";
		pathOut = "./Lote de Prueba PP/Salida esperada/Caso04_parcelasConsecutivas.out";
		
		terreno = new Terreno(pathIn);
		casa = new Casa(pathIn, pathOut);
		
		System.out.println(terreno.comprobarUbicacion(casa));
		
		/* CASO 5 */
		pathIn = "./Lote de Prueba PP/Entrada/Caso05_DimensionesNoPermitidas.in";
		pathOut = "./Lote de Prueba PP/Salida esperada/Caso05_DimensionesNoPermitidas.out";
		
		terreno = new Terreno(pathIn);
		casa = new Casa(pathIn, pathOut);
		
		System.out.println(terreno.comprobarUbicacion(casa));
		
		/* CASO 6 */
		pathIn = "./Lote de Prueba PP/Entrada/Caso06_posicionEquivocada.in";
		pathOut = "./Lote de Prueba PP/Salida esperada/Caso06_posicionEquivocada.out";
		
		terreno = new Terreno(pathIn);
		casa = new Casa(pathIn, pathOut);
		
		System.out.println(terreno.comprobarUbicacion(casa));

	}

}
