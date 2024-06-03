package interfaz;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;
//import java.awt.ImagenIcon;

public class InterfazBienvenida extends JFrame {

	private PanelBienvenida PB;

	FondoPanel fondo = new FondoPanel();

	public InterfazBienvenida() {
		this.setContentPane(fondo);
		// setLayout(new BorderLayout());
		setTitle("GALERIA DE ARTE");
		setSize(700, 600); // Tamaño general de la ventana
		setLayout(null); // Usando layout nulo para control preciso
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Crear panel bienvenida
		PB = new PanelBienvenida();
		PB.setBackground(new Color(0, 0, 0, 0));
		PB.setBounds(250, 300, 200, 200);
		add(PB);
	}

	public static void main(String[] args) {
		new InterfazBienvenida().setVisible(true);

	}

	class FondoPanel extends JPanel {
		private Image imagen;

		public void paint(Graphics g) {
			imagen = new ImageIcon(getClass().getResource("/imagenes/GaleriaBienvenidos.png")).getImage();
			g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			setOpaque(false);
			super.paint(g);
		}

	}
}
