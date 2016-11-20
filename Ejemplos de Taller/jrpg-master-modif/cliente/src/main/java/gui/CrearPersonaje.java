package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.*;
import cliente.Usuario;
import dominio.*;
import razas.*;

//import connection.Mensaje;
//import entities.MensajePersonaje;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class CrearPersonaje extends JFrame {

	private JPanel contentPane;
//	private Login login;
	private MenuPrincipal menu;
//	private Mensaje msj;
//	private Gson gson;
	private JComboBox<String> comboRaza;
	private JComboBox<String> comboCasta;
	private JLabel lblRazaElegida;
	private JLabel lblCastaElegida;
//	private MensajePersonaje personaje;
	private boolean nuevoPersonaje=false;

	/**
	 * Launch the application.
	 * @wbp.parser.constructor
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CrearPersonaje frame = new CrearPersonaje(null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
*/
	// Crear personaje desde 0
	public CrearPersonaje(final Personaje personaje, final Semaphore semaforo, final Usuario usuario) {
		setTitle("Seleccion de Personaje");
		setResizable(false);
//		personaje = new MensajePersonaje();
//		msj = new Mensaje();
//		gson = new Gson();
//		this.login = login;
//		this.menu = menu;
//		this.menu.setVisible(false);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblSeleccioneSuPersonaje = new JLabel("Seleccione su personaje:");
		lblSeleccioneSuPersonaje.setBounds(50, 32, 153, 14);
		panel.add(lblSeleccioneSuPersonaje);

		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(50, 61, 46, 14);
		panel.add(lblRaza);

		JLabel lblCasta = new JLabel("Casta: ");
		lblCasta.setBounds(50, 190, 57, 14);
		panel.add(lblCasta);

		lblRazaElegida = new JLabel("");
		lblRazaElegida.setBounds(286, 61, 90, 90);
		panel.add(lblRazaElegida);

		lblCastaElegida = new JLabel("");
		lblCastaElegida.setBounds(286, 190, 90, 90);
		panel.add(lblCastaElegida);

		comboRaza = new JComboBox<String>();
		comboRaza.setBounds(50, 86, 116, 22);
		panel.add(comboRaza);

		comboRaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cargarImagenesPersonaje(comboRaza.getSelectedItem().toString());
			}
		});

		comboCasta = new JComboBox<String>();
		comboCasta.setBounds(50, 216, 116, 22);
		panel.add(comboCasta);
		cargarCombo();

		JButton button = new JButton("Guardar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				/*
				 * CREO UN PERSONAJE CON LA RAZA QUE CORRESPONDA
				 * */
				if(comboRaza.getSelectedItem().toString().contentEquals("HUMANO")){
					personaje = new Humano(usuario.getNombre_usuario());
				} else if (comboRaza.getSelectedItem().toString().contentEquals("ORCO")){
					personaje = new Orco(usuario.getNombre_usuario());	
				} else if (comboRaza.getSelectedItem().toString().contentEquals("ELFO")){
					personaje = new Elfo(usuario.getNombre_usuario());	
				}
				
				
				p1.getCasta().setNombreCasta((String) comboBox2.getSelectedItem());
				p1.setFuerza(Integer.parseInt(vecFuerza[comboBox2.getSelectedIndex()]));
				p1.setDestreza(Integer.parseInt(vecDestreza[comboBox2.getSelectedIndex()]));
				p1.setInteligencia(Integer.parseInt(vecInteligencia[comboBox2.getSelectedIndex()]));
				semaforo.release();
			
				dispose();
//				guardarPersonaje();
			}
		});
		button.setBounds(213, 325, 107, 25);
		panel.add(button);
		
