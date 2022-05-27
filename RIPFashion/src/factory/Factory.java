package factory;

import java.util.Random;

import pojos.Articulo;
import pojos.Cliente;
import pojos.Empleado;
import pojos.Marca;
import pojos.Tienda;

public class Factory {

	private final static String [] TIPOSEMPLEADO = {"Dependiente", "Asesor", "Encargado"};
	
	private final static String[] NOMBRES = {"Álvaro", "Ana", "María", "Javier", "Pablo", "Andrea", "Carlos", "Juan", "Ignacio",
			 "Francisco", "Iria", "Roberto", "Antonio", "Manuel", "Jose", "David", "Carmen", "Isabel", "Laura", "Lucía"};
	
	private final static String[] APELLIDOS = {"Sánchez", "Martínez", "Fernandez", "Hernández", "García", "Rodríguez",
			"González", "López", "Pérez"};
	
	private final static String [] DIRECCIONES = {"Avenida Reina Victoria 70", "Calle guzman el bueno 84", "Calle Argensola 18", "Calle Serrano 36"};
	
	private final static String [] MARCAS = {"YSL", "Balmain", "Balenciaga", "Gucci", "Golden Goose", "Moncler", "Valentino", "Dior", "Louis Vuitton", "Hermes", "Chanel"};
	
	private final static String [] CATEGORIA = {"Sandalia", "Tacon", "Sneaker", "Bolso", "Foulard", "Cinturon", "Zapato de vestir", "Camiseta", "Sudadera", "Pantalon", "Blazer", "Plumifero", "100 gramos"};
	
	private final static String [] CAMPAÑA = {"SS22", "FW22"};
	
	private final static String [] COLOR = {"Rojo", "Azul marino", "Azul celureo", "Multicolor", "Blanco", "Negro", "Amarillo", "Rosa", "Naranja", "Verde"};
	
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
		
		empleado.setTienda(getTiendaAleatoria());
		
		return empleado;
		
	}
	
	public static Tienda getTiendaAleatoria() {
		
		int i = randomInt(3);
		
		Tienda t = new Tienda ();
		
		if (i==1) {
			
			t.setNombreTienda("Delfin&Maria");
			
		}else if (i ==2){
			
			t.setNombreTienda("Sterling");
			
		} else if (i == 3) {
			
			t.setNombreTienda("Delfin 1953");
			
		}
		
		return t;
	}
	
	public static Articulo generarArticuloAleatorio() {
		
		Articulo articulo = new Articulo();
		
		articulo.setCampaña(randomStringFromArray(CAMPAÑA));
		articulo.setCategoria(randomStringFromArray(CATEGORIA));
		articulo.setColor(randomStringFromArray(COLOR));
		articulo.setMarca(generarMarcasAleatorias());
		articulo.setPrecio(randomInt (10001));
		articulo.setSexo(randomBoolean());
		
		return articulo;
	}
	
	
	private static String randomStringFromArray(String[] array) {
		
		return array[randomInt(array.length)];
	}
	

	private static int randomInt(int max) {
		
		return (int) (Math.random() * max);
	}
	
	private static boolean randomBoolean () {
		
		Random random = new Random();
		return random.nextBoolean();
	}
}
