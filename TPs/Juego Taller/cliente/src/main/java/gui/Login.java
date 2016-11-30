package gui;

//import connection.Mensaje;
//import entities.Usuario;
//import utilities.Loggin;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import java.util.concurrent.Semaphore;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import cliente.Usuario;
import utilities.Loggin;
import javax.swing.ImageIcon;
import java.awt.Color;
import java.awt.SystemColor;

public class Login extends JFrame {

	private static final long serialVersionUID = -6471222468956323219L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField tpContrasena;
	private JLabel lblUsuario;
	private JLabel lblContrasena;
	private JButton btnIngresar;
	private Login login;
	private Socket cliente;
	private String username;
	private JButton btnConexion;
	private JLabel lblNewLabel;

	public Socket getCliente() {
		return cliente;
	}

	public Login(final Usuario usuario, final Semaphore semaforo,Splash s) {
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				usuario.setAccion("cerrar");
				semaforo.release();
				setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
				Loggin.getInstance().info("Cerrar sesion cliente");
			}
		});

		setTitle("BloodyWars - Login");
		setLogin(this);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 504, 275);
		contentPane = new JPanel();
		contentPane.setBackground(SystemColor.menu);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblUsuario = new JLabel("Usuario: ");
		lblUsuario.setForeground(new Color(255, 255, 255));
		lblUsuario.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
		
		lblUsuario.setBounds(7, 122, 96, 20);
		contentPane.add(lblUsuario);
		lblContrasena = new JLabel("Contrase\u00F1a: ");
		lblContrasena.setForeground(new Color(255, 255, 255));
		lblContrasena.setFont(new Font("OCR A Extended", Font.PLAIN, 11));
		lblContrasena.setBounds(7, 153, 96, 20);
		contentPane.add(lblContrasena);

		txtUsuario = new JTextField();
		txtUsuario.setFont(new Font("Tahoma", Font.PLAIN, 11));
		txtUsuario.setBounds(107, 122, 353, 20);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);

		tpContrasena = new JPasswordField();
		tpContrasena.setFont(new Font("Tahoma", Font.PLAIN, 11));
		tpContrasena.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent key) {
				if (key.getKeyChar() == key.VK_ENTER)
					try {
						if (camposCompletos()) {
							usuario.setAccion("login");
							usuario.setNombre_usuario(txtUsuario.getText());
							usuario.setPassword_usuario(tpContrasena.getText());
							usuario.setOpcion(1);
							semaforo.release();
							dispose();
						}
					} catch (Exception e) {
						Loggin.getInstance().error("Error al validar contrasena " + e.getMessage());
					}
			}
		});
		tpContrasena.setBounds(107, 153, 353, 20);
		contentPane.add(tpContrasena);

		btnIngresar = new JButton("Ingresar");
		btnIngresar.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		btnIngresar.setBounds(209, 204, 120, 21);
		btnIngresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (camposCompletos()) {
					usuario.setAccion("login");
					usuario.setNombre_usuario(txtUsuario.getText());
					usuario.setPassword_usuario(tpContrasena.getText());
					usuario.setOpcion(1);
					semaforo.release();
					dispose();
				}
			}
		});

		contentPane.add(btnIngresar);

		btnConexion = new JButton("Conexi\u00F3n");
		btnConexion.setEnabled(false);
		btnConexion.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		btnConexion.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
			}
		});

		btnConexion.setBounds(339, 203, 121, 23);
		contentPane.add(btnConexion);
		
		lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(Login.class.getResource("/imagenes/logo.png")));
		lblNewLabel.setBounds(79, 32, 360, 79);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setIcon(new ImageIcon(Login.class.getResource("/imagenes/bloody_wall.jpg")));
		lblNewLabel_1.setBounds(0, -23, 500, 534);
		contentPane.add(lblNewLabel_1);
		visible(true);

	}

	private boolean camposCompletos() {
		if (txtUsuario.getText().length() != 0 && tpContrasena.getPassword().length != 0) {
			return true;
		}
		JOptionPane.showMessageDialog(null, "Complete todos los campos", "Campo vacio", JOptionPane.WARNING_MESSAGE);
		return false;
	}

	public void setLogin(Login miLogin) {
		this.login = miLogin;
	}

	public void cancelar() {
		txtUsuario.setText("");
		tpContrasena.setText("");
		txtUsuario.grabFocus();
	}

	public void visible(boolean value) {
		this.setVisible(value);
	}

	public String getUsername() {
		return username.toUpperCase();
	}

	public void setUsername(String username) {
		this.username = username;
	}
}
