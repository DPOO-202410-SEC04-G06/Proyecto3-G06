package pruebaUsuariosP2;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import galeria.Galeria;
import pagos.Transaccion;
import piezas.Cuadro;
import piezas.Pieza;
import usuarios.UsuarioCorriente;

class PruebaHistorialComprador {

	private Galeria galeria;
    private UsuarioCorriente comprador;
    private HashMap<String, List<Transaccion>> comprasPorUsuario;

	@BeforeEach
	void setUp() throws Exception 
	{
		galeria = new Galeria();
        comprador = new UsuarioCorriente("Comprador", "123456789", "compradorUsuario", "password123");
        galeria.crearUsuarioCorriente(comprador.getNombre(), comprador.getTelefono(), comprador.getUsername(), comprador.getPassword());

        comprasPorUsuario = new HashMap<>();
        
        
        List<Transaccion> comprasDelComprador = new ArrayList<>();
        comprasDelComprador.add(new Transaccion("compradorUsuario", "vendedorUsuario", new Date(), false, 1000, "Obra 1", 1, 1));
        comprasDelComprador.add(new Transaccion("compradorUsuario", "vendedorUsuario", new Date(), false, 2000, "Obra 2", 1, 2));
        comprasPorUsuario.put(comprador.getUsername(), comprasDelComprador);
	}

	@Test
	void test() 
	{
		List<Transaccion> compras = comprasPorUsuario.getOrDefault("compradorUsuario", new ArrayList<>());

        String historia = compras.stream()
            .map(transaccion -> String.format("Pieza: %s, Precio: %d, Fecha: %s\n",
                    transaccion.getNombrePieza(), transaccion.getPrecio(), transaccion.getFecha().toString()))
            .collect(Collectors.joining());

        assertNotNull(historia, "La historia no debería ser nula");
        assertTrue(historia.contains("Obra 1"), "La historia debe contener 'Obra 1'");
        assertTrue(historia.contains("Obra 2"), "La historia debe contener 'Obra 2'");
        assertTrue(historia.contains("1000"), "La historia debe incluir el precio 1000");
        assertTrue(historia.contains("2000"), "La historia debe incluir el precio 2000");
	}

}
