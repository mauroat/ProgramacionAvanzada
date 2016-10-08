package probador;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Terreno {
	
	private int[][] terreno;
	private int cantPeniascos;
	private int filas;
	private int cols;
	
	public Terreno(String path) throws FileNotFoundException {
		
		Scanner sc = new Scanner(new File(path));
		this.filas = sc.nextInt();
		this.cols = sc.nextInt();
		this.terreno = new int[this.filas][this.cols];
		sc.nextLine(); // Dimensiones Casa
		sc.nextLine(); // Dimensiones Casa
		this.cantPeniascos = sc.nextInt();
		
		for (int i = 0; i < this.filas; i++)
			for (int j = 0; j < this.cols; j++)
				this.terreno[i][j] = 0;
		
		for(int k = 0; k < this.cantPeniascos; k++)
			this.terreno[sc.nextInt() - 1][sc.nextInt() - 1] = 1;
				
		sc.close();
	}
	
	public boolean comprobarUbicacion(Casa casa) {
		
		int dx, dy;
		
		if (casa.getOrientacion().contains("N") || casa.getOrientacion().contains("S"))	{
			dx = casa.getFrente();
			dy = casa.getProf();
		}
		else {
			dx = casa.getProf();
			dy = casa.getFrente();
		}
		
		if(this.filas < dx || this.cols < dy)
			return false;
		
		for (int i = 0, x = casa.getxInic() - 1; i < dx; i++, x++)
			for (int j = 0, y = casa.getyInic() - 1; j < dy; j++, y++){
				if(this.filas < x + 1 || this.cols < y + 1)
					return false;
				if (this.terreno[x][y] == 1)
					return false;
			}
		return true;
	}

}
