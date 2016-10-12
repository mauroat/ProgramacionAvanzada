package composite;

import java.util.*;

public class Composite implements Figura{

	private List<Figura> tren = new ArrayList<Figura>();
	
	public Composite(){}
	
	public Composite(List<Figura> tren) {
		super();
		this.tren = tren;
	}
	
	public void add(Figura figura){
		this.tren.add(figura);
	}

	@Override
	public double calcularArea() {
		double area = 0;
		for (Figura figura : tren) {
			area+=figura.calcularArea();
		}
		return area;
	}
}
