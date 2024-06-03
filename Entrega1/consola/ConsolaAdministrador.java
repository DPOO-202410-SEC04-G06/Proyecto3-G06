package consola;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import javax.swing.JOptionPane;
import controlador.ControladorGaleria;
import galeria.Inventario;
import pagos.Transaccion;
import piezas.Pieza;
import usuarios.*;

public class ConsolaAdministrador extends ConsolaEmpleado 
{
    // ############################################ Atributos

	Administrador usuario;
	
	// ############################################ Constructor
	
	public ConsolaAdministrador(ControladorGaleria controladorGaleria)
	{
		super(controladorGaleria);
		this.usuario = (Administrador) controladorGaleria.usuarioDeLaSesion;
	}
		
	// ############################################ Metodos

	/**
	 * Realiza una o varias verificaciones de usuario
	 * @throws IOException
	 */
	private void verificarUsuarios() throws IOException
	{
		String[] opciones = { "Verificar comprador", "Verificar vendedor", "Verificar usuario para subasta", "Salir" };

		int iInput = this.mostrarMenu( "Elija una opcion", opciones );

		switch ( iInput )
		{
			case 1: // Comprador
			{
				String nextComp = usuario.nextCompradorPendiente();

				if ( nextComp == null )
				{
					JOptionPane.showMessageDialog(null, "No hay compradores para verificar");
				}

				else
				{
					JOptionPane.showMessageDialog(null, "Usuario del siguiente comprador: " + nextComp);

					UsuarioCorriente comprador = controladorGaleria.galeria.buscarUsuarioCorrienteUsername(nextComp);

					JOptionPane.showMessageDialog(null, "Nombre: " + comprador.getNombre());
					JOptionPane.showMessageDialog(null, "Telefono: " + comprador.getTelefono());
					JOptionPane.showMessageDialog(null, "Estado de verificacion vendedor: " + comprador.isVerifVendedor());
					JOptionPane.showMessageDialog(null, "Estado de verificacion comprador: " + comprador.isVerifComprador());

					boolean confirmacion = this.pedirConfirmacionAlUsuario("Desea verificar este usuario?");

					if ( confirmacion )
					{
						int montoMax = this.pedirEnteroAlUsuario("Ingrese el monto maximo permitido para el usuario");
						usuario.verificarComprador( comprador, montoMax );
					}

				}
				controladorGaleria.salvarGaleria( controladorGaleria.galeria );
				correrConsola();
				break;
			}

			case 2: // Vendedor
			{
				String nextComp = usuario.nextVendedorPendiente();

				if ( nextComp == null )
				{
					JOptionPane.showMessageDialog(null, "No hay vendedores para verificar");
				}

				else
				{
					JOptionPane.showMessageDialog(null, "Usuario del siguiente vendedor: " + nextComp);

					UsuarioCorriente comprador = controladorGaleria.galeria.buscarUsuarioCorrienteUsername(nextComp);

					JOptionPane.showMessageDialog(null, "Nombre: " + comprador.getNombre());
					JOptionPane.showMessageDialog(null, "Telefono: " + comprador.getTelefono());
					JOptionPane.showMessageDialog(null, "Estado de verificacion vendedor: " + comprador.isVerifVendedor());
					JOptionPane.showMessageDialog(null, "Estado de verificacion comprador: " + comprador.isVerifComprador());

					boolean confirmacion = this.pedirConfirmacionAlUsuario("Desea verificar este usuario?");

					if ( confirmacion )
					{
						usuario.verificarVendedor(comprador);
					}

				}
				controladorGaleria.salvarGaleria( controladorGaleria.galeria );
				correrConsola();
				break;
			}

			case 3: // Subasta
			{
				String nextComp = usuario.nextOfertaSubasta();

				if ( nextComp == null )
				{
					JOptionPane.showMessageDialog(null, "No hay usuarios para verificar");
				}

				else
				{
					JOptionPane.showMessageDialog(null, "Usuario del ofertante: " + nextComp);

					UsuarioCorriente comprador = controladorGaleria.galeria.buscarUsuarioCorrienteUsername(nextComp);

					JOptionPane.showMessageDialog(null, "Nombre: " + comprador.getNombre());
					JOptionPane.showMessageDialog(null, "Telefono: " + comprador.getTelefono());
					JOptionPane.showMessageDialog(null, "Estado de verificacion vendedor: " + comprador.isVerifVendedor());
					JOptionPane.showMessageDialog(null, "Estado de verificacion comprador: " + comprador.isVerifComprador());

					boolean confirmacion = this.pedirConfirmacionAlUsuario("Desea verificar este usuario?");

					if ( confirmacion )
					{
						usuario.verificarOfertaSubasta(nextComp, (Operador)controladorGaleria.galeria.buscarEmpleadoUsername(nombreOperador));
					}

				}
				controladorGaleria.salvarGaleria( controladorGaleria.galeria );
				correrConsola();
				break;
			}

			case 4:
			{
				controladorGaleria.salvarGaleria( controladorGaleria.galeria );
				correrConsola();
				break;
			}
		}

		
	}

