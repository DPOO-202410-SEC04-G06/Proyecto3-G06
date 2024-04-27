package piezas;

import java.util.*;

import usuarios.UsuarioCorriente;

public class Escultura extends Pieza 
{
	/**
	 * 
	 */
	private static final long serialVersionUID = -8770326727498916843L;
	// ############################################ Atributos publicos
	public static final String MATERIALES = "materiales";
	public static final String ALTO = "alto";
	public static final String LARGO = "largo";
	public static final String ANCHO = "ancho";
	public static final String USO_ELECTRICIDAD = "usoDeElectricidad";
	
    // ############################################ Atributos escultura
    private String materiales;
    private String alto;
    private String largo;
    private String ancho;
    private String usoDeElectricidad;

    // ############################################ Constructor

    public Escultura(String titulo, String tipo, int estado, ArrayList<String> autores, String anio, String ciudad, String pais, UsuarioCorriente propietario,
                        String materiales, String alto, String largo, String ancho, String usoDeElectricidad)
    {
        super(titulo, tipo, estado, autores, anio, ciudad, pais, propietario);
        this.materiales = materiales;
        this.alto = alto;
        this.largo = largo;
        this.ancho = ancho;
        this.usoDeElectricidad = usoDeElectricidad;
    }

    // ############################################ Getters & Setters

    /**
     * @return String return the materiales
     */
    public String getMateriales() 
    {
        return materiales;
    }

    /**
     * @param materiales the materiales to set
     */
    public void setMateriales(String materiales) 
    {
        this.materiales = materiales;
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
     * @return String return the largo
     */
    public String getLargo() 
    {
        return largo;
    }

    /**
     * @param largo the largo to set
     */
    public void setLargo(String largo) 
    {
        this.largo = largo;
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
     * @return String return the usoDeElectricidad
     */
    public String getUsoDeElectricidad() 
    {
        return usoDeElectricidad;
    }

    /**
     * @param usoDeElectricidad the usoDeElectricidad to set
     */
    public void setUsoDeElectricidad(String usoDeElectricidad) 
    {
        this.usoDeElectricidad = usoDeElectricidad;
    }

    // ############################################ Metodos

    @Override
    public String getInformacion(String informacion) 
    {
        switch (informacion) 
        {
            case MATERIALES:
            {
                return getMateriales();
            }
            case ALTO:
            {
                return getAlto();
            }
            case LARGO:
            {
                return getLargo();
            }
            case ANCHO:
            {
                return getAncho();
            }
            case USO_ELECTRICIDAD:
            {
                return getUsoDeElectricidad();
            }
            default:
            {
                return null;
            }
        }
    }

}
