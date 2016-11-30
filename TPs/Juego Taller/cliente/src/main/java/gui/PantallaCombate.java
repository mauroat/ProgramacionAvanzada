package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.google.gson.Gson;

import castas.*;
import cliente.MensajePersonaje;
import dominio.Personaje;
import juego.Juego;
import razas.*;
import utilities.Loggin;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.awt.event.ActionEvent;
import java.awt.Color;
import javax.swing.JTextField;

public class PantallaCombate extends JFrame {

	private static final long serialVersionUID = 7687024501999321838L;
	private JPanel contentPane;
	private Personaje p1, pEnemigo;
	private JTextField textFieldVida;
	private JTextField textFieldEnergia;
	private JTextField textFieldVidaEnemigo;
	private JTextField textFieldEnergiaEnemigo;
	private final Gson gson = new Gson();
	
/*
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PantallaCombate frame = new PantallaCombate(juego, mp);
					frame.setVisible(true);
				} catch (Exception e) {
					Loggin.getInstance().error("Error iniciar combate "+e.getMessage());
				}
			}
		});
	}
*/
	/**
	 * Create the frame.
	 */
	public PantallaCombate(final Juego juego, MensajePersonaje mp) {
		
		// CREO MI PERSONAJE
		this.setTitle("BloodyWars - ¡Combate!");
		if(juego.getPersonaje().getRaza().equals("ORCO")){
			p1 = new Orco(juego.getPersonaje().getIdPersonaje(),juego.getPersonaje().getNick());
		} else if(juego.getPersonaje().getRaza().equals("HUMANO")){
			p1 = new Humano(juego.getPersonaje().getIdPersonaje(),juego.getPersonaje().getNick());
		} else {
			p1 = new Elfo(juego.getPersonaje().getIdPersonaje(),juego.getPersonaje().getNick());
		}
		
		if(juego.getPersonaje().getCasta().equals("GUERRERO")){
			p1.setClase(new Guerrero());
		} else if(juego.getPersonaje().getCasta().equals("HECHICERO")){
			p1.setClase(new Hechicero());
		} else {
			p1.setClase(new Chaman());
		}
		
		// CREO EL OTRO PERSONAJE
		
		if(mp.getRaza().equals("ORCO")){
			pEnemigo = new Orco(mp.getIdPersonaje(),mp.getNick());
		} else if(mp.getRaza().equals("HUMANO")){
			pEnemigo = new Humano(mp.getIdPersonaje(),mp.getNick());
		} else {
			pEnemigo = new Elfo(mp.getIdPersonaje(),mp.getNick());
		}
				
		if(mp.getCasta().equals("GUERRERO")){
			pEnemigo.setClase(new Guerrero());
		} else if(mp.getCasta().equals("HECHICERO")){
			pEnemigo.setClase(new Hechicero());
		} else {
			pEnemigo.setClase(new Chaman());
		}
		
		
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 689, 398);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new BorderLayout(0, 0));
		
		JButton btnAtacar = new JButton("ATACAR!");
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if(pEnemigo.estaVivo() && p1.puedeAtacar()){
					p1.atacar(pEnemigo);									
				} 
				else if(pEnemigo.estaVivo() && !p1.puedeAtacar()){
					p1.setEnergia(p1.getEnergia()+25);					
				}
				
				if(p1.estaVivo() && pEnemigo.puedeAtacar()){
					pEnemigo.atacar(p1);
									
				} else if(p1.estaVivo() && !pEnemigo.puedeAtacar()){
					pEnemigo.setEnergia(pEnemigo.getEnergia()+25);					
				}
				
				textFieldVidaEnemigo.setText(String.valueOf(pEnemigo.getVida()));	
				textFieldEnergia.setText(String.valueOf(p1.getEnergia()));
				
				try {
					TimeUnit.SECONDS.sleep(2);
				} catch (InterruptedException e) {
					Loggin.getInstance().error(e.getMessage());
				}
				
				textFieldEnergiaEnemigo.setText(String.valueOf(pEnemigo.getEnergia()));
				textFieldVida.setText(String.valueOf(p1.getVida()));
				
				if(p1.getEnergia() < p1.calcularPuntosDeAtaque()){
					btnAtacar.setEnabled(false);
				}
				
				gson.toJson(p1.getIdPersonaje(),Integer.class);
				if(!pEnemigo.estaVivo()){
					JOptionPane.showMessageDialog(null, "El combate finalizó: usted fue el ganador!");
					juego.getPersonaje().setPosX(0);
					juego.getPersonaje().setPosY(1536);
					juego.getPersonaje().setEnCombate(false);
					dispose();
				}
					
				if(!p1.estaVivo()){
					// idem pero conmigo
					JOptionPane.showMessageDialog(null, "El combate finalizó: usted fue derrotado!");
					juego.getPersonaje().setPosX(0);
					juego.getPersonaje().setPosY(1536);
					juego.getPersonaje().setEnCombate(false);
					dispose();
				}
			}
		});
		btnAtacar.setFont(new Font("OCR A Extended", Font.PLAIN, 16));
		contentPane.add(btnAtacar, BorderLayout.EAST);
		
		JButton btnNewButton_1 = new JButton("Pasar turno");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
