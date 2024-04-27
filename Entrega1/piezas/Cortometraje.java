package piezas;

import java.util.*;

import usuarios.UsuarioCorriente;

public class Cortometraje extends Pieza 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3276928965915524428L;
	// ############################################ Atributos publicos
	public static final String DURACION = "duracion";
	public static final String GENERO = "genero";
		
    // ############################################ Atributos cortometraje
    private String duracion;
    private String genero;

    // ############################################ Constructor

    public Cortometraje(String titulo, String tipo, int estado, ArrayList<String> autores, String anio, String ciudad, String pais, UsuarioCorriente propietario,
                        String duracion, String genero)
    {
        super(titulo, tipo, estado, autores, anio, ciudad, pais, propietario);
        this.duracion = duracion;
        this.genero = genero;
    }

    // ############################################ Getters & Setters

    public String getDuracion()
    {
        return duracion;
    }

    public String getGenero()
    {
        return genero;
    }

    public void setDuracion(String duracion)
    {
        this.duracion = duracion;
    }

    public void setGenero(String genero)
    {
        this.genero = genero;
    }

    // ############################################ Metodos

    @Override
    public String getInformacion(String informacion) 
    {
        switch( informacion )
        {
            case DURACION:
            {
                return getDuracion();
            }
            case GENERO:
            {
                return getDuracion();
            }
            default:
            {
                return null;
            }
        }
    }

}
