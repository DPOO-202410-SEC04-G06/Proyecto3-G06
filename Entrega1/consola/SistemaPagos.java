package consola;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.*;

// Interfaz para las pasarelas de pago
interface PasarelaPago {
    boolean procesarPago(String numeroTarjeta, String nombreTarjeta, double monto);
}

// Implementación de la pasarela PayPal
class PayPal implements PasarelaPago {
    public boolean procesarPago(String numeroTarjeta, String nombreTarjeta, double monto) {
        // Simulación de procesamiento de pago PayPal
        // Registro de transacción en archivo PayPal.log
        try {
            FileWriter writer = new FileWriter("PayPal.log", true);
            writer.write("Transacción PayPal: $" + monto + " - Tarjeta: " + numeroTarjeta + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true; // Simulación de éxito
    }
}

// Implementación de la pasarela PayU
class PayU implements PasarelaPago {
    public boolean procesarPago(String numeroTarjeta, String nombreTarjeta, double monto) {
        // Simulación de procesamiento de pago PayU
        // Registro de transacción en archivo PayU.txt
        try {
            FileWriter writer = new FileWriter("PayU.txt", true);
            writer.write("Transacción PayU: $" + monto + " - Tarjeta: " + numeroTarjeta + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true; // Simulación de éxito
    }
}

// Implementación de la pasarela Sire
class Sire implements PasarelaPago {
    public boolean procesarPago(String numeroTarjeta, String nombreTarjeta, double monto) {
        // Simulación de procesamiento de pago Sire
        // Registro de transacción en archivo Sire.json
        try {
            FileWriter writer = new FileWriter("Sire.json", true);
            writer.write("Transacción Sire: $" + monto + " - Tarjeta: " + numeroTarjeta + "\n");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return true; // Simulación de éxito
    }
}

// Clase principal que contiene la GUI
public class SistemaPagos extends JFrame {
    private JComboBox<String> pasarelasCombo;
    private JTextField numeroTarjetaField;
    private JTextField nombreTarjetaField;
    private JTextField montoField;

    public SistemaPagos() {
        setTitle("Sistema de Pagos");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        // Componentes de la GUI
        JLabel pasarelaLabel = new JLabel("Pasarela de Pago:");
        panel.add(pasarelaLabel);
        pasarelasCombo = new JComboBox<>();
        cargarPasarelas();
        panel.add(pasarelasCombo);

        JLabel numeroTarjetaLabel = new JLabel("Número de Tarjeta:");
        panel.add(numeroTarjetaLabel);
        numeroTarjetaField = new JTextField();
        panel.add(numeroTarjetaField);

        JLabel nombreTarjetaLabel = new JLabel("Nombre en la Tarjeta:");
        panel.add(nombreTarjetaLabel);
        nombreTarjetaField = new JTextField();
        panel.add(nombreTarjetaField);

        JLabel montoLabel = new JLabel("Monto a Pagar:");
        panel.add(montoLabel);
        montoField = new JTextField();
        panel.add(montoField);

        JButton pagarButton = new JButton("Pagar");
        pagarButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                procesarPago();
            }
        });
        panel.add(pagarButton);

        add(panel);
        setVisible(true);
    }

    // Método para cargar las pasarelas de pago desde el archivo de configuración
    private void cargarPasarelas() {
        try {
            File archivo = new File("configuracion.txt");
            Scanner scanner = new Scanner(archivo);
            while (scanner.hasNextLine()) {
                String nombreClase = scanner.nextLine();
                pasarelasCombo.addItem(nombreClase);
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    // Método para procesar el pago utilizando la pasarela seleccionada
    private void procesarPago() {
        String pasarelaSeleccionada = (String) pasarelasCombo.getSelectedItem();
        String numeroTarjeta = numeroTarjetaField.getText();
        String nombreTarjeta = nombreTarjetaField.getText();
        double monto = Double.parseDouble(montoField.getText());

        try {
            Class<?> pasarelaClass = Class.forName(pasarelaSeleccionada);
            PasarelaPago pasarelaPago = (PasarelaPago) pasarelaClass.getDeclaredConstructor().newInstance();
            boolean resultadoPago = pasarelaPago.procesarPago(numeroTarjeta, nombreTarjeta, monto);
            if (resultadoPago) {
                JOptionPane.showMessageDialog(this, "Pago realizado exitosamente.", "Pago Exitoso", JOptionPane.INFORMATION_MESSAGE);
            } else {
                JOptionPane.showMessageDialog(this, "Error al procesar el pago.", "Error de Pago", JOptionPane.ERROR_MESSAGE);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método principal
    public static void main(String[] args) {
        new SistemaPagos();
    }
}
