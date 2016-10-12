package composite;

public class Rectangulo implements Figura{
	double base;
	double altura;
	
	public Rectangulo(double base, double altura) {
		super();
		this.base = base;
		this.altura = altura;
	}
	
	@Override
	public double calcularArea() {
		return base*altura;
	}

}
