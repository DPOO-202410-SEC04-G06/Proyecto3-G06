package galeria;

import java.io.Serializable;
import java.util.*;

import pagos.PortalPagos;
import piezas.Pieza;
import usuarios.Administrador;
import usuarios.Cajero;
import usuarios.Empleado;
import usuarios.Operador;
import usuarios.Usuario;
import usuarios.UsuarioCorriente;

public class Galeria implements Serializable 
{
    /**
	 * 
	 */
	private static final long serialVersionUID = -7390933211907859963L;
	// ############################################ Atributos publicos
    public static final String PRECIO_PIEZA = "precio";
    public static final String COMPRADOR = "comprador";
    public String nombreAdministrador = null;
    public String nombreCajero = null;
    public String nombreOperador = null;
    public Usuario usuarioDeLaSesion = null;

	// ############################################ Atributos de la galeria
    private Inventario inventarioPiezas;
    private HashMap<String, UsuarioCorriente> mapaUsuariosCorrientes;
    private HashMap<String, Empleado> mapaEmpleados;
    private HashMap<String, String> mapaUsernamesPasswords; // mapa de los usuarios y las contraseñas
    private PortalPagos portalPagos;
    private HashMap< String, HashMap< String, String >> piezasEnVenta; // mapa de piezas en venta, con un mapa que contiene el precio y el comprador
    

    private HashMap< String, ArrayList< Pieza > > mapaAutores;


    // ############################################ Constructor

    public Galeria()
    {
        inventarioPiezas = new Inventario();
        mapaUsuariosCorrientes = new HashMap<String, UsuarioCorriente>();
        mapaEmpleados = new HashMap<String, Empleado>();
        mapaUsernamesPasswords = new HashMap<String, String>();
        portalPagos = new PortalPagos();
        piezasEnVenta = new HashMap< String, HashMap< String, String > >();
    }

    // ############################################ Getters & Setters

    /**
     * @return Inventario return the inventarioPiezas
     */
    public Inventario getInventarioPiezas() 
    {
        return inventarioPiezas;
    }

    /**
     * @param inventarioPiezas the inventarioPiezas to set
     */
    public void setInventarioPiezas(Inventario inventarioPiezas) 
    {
        this.inventarioPiezas = inventarioPiezas;
    }

    /**
     * @return HashMap<String, UsuarioCorriente> return the mapaUsuariosCorrientes
     */
    public HashMap<String, UsuarioCorriente> getMapaUsuariosCorrientes()
    {
        return mapaUsuariosCorrientes;
    }

    public HashMap< String, HashMap< String, String >> getPiezasEnVenta()
    {
        return piezasEnVenta;
    }

    /**
     * @param mapaUsuariosCorrientes the mapaUsuariosCorrientes to set
     */
    public void setMapaUsuariosCorrientes(HashMap<String, UsuarioCorriente> mapaUsuariosCorrientes) 
    {
        this.mapaUsuariosCorrientes = mapaUsuariosCorrientes;
    }

    /**
     * @return HashMap<String, Empleado> return the mapaEmpleados
     */
    public HashMap<String, Empleado> getMapaEmpleados() 
    {
        return mapaEmpleados;
    }

    /**
     * @param mapaEmpleados the mapaEmpleados to set
     */
    public void setMapaEmpleados(HashMap<String, Empleado> mapaEmpleados) 
    {
        this.mapaEmpleados = mapaEmpleados;
    }

    /**
     * @return PortalPagos return the portalPagos
     */
    public PortalPagos getPortalPagos() 
    {
        return portalPagos;
    }

    /**
     * @param portalPagos the portalPagos to set
     */
    public void setPortalPagos(PortalPagos portalPagos) 
    {
        this.portalPagos = portalPagos;
    }

    public void setPiezasEnVenta(HashMap< String, HashMap< String, String >> piezasEnVenta)
    {
        this.piezasEnVenta = piezasEnVenta;
    }

    public HashMap< String , ArrayList<Pieza> > getMapaAutores()
    {
        return mapaAutores;
    } 

    public void setMapaAutores( HashMap< String , ArrayList<Pieza> > mapaAutores )
    {
        this.mapaAutores = mapaAutores;
    }

    // ############################################ Metodos

