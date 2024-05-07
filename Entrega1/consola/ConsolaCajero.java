package consola;

import java.io.IOException;
import java.util.Date;

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
	
	// ############################################ Metodos
	
	/**
	 * Verifica si la transaccion va a ser procesada o no
	 * @param nextTransaccion
	 * @return La respuesta del usuario
	 */
	public boolean showTransaccion( Transaccion nextTransaccion )
	{
		int codigoTransaccion = nextTransaccion.getCodigoTransaccion();
		Date fechaTransaccion = nextTransaccion.getFecha();
		String nombrePieza = nextTransaccion.getNombrePieza();
		String nombreComprador = nextTransaccion.getNombreComprador();
		String nombreVendedor = nextTransaccion.getNombreVendedor();
		int precioTransaccion = nextTransaccion.getPrecio();

		System.out.println( "\nCodigo: " + codigoTransaccion );
		System.out.println( "Fecha: " + fechaTransaccion );
		System.out.println( "Pieza: " + nombrePieza );
		System.out.println( "Comprador: " + nombreComprador );
		System.out.println( "Vendedor: " + nombreVendedor );
		System.out.println( "Precio de venta: " + precioTransaccion );

		return pedirConfirmacionAlUsuario( "Desea confirmar esta transaccion?" );

	}

	/**
	 * Registra una nueva transaccion en el sistema
	 * @throws IOException 
	 */
	public void registrarTransaccion() throws IOException
	{
		Transaccion nextTransaccion = usuario.nextTransaccionPendiente();

		boolean confirmacion = showTransaccion( nextTransaccion );
		
		if ( confirmacion )
		{
			usuario.registrarTransaccion(nextTransaccion, nextTransaccion.getFecha(), nextTransaccion.getCodigoTransaccion(), controladorGaleria.galeria.getPortalPagos());
			System.out.println("Registro exitoso.");
		}
		else
		{
			usuario.nuevaTransaccionPendiente(nextTransaccion);
			System.out.println("Operacion cancelada");
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}

	// ############################################ Run
	
	public void correrConsola( ) throws IOException
	{
		
		String[] opcionesMenuUsuario = { "Actualizar estado de una pieza", "Cambiar propietario de una pieza",
										"Buscar transaccion", "Consultar pieza", "Consultar historial de una pieza",
										"Consultar historial de un artista", "Registrar transaccion en el sistema",
										"Cerrar sesion"};
		
		int iInput = this.mostrarMenu( "Menu de cajero. Bienvenido " + nombreUsuario , opcionesMenuUsuario );
		
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
			
			case 3: // Buscar transaccion
			{
				this.buscarTransaccion();
				break;
			}
			
			case 4: // consultar pieza
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

			case 7: // Registrar transaccion
			{
				registrarTransaccion();
				break;
			}
			
			case 8: // Cerrar sesion
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