//		cargarDatos(p,pp);
		this.setVisible(true);
	}
	
	/**
	 * Create the frame.
	 */
	
	// Editar personaje ya creado
	public CrearPersonaje(final Paquete p, final Semaphore semaforo, final PaquetePersonaje pp) {
		setTitle("Seleccion de Personaje");
		setResizable(false);
//		personaje = new MensajePersonaje();
//		msj = new Mensaje();
//		gson = new Gson();
//		this.login = login;
//		this.menu = menu;
//		this.menu.setVisible(false);

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblSeleccioneSuPersonaje = new JLabel("Seleccione su personaje:");
		lblSeleccioneSuPersonaje.setBounds(50, 32, 153, 14);
		panel.add(lblSeleccioneSuPersonaje);

		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setBounds(50, 61, 46, 14);
		panel.add(lblRaza);

		JLabel lblCasta = new JLabel("Casta: ");
		lblCasta.setBounds(50, 190, 57, 14);
		panel.add(lblCasta);

		lblRazaElegida = new JLabel("");
		lblRazaElegida.setBounds(286, 61, 90, 90);
		panel.add(lblRazaElegida);

		lblCastaElegida = new JLabel("");
		lblCastaElegida.setBounds(286, 190, 90, 90);
		panel.add(lblCastaElegida);

		comboRaza = new JComboBox<String>();
		comboRaza.setBounds(50, 86, 116, 22);
		comboRaza.setSelectedItem(pp.getRaza());
		panel.add(comboRaza);

		comboRaza.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent evt) {
				cargarImagenesPersonaje(comboRaza.getSelectedItem().toString());
			}
		});

		comboCasta = new JComboBox<String>();
		comboCasta.setBounds(50, 216, 116, 22);
		comboCasta.setSelectedItem(pp.getCasta());
		panel.add(comboCasta);
		cargarCombo();

		JButton button = new JButton("Guardar");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				guardarPersonaje();
			}
		});
		button.setBounds(213, 325, 107, 25);
		panel.add(button);
		
//		cargarDatos(p,pp);
		this.setVisible(true);
	}

/*
	private void cargarDatos(Paquete p, PaquetePersonaje pp) {
//		msj.setId("obtenerPersonaje");
//		msj.setMensaje(this.login.getUsername());
//		this.login.enviarMensaje(msj);
//		String resp = this.login.leerRespuesta();
//		personaje = gson.fromJson(resp, MensajePersonaje.class);
		Personaje personaje = null;
		
		cargarImagenesPersonaje(pp.getRaza());
		if (pp.getCasta() != null && personaje.getRaza() != null) {
			nuevoPersonaje = false;
			comboCasta.setSelectedItem(pp.getCasta());
			comboRaza.setSelectedItem(pp.getRaza());
		}else{
			nuevoPersonaje = true;
		}
	}
*/
	private void cargarImagenesPersonaje(String raza) {
		if (raza != null && !raza.isEmpty()) {
			if (raza.contentEquals("HUMANO")) {
				lblRazaElegida.setIcon(new ImageIcon("src/main/resources/imagenes/personajes/humano_short.png"));
			} else if (raza.contentEquals("ELFO")) {
				lblRazaElegida.setIcon(new ImageIcon("src/main/resources/imagenes/personajes/elfo_short.png"));
			} else if (raza.contentEquals("ORCO")) {
				lblRazaElegida.setIcon(new ImageIcon("src/main/resources/imagenes/personajes/orco_short.png"));
			}
		}
	}
/*
	private void guardarPersonaje() {
		int razaItem = comboRaza.getSelectedIndex();
		int castaItem = comboCasta.getSelectedIndex();
		String resp = "";
		personaje = new MensajePersonaje(personaje.getIdUsuario(), login.getUsername(),
				comboRaza.getSelectedItem().toString(), comboCasta.getSelectedItem().toString());
		if (comboCompleto(razaItem, castaItem)) {
			if (nuevoPersonaje) {
				msj.setId("guardarPersonaje");
			} else {
				msj.setId("actualizarPersonaje");
			}
			msj.setMensaje(gson.toJson(personaje));
			this.login.enviarMensaje(msj);
			resp = this.login.leerRespuesta();
			if (resp.equals("OK")) {
				JOptionPane.showMessageDialog(null, "Personaje guardado correctamente", "Personaje Guardado",
						JOptionPane.INFORMATION_MESSAGE);
			} else {
				JOptionPane.showMessageDialog(null, "Error al intentar guadar personaje", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		}
	}
*/
	private boolean comboCompleto(int razaItem, int castaItem) {
		if (razaItem == 0) {
			JOptionPane.showMessageDialog(null, "Seleccione una Raza", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			if (castaItem == 0) {
				JOptionPane.showMessageDialog(null, "Seleccione una Casta", "Error", JOptionPane.ERROR_MESSAGE);
				return false;
			}
			return true;
		}
	}

	private void cargarCombo() {
		comboRaza.addItem("");
		comboRaza.addItem("ORCO");
		comboRaza.addItem("HUMANO");
		comboRaza.addItem("ELFO");
		comboCasta.addItem("");
		comboCasta.addItem("GUERRERO");
		comboCasta.addItem("HECHICERO");
		comboCasta.addItem("CHAMAN");
//		cargarPersonaje();
	}

	private void salir() {
		menu.setVisible(true);
		dispose();
	}
}
