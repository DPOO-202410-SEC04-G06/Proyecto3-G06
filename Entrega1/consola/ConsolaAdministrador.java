package consola;

import java.io.IOException;

import controlador.ControladorGaleria;
import usuarios.*;

public class ConsolaAdministrador extends ConsolaEmpleado 
{
    // TODO implementar metodos admin

    // ############################################ Atributos

	Administrador usuario;
	
	// ############################################ Constructor
	
	public ConsolaAdministrador(ControladorGaleria controladorGaleria)
	{
		super(controladorGaleria);
		this.usuario = (Administrador) controladorGaleria.usuarioDeLaSesion;
	}
		
	// ############################################ Metodos
	
	// ############################################ Run
	
	public void correrConsola( ) throws IOException
	{
		
		String[] opcionesMenuUsuario = { "Actualizar estado de una pieza", "Cambiar propietario de una pieza",
										"Buscar transaccion", "Consultar pieza", "Consultar historial de una pieza",
										"Consultar historial de un artista", "Verificar usuario(s)", ""};
		
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
