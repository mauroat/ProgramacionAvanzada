package jframe;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

public class JFrame extends javax.swing.JFrame {

	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrame frame = new JFrame();
					frame.setVisible(true);		// Si no está en true, cuando lo ejecute no se va a ver.
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public JFrame() {
		setTitle("Mi primer Ventana");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Que quiero que haga cuando toco el boton de cerrar
														// 3 opciones : EXIT_ON_CLOSE: Cuanto toco se cierra TODO inc. la ventana principal.
														//			  : DISPOSE_ON_CLOSE: Cuando toco esta, se cierra la activa pero la de fondo permanece.
													//
		setLocationRelativeTo(null); 		// Sin este método, la ventana se abre donde quiere Windows.
										// Si le pongo null > Aparecerá en el medio del escritorio.
										// Le pongo null en una sub ventana, > aparecerá en el medio de la ventana de origen
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(50, 64, 200, 121);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblHolaJava = new JLabel("hola java");		// label.setBounds(X,Y,A,L)
		lblHolaJava.setBounds(54, 47, 46, 14);
		panel.add(lblHolaJava);
		
		
	}
}
