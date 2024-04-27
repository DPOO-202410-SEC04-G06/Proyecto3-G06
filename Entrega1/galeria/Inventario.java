package galeria;

import java.io.Serializable;
import java.util.*;

import piezas.Pieza;

public class Inventario implements Serializable
{
	/**
	 * 
	 */
	private static final long serialVersionUID = 4061729888225121045L;
	// ############################################ Atributos publicos
    public static final int NO_ESTA = -1;
	public static final int PASADA = 0;
	public static final int EXPUESTA = 1;
    public static final int BODEGA = 2;
	public static final int SUBASTA = 3;
	
    // ############################################ Atributos del inventario
    private HashMap<String, Pieza> piezasExpuestas;
    private HashMap<String, Pieza> piezasBodega;
    private HashMap<String, Pieza> subastaActiva;
    private HashMap<String, Pieza> piezasPasadas;
    
    // ############################################ Constructor

    public Inventario()
    {
        piezasExpuestas = new HashMap<String, Pieza>();
        piezasBodega = new HashMap<String, Pieza>();
        piezasPasadas = new HashMap<String, Pieza>();
        subastaActiva = new HashMap<String, Pieza>();
    }

    // ############################################ Getters & Setters

     /**
     * @return HashMap<String, Pieza> return the piezasExpuestas
     */
    public HashMap<String, Pieza> getPiezasExpuestas() 
    {
        return piezasExpuestas;
    }

    /**
     * @param piezasExpuestas the piezasExpuestas to set
     */
    public void setPiezasExpuestas(HashMap<String, Pieza> piezasExpuestas) 
    {
        this.piezasExpuestas = piezasExpuestas;
    }

    /**
     * @return HashMap<String, Pieza> return the piezasBodega
     */
    public HashMap<String, Pieza> getPiezasBodega() 
    {
        return piezasBodega;
    }

    /**
     * @param piezasBodega the piezasBodega to set
     */
    public void setPiezasBodega(HashMap<String, Pieza> piezasBodega) 
    {
        this.piezasBodega = piezasBodega;
    }

    /**
     * @return HashMap<String, Pieza> return the subastaActiva
     */
    public HashMap<String, Pieza> getSubastaActiva() 
    {
        return subastaActiva;
    }

    /**
     * @param subastaActiva the subastaActiva to set
     */
    public void setSubastaActiva(HashMap<String, Pieza> subastaActiva) 
    {
        this.subastaActiva = subastaActiva;
    }

    /**
     * @return HashMap<String, Pieza> return the piezasPasadas
     */
    public HashMap<String, Pieza> getPiezasPasadas() 
    {
        return piezasPasadas;
    }

    /**
     * @param piezasPasadas the piezasPasadas to set
     */
    public void setPiezasPasadas(HashMap<String, Pieza> piezasPasadas) 
    {
        this.piezasPasadas = piezasPasadas;
    }

    // ############################################ Metodos

    /**
     * Consulta una pieza que actualmente se encuentra en la galeria
     * @param nombrePieza
     * @return Pieza buscada actualmente en la galeria
     */
    public Pieza consultarPiezaGaleria( String nombrePieza )
    {
        if (piezasExpuestas.containsKey(nombrePieza))
        { 
            return piezasExpuestas.get(nombrePieza); 
        }
        else if (piezasBodega.containsKey(nombrePieza))
        {
            return piezasBodega.get(nombrePieza);
        }
        else if (subastaActiva.containsKey(nombrePieza))
        {
            return subastaActiva.get(nombrePieza);
        }
        else
        {
            return null;
        }
    }

    /**
     * Consulta una pieza en todo el inventario de la galeria
     * @param nombrePieza
     * @return Pieza buscada actualmente en la galeria o en el pasado
     */
    public Pieza consultarTodasLasPiezas(String nombrePieza)
    {
        Object piezaEnGaleria = consultarPiezaGaleria(nombrePieza);
        if (piezaEnGaleria instanceof Pieza)
        {
            return (Pieza) piezaEnGaleria;
        }
        else if (piezasPasadas.containsKey(nombrePieza))
        {
            return piezasPasadas.get(nombrePieza);
        }
        else
        {
            return null;
        }
    }
    
    /**
     * Consulta una pieza en subasta. Puede no estarlo
     * @param nombrePieza
     * @return Pieza buscada actualmente en subasta. null si no lo esta
     */
    public Pieza consultarPiezaEnSubasta(String nombrePieza)
    {
        return subastaActiva.get(nombrePieza);
    }
    