	/**
	 * 
	 * @throws IOException
	 */
	private void consultarHistorialVendedor() throws IOException
	{

		String username = this.pedirCadenaAlUsuario("Ingrese el nombre del vendedor");

		UsuarioCorriente usuario = controladorGaleria.galeria.buscarUsuarioCorrienteUsername(username);

		if ( usuario == null )
		{
			JOptionPane.showMessageDialog(null, "El usuario no fue encontrado");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Nombre: " + usuario.getNombre());
			JOptionPane.showMessageDialog(null, "Telefono: " + usuario.getTelefono());
			JOptionPane.showMessageDialog(null, "Estado de verificacion vendedor: " + usuario.isVerifVendedor());
			JOptionPane.showMessageDialog(null, "Estado de verificacion comprador: " + usuario.isVerifComprador());

			HashMap<Date, HashMap<String, Transaccion>> historialTransacciones = usuario.getHistorialPagosFecha();

			if ( historialTransacciones.isEmpty() )
			{
				JOptionPane.showMessageDialog(null, "El usuario no tiene transacciones");
			}
			else
			{
				JOptionPane.showMessageDialog(null, "El usuario tiene transacciones");
			}
		}

		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}

	/**
	 * 
	 * @throws IOException
	 */
	private void registrarEntrada() throws IOException
	{

		Pieza pieza = usuario.nextPIn();

		if ( pieza == null )
		{
			JOptionPane.showMessageDialog(null, "No hay piezas de salida");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Titulo: " + pieza.getTitulo());
			JOptionPane.showMessageDialog(null, "Precio de venta (-1 si no esta en venta): " + pieza.getPrecioVenta());
			JOptionPane.showMessageDialog(null, "Autores: " + pieza.getAutores());
			JOptionPane.showMessageDialog(null, "Año: " + pieza.getAnio());
			JOptionPane.showMessageDialog(null, "Ciudad: " + pieza.getCiudad());
			JOptionPane.showMessageDialog(null, "Pais: " + pieza.getCiudad());
			JOptionPane.showMessageDialog(null, "Estado: " + pieza.getEstado());
			JOptionPane.showMessageDialog(null, "Propietario actual: " + pieza.getPropietario());

			boolean confirmacion = this.pedirConfirmacionAlUsuario("Desea verificar la entrada de esta pieza?");

			if ( confirmacion )
			{
				int estado;
				
				String[] opcionesEstado = { "Bodega", "Exponer" };
				
				int iUpdateState = this.mostrarMenu( "Elija el nuevo estado de la pieza", opcionesEstado);
				
				if ( iUpdateState == 1 )
				{
					estado = Inventario.BODEGA;
				}
				else
				{
					estado = Inventario.EXPUESTA;
				}

				Date fechaSalida = usuario.getFechasSalidaPiezas().get( pieza.getTitulo() );

				usuario.agregarPieza(pieza, estado, fechaSalida, controladorGaleria.galeria.getInventarioPiezas(), controladorGaleria.galeria);
			}
		}
		
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}

