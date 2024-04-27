package usuarios;

import java.io.Serializable;

public abstract class Usuario implements Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 2576956028247458472L;
	// ############################################ Atributos publicos
    public static final int CORRIENTE = 1;
    public static final int EMPLEADO = 2;

    // ############################################ Atributos Usuario
    private String nombre;
    private String telefono;
    private int tipo;
    private String username;
    private String password;

    // ############################################ Constructor

    public Usuario(String nombreU, String telefonoU, String usernameU, String passwordU)
    {
        nombre = nombreU;
        telefono = telefonoU;
        username = usernameU;
        password = passwordU;

    }

    // ############################################ Getter & Setters
    
    /**
     * @return String return the nombre
     */
    public String getNombre() 
    {
        return nombre;
    }

    /**
     * @param nombre the nombre to set
     */
    public void setNombre(String nombre) 
    {
        this.nombre = nombre;
    }

    /**
     * @return String return the telefono
     */
    public String getTelefono() 
    {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) 
    {
        this.telefono = telefono;
    }

    /**
     * @return int return the tipo
     */
    public int getTipo() 
    {
        return tipo;
    }

    /**
     * @param tipo the tipo to set
     */
    public void setTipo(int tipo) 
    {
        this.tipo = tipo;
    }

    /**
     * @return String return the username
     */
    public String getUsername() 
    {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) 
    {
        this.username = username;
    }

    /**
     * @return String return the password
     */
    public String getPassword() 
    {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) 
    {
        this.password = password;
    }
}
