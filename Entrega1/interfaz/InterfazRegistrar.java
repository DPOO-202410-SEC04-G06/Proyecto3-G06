package interfaz;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionListener;

public class InterfazRegistrar extends JFrame {
	
	
	private PanelRegistrar PR;
	
	FondoPanel fondo = new FondoPanel();
	
	public InterfazRegistrar() {
		this.setContentPane(fondo);
        setTitle("REGISTRO");
        setSize(700, 600); // Tamaño general de la ventana
        setLayout(null); // Usando layout nulo para control preciso
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
	
        // Crear panel bienvenida
        
        PR = new PanelRegistrar();
        PR.setBackground(new Color(0, 0, 0, 0));
        PR.setBounds(243, 130, 200, 600);
        add(PR);
		
		
	}
	
	   public static void main(String[] args) {
           new InterfazRegistrar().setVisible(true);
           
	   }
	   
	   class FondoPanel extends JPanel{
		   private Image imagen;
		   public void paint(Graphics g) {
			   imagen = new ImageIcon(getClass().getResource("/imagenes/GaleriaRegistro.png")).getImage();
			   g.drawImage(imagen, 0, 0, getWidth(), getHeight(), this);
			   setOpaque(false);
			   super.paint(g);
		   }
	
	   }
}
