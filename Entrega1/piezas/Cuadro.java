package piezas;

import java.util.*;

import usuarios.UsuarioCorriente;

public class Cuadro extends Pieza 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8619818903737981930L;
	// ############################################ Atributos publicos
	public static final String TECNICA = "tecnica";
	public static final String ALTO = "alto";
	public static final String ANCHO = "ancho";
	public static final String ENMARCACION = "enmarcacion";
	
    // ############################################ Atributos cuadro
    private String tecnica;
    private String alto;
    private String ancho;
    private String enmarcacion;

    // ############################################ Constructor

    public Cuadro(String titulo, String tipo, int estado, ArrayList<String> autores, String anio, String ciudad, String pais, UsuarioCorriente propietario,
                    String tecnica, String alto, String ancho, String enmarcacion)
    {
        super(titulo, tipo, estado, autores, anio, ciudad, pais, propietario);
        this.tecnica = tecnica;
        this.alto = alto;
        this.ancho = ancho;
        this.enmarcacion = enmarcacion;
    }

    // ############################################ Getters & Setters

    /**
     * @return String return the tecnica
     */
    public String getTecnica() 
    {
        return tecnica;
    }

    /**
     * @param tecnica the tecnica to set
     */
    public void setTecnica(String tecnica) 
    {
        this.tecnica = tecnica;
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

    /**
     * @return String return the enmarcacion
     */
    public String getEnmarcacion() 
    {
        return enmarcacion;
    }

    /**
     * @param enmarcacion the enmarcacion to set
     */
    public void setEnmarcacion(String enmarcacion) 
    {
        this.enmarcacion = enmarcacion;
    }

    // ############################################ Metodos

    @Override
    public String getInformacion(String informacion) 
    {
        switch (informacion) 
        {
            case TECNICA:
            {
                return getTecnica();
            }
            case ALTO:
            {
                return getAlto();
            }
            case ANCHO:
            {
                return getAncho();
            }
            case ENMARCACION:
            {
                getEnmarcacion();
            }
            default:
            {
                return null;
            }
        }
    }

}
