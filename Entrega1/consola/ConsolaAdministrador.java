package consola;

import java.io.IOException;

import controlador.ControladorGaleria;
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
	public void verificarUsuarios() throws IOException
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
					System.out.println( "No hay compradores para verificar" );
				}

				else
				{
					System.out.println( "Usuario del siguiente comprador: " + nextComp );

					UsuarioCorriente comprador = controladorGaleria.galeria.buscarUsuarioCorrienteUsername(nextComp);

					System.out.println( "Nombre: " + comprador.getNombre() );
					System.out.println( "Telefono: " + comprador.getTelefono() );
					System.out.println( "Estado de verificacion vendedor: " + comprador.isVerifVendedor() );
					System.out.println( "Estado de verificacion comprador: " + comprador.isVerifComprador() );

					boolean confirmacion = this.pedirConfirmacionAlUsuario("Desea verificar este usuario?");

					if ( confirmacion )
					{
						int montoMax = this.pedirEnteroAlUsuario( "Ingrese el monto maximo permitido para el usuario" );
						usuario.verificarComprador( comprador, montoMax );
					}

				}

				verificarUsuarios();
				break;
			}

			case 2: // Vendedor
			{
				String nextComp = usuario.nextVendedorPendiente();

				if ( nextComp == null )
				{
					System.out.println( "No hay vendedores para verificar" );
				}

				else
				{
					System.out.println( "Usuario del siguiente vendedor: " + nextComp );

					UsuarioCorriente comprador = controladorGaleria.galeria.buscarUsuarioCorrienteUsername(nextComp);

					System.out.println( "Nombre: " + comprador.getNombre() );
					System.out.println( "Telefono: " + comprador.getTelefono() );
					System.out.println( "Estado de verificacion vendedor: " + comprador.isVerifVendedor() );
					System.out.println( "Estado de verificacion comprador: " + comprador.isVerifComprador() );

					boolean confirmacion = this.pedirConfirmacionAlUsuario("Desea verificar este usuario?");

					if ( confirmacion )
					{
						usuario.verificarVendedor(comprador);
					}

				}
				verificarUsuarios();
				break;
			}

			case 3: // Subasta
			{
				String nextComp = usuario.nextOfertaSubasta();

				if ( nextComp == null )
				{
					System.out.println( "No hay usuarios para verificar" );
				}

				else
				{
					System.out.println( "Usuario del ofertante: " + nextComp );

					UsuarioCorriente comprador = controladorGaleria.galeria.buscarUsuarioCorrienteUsername(nextComp);

					System.out.println( "Nombre: " + comprador.getNombre() );
					System.out.println( "Telefono: " + comprador.getTelefono() );
					System.out.println( "Estado de verificacion vendedor: " + comprador.isVerifVendedor() );
					System.out.println( "Estado de verificacion comprador: " + comprador.isVerifComprador() );

					boolean confirmacion = this.pedirConfirmacionAlUsuario("Desea verificar este usuario?");

					if ( confirmacion )
					{
						usuario.verificarOfertaSubasta(nextComp, (Operador)controladorGaleria.galeria.buscarEmpleadoUsername(nombreOperador));
					}

				}
				verificarUsuarios();
				break;
			}

			case 4:
			{
				break;
			}
		}

		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void consultarHistorialVendedor() throws IOException
	{
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void registrarEntrada() throws IOException
	{
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}

	/**
	 * 
	 * @throws IOException
	 */
	public void registrarSalida() throws IOException
	{
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}
	
	// ############################################ Run
	
	public void correrConsola( ) throws IOException
	{
		
		String[] opcionesMenuUsuario = { "Actualizar estado de una pieza", "Cambiar propietario de una pieza",
										"Buscar transaccion", "Consultar pieza", "Consultar historial de una pieza",
										"Consultar historial de un artista", "Verificar usuario(s)", "Consultar historial vendedor",
										"Registrar entrada de una pieza", "Registrar salida de una pieza", "Cerrar sesion"};
		
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
			
			case 11: // Cerrar sesion
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
