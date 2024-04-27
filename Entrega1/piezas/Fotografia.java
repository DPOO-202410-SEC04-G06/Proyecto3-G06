package piezas;

import java.util.*;

import usuarios.UsuarioCorriente;

public class Fotografia extends Pieza 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 8845220078166344551L;
	// ############################################ Atributos publicos
	public static final String COLOR = "color";
	public static final String ALTO = "alto";
	public static final String ANCHO = "ancho";
	
    // ############################################ Atributos fotografia
    private String color;
    private String alto;
    private String ancho;

    // ############################################ Constructor

    public Fotografia(String titulo, String tipo, int estado, ArrayList<String> autores, String anio, String ciudad, String pais, UsuarioCorriente propietario,
                        String color, String alto, String ancho)
    {
        super(titulo, tipo, estado, autores, anio, ciudad, pais, propietario);
        this.color = color;
        this.alto = alto;
        this.ancho = ancho;
    }

    // ############################################ Getters & Setters
    
    /**
     * @return String return the color
     */
    public String getColor() 
    {
        return color;
    }

    /**
     * @param color the color to set
     */
    public void setColor(String color) 
    {
        this.color = color;
    }

    /**
     * @return String return the alto
     */
    public String getAlto() 
    {
        return alto;
    }

    /**
     * @param alto the alto to set
     */
    public void setAlto(String alto) 
    {
        this.alto = alto;
    }

    /**
     * @return String return the ancho
     */
    public String getAncho() 
    {
        return ancho;
    }

    /**
     * @param ancho the ancho to set
     */
    public void setAncho(String ancho) 
    {
        this.ancho = ancho;
    }

    // ############################################ Metodos

    @Override
    public String getInformacion(String informacion) 
    {
        switch(informacion)
        {
            case COLOR:
            {
                return getColor();
            }
            case ALTO:
            {
                return getAlto();
            }
            case ANCHO:
            {
                return getAncho();
            }
            default:
            {
                return null;
            }
        }
    }

}
