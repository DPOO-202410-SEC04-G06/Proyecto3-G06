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
	private HashMap<String, List<Cuadro>> obrasPorArtista;

	@BeforeEach
	void setUp() throws Exception 
	{
		galeria = new Galeria();
        propietario = new UsuarioCorriente("Propietario", "123456789", "propietarioUser", "password123");
        galeria.crearUsuarioCorriente(propietario.getNombre(), propietario.getTelefono(), propietario.getUsername(), propietario.getPassword());

        obrasPorArtista = new HashMap<>();

        
        List<Cuadro> obrasDeLeonardo = Arrays.asList(
            new Cuadro("La Divina", Pieza.CUADRO, 1, new ArrayList<>(Arrays.asList("Jeronimo Vasquez")), "2024", "Bogota", "Colombia", propietario, "Óleo sobre lienzo", "77 cm", "53 cm", "Sí"),
            new Cuadro("Ella", Pieza.CUADRO, 1, new ArrayList<>(Arrays.asList("Jeronimo Vasquez")), "2022", "Bucaramanga", "Colombia", propietario, "Óleo sobre lienzo", "10 cm", "10 cm", "No"));
        obrasPorArtista.put("Leonardo Da Vinci", obrasDeLeonardo);
	}

	@Test
	void test() 
	{
		String nombreArtista = "Jeronimo Vasquez";
        List<Cuadro> obras = obrasPorArtista.getOrDefault(nombreArtista, new ArrayList<>());

        String historia = obras.stream()
            .map(obra -> String.format("Título: %s, Año: %s, Ciudad: %s, País: %s, Tipo: %s\n",
                    obra.getTitulo(), obra.getAnio(), obra.getCiudad(), obra.getPais(), obra.getTipo()))
            .collect(Collectors.joining());

        assertNotNull(historia, "La historia no debería ser nula");
        assertTrue(historia.contains("La Divina"), "La historia debe contener 'La Divina'");
        assertTrue(historia.contains("Ella"), "La historia debe contener 'Ella'");
        assertTrue(historia.contains("2024"), "La historia debe incluir el año 2024");
        assertTrue(historia.contains("2022"), "La historia debe incluir el año 2022");
	}

}
