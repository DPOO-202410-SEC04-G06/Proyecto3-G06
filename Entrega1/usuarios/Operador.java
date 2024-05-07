package usuarios;

import java.util.*;

import galeria.Inventario;
import pagos.PortalPagos;
import pagos.Transaccion;
import piezas.Pieza;

public class Operador extends Empleado 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -6048653134072560022L;
	// ############################################ Atributos Operador
    private ArrayList< String > usuariosVerificadosSubasta;
    private HashMap< String, TreeSet<Integer> > manejoOfertas;
    private HashMap<String, HashMap<Integer, UsuarioCorriente> > ofertasUsuario;

    // ############################################ Constructor

    public Operador(String nombreU, String telefonoU, String usernameU, String passwordU)
    {
        super(nombreU, telefonoU, usernameU, passwordU);
        setRol( Empleado.OP );
        usuariosVerificadosSubasta = new ArrayList< String >();
        manejoOfertas = new HashMap< String, TreeSet<Integer> >();
        ofertasUsuario = new HashMap<String, HashMap<Integer, UsuarioCorriente> >();
    }

    // ############################################ Getters & Setters

    /**
     * @return ArrayList< String > return the usuariosVerificadosSubasta
     */
    public ArrayList< String > getUsuariosVerificadosSubasta() 
    {
        return usuariosVerificadosSubasta;
    }

    /**
     * @param usuariosVerificadosSubasta the usuariosVerificadosSubasta to set
     */
    public void setUsuariosVerificadosSubasta(ArrayList< String > usuariosVerificadosSubasta) 
    {
        this.usuariosVerificadosSubasta = usuariosVerificadosSubasta;
    }

    /**
     * @return HashMap< String, TreeSet<Integer> > return the manejoOfertas
     */
    public HashMap< String, TreeSet<Integer> > getManejoOfertas() 
    {
        return manejoOfertas;
    }

    /**
     * @param manejoOfertas the manejoOfertas to set
     */
    public void setManejoOfertas(HashMap< String, TreeSet<Integer> > manejoOfertas) 
    {
        this.manejoOfertas = manejoOfertas;
    }

    /**
     * @return HashMap<String, HashMap<Integer, UsuarioCorriente> > return the ofertasUsuario
     */
    public HashMap<String, HashMap<Integer, UsuarioCorriente> > getOfertasUsuario() 
    {
        return ofertasUsuario;
    }

    /**
     * @param ofertasUsuario the ofertasUsuario to set
     */
    public void setOfertasUsuario(HashMap<String, HashMap<Integer, UsuarioCorriente> > ofertasUsuario) 
    {
        this.ofertasUsuario = ofertasUsuario;
    }

    // ############################################ Metodos

    /**
     * Agrega un nuevo usuario a la lista de usuarios verificados
     * @param username
     */
    public void nuevoUsuarioVerificadoSubasta(String username)
    {
        usuariosVerificadosSubasta.add(username);
    }

    /**
     * Registra una oferta de un usuario durante la subasta de una pieza
     * @param usuario
     * @param nombrePieza
     * @param oferta
     */
    public void registrarOferta(UsuarioCorriente usuario, String nombrePieza, int oferta)
    {
        manejoOfertas.get(nombrePieza).add(oferta);
        ofertasUsuario.get(nombrePieza).put(oferta, usuario);
    }

    /**
     * Inicia una subasta de una pieza
     * @param nombrePieza
     * @param pieza
     * @param precioInicial
     * @param precioMinimo
     * @param inventarioPiezas
     * @return
     */
    public boolean nuevaSubasta(String nombrePieza, Pieza pieza, int precioInicial, int precioMinimo, Inventario inventarioPiezas)
    {   

        boolean result = inventarioPiezas.actualizarEstadoPieza(nombrePieza, Inventario.SUBASTA);

        if ( !result )
        {
            return result;
        }

        pieza.setPrecioInicialSubasta(precioInicial);
        pieza.setPrecioMinSubasta(precioMinimo);

        manejoOfertas.put(nombrePieza, new TreeSet<Integer>() );
        ofertasUsuario.put(nombrePieza, new HashMap<Integer, UsuarioCorriente>() );

        return result;
    }

     /**
      * Finaliza la subasta de una pieza y realiza el cambio del propietario de la pieza
      * @param nombrePieza
      * @param pieza
      * @param inventarioPiezas
      * @param cajero
      * @param portalPagos
      * @param administrador
      * @return true si fue exitosa la subasta, false de lo contrario. Tambien pone la pieza en la lista de salida para el administrador
      */
    public boolean finalizarSubasta(String nombrePieza, Pieza pieza, Inventario inventarioPiezas, Cajero cajero, PortalPagos portalPagos, Administrador administrador)
    {

        int precioMinimo = pieza.getPrecioMinSubasta();
        int ofertaMasGrande = manejoOfertas.get(nombrePieza).first();

        if ( precioMinimo <= ( ofertaMasGrande ) )
        {
            UsuarioCorriente nuevoUsuario = ofertasUsuario.get( nombrePieza ).get( ofertaMasGrande );
            if ( !( usuariosVerificadosSubasta.contains(nuevoUsuario.getUsername()) ) )
            {
                manejoOfertas.remove(nombrePieza);
                ofertasUsuario.remove(nombrePieza);
                return false;
            }
            cambiarPropietarioPieza(nombrePieza, pieza, nuevoUsuario);
            pieza.setPrecioInicialSubasta( Pieza.SIN_PRECIO );
            pieza.setPrecioMinSubasta( Pieza.SIN_PRECIO );

            manejoOfertas.remove(nombrePieza);
            ofertasUsuario.remove(nombrePieza);

            nuevaTransaccion(nuevoUsuario.getNombre(), 
                                nuevoUsuario, 
                                pieza.getNombrePropietario(), 
                                pieza.getPropietario(), 
                                Transaccion.SUBASTADO, 
                                ofertaMasGrande, nombrePieza, 
                                nuevoUsuario.getMetodoPago(), 
                                cajero, 
                                portalPagos);

            administrador.nuevaPiezaSalida(pieza);
            
            return true;
        }

        manejoOfertas.remove(nombrePieza);
        ofertasUsuario.remove(nombrePieza);

        return false;
    }
}
