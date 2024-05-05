package consola;

import controlador.ControladorGaleria;
import usuarios.*;

public class ConsolaOperador extends ConsolaEmpleado 
{
	// TODO

	// ############################################ Atributos

	ControladorGaleria controladorGaleria;
	String nombreUsuario;
	String nombreAdmin;
	String nombreCajero;
	String nombreOperador;
	Operador usuario;
	
	// ############################################ Constructor
	
	public ConsolaOperador(ControladorGaleria controladorGaleria)
	{
		super(controladorGaleria);
		this.usuario = (Operador) controladorGaleria.usuarioDeLaSesion;
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
