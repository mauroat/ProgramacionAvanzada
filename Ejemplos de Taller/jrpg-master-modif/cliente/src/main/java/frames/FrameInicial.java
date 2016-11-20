package frames;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import cliente.*;
import gui.Login;
import gui.Registro;

import javax.swing.BoxLayout;
import javax.swing.JSplitPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class FrameInicial extends JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameInicial frame = new FrameInicial();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public FrameInicial(final Usuario usuario,final Semaphore semaforo) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
					
					usuario.setAccion("salir");
					semaforo.release();
					setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

					
			}
		});
		setTitle("BloodyWars");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Registro r1 = new Registro (usuario, semaforo);
				r1.setVisible(true);
				dispose();
			}
		});
		btnRegistrarse.setBounds(58, 119, 118, 23);
		contentPane.add(btnRegistrarse);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Login loginFrm = new Login(usuario, semaforo);
				loginFrm.setVisible(true);
				dispose();
			}
		});
		
		btnLogin.setBounds(242, 119, 128, 23);
		contentPane.add(btnLogin);
	}
}

