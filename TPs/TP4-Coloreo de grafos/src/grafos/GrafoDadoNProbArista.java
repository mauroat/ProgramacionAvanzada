package grafos;

import java.util.Random;

public class GrafoDadoNProbArista {
	
	private int cantAristas;
	private int cantNodos;
	private double probArista;
	private MatrizSimetrica matriz;
	

	public GrafoDadoNProbArista(int cantNodos, double probArista) {
		this.cantNodos = cantNodos;
		this.cantAristas = 0;
		this.probArista = probArista;
		this.matriz = new MatrizSimetrica(this.cantNodos);
	}
	
	public void GrafoAleatorio() {
		Random r = new Random(2);
		
		for(int i = 0; i < cantNodos; i++)
			for(int j = cantNodos; i < j; j--)
				if(r.nextInt() == 1)
				{
					matriz.setDato(i, j);
					this.cantAristas++;
				}
	}

}
