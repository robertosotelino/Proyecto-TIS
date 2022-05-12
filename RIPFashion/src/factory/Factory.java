package factory;

import pojos.Cliente;
import pojos.Empleado;
import pojos.Marca;

public class Factory {

	private final static String [] TIPOSEMPLEADO = {"Dependiente", "Asesor", "Encargado"};
	
	private final static String[] NOMBRES = {"Álvaro", "Ana", "María", "Javier", "Pablo", "Andrea", "Carlos", "Juan", "Ignacio",
			 "Francisco", "Iria", "Roberto", "Antonio", "Manuel", "Jose", "David", "Carmen", "Isabel", "Laura", "Lucía"};
	
	private final static String[] APELLIDOS = {"Sánchez", "Martínez", "Fernandez", "Hernández", "García", "Rodríguez",
			"González", "López", "Pérez"};
	
	private final static String [] DIRECCIONES = {"Avenida Reina Victoria 70", "Calle guzman el bueno 84", "Calle Argensola 18", "Calle Serrano 36"};
	
	private final static String [] MARCAS = {"YSL", "Balmain", "Balenciaga", "Gucci", "Golden Goose", "Moncler", "Valentino", "Dior", "Louis Vuitton", "Hermes", "Chanel"};
	
	private final static String [] CATEGORIAS = {"Sandalia", "Tacon", "Sneaker", "Bolso", "Foulard", "Cinturon", "Zapato de vestir", "Camiseta", "Sudadera", "Pantalon", "Blazer", "Plumifero", "100 gramos"};
	
	private final static String [] CAMPAÑA = {"SS22", "FW22"};
	
	private final static String [] COLOR = {"Rojo", "Azul marino", "Azul celureo", "Multicolor", "Blanco", "Negro", "Amarillo", "Rosa", "Naranja", "Verde"};
	
	private final static String [] SEXO = {"1", "0"};
	
	public static Cliente generarClienteAleatorio() {
		
		Cliente cliente = new Cliente();
		
		cliente.setNombre(randomStringFromArray(NOMBRES));
		cliente.setApellido(randomStringFromArray(APELLIDOS));
		cliente.setDireccion(randomStringFromArray(DIRECCIONES));
		return cliente;
		
	}
	
	public static Marca generarMarcasAleatorias() {
		
		Marca marca = new Marca ();
		marca.setNombre(randomStringFromArray(MARCAS));
		return marca;
	}
	
	
	public static Empleado generarEmpleadoAleatorio() {
		
		Empleado empleado = new Empleado();
		
		empleado.setTipo(randomStringFromArray(TIPOSEMPLEADO));
		return empleado;
		
	}
	
	private static String randomStringFromArray(String[] array) {
		return array[randomInt(array.length)];
	}
	
	private static int randomInt(int max) {
		return (int) (Math.random() * max);
	}
}
