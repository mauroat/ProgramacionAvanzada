package probador;

import java.io.FileNotFoundException;

import org.junit.Assert;
import org.junit.Test;

public class ProbadorTests {
	
	@Test
	public void caso01_muchasUbicacionesPosibles() throws FileNotFoundException {
		
		String pathIn = "./Lote de Prueba PP/Entrada/Caso01_muchasUbicacionesPosibles.in";
		String pathOut = "./Lote de Prueba PP/Salida esperada/Caso01_muchasUbicacionesPosibles.out";
		Terreno terreno = new Terreno(pathIn);
		Casa casa = new Casa(pathIn, pathOut);
		Assert.assertTrue(terreno.comprobarUbicacion(casa));
	}
	
	@Test
	public void caso02_unaSolaUbicacionDosOrientacionesPosibles() throws FileNotFoundException {
		
		String pathIn = "./Lote de Prueba PP/Entrada/Caso02_unaSolaUbicacionDosOrientacionesPosibles.in";
		String pathOut = "./Lote de Prueba PP/Salida esperada/Caso02_unaSolaUbicacionDosOrientacionesPosibles.out";
		Terreno terreno = new Terreno(pathIn);
		Casa casa = new Casa(pathIn, pathOut);
		Assert.assertFalse(terreno.comprobarUbicacion(casa));
	}
	
	@Test
	public void caso03_unaSolaUbicacion4OrientacionesPosibles() throws FileNotFoundException {
		
		String pathIn = "./Lote de Prueba PP/Entrada/Caso03_unaSolaUbicacion4OrientacionesPosibles.in";
		String pathOut = "./Lote de Prueba PP/Salida esperada/Caso03_unaSolaUbicacion4OrientacionesPosibles.out";
		Terreno terreno = new Terreno(pathIn);
		Casa casa = new Casa(pathIn, pathOut);
		Assert.assertTrue(terreno.comprobarUbicacion(casa));
	}
	
	@Test
	public void caso04_pasillo() throws FileNotFoundException {
		
		String pathIn = "./Lote de Prueba PP/Entrada/Caso04_parcelasConsecutivas.in";
		String pathOut = "./Lote de Prueba PP/Salida esperada/Caso04_parcelasConsecutivas.out";
		Terreno terreno = new Terreno(pathIn);
		Casa casa = new Casa(pathIn, pathOut);
		Assert.assertFalse(terreno.comprobarUbicacion(casa));
	}
	
	@Test
	public void caso05_dimensionesNoPermitidas() throws FileNotFoundException {
		
		String pathIn = "./Lote de Prueba PP/Entrada/Caso05_DimensionesNoPermitidas.in";
		String pathOut = "./Lote de Prueba PP/Salida esperada/Caso05_DimensionesNoPermitidas.out";
		Terreno terreno = new Terreno(pathIn);
		Casa casa = new Casa(pathIn, pathOut);
		Assert.assertFalse(terreno.comprobarUbicacion(casa));
	}
	
	@Test
	public void Caso06_posicionEquivocada() throws FileNotFoundException {
		String pathIn = "./Lote de Prueba PP/Entrada/Caso06_posicionEquivocada.in";
		String pathOut = "./Lote de Prueba PP/Salida esperada/Caso06_posicionEquivocada.out";
		Terreno terreno = new Terreno(pathIn);
		Casa casa = new Casa(pathIn, pathOut);
		Assert.assertFalse(terreno.comprobarUbicacion(casa));
	}

}
