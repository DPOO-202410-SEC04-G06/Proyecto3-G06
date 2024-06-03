package interfaz;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class InterfazIngresar extends JFrame {

	private PanelIngresar PI;

	FondoPanel fondo = new FondoPanel();

	public InterfazIngresar() {
		this.setContentPane(fondo);
		// setLayout(new BorderLayout());
		setTitle("INGRESAR");
		setSize(700, 600); // Tamaño general de la ventana
		setLayout(null); // Usando layout nulo para control preciso
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Crear panel bienvenida

		PI = new PanelIngresar();
		PI.setBackground(new Color(0, 0, 0, 0));
		PI.setBounds(246, 230, 200, 800);
		add(PI);

	}

	public static void main(String[] args) {
		new InterfazIngresar().setVisible(true);

	}

	class FondoPanel extends JPanel {
		private Image imagen;

		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("/imagenes/GaleriaIngresar.png")).getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}
	}
}