//				deshabilito el boton de atacar y aviso al servidor
				p1.serEnergizado();
				btnAtacar.setEnabled(false);
			}
		});
		btnNewButton_1.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		contentPane.add(btnNewButton_1, BorderLayout.SOUTH);
		
		JPanel panel = new JPanel();
		contentPane.add(panel, BorderLayout.CENTER);
		panel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(p1.getNombre());
		lblNewLabel.setForeground(Color.GREEN);
		lblNewLabel.setFont(new Font("OCR A Extended", Font.BOLD, 16));
		lblNewLabel.setBounds(20, 21, 171, 45);
		panel.add(lblNewLabel);

		
		JLabel lblNewLabel_1 = new JLabel(pEnemigo.getNombre());
		lblNewLabel_1.setForeground(Color.CYAN);
		lblNewLabel_1.setFont(new Font("OCR A Extended", Font.BOLD, 16));
		lblNewLabel_1.setBounds(407, 21, 141, 45);
		panel.add(lblNewLabel_1);
		
		JLabel lblVida = new JLabel("Vida: ");
		lblVida.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblVida.setForeground(Color.RED);
		lblVida.setBounds(20, 77, 75, 14);
		panel.add(lblVida);
		
		JLabel labelVidaEnemigo = new JLabel("Vida: ");
		labelVidaEnemigo.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		labelVidaEnemigo.setForeground(Color.RED);
		labelVidaEnemigo.setBounds(381, 77, 72, 14);
		panel.add(labelVidaEnemigo);
		
		JLabel lblEnergia = new JLabel("Energia:");
		lblEnergia.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		lblEnergia.setForeground(Color.RED);
		lblEnergia.setBounds(20, 102, 64, 14);
		panel.add(lblEnergia);
		
		JLabel labelEnergiaEnemigo = new JLabel("Energia:");
		labelEnergiaEnemigo.setFont(new Font("OCR A Extended", Font.PLAIN, 13));
		labelEnergiaEnemigo.setForeground(Color.RED);
		labelEnergiaEnemigo.setBounds(381, 102, 72, 14);
		panel.add(labelEnergiaEnemigo);
		
		JLabel lblAvataryo = new JLabel();
		
		lblAvataryo.setBounds(33, 165, 158, 101);
		panel.add(lblAvataryo);
		lblAvataryo.setVisible(true);
		
		
		JLabel lblAvatarEnemigo = new JLabel("");
		lblAvatarEnemigo.setBounds(381, 165, 157, 101);
		panel.add(lblAvatarEnemigo);
		lblAvatarEnemigo.setVisible(true);
		
		textFieldVida = new JTextField();
		textFieldVida.setBounds(105, 75, 86, 20);
		panel.add(textFieldVida);
		textFieldVida.setColumns(10);
		textFieldVida.setEditable(false);
		
		textFieldEnergia = new JTextField();
		textFieldEnergia.setBounds(105, 100, 86, 20);
		panel.add(textFieldEnergia);
		textFieldEnergia.setColumns(10);
		textFieldEnergia.setEditable(false);
		
		textFieldVidaEnemigo = new JTextField();
		textFieldVidaEnemigo.setBounds(452, 74, 86, 20);
		panel.add(textFieldVidaEnemigo);
		textFieldVidaEnemigo.setColumns(10);
		textFieldVidaEnemigo.setEditable(false);
		
		textFieldEnergiaEnemigo = new JTextField();
		textFieldEnergiaEnemigo.setBounds(452, 99, 86, 20);
		panel.add(textFieldEnergiaEnemigo);
		textFieldEnergiaEnemigo.setColumns(10);
		textFieldEnergiaEnemigo.setEditable(false);
		
		textFieldVida.setText(String.valueOf(p1.getVida()));
		textFieldVidaEnemigo.setText(String.valueOf(pEnemigo.getVida()));
		textFieldEnergia.setText(String.valueOf(p1.getEnergia()));
		textFieldEnergiaEnemigo.setText(String.valueOf(pEnemigo.getEnergia()));
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setIcon(new ImageIcon(Login.class.getResource("/imagenes/fondoPelea.jpg")));
		lblNewLabel_2.setBounds(0, 0, 652, 333);
		panel.add(lblNewLabel_2);
		if(p1.getRaza().equals("ORCO")){
			lblAvataryo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/personajes/orco_short.png")));
		} else if (p1.getRaza().equals("ELFO")){
			lblAvataryo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/personajes/elfo_short.png")));
		} else if (p1.getRaza().equals("HUMANO")){
			lblAvataryo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/personajes/humano_short.png")));
		}
		if(pEnemigo.getRaza().equals("ORCO")){
			lblAvatarEnemigo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/personajes/orco_short.png")));
		} else if (pEnemigo.getRaza().equals("ELFO")){
			lblAvatarEnemigo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/personajes/elfo_short.png")));
		} else if (pEnemigo.getRaza().equals("HUMANO")){
			lblAvatarEnemigo.setIcon(new ImageIcon(Login.class.getResource("/imagenes/personajes/humano_short.png")));
		}

	}
}
