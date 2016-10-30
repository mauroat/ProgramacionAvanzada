package grafos;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class GrafoNDNP {
	
	private int n, ca, GradoMax, GradoMin;
	private double porcAdy;
	private MatrizSimetrica matriz;
	
	public GrafoNDNP(String path) throws FileNotFoundException{
		
		Scanner sc = new Scanner(new File(path));
		
		n = sc.nextInt();
		ca = sc.nextInt();
		porcAdy = sc.nextDouble();
		GradoMax = sc.nextInt();
		GradoMin = sc.nextInt();
		
		
		
		sc.close();
	}

}
