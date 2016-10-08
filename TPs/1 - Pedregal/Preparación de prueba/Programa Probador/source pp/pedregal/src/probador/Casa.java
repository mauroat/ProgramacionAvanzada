package probador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Casa {
	
	private int frente;
	private int prof;
	private int xInic;
	private int yInic;
	private String orientacion;
	
	public int getFrente() {
		return frente;
	}

	public void setFrente(int frente) {
		this.frente = frente;
	}

	public int getProf() {
		return prof;
	}

	public void setProf(int prof) {
		this.prof = prof;
	}

	public int getxInic() {
		return xInic;
	}

	public void setxInic(int xInic) {
		this.xInic = xInic;
	}

	public int getyInic() {
		return yInic;
	}

	public void setyInic(int yInic) {
		this.yInic = yInic;
	}

	public String getOrientacion() {
		return orientacion;
	}

	public void setOrientacion(String orientacion) {
		this.orientacion = orientacion;
	}
	
	public Casa(String pathIn, String pathOut) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File(pathIn));
		sc.nextLine(); // Dimensiones Terreno
		this.frente = sc.nextInt();
		this.prof = sc.nextInt();
		sc.close();
		
		sc = new Scanner(new File(pathOut));
		sc.nextLine(); // Si
		this.xInic = sc.nextInt();
		this.yInic = sc.nextInt();
		this.orientacion = sc.next();
		sc.close();
	}
}
