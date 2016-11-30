package juego;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/*Se utiliza para devpñver la posición de la pantalla donde se hizo click y calcular el recorrido*/
public class Mouse implements MouseListener {
	
	private int x;
	private int y;
	private int posicion[];
	private boolean recorrido;
	
	
	public Mouse() {
		posicion = new int[2];
	}
	
	public void actualizar() {
		posicion[0] = x;
		posicion[1] = y;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getButton()==MouseEvent.BUTTON3){ 
		      x = e.getX(); 
		      y = e.getY(); 
		      recorrido = true; 
		}
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		
	}
	
	public int[] obtenerPosicion() {
		return posicion;
	}

	public boolean obtenerRecorrido() {
		return recorrido;
	}
	
	public void setearRecorrido(boolean b) {
		recorrido = b;
	}
}
