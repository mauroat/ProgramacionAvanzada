package juego;

import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.JFrame;

import com.google.gson.Gson;

import cliente.Cliente;
import cliente.Mensaje;
import utilities.Loggin;

public class Pantalla {

	private JFrame pantalla;
	private Canvas canvas;
	
	private final Gson gson = new Gson();

	public Pantalla(final String tituloJuego, final int ANCHO, final int ALTO, final Cliente cliente) { 
	    pantalla = new JFrame(tituloJuego); 
		pantalla.setSize(ANCHO, ALTO);
		pantalla.setResizable(false);
		pantalla.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		pantalla.addWindowListener(new WindowAdapter() {
			   public void windowClosing(WindowEvent evt) {
				   try {
					Mensaje p = new Mensaje();
					p.setComando("desconectar");
					p.setIp(cliente.getMiIp());
					cliente.getSalida().writeObject(gson.toJson(p));
					cliente.getEntrada().close();
					cliente.getSalida().close();
					System.exit(0);
				} catch (IOException e) {
					Loggin.getInstance().error("Error Pantalla: "+e.getMessage());
				}
			   }
			  });

		pantalla.setLocationRelativeTo(null);
		pantalla.setVisible(true);

		canvas = new Canvas();
		canvas.setPreferredSize(new Dimension(ANCHO, ALTO));
		canvas.setMaximumSize(new Dimension(ANCHO, ALTO));
		canvas.setMinimumSize(new Dimension(ANCHO, ALTO));
		canvas.setFocusable(false);
		
		pantalla.add(canvas);
		pantalla.pack();
	}

	public Canvas getCanvas() {
		return canvas;
	}
	
	public JFrame getFrame() {
		return pantalla;
	}
}