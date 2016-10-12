package acorrer;

public class Corredor {
	
	private int posInsc;
	private int edad;
	private char sexo;
	private int cat;
	private int llegada;
	
	
	public Corredor(int posInsc, int edad, char sexo, int cat){
		this.posInsc = posInsc;
		this.edad = edad;
		this.sexo = sexo;
		this.cat = cat;
		this.llegada = 0;
	}

	public int definirCategoria(int[][] rango, char sexo){
		int aux=0;
		for (int i=0; i < rango.length;i++)
			if(this.sexo == sexo && (this.edad>= rango[i][0] && this.edad<= rango[i][1])){
				this.cat = i;
				aux = i;
			}
		return aux;
	}
	
	
	public int getPosInsc() {
		return posInsc;
	}


	public void setPosInsc(int posInsc) {
		this.posInsc = posInsc;
	}


	public int getEdad() {
		return edad;
	}


	public void setEdad(int edad) {
		this.edad = edad;
	}


	public char getSexo() {
		return sexo;
	}


	public void setSexo(char sexo) {
		this.sexo = sexo;
	}


	public int getCat() {
		return cat;
	}


	public void setCat(int cat) {
		this.cat = cat;
	}


	public int getLlegada() {
		return llegada;
	}


	public void setLlegada(int llegada) {
		this.llegada = llegada;
	}

}