    /**
     * Verifica la presencia de un usuario en la galeria y garantiza que los metodos correspondientes
     * estén disponibles para su uso.
     * @param username
     * @param password
     */
    public boolean iniciarSesion(String username, String password)
    {
        if ( mapaUsernamesPasswords.containsKey( username ) )
        {
            if ( mapaUsernamesPasswords.get( username ).equals( password ) )
            {
                Usuario usuario = buscarUsuarioUsername(username);
                usuarioDeLaSesion = usuario;
                return true;
            }
            return false;
        }
        return false;
    }

    /**
     * Crea un nuevo usuario corriente, el cual puede ser propietario y/o comprador
     * @param nombre
     * @param telefono
     * @param username
     * @param password
     */
    public void crearUsuarioCorriente(String nombre, String telefono, String username, String password)
    {
        UsuarioCorriente nuevoUsuario = new UsuarioCorriente(nombre, telefono, username, password);
        mapaUsernamesPasswords.put(username, password);
        mapaUsuariosCorrientes.put(username, nuevoUsuario);
    }

    /**
     * Crea un nuevo empleado, el cual puede tener uno de cuatro roles disponibles.
     * @param nombre
     * @param telefono
     * @param username
     * @param password
     * @param rol
     */
    public void crearEmpleado(String nombre, String telefono, String username, String password, int rol)
    {
        Empleado nuevoEmpleado;

        switch ( rol )
        {
            case Empleado.ADMIN:
            {
                nuevoEmpleado = new Administrador(nombre, telefono, username, password);

                if ( nombreAdministrador != null )
                {
                    cambiarRolEmpleado(nombreAdministrador, Empleado.CORRIENTE);
                }
                
                nombreAdministrador = username;
                break;
            }

            case Empleado.OP:
            {
                nuevoEmpleado = new Operador(nombre, telefono, username, password);

                if ( nombreOperador != null )
                {
                    cambiarRolEmpleado(nombreAdministrador, Empleado.CORRIENTE);
                }

                nombreOperador = username;
                break;
            }

            case Empleado.CAJERO:
            {
                nuevoEmpleado = new Cajero(nombre, telefono, username, password);

                if ( nombreCajero != null )
                {
                    cambiarRolEmpleado(nombreCajero, Empleado.CORRIENTE);
                }

                nombreCajero = username;
                break;
            }

            default:
            {
                nuevoEmpleado = new Empleado(nombre, telefono, username, password);
                break;
            }
        }

        mapaUsernamesPasswords.put(username, password);
        mapaEmpleados.put(username, nuevoEmpleado);
    }

    /**
     * Cambia el rol actual de un empleado, y guarda todos sus atributos
     * @param username
     * @param nuevoRol
     */
    private void cambiarRolEmpleado(String username, int nuevoRol)
    {
        Empleado empleado = (Empleado) buscarUsuarioUsername( username );
        String nombre = empleado.getNombre();
        String telefono = empleado.getTelefono();
        String password = empleado.getPassword();

        switch( nuevoRol )
        {
            case Empleado.ADMIN:
            {
                Administrador nuevoEmpleado = new Administrador(nombre, telefono, username, password);
                mapaEmpleados.put( username, nuevoEmpleado );
                break;
            }
            case Empleado.OP:
            {  
                Operador nuevoEmpleado = new Operador(nombre, telefono, username, password);
                mapaEmpleados.put( username, nuevoEmpleado );
                break;
            }
            case Empleado.CAJERO:
            {
                Cajero nuevoEmpleado = new Cajero(nombre, telefono, username, password);
                mapaEmpleados.put( username, nuevoEmpleado );
                break;
            }
            case Empleado.CORRIENTE:
            {
                Empleado nuevoEmpleado = new Empleado(nombre, telefono, username, password);
                mapaEmpleados.put( username, nuevoEmpleado );
                break;
            }
        }
    }

    /**
     * Elimina el usuario activo de la sesion para evitar que la aplicacion sea utilizada. Para volver a
     * hacer uso del programa se debe iniciar sesión.
     */
    public void cerrarSesion()
    {
        usuarioDeLaSesion = null; 
    }

    /**
     * Retorna una pieza que se encuentre en la galería actualmente
     * @param nombrePieza
     * @return La pieza buscada. null si no la encuentra.
     */
    public Pieza consultarPiezaGaleria(String nombrePieza)
    {
        return inventarioPiezas.consultarPiezaGaleria(nombrePieza);
    }

    /**
     * Retorna una pieza que esté o haya estado alguna vez en la galería
     * @param nombrePieza
     * @return La pieza buscada incluso si no se encuentra en la galeria. null si no la encuentra
     */
    public Pieza consultarTodasLasPiezas(String nombrePieza)
    {
        return inventarioPiezas.consultarTodasLasPiezas(nombrePieza);
    }

