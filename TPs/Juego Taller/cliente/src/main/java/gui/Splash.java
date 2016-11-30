package gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import cliente.Usuario;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.BoxLayout;

public class Splash extends JFrame {

	private JPanel contentPane;
	private Splash miMismo;

	/**
	 * Create the frame.
	 */
	public Splash(final Usuario usuario,final Semaphore semaforo) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
					usuario.setAccion("salir");
					semaforo.release();
					setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
					//setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);		
			}
		});
		setTitle("Bienvenido a BloodyWars");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
		miMismo = this;
		
		JButton btnRegistro = new JButton("Registro");
		btnRegistro.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		btnRegistro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Registro (usuario, semaforo,miMismo);
				dispose();
			}
		});
		btnRegistro.setBounds(153, 227, 128, 23);
		contentPane.add(btnRegistro);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Login(usuario, semaforo,miMismo);
				dispose();
			}
		});
		
		btnLogin.setBounds(153, 193, 128, 23);
		contentPane.add(btnLogin);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Splash.class.getResource("/imagenes/logo.png")));
		lblNewLabel.setBounds(47, 54, 345, 88);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/bloody_wall.jpg")));
		lblNewLabel_1.setBounds(0, -12, 500, 523);
		contentPane.add(lblNewLabel_1);
	}
}

