package grafos;

import java.util.Arrays;
import java.util.Random;

public class GrafoDadoNProbArista {
	
	private int cantAristas;
	private int cantNodos;
	private double probArista;
	private MatrizSimetrica matriz;
	private Nodo[] listaNodos;
	private int gradoMaximo;
	private int gradoMinimo;

	public GrafoDadoNProbArista(int cantNodos, double probArista) {
		this.cantNodos = cantNodos;
		this.cantAristas = 0;
		this.probArista = probArista;
		this.matriz = new MatrizSimetrica(this.cantNodos);
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
		
		for(int i = 0; i < cantNodos; i++)
			for(int j = cantNodos; i < j; j--)
				if(r.nextInt() == 1)
				{
					matriz.setDato(i, j);
					listaNodos[i].incrementarGrado();
					listaNodos[j].incrementarGrado();
					this.cantAristas++;
				}
		
		Arrays.sort(listaNodos);
		this.gradoMaximo = listaNodos[this.cantNodos - 1].getGrado();
		this.gradoMinimo = listaNodos[0].getGrado();
	}

}
