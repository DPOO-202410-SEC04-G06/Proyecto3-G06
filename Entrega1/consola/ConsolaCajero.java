package consola;

import controlador.ControladorGaleria;
import usuarios.*;

public class ConsolaCajero extends ConsolaEmpleado 
{
	// TODO

	// ############################################ Atributos

	ControladorGaleria controladorGaleria;
	String nombreUsuario;
	String nombreAdmin;
	String nombreCajero;
	String nombreOperador;
	Cajero usuario;
	
	// ############################################ Constructor
	
	public ConsolaCajero(ControladorGaleria controladorGaleria)
	{
		super(controladorGaleria);
		this.usuario = (Cajero) controladorGaleria.usuarioDeLaSesion;
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
