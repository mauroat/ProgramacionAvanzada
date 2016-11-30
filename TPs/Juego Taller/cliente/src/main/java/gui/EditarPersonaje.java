package gui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.Semaphore;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;

//import connection.Mensaje;
//import entities.MensajePersonaje;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Cliente;
import cliente.Mensaje;
import cliente.MensajePersonaje;
import utilities.Loggin;
import java.awt.Color;
import java.awt.Font;

public class EditarPersonaje extends JFrame {

	private static final long serialVersionUID = 4086294890156447655L;
	private JPanel contentPane;
	private MenuPrincipal menu;
	private JComboBox<String> comboRaza;
	private JComboBox<String> comboCasta;
	private JLabel lblRazaElegida;
	private JLabel lblCastaElegida;
	private MenuPrincipal mp;
	private Gson gson;

	public EditarPersonaje(final Mensaje p, final Semaphore semaforo, final MensajePersonaje pp, MenuPrincipal menuAnt,
			final Cliente cli) {
		setTitle("Seleccion de Personaje");
		setResizable(false);
		mp = menuAnt;
		setBounds(100, 100, 600, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		gson = new Gson();

		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dispose();
				mp.setVisible(true);
			}
		});

		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);

		JLabel lblSeleccioneSuPersonaje = new JLabel("Seleccione su personaje:");
		lblSeleccioneSuPersonaje.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		lblSeleccioneSuPersonaje.setForeground(Color.RED);
		lblSeleccioneSuPersonaje.setBounds(50, 32, 216, 14);
		panel.add(lblSeleccioneSuPersonaje);

		JLabel lblRaza = new JLabel("Raza");
		lblRaza.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		lblRaza.setForeground(Color.RED);
		lblRaza.setBounds(50, 61, 46, 14);
		panel.add(lblRaza);

		JLabel lblCasta = new JLabel("Casta: ");
		lblCasta.setFont(new Font("OCR A Extended", Font.PLAIN, 14));
		lblCasta.setForeground(Color.RED);
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
				if (comboRaza.getSelectedItem() != null) {
					cargarImagenesPersonaje(comboRaza.getSelectedItem().toString());
				}
			}
		});

		comboCasta = new JComboBox<String>();
		comboCasta.setBounds(50, 216, 116, 22);
		comboCasta.setSelectedItem(pp.getCasta());
		panel.add(comboCasta);
		cargarCombo();
		JButton button = new JButton("Guardar");
		button.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		button.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent arg0) {
				p.setComando("actualizarPersonaje");
				guardarPersonaje(pp);
				p.setMensaje((gson).toJson(pp));
				try {
					cli.getSalida().writeObject(gson.toJson(p));
					Mensaje resp=gson.fromJson((String) cli.getEntrada().readObject(), Mensaje.class);
					if(resp.getMensaje().equals("1")){
						JOptionPane.showMessageDialog(null, "Personaje actualizado", "", JOptionPane.INFORMATION_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null, "Error al actualizar personaje", "Error", JOptionPane.ERROR_MESSAGE);
					}
				} catch (Exception e) {
					Loggin.getInstance().error("Error actualizarPersonaje"+e.getMessage());
				}
			}
		});
		button.setBounds(213, 325, 107, 25);
		panel.add(button);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/bloody_wall.jpg")));
		lblNewLabel_1.setBounds(0, 0, 584, 454);
		panel.add(lblNewLabel_1);

		cargarDatos(pp);
		this.setVisible(true);
	}

	private void cargarDatos(MensajePersonaje pp) {
		if (pp != null) {
			comboCasta.setSelectedItem(pp.getCasta());
			comboRaza.setSelectedItem(pp.getRaza());
			cargarImagenesPersonaje(pp.getRaza());
		}
	}

	private void cargarImagenesPersonaje(String raza) {
		if (raza != null && !raza.isEmpty()) {
			if (raza.contentEquals("HUMANO")) {
				lblRazaElegida.setIcon(new ImageIcon("resources/imagenes/personajes/humano_short.png"));
			} else if (raza.contentEquals("ELFO")) {
				lblRazaElegida.setIcon(new ImageIcon("resources/imagenes/personajes/elfo_short.png"));
			} else if (raza.contentEquals("ORCO")) {
				lblRazaElegida.setIcon(new ImageIcon("resources/imagenes/personajes/orco_short.png"));
			}
		}
	}

	private void guardarPersonaje(MensajePersonaje pp) {
		int razaItem = comboRaza.getSelectedIndex();
		int castaItem = comboCasta.getSelectedIndex();
		if (comboCompleto(razaItem, castaItem)) {
			pp.setCasta(comboCasta.getSelectedItem().toString());
			pp.setRaza(comboRaza.getSelectedItem().toString());
		}
	}

	private boolean comboCompleto(int razaItem, int castaItem) {
		if (razaItem == 0 || razaItem == -1) {
			JOptionPane.showMessageDialog(null, "Seleccione una Raza", "Error", JOptionPane.ERROR_MESSAGE);
			return false;
		} else {
			if (castaItem == 0 || castaItem == -1) {
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
	}
}
