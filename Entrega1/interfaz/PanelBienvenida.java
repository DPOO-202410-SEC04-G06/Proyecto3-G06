package interfaz;

import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



public class PanelBienvenida extends JPanel{
	
	// Atributos de la interfaz
	
	private JButton ingresar;
	private JButton registrar;
	private JButton salir;
	
	public PanelBienvenida(){
		setLayout(new GridLayout(3, 1, 10, 10)); // Ajusta el GridLayout con 4 filas, 1 columna, y espacios
        JPanel PanelBienvenida = new JPanel();
        PanelBienvenida.setLayout(null); // Usa un layout manager nulo
        
        //Boton Ingresar
        ingresar = new JButton("Ingresar");
        ingresar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazIngresar ventanaIngreso  = new InterfazIngresar();
				ventanaIngreso.setVisible(true);
			} 	
        }); 
        add(ingresar);
        
        //Boton Registrar
        registrar = new JButton("Registrar");
        registrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				InterfazRegistrar ventanaRegistro  = new InterfazRegistrar();
				ventanaRegistro.setVisible(true);
				

				
			}
        	
        });
        add(registrar);
        
        //Boton Salir
        salir = new JButton("salir");
        salir.addActionListener(new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		System.exit(0);
        	} 	
        });
        add(salir);
		
	}


}
