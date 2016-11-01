package grafos;

public class GrafoRegDadoNProbAdyacencia {
	private int cantAristas;
	private int cantNodos;
	private int gradoMaximo;
	private int gradoMinimo;
	private MatrizSimetrica matriz;
	private Nodo[] listaNodos;
	private int porcAdyacencia;
	
	public GrafoRegDadoNProbAdyacencia(int cantNodos, int porcAdyacencia)
	{
		this.cantNodos = cantNodos;
		this.matriz = new MatrizSimetrica(cantNodos);
		this.gradoMaximo = 0;
		this.gradoMinimo = 0;
		this.cantAristas = 0;
		this.listaNodos = new Nodo[this.cantNodos];
		inicializarLista();
		this.porcAdyacencia = 0;
	}

	private void inicializarLista() {
		for (int i = 0; i < this.listaNodos.length; i++) {
			this.listaNodos[i] = new Nodo(i, 0, 0);
		}
	}

	public void GrafoAleatorio()
	{
		int grado = (porcAdyacencia / 100) * ((this.cantNodos * (this.cantNodos - 1)) / 2) / this.cantNodos;
		if(grado + 1 > this.cantNodos || (this.cantNodos * grado) % 2 != 0)
			return;
		
		if(this.gradoMinimo != 0)
		{
			for(int i = 0; i < cantNodos; i++)
			{
				for(int j = cantNodos; i < j; j--)
				{
					if(i != j)
					{
						if (listaNodos[i].getGrado() == grado || listaNodos[j].getGrado() == grado)
							break;
						
						this.matriz.setDato(listaNodos[i].getIndice(), listaNodos[j].getIndice());
						this.cantAristas++;
						listaNodos[i].incrementarGrado();
						listaNodos[j].incrementarGrado();	
					}	
				}
			}
		}
		
		int totalAristas = factorial(this.cantNodos - 1);
		this.porcAdyacencia = this.cantAristas / totalAristas * 100;
				
	}

	private int factorial(int n) {
		if(n == 0)
			return 1;
		return n * factorial(n-1);
	}
}
