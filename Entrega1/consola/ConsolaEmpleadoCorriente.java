package consola;

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
	
	public void correrConsola( )
	{
		
		String[] opcionesMenuUsuario = {  };
		
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
