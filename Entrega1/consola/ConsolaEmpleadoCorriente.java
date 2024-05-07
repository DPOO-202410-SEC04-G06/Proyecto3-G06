package consola;

import java.io.IOException;

import controlador.ControladorGaleria;
import usuarios.*;

public class ConsolaEmpleadoCorriente extends ConsolaEmpleado 
{

	// ############################################ Atributos

	Empleado usuario;
	
	// ############################################ Constructor
	
	public ConsolaEmpleadoCorriente(ControladorGaleria controladorGaleria)
	{
		super( controladorGaleria );
		this.usuario = (Empleado) controladorGaleria.usuarioDeLaSesion;
	}
	
	// ############################################ Metodos
	
	// ############################################ Run
	
	public void correrConsola( ) throws IOException
	{
		
		String[] opcionesMenuUsuario = { "Actualizar estado de una pieza", "Cambiar propietario de una pieza",
										"Buscar transaccion", "Consultar pieza", "Consultar historial de una pieza",
										"Consultar historial de un artista", "Cerrar sesion"};
		
		int iInput = this.mostrarMenu( "Menu de empleado. Bienvenido " + nombreUsuario , opcionesMenuUsuario );
		
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
			}
			
			case 6: // Consultar historial de un artista
			{
				this.consultarHistorialArtista();
			}
			
			case 7: // Cerrar sesion
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