    /**
     * Consulta el estado actual de una pieza
     * @param nombrePieza
     * @return Estado de la pieza, puede no estar en el inventario
     */
    public int consultarEstadoPieza(String nombrePieza)
    {
        Object pieza = consultarTodasLasPiezas(nombrePieza);
        if ( pieza == null )
        {
            return NO_ESTA;
        }
        return ( (Pieza) pieza ).getEstado();
    }

    /**
     * Actualiza el estado de una pieza y la mueve de un mapa a otro
     * @param nombrePieza
     * @param nuevoEstado
     * @throws Exception 
     */
    public boolean actualizarEstadoPieza(String nombrePieza, int nuevoEstado)
    {
        int estadoPieza = consultarEstadoPieza(nombrePieza);
        if ( estadoPieza == NO_ESTA )
        {
            return false;
        }

        Pieza pieza = consultarTodasLasPiezas(nombrePieza);
        pieza.setEstado(nuevoEstado);
        eliminarPiezaEstado(nombrePieza, estadoPieza);
        agregarPiezaEstado(nombrePieza, pieza, nuevoEstado);

        return true;
    }

    /**
     * Elimina una pieza de un estado especifico
     * @param nombrePieza
     * @param estadoActual
     */
    private void eliminarPiezaEstado(String nombrePieza, int estadoActual)
    {
        switch( estadoActual )
        {
            case PASADA:
            {
                piezasPasadas.remove(nombrePieza);
                break;
            }
            
            case EXPUESTA:
            {
                piezasExpuestas.remove(nombrePieza);
                break;
            }
            
            case BODEGA:
            {
                piezasBodega.remove(nombrePieza);
                break;
            }
            case SUBASTA:
            {
                subastaActiva.remove(nombrePieza);
                break;
            }
            default:
            {
                break;
            }
        }
    }

    /**
     * Agrega una pieza a un estado especifico
     * @param nombrePieza
     * @param pieza
     * @param nuevoEstado
     */
    private void agregarPiezaEstado(String nombrePieza, Pieza pieza, int nuevoEstado)
    {
        switch( nuevoEstado )
        {
            case PASADA:
            {
                piezasPasadas.put(nombrePieza, pieza);
                break;
            }

            case EXPUESTA:
            {
                piezasExpuestas.put(nombrePieza, pieza);
                break;
            }

            case BODEGA:
            {
                piezasBodega.put(nombrePieza, pieza);
                break;
            }

            case SUBASTA:
            {
                subastaActiva.put(nombrePieza, pieza);
                break;
            }
            default:
            {
                break;
            }
        }
    }

    /**
     * Agrega una nueva pieza al inventario. El estado corresponde a una de las variables
     * public static void del inventario.
     * @param pieza
     * @param estado
     */
    public void agregarPieza(Pieza pieza, int estado)
    {
        pieza.setEstado(estado);
        switch( estado )
        {
            case PASADA:
            {
                String nombrePieza = pieza.getTitulo();
                piezasPasadas.put(nombrePieza, pieza);
                break;
            }

            case EXPUESTA:
            {
                String nombrePieza = pieza.getTitulo();
                piezasExpuestas.put(nombrePieza, pieza);
                break;
            }

            case BODEGA:
            {
                String nombrePieza = pieza.getTitulo();
                piezasBodega.put(nombrePieza, pieza);
                break;
            }

            case SUBASTA:
            {
                String nombrePieza = pieza.getTitulo();
                subastaActiva.put(nombrePieza, pieza);
                break;
            }
            default:
            {
                break;
            }
        }
    }

    /**
     * El metodo se encarga de consultar un atributo específico de una pieza. Debido a la gran cantidad
     * de atributos y la diparidad entre los atributos de las subclases de pieza, este metodo
     * pretende evaluar si el atributo buscado pertenece o no a la pieza, y retorna un valor
     * correspondiente.
     * @param nombrePieza
     * @param informacionPieza : String nombre del atributo a buscar
     * @return el valor del atributo de la pieza consultado
     */
    public String consultarInformacionPieza(String nombrePieza, String informacionPieza)
    {
        Pieza pieza = consultarTodasLasPiezas(nombrePieza);
        if ( pieza == null )
        {
            return "La pieza no se encuentra en el inventario de la galería";
        }
        return pieza.getInformacion( informacionPieza );
    }
}