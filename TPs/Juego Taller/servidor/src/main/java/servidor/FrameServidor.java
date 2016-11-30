package servidor;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import utilities.Loggin;

public class FrameServidor extends JFrame {

	private static final long serialVersionUID = -3545318975171115521L;
	private JPanel contentPane;
	private JButton btnParar;
	private JButton btnIniciar;
	private Servidor serv;
	private JLabel lblOnline;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FrameServidor frame = new FrameServidor();
					frame.setVisible(true);
				} catch (Exception e) {
					Loggin.getInstance().error("Error al iniciar frame servidor " + e.getMessage());
				}
			}
		});
	}

	public FrameServidor() {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				if(serv != null)
					serv.parar();
			}
		});

		setTitle("Servidor BloodyWars");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		btnIniciar = new JButton("Iniciar");		
		btnIniciar.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serv = new Servidor();
				serv.start();
				lblOnline.setVisible(true);
				btnIniciar.setEnabled(false);
				btnParar.setEnabled(true);
			}
		});
		btnIniciar.setBounds(153, 227, 128, 23);
		contentPane.add(btnIniciar);

		btnParar = new JButton("Parar");
		btnParar.setEnabled(false);
		btnParar.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		btnParar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				serv.parar();
				lblOnline.setVisible(false);
				btnIniciar.setEnabled(true);
				btnParar.setEnabled(false);
			}
		});

		btnParar.setBounds(153, 193, 128, 23);
		contentPane.add(btnParar);

		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(FrameServidor.class.getResource("/imagenes/logo.png")));
		lblNewLabel.setBounds(47, 54, 345, 88);
		contentPane.add(lblNewLabel);
		
		lblOnline = new JLabel("Servidor Online");
		lblOnline.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
		lblOnline.setBounds(160,150,150,60);
		lblOnline.setVisible(false);
		contentPane.add(lblOnline);
	}
}