	/**
	 * 
	 * @throws IOException
	 */
	private void registrarSalida() throws IOException
	{
		Pieza pieza = usuario.nextPOut();

		if ( pieza == null )
		{
			JOptionPane.showMessageDialog(null, "No hay piezas de salida");
		}
		else
		{
			JOptionPane.showMessageDialog(null, "Titulo: " + pieza.getTitulo());
			JOptionPane.showMessageDialog(null, "Precio de venta (-1 si no esta en venta): " + pieza.getPrecioVenta());
			JOptionPane.showMessageDialog(null, "Autores: " + pieza.getAutores());
			JOptionPane.showMessageDialog(null, "Año: " + pieza.getAnio());
			JOptionPane.showMessageDialog(null, "Ciudad: " + pieza.getCiudad());
			JOptionPane.showMessageDialog(null, "Pais: " + pieza.getCiudad());
			JOptionPane.showMessageDialog(null, "Estado: " + pieza.getEstado());
			JOptionPane.showMessageDialog(null, "Propietario actual: " + pieza.getPropietario());

			boolean confirmacion = this.pedirConfirmacionAlUsuario("Desea verificar la entrada de esta pieza?");

			if ( confirmacion )
			{
				usuario.registrarSalidaPieza( pieza.getTitulo(), controladorGaleria.galeria.getInventarioPiezas(), controladorGaleria.galeria );
			}
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}

	/**
	 * Registra una nueva adquisicion
	 * @throws IOException
	 */
	private void registrarAdquisicion() throws IOException
	{
		String titulo = usuario.nextAdquisicion();

		if ( titulo == null )
		{
			JOptionPane.showMessageDialog(null, "No hay piezas de salida");
		}
		else
		{
			Pieza pieza = controladorGaleria.galeria.consultarPiezaGaleria(titulo);
			JOptionPane.showMessageDialog(null, "Titulo: " + pieza.getTitulo());
			JOptionPane.showMessageDialog(null, "Precio de venta (-1 si no esta en venta): " + pieza.getPrecioVenta());
			JOptionPane.showMessageDialog(null, "Autores: " + pieza.getAutores());
			JOptionPane.showMessageDialog(null, "Año: " + pieza.getAnio());
			JOptionPane.showMessageDialog(null, "Ciudad: " + pieza.getCiudad());
			JOptionPane.showMessageDialog(null, "Pais: " + pieza.getCiudad());
			JOptionPane.showMessageDialog(null, "Estado: " + pieza.getEstado());
			JOptionPane.showMessageDialog(null, "Propietario actual: " + pieza.getPropietario());

			boolean confirmacion = this.pedirConfirmacionAlUsuario("Desea verificar la compra de esta pieza?");

			if ( confirmacion )
			{
				usuario.registrarAdquisicionPieza(titulo, controladorGaleria.galeria.getMapaUsuariosCorrientes(), controladorGaleria.galeria, controladorGaleria.galeria.getPortalPagos(), (Cajero)controladorGaleria.galeria.buscarEmpleadoUsername(nombreCajero));
			}
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	// ############################################ Run
	
	public void correrConsola( ) throws IOException
	{
		
		String[] opcionesMenuUsuario = { "Actualizar estado de una pieza", "Cambiar propietario de una pieza",
										"Buscar transaccion", "Consultar pieza", "Consultar historial de una pieza",
										"Consultar historial de un artista", "Verificar usuario(s)", "Consultar historial vendedor",
										"Registrar entrada de una pieza", "Registrar salida de una pieza", "Registrar nueva adquisicion", "Cerrar sesion"};
		
		int iInput = this.mostrarMenu( "Menu de administrador. Bienvenido " + nombreUsuario , opcionesMenuUsuario );
		
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

			case 7: // Verificar usuarios
			{
				verificarUsuarios();
				break;
			}

			case 8: // Consultar historial vendedor
			{
				consultarHistorialVendedor();
				break;
			}

			case 9: // Registrar entrada de una pieza
			{
				registrarEntrada();
				break;
			}

			case 10: // Registrar salida de una pieza
			{
				registrarSalida();
				break;
			}

			case 11:
			{
				registrarAdquisicion();
				break;
			}
			
			case 12: // Cerrar sesion
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
