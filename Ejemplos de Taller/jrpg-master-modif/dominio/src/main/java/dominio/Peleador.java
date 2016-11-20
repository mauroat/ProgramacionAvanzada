package dominio;

import java.io.FileNotFoundException;

public interface Peleador {

	public void atacar(Peleador victima) ;
	
	public abstract boolean puedeAtacar();
	public abstract void despuesDeAtacar();
	public void serAtacado(int dano);
	public void despuesDeSerAtacado();
	
	public int getNivel();
	public boolean estaVivo();
	
	public void agregarItem(Item i);
	public Item dejarItem();

}
