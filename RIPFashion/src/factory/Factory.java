package factory;

import pojos.Cliente;
import pojos.Empleado;

public class Factory {

	private final static String [] TIPOSEMPLEADO = {"Dependiente", "Asesor", "Encargado"};
	
	private final static String[] NOMBRES = {"Álvaro", "Ana", "María", "Javier", "Pablo", "Andrea", "Carlos", "Juan", "Ignacio",
			 "Francisco", "Iria", "Roberto", "Antonio", "Manuel", "Jose", "David", "Carmen", "Isabel", "Laura", "Lucía"};
	
	private final static String[] APELLIDOS = {"Sánchez", "Martínez", "Fernandez", "Hernández", "García", "Rodríguez",
			"González", "López", "Pérez"};
	
	private final static String [] DIRECCIONES = {"Avenida Reina Victoria 70", "Calle guzman el bueno 84", "Calle Argensola 18", "Calle Serrano 36"};
	
	
	public static Cliente generarClienteAleatorio() {
		Cliente cliente = new Cliente();
		cliente.setNombre(randomStringFromArray(NOMBRES));
		cliente.setApellido(randomStringFromArray(APELLIDOS));
		cliente.setDireccion(randomStringFromArray(DIRECCIONES));
		return cliente;
		
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