    /**
     * Retorna el estado actual de una pieza, incluyendo si esta no se encuentra en el inventario
     * @param nombrePieza
     * @return estado de la pieza correspondiente a los estados posibles dictados por el inventario
     */
    public int consultarEstadoPieza(String nombrePieza)
    {
        return inventarioPiezas.consultarEstadoPieza(nombrePieza);
    }

    /**
     * Retorna uno de los atributos de la pieza, si la pieza no posee este atributo, retorna que no lo tiene.
     * @param nombrePieza
     * @param informacionBuscada : String nombre del atributo
     * @return el valor del atributo de la pieza consultado
     */
    public String consultarInformacionPieza(String nombrePieza, String informacionBuscada)
    {
        return inventarioPiezas.consultarInformacionPieza(nombrePieza, informacionBuscada);
    }

    /**
     * Busca un usuario registrado en la galería
     * @param username
     * @return EL usuario buscado. null si no existe
     */
    public Usuario buscarUsuarioUsername(String username)
    {
        if (mapaUsuariosCorrientes.containsKey(username))
        {
            return mapaUsuariosCorrientes.get(username);
        }
        else if (mapaEmpleados.containsKey(username))
        {
            return mapaEmpleados.get(username);
        }
        else
        {
            return null;
        }
    }

    /**
     * Busca un empleado en la galería
     * @param username
     * @return El empleado buscado, null si no existe
     */
    public Empleado buscarEmpleadoUsername(String username)
    {
        if (mapaEmpleados.containsKey(username))
        {
            return mapaEmpleados.get(username);
        }
        else
        {
            return null;
        }
    }

    /**
     * Busca un usuario corriente en la galería
     * @param username
     * @return El usuario corriente buscado, null si no existe
     */
    public UsuarioCorriente buscarUsuarioCorrienteUsername(String username)
    {
        if (mapaUsuariosCorrientes.containsKey(username))
        {
            return mapaUsuariosCorrientes.get(username);
        }
        else
        {
            return null;
        }
    }

    /**
     * Agrega una nueva pieza a las piezas en venta
     * @param pieza
     */
    public void nuevaPiezaEnVenta(Pieza pieza)
    {
        piezasEnVenta.put( pieza.getTitulo(), new HashMap<String, String>() );
        piezasEnVenta.get( pieza.getTitulo() ).put( PRECIO_PIEZA , ( (Integer) pieza.getPrecioVenta() ).toString() );
        piezasEnVenta.get( pieza.getTitulo() ).put( COMPRADOR, null );
    }

    /**
     * Bloquea la venta de una pieza
     * @param nombrePieza
     * @param username
     * @return
     */
    public boolean bloquearPiezaEnVenta(String nombrePieza, String username)
    {
        String estadoPiezaVenta = piezasEnVenta.get( nombrePieza ).get( COMPRADOR );
        if ( estadoPiezaVenta == null )
        {
        	Administrador admin = (Administrador) mapaEmpleados.get(nombreAdministrador);
            admin.getVerificacionesCompraPiezas().add(nombrePieza);
            piezasEnVenta.get( nombrePieza ).put( COMPRADOR, username );
            return true;
        }
        return false;
    }

    public void desbloquearPieza(String nombrePieza)
    {
        piezasEnVenta.get( nombrePieza ).put( COMPRADOR, null );
    }

    /**
     * Quita una pieza de las piezas en venta
     * @param nombrePieza
     */
    public void removerPiezaEnVenta(String nombrePieza)
    {
        piezasEnVenta.remove( nombrePieza );
    }

    /**
     * Agrega una nueva pieza a lal mapa de autores.
     * @param nombreAutor
     * @param pieza
     */
    public void nuevaPiezaAutor( String nombreAutor, Pieza pieza )
    {
        if ( !mapaAutores.containsKey(nombreAutor) )
        {
            mapaAutores.put(nombreAutor, new ArrayList<Pieza>() );
        }

        mapaAutores.get(nombreAutor).add(pieza);
    }

    /**
     * Retorna las piezas de una autor
     * @param nombreAutor
     * @return ArrayList<Pieza> piezas del autor o null si no tiene
     */
    public ArrayList<Pieza> getPiezasAutor( String nombreAutor )
    {
        return mapaAutores.get(nombreAutor);
    }
}