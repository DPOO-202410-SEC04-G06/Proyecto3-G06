package interfaz;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;



public class PanelIngresar extends JPanel{
	
	// Atributos de la interfaz

	private JTextField login;
	private JTextField contraseña;
	
	public PanelIngresar() {
		IniciarRegistro();
		ConfigurarRegistro();
		AgregarRegistro();
	}

	private void IniciarRegistro() {;
		login = new JTextField(15);
		contraseña = new JTextField(15);
	}
	
	private void ConfigurarRegistro() {

		login.setEditable(true);
		contraseña.setEditable(true );	
		
	}
	
	private void AgregarRegistro() {

        add(new JLabel("Login:"));
        add(login);
        add(new JLabel("Contraseña:"));
        add(contraseña);
		
	}
}
