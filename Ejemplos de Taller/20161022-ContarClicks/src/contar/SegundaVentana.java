package contar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JTextField;
import javax.swing.JEditorPane;
import javax.swing.JTextPane;
import javax.swing.JTextArea;

public class SegundaVentana extends JFrame {

	private JPanel contentPane;
	private ContarClicks padre;	// IMPORTANT
	private JTextField textField;

	/**
	 * Launch the application.
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					SegundaVentana frame = new SegundaVentana(padre);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
 */
	/**
	 * Create the frame.
	 */
	public SegundaVentana(ContarClicks padre) {
		this.padre = padre;		// IMPORTANTE
		
		
		setLocationRelativeTo(padre);// IMPORTANTE - seteo en donde va a aparecer la ventana
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(53, 33, 340, 126);
		contentPane.add(panel);
		
		textField = new JTextField();
		panel.add(textField);
		textField.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					
					/*
					 * Cuando hago click, ejecuto el metodo que actualiza el panel
					 * */
					actualizarPrincipal();
				} catch (Exception e1) {
					e1.printStackTrace();
				}
			}
		});
		btnAceptar.setBounds(177, 170, 89, 23);
		contentPane.add(btnAceptar);
	}
	
	/*
	 * Como no puedo mandar el this en el constructor, armo este metodo
	 * */
	public void actualizarPrincipal(){
		padre.escribirEtiqueta(textField.getText());
		dispose();	// Este comando me cierra la ventana al "aceptar"
	}
}
