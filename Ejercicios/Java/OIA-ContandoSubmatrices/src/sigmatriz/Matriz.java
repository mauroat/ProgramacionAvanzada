package sigmatriz;

public class Matriz {
	private int filas, columnas;
	private int[][] matriz;
	
	public Matriz(int filas, int columnas){
		this.filas = filas;
		this.columnas = columnas;
		this.matriz = new int[this.filas][this.columnas];
	}
	
	public void inicializarMatriz(){
		for (int i=0; i<this.filas;i++)
			for (int j=0; j<this.columnas;j++)
				this.matriz[i][j] = 0;
	}

	public int getFilas() {
		return filas;
	}

	public void setFilas(int filas) {
		this.filas = filas;
	}

	public int getColumnas() {
		return columnas;
	}

	public void setColumnas(int columnas) {
		this.columnas = columnas;
	}

	public int[][] getMatriz() {
		return matriz;
	}

	public void setMatriz(int[][] matriz) {
		this.matriz = matriz;
	}
	
	public void setValorMatriz(int fila, int columna, int val){
		this.matriz[fila][columna] = val;
	}
	
	public int getValorMatriz(int fila, int columna){
		return this.matriz[fila][columna];
	}
	
	
}
