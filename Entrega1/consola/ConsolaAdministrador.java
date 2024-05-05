package consola;

import controlador.ControladorGaleria;
import usuarios.*;

public class ConsolaAdministrador extends ConsolaEmpleado 
{
    // TODO

    // ############################################ Atributos

	ControladorGaleria controladorGaleria;
	String nombreUsuario;
	String nombreAdmin;
	String nombreCajero;
	String nombreOperador;
	Administrador usuario;
	
	// ############################################ Constructor
	
	public ConsolaAdministrador(ControladorGaleria controladorGaleria)
	{
		super(controladorGaleria);
		this.usuario = (Administrador) controladorGaleria.usuarioDeLaSesion;
	}
		
	// ############################################ Metodos
	
	// ############################################ Run
	
	public void correrConsola( )
	{
		
		String[] opcionesMenuUsuario = { };
		
		int iInput = this.mostrarMenu( "Menu de " + nombreUsuario , opcionesMenuUsuario );
		
		switch ( iInput )
		{
			case 1:
			{
				break;
			}
		}
	}

}
