package personaje;

public abstract class Personaje {

	protected int energia = 100;
	protected int salud = 100;
	
	public final void atacar(Personaje atacado) {
		if (puedeAtacar()) {
			atacado.serAtacado(calcularPuntosDeAtaque());
			energia -= calcularPuntosDeAtaque();
			despuesDeAtacar();
		}
	}

	protected void despuesDeAtacar() { }
	
	protected abstract boolean puedeAtacar();
	protected abstract int calcularPuntosDeAtaque();
	
	public void serAtacado(int da�o) {
		this.salud -= da�o;
	}

	public void serCurado() {
		this.salud = 100;
	}

	public void serEnergizado() {
		this.energia = 100;
	}

}