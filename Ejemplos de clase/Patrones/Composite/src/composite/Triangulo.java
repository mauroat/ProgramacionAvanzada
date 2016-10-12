package composite;

public class Triangulo implements Figura{
	private double base;
	private double altura;
	
	public Triangulo(double base, double altura) {
		super();
		this.base = base;
		this.altura = altura;
	}

	@Override
	public double calcularArea() {
		return (base*altura)/2;
	}
	
	
}
