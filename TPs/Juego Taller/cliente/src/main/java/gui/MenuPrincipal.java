package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.Cliente;
import cliente.Mensaje;
import cliente.MensajePersonaje;
import cliente.Usuario;

//import connection.Mensaje;
//import utilities.Loggin;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.SwingConstants;

public class MenuPrincipal extends JFrame {

	private JPanel contentPane;
	private Login login;
	private MenuPrincipal miMismo;

	/**
	 * Create the frame.
	 */
	public MenuPrincipal(final Mensaje p, final Semaphore semaforo, final MensajePersonaje pp,final Cliente cli) {
		setTitle("BloodyWars - Menu Principal");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
				miMismo = this;
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JButton btnPersonaje = new JButton("Editar Personaje");
		btnPersonaje.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		btnPersonaje.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				EditarPersonaje cpFrm = new EditarPersonaje(p,semaforo,pp,miMismo,cli);
				cpFrm.setVisible(true);
				dispose();
			}
		});
		
//		btnPersonaje.addActionListener(new ActionListener() {
//			public void actionPerformed(ActionEvent arg0) {
//				crearPersonaje();
//			}
//		});
		
		btnPersonaje.setBounds(10, 101, 404, 33);
		panel.add(btnPersonaje);
		
		JButton btnJugar = new JButton("JUGAR");
		btnJugar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				new ElegirMapa(p,semaforo,miMismo);
				dispose();	// nuevo
			}
		});
		btnJugar.setFont(new Font("OCR A Extended", Font.BOLD, 16));
		btnJugar.setBounds(10, 145, 404, 60);
		panel.add(btnJugar);
		
		JButton btnCerrarSesin = new JButton("Cerrar sesi\u00F3n");
		btnCerrarSesin.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		btnCerrarSesin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				salir();
			}
		});
		btnCerrarSesin.setBounds(10, 216, 404, 24);
		panel.add(btnCerrarSesin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setIcon(new ImageIcon(MenuPrincipal.class.getResource("/imagenes/logo.png")));
		lblNewLabel.setBounds(10, 11, 404, 79);
		panel.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/bloody_wall.jpg")));
		lblNewLabel_1.setBounds(-12, -10, 450, 279);
		panel.add(lblNewLabel_1);
		
		this.setVisible(true);
	}
	
	private void crearPersonaje() {
//		new CrearPersonaje(login, this);
	}
	
	private void seleccionarMapa(){
//		new SeleccionarMapa(this);
	}
	
	private void salir() {
//		login.visible(true);
		this.dispose();
	}
}
