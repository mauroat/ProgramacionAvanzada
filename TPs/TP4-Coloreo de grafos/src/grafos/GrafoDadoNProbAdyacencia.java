package grafos;

import java.util.Arrays;
import java.util.Random;

public class GrafoDadoNProbAdyacencia {
	private int cantAristas;
	private int cantNodos;
	private int porcAdyacencia;
	private MatrizSimetrica matriz;
	private Nodo[] listaNodos;
	private int gradoMaximo;
	private int gradoMinimo;
	
	public GrafoDadoNProbAdyacencia(int cantNodos, int porcAdyacencia)
	{
		this.cantNodos = cantNodos;
		matriz = new MatrizSimetrica(cantNodos);
		this.porcAdyacencia = porcAdyacencia;
		this.cantAristas = (porcAdyacencia / 100) * ((this.cantNodos * (this.cantNodos - 1)) / 2);
		this.listaNodos = new Nodo[this.cantNodos];
		inicializarLista();
		this.gradoMaximo = 0;
		this.gradoMinimo = 0;
	}
	
	private void inicializarLista() {
		for (int i = 0; i < this.listaNodos.length; i++) {
			this.listaNodos[i] = new Nodo(i, 0, 0);
		}
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
						listaNodos[i].incrementarGrado();
						listaNodos[j].incrementarGrado();
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

		Arrays.sort(listaNodos);
		this.gradoMaximo = listaNodos[this.cantNodos - 1].getGrado();
		this.gradoMinimo = listaNodos[0].getGrado();
	}
}
