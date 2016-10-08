package hechizos;

public class Engorgio extends Hechizo {

	@Override
	public void afectar(Personaje personaje) {
		personaje.altura *= 2;
	}

}
