package grafos;

import java.util.Random;

public class GeneradorAleatorioProbArista {
	
	private int cantAristas, cantNodos;
	private MatrizSimetrica matriz;
	
	public GeneradorAleatorioProbArista() {
		Random r = new Random(2);
		
		for(int i = 0; i < cantNodos; i++)
			for(int j = cantNodos; i < j; j--)
				if(r.nextInt() == 1)
					matriz.setDato(i, j);
		
	}

}
