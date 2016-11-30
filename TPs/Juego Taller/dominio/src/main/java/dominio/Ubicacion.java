package dominio;

import dominio.*;

public class Ubicacion {
	
	private double x;
	private double y;
	private double radio = 2;
	
	public Ubicacion(double x, double y) {
		this.x = x;
		this.y = y;
	}
	
	public Ubicacion() {
		this.x = 0;
		this.y = 0;
	}
	
	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public double getRadio() {
		return radio;
	}
	/*
	public void setRadio(double radio) {
		this.radio = radio;
	}
	*/
	public double distanciaA(Ubicacion u){
		return Math.sqrt(Math.pow(this.x - u.x, 2) + Math.pow(this.y - u.y, 2));
	}
	
	public boolean contiene(Ubicacion u) {
		return this.distanciaA(u) <= radio;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		Ubicacion other = (Ubicacion) obj;
		if (Double.doubleToLongBits(x) != Double.doubleToLongBits(other.x))
			return false;
		if (Double.doubleToLongBits(y) != Double.doubleToLongBits(other.y))
			return false;
		return true;
	}
	
	

}
