package consola;

import java.io.IOException;

import controlador.ControladorGaleria;
import piezas.Pieza;
import usuarios.*;

public class ConsolaOperador extends ConsolaEmpleado 
{

	// ############################################ Atributos

	Operador usuario;
	
	// ############################################ Constructor
	
	public ConsolaOperador(ControladorGaleria controladorGaleria)
	{
		super(controladorGaleria);
		this.usuario = (Operador) controladorGaleria.usuarioDeLaSesion;
	}
	
	// ############################################ Metodos
	
	/**
	 * Inicia una nueva subasta
	 * @throws IOException
	 */
	public void nuevaSubasta() throws IOException
	{

		String nombrePieza = this.pedirCadenaAlUsuario( "Ingrese el nombre de la pieza");

		Pieza pieza = controladorGaleria.galeria.consultarPiezaGaleria( nombrePieza );

		if ( pieza == null )
		{
			System.out.println( "La pieza no fue encontrada, intente de nuevo" );
			correrConsola();
		}

		int precioInicial = this.pedirEnteroAlUsuario("Ingrese el precio inicial");
		int precioMinimo = this.pedirEnteroAlUsuario("Ingrese el precio mínimo");

		boolean resultadoOperacion = usuario.nuevaSubasta( nombrePieza, pieza, precioInicial, precioMinimo, controladorGaleria.galeria.getInventarioPiezas());

		if ( resultadoOperacion )
		{
			System.out.println( "Operacion realizada exitosamente" );
		}
		else
		{
			System.out.println( "Hubo un error al iniciar la subasta" );
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}

	/**
	 * Finaliza una subasta existente
	 * @throws IOException
	 */
	public void finalizarSubasta() throws IOException
	{
		String nombrePieza = this.pedirCadenaAlUsuario( "Ingrese el nombre de la pieza");

		Pieza pieza = controladorGaleria.galeria.consultarPiezaGaleria( nombrePieza );

		if ( pieza == null )
		{
			System.out.println( "La pieza no fue encontrada, intente de nuevo" );
			correrConsola();
		}

		boolean resultadoOperacion = usuario.finalizarSubasta(nombrePieza, pieza, controladorGaleria.galeria.getInventarioPiezas(), (Cajero)controladorGaleria.galeria.buscarEmpleadoUsername(nombreCajero), controladorGaleria.galeria.getPortalPagos(), (Administrador)controladorGaleria.galeria.buscarEmpleadoUsername(nombreAdmin));
		if ( resultadoOperacion )
		{
			System.out.println( "Operacion realizada exitosamente" );
		}
		else
		{
			System.out.println( "Hubo un error al iniciar la subasta" );
		}
		controladorGaleria.salvarGaleria( controladorGaleria.galeria );
		correrConsola();
	}

	// ############################################ Run
	
	public void correrConsola( ) throws IOException
	{
		
		String[] opcionesMenuUsuario = { "Actualizar estado de una pieza", "Cambiar propietario de una pieza",
										"Buscar transaccion", "Consultar pieza", "Consultar historial de una pieza",
										"Consultar historial de un artista", "Empezar una nueva subasta",
										"Finalizar subasta activa", "Cerrar sesion"};
		
		int iInput = this.mostrarMenu( "Menu de operador. Bienvenido " + nombreUsuario , opcionesMenuUsuario );
		
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
			
			case 7: // Nueva subasta
			{
				nuevaSubasta();
				break;
			}

			case 8: // Finalizar subasta
			{
				finalizarSubasta();
				break;
			}

			case 9: // Cerrar sesion
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
