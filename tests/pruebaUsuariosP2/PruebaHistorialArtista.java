package pruebaUsuariosP2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.Galeria;
import piezas.Cuadro;
import piezas.Pieza;
import usuarios.UsuarioCorriente;

class PruebaHistorialArtista {

	private Galeria galeria;
	private UsuarioCorriente propietario;
	private HashMap< String, ArrayList<String>> obrasPorArtista;

	@BeforeEach
	void setUp() throws Exception 
	{
		galeria = new Galeria();
        propietario = new UsuarioCorriente("Propietario", "123456789", "propietarioUser", "password123");
        galeria.crearUsuarioCorriente(propietario.getNombre(), propietario.getTelefono(), propietario.getUsername(), propietario.getPassword());

        obrasPorArtista = new HashMap < String, ArrayList<String>>();
        
        obrasPorArtista.put("Leonardo Da Vinci", new ArrayList<String>() );
	}

	@Test
	void test() 
	{
		Cuadro c1 = new Cuadro("La Divina", Pieza.CUADRO, 1, new ArrayList<>(Arrays.asList("Jeronimo Vasquez")), "2024", "Bogota", "Colombia", propietario, "Óleo sobre lienzo", "77 cm", "53 cm", "Sí");
        Cuadro c2 = new Cuadro("Ella", Pieza.CUADRO, 1, new ArrayList<>(Arrays.asList("Jeronimo Vasquez")), "2022", "Bucaramanga", "Colombia", propietario, "Óleo sobre lienzo", "10 cm", "10 cm", "No");
        
        obrasPorArtista.get("Leonardo Da Vinci").add( "La Divina" );
        obrasPorArtista.get("Leonardo Da Vinci").add( "Ella" );

        assertFalse(obrasPorArtista.isEmpty(), "La historia no debería ser nula");
        assertTrue(obrasPorArtista.containsKey("Leonardo Da Vinci"), "La historia debe contener 'La Divina'");
        
        ArrayList<String> listaPiezas = obrasPorArtista.get("Leonardo Da Vinci");
        
        assertTrue(listaPiezas.contains("La Divina"), "La historia debe contener 'La Divina'");
        assertTrue(listaPiezas.contains("Ella"), "La historia debe contener 'Ella'");
	}

}
