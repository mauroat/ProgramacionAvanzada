package contar;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ContarClicks extends JFrame {

	private JPanel contentPane;
	
	
	private JLabel label ; 
	int cont = 1;
	String tecsto;
	private JButton btnAbrir;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ContarClicks frame = new ContarClicks();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public ContarClicks() {
		setTitle("Contar clicks");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 414, 239);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JButton btnContarClicks = new JButton("Contar clicks");
		btnContarClicks.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				cont++;
			}
		});
		btnContarClicks.addActionListener(new ActionListener() {
			// Boton derechos sobre el boton -> addEvent -> Action performed
			
			public void actionPerformed(ActionEvent arg0) {
			// No puedo usar el THIS!!
				label.setText((String.valueOf(cont)));
			}
		});
		btnContarClicks.setBounds(94, 159, 120, 23);
		panel.add(btnContarClicks);
		
		label = new JLabel("####");
		
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setBounds(182, 79, 46, 14);
		panel.add(label);
		
		btnAbrir = new JButton("Abrir");
		btnAbrir.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					abrirVentana();						// IMPORTANTE
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		});
		
		btnAbrir.setBounds(224, 159, 89, 23);
		panel.add(btnAbrir);
	}
	
	public void abrirVentana(){
		SegundaVentana frame = new SegundaVentana(this);	// IMPORTANTE
		frame.setVisible(true);	
	}
	
	public void escribirEtiqueta(String texto){
		label.setText(texto);
	}
}
