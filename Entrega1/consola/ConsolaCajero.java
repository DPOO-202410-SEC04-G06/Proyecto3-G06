package consola;

import java.io.IOException;
import java.util.Date;

import javax.swing.JOptionPane;

import controlador.ControladorGaleria;
import pagos.*;
import usuarios.*;

public class ConsolaCajero extends ConsolaEmpleado 
{

	// ############################################ Atributos

	Cajero usuario;
	
	// ############################################ Constructor
	
	public ConsolaCajero(ControladorGaleria controladorGaleria)
	{
		super(controladorGaleria);
		this.usuario = (Cajero) controladorGaleria.usuarioDeLaSesion;
	}
	
	// ############################################ Métodos
	
	/**
	 * Verifica si la transacción va a ser procesada o no
	 * @param nextTransaccion
	 * @return La respuesta del usuario
	 */
	public boolean showTransaccion( Transaccion nextTransaccion )
	{
		if ( nextTransaccion == null )
		{
			return false;
		}
		int codigoTransaccion = nextTransaccion.getCodigoTransaccion();
		Date fechaTransaccion = nextTransaccion.getFecha();
		String nombrePieza = nextTransaccion.getNombrePieza();
		String nombreComprador = nextTransaccion.getNombreComprador();
		String nombreVendedor = nextTransaccion.getNombreVendedor();
		int precioTransaccion = nextTransaccion.getPrecio();

		String mensaje = String.format(
			"Codigo: %d\nFecha: %s\nPieza: %s\nComprador: %s\nVendedor: %s\nPrecio de venta: %d", 
			codigoTransaccion, fechaTransaccion, nombrePieza, nombreComprador, nombreVendedor, precioTransaccion
		);

		JOptionPane.showMessageDialog(null, mensaje, "Detalles de la Transacción", JOptionPane.INFORMATION_MESSAGE);

		return pedirConfirmacionAlUsuario("¿Desea confirmar esta transacción?");
	}

	/**
	 * Registra una nueva transacción en el sistema
	 * @throws IOException 
	 */
	public void registrarTransaccion() throws IOException
	{
		Transaccion nextTransaccion = usuario.nextTransaccionPendiente();

		if ( nextTransaccion != null )
		{
			boolean confirmacion = showTransaccion( nextTransaccion );
			
			if ( confirmacion )
			{
				usuario.registrarTransaccion(nextTransaccion, nextTransaccion.getFecha(), nextTransaccion.getCodigoTransaccion(), controladorGaleria.galeria.getPortalPagos());
				JOptionPane.showMessageDialog(null, "Registro exitoso.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
			}
			else
			{
				usuario.nuevaTransaccionPendiente(nextTransaccion);
				JOptionPane.showMessageDialog(null, "Operación cancelada.", "Cancelación", JOptionPane.INFORMATION_MESSAGE);
			}
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Operación no exitosa.", "Error", JOptionPane.ERROR_MESSAGE);
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}

	// ############################################ Run
	
	public void correrConsola() throws IOException
	{
		
		String[] opcionesMenuUsuario = { "Actualizar estado de una pieza", "Cambiar propietario de una pieza",
										"Buscar transacción", "Consultar pieza", "Consultar historial de una pieza",
										"Consultar historial de un artista", "Registrar transacción en el sistema",
										"Cerrar sesión"};
		
		int iInput = this.mostrarMenu("Menú de cajero. Bienvenido " + nombreUsuario, opcionesMenuUsuario);
		
		switch ( iInput )
		{
			case 1: // Actualizar estado
			{
				this.actualizarEstadoPieza();
				break;
			}
			
			case 2: // Cambiar propietario
			{
				this.cambiarPropietarioPieza();
				break;
			}
			
			case 3: // Buscar transacción
			{
				this.buscarTransaccion();
				break;
			}
			
			case 4: // Consultar pieza
			{
				this.consultarPieza();
				break;
			}
			
			case 5: // Consultar historial de una pieza
			{
				this.consultarHistorialPieza();
				break;
			}
			
			case 6: // Consultar historial de un artista
			{
				this.consultarHistorialArtista();
				break;
			}

			case 7: // Registrar transacción
			{
				registrarTransaccion();
				break;
			}
			
			case 8: // Cerrar sesión
			{
				controladorGaleria.cerrarSesion();
				break;
			}
			
			default:
			{
				correrConsola();
			}
		}
	}

}
