package interfaz;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Date;

import java.io.IOException;
import java.util.*;

import controlador.ControladorGaleria;
import pagos.PortalPagos;
import pagos.Transaccion;
import piezas.*;
import usuarios.*;


public class InterfazUsuarioCorriente extends JFrame {
    private ControladorGaleria controladorGaleria;
    private UsuarioCorriente usuario;
    
    public InterfazUsuarioCorriente(ControladorGaleria controladorGaleria) {
        this.controladorGaleria = controladorGaleria;
        this.usuario = (UsuarioCorriente) controladorGaleria.usuarioDeLaSesion;
        
        setTitle("Interfaz de Usuario Corriente - Bienvenido " + usuario.getNombre());
        setSize(800, 600);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        initUI();
    }

    private void initUI() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(4, 2)); // Configurar según necesidad

        // Botón para consignar una pieza
        JButton btnConsignarPieza = new JButton("Consignar Pieza");
        btnConsignarPieza.addActionListener(e -> consignarPieza());
        mainPanel.add(btnConsignarPieza);

        // Botón para comprar pieza
        JButton btnComprarPieza = new JButton("Comprar Pieza");
        btnComprarPieza.addActionListener(e -> comprarPieza());
        mainPanel.add(btnComprarPieza);

        // Botón para realizar oferta
        JButton btnRealizarOferta = new JButton("Realizar Oferta");
        btnRealizarOferta.addActionListener(e -> realizarOferta());
        mainPanel.add(btnRealizarOferta);

        // Botón para consultar pieza
        JButton btnConsultarPieza = new JButton("Consultar Pieza");
        btnConsultarPieza.addActionListener(e -> consultarPieza());
        mainPanel.add(btnConsultarPieza);

        // Botón para consultar historial de pieza
        JButton btnConsultarHistorialPieza = new JButton("Consultar Historial Pieza");
        btnConsultarHistorialPieza.addActionListener(e -> consultarHistorialPieza());
        mainPanel.add(btnConsultarHistorialPieza);

        // Botón para consultar historial artista
        JButton btnConsultarHistorialArtista = new JButton("Consultar Historial Artista");
        btnConsultarHistorialArtista.addActionListener(e -> consultarHistorialArtista());
        mainPanel.add(btnConsultarHistorialArtista);

        // Botón para cambiar método de pago
        JButton btnCambiarMetodoPago = new JButton("Cambiar Método de Pago");
        btnCambiarMetodoPago.addActionListener(e -> cambiarMetodoPago());
        mainPanel.add(btnCambiarMetodoPago);

        // Botón para cerrar sesión
        JButton btnCerrarSesion = new JButton("Cerrar Sesión");
        btnCerrarSesion.addActionListener(e -> cerrarSesion());
        mainPanel.add(btnCerrarSesion);

        add(mainPanel, BorderLayout.CENTER);
    }

    private void consignarPieza() {
        // Panel para los campos de entrada
        JPanel panel = new JPanel(new GridLayout(0, 2, 2, 2));
        JTextField tituloField = new JTextField(10);
        JTextField tipoField = new JTextField(10);
        JTextField autorField = new JTextField(10);
        JTextField anioField = new JTextField(10);
        JTextField ciudadField = new JTextField(10);
        JTextField paisField = new JTextField(10);
        JTextField precioField = new JTextField(10);
        JTextField añoSalidaField = new JTextField(4);
        JTextField mesSalidaField = new JTextField(2);
        JTextField diaSalidaField = new JTextField(2);

        panel.add(new JLabel("Título:"));
        panel.add(tituloField);
        panel.add(new JLabel("Tipo (Cuadro, Escultura, etc.):"));
        panel.add(tipoField);
        panel.add(new JLabel("Autor(es):"));
        panel.add(autorField);
        panel.add(new JLabel("Año:"));
        panel.add(anioField);
        panel.add(new JLabel("Ciudad:"));
        panel.add(ciudadField);
        panel.add(new JLabel("País:"));
        panel.add(paisField);
        panel.add(new JLabel("Precio (dejar en blanco si no está a la venta):"));
        panel.add(precioField);
        panel.add(new JLabel("Año de salida:"));
        panel.add(añoSalidaField);
        panel.add(new JLabel("Mes de salida:"));
        panel.add(mesSalidaField);
        panel.add(new JLabel("Día de salida:"));
        panel.add(diaSalidaField);

        int result = JOptionPane.showConfirmDialog(null, panel, "Consignar Nueva Pieza",
            JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String titulo = tituloField.getText();
            String tipo = tipoField.getText();
            ArrayList<String> autores = new ArrayList<>(Arrays.asList(autorField.getText().split(",\\s*")));
            String anio = anioField.getText();
            String ciudad = ciudadField.getText();
            String pais = paisField.getText();
            int precio = precioField.getText().isEmpty() ? -1 : Integer.parseInt(precioField.getText());
            int añoSalida = Integer.parseInt(añoSalidaField.getText());
            int mesSalida = Integer.parseInt(mesSalidaField.getText());
            int diaSalida = Integer.parseInt(diaSalidaField.getText());

            Date fechaSalida = new Date(añoSalida - 1900, mesSalida - 1, diaSalida);

            Administrador admin = (Administrador) controladorGaleria.galeria.buscarEmpleadoUsername(controladorGaleria.galeria.nombreAdministrador);

            // Supongamos que Pieza es una clase que se debe instanciar aquí
            Pieza nuevaPieza = usuario.nuevaPiezaParaConsignar(titulo, tipo, autores, anio, ciudad, pais, null);
            usuario.consignarPieza(nuevaPieza, precio, fechaSalida, admin);
            JOptionPane.showMessageDialog(this, "Pieza consignada correctamente", "Consignación Exitosa", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private void comprarPieza() {
        // Implementar diálogo para comprar pieza
    }

    private void realizarOferta() {
        // Implementar diálogo para realizar oferta
    }

    private void consultarPieza() {
        // Implementar diálogo para consultar detalles de pieza
    }

    private void consultarHistorialPieza() {
        // Implementar diálogo para consultar historial de pieza
    }

    private void consultarHistorialArtista() {
        // Implementar diálogo para consultar historial de artista
    }

    private void cambiarMetodoPago() {
        // Implementar diálogo para cambiar método de pago
    }

    private void cerrarSesion() {
        controladorGaleria.cerrarSesion();
        dispose();
        JOptionPane.showMessageDialog(null, "Sesión cerrada correctamente.");
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            new InterfazUsuarioCorriente(new ControladorGaleria()).setVisible(true);
        });
    }
}
