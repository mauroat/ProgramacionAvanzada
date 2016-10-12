package singleton;

public class Singleton {
	
	private double atributo1;
	private double atributo2;
		
	//2) Variable estatica privada que tenga referencia al objeto
	private static Singleton instancia;
	
	//1) Constructor privado
	private Singleton(double atributo1, double atributo2){
		this.atributo1 = atributo1;
		this.atributo2 = atributo2;
	}
	
	//3) Un metodo estatico publico que permita acceder al objeto o instanciarlo por primera vez
	public static Singleton getInstance(double a, double b){
		if (instancia == null) {
			instancia = new Singleton(a,b);
		}
		return instancia;
	}
}
