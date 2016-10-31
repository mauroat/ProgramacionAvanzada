package grafos;

import java.util.Random;

public class GrafoDadoNProbAdyacencia {
	private int cantAristas;
	private int cantNodos;
	private int porcAdyacencia;
	private MatrizSimetrica matriz;
	
	public GrafoDadoNProbAdyacencia(int cantNodos, int porcAdyacencia)
	{
		this.cantNodos = cantNodos;
		matriz = new MatrizSimetrica(cantNodos);
		this.porcAdyacencia = porcAdyacencia;
		this.cantAristas = (porcAdyacencia / 100) * ((this.cantNodos * (this.cantNodos - 1)) / 2);
	}
	
	public void GrafoAleatorio() {
		Random r = new Random(2);
		int aristas = 0;
		
		while(aristas != this.cantAristas)
		{
			for(int i = 0; i < cantNodos; i++)
			{
				for(int j = cantNodos; i < j; j--)
				{
					if(r.nextInt() == 1)
					{
						matriz.setDato(i, j);
						aristas++;
					}
					if(aristas == cantAristas)
						break;
				}
				if(aristas == cantAristas)
					break;
			}
		}
	}
}
