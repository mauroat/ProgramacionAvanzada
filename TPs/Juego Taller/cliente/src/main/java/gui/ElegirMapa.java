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

import cliente.Mensaje;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ElegirMapa extends JFrame {

	private JPanel contentPane;
	private MenuPrincipal mp;

	public ElegirMapa(final Mensaje p,final Semaphore sem, MenuPrincipal menuAnt) {
		setTitle("BloodyWars - Elegir Mapa");
		//setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 450, 258);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		mp = menuAnt;
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				mp.setVisible(true);
			}
		});
		JButton btnNewButton = new JButton("");
		btnNewButton.setIcon(new ImageIcon(ElegirMapa.class.getResource("/imagenes/llanura.jpg")));
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setMensaje("1");
				sem.release();
				dispose();
			}
		});
		btnNewButton.setBounds(97, 37, 237, 59);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Mapa 2");
		btnNewButton_1.setIcon(new ImageIcon(ElegirMapa.class.getResource("/imagenes/desierto.jpg")));
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				p.setMensaje("2");
				sem.release();
				dispose();
			}
		});
		btnNewButton_1.setBounds(97, 132, 237, 59);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/bloody_wall.jpg")));
		lblNewLabel_1.setBounds(0, -12, 446, 283);
		contentPane.add(lblNewLabel_1);
		this.setVisible(true);
	}
}

