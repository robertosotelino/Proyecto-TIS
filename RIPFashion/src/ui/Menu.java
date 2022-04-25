package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

import db.interfaces.DBManager;
import db.jdbc.JDBCManager;

public class Menu {

	private static JDBCManager dbman = new JDBCManager();
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private final static DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	private static final String[] MENU_ROL = { "1.-Rol Empresario", "2.-Rol Cliente","3.-Salir del programa"};
	private static final String[] MENU_EMPRESARIO = { "Consultar informacion de la tienda","Listar Articulos", 
			"Consultar capital","Consultar articulo por id", "Añadir un articulo",
			"Eliminar un articulo","Modificar articulo","Añadir marca","Añadir una tienda","Añadir un empleado",
			"Listar empleados", "Salir"};

	public static void main (String[] args) throws IOException {
		
		//dbman.connect();
		System.out.println("Bienvenido a nuestras tiendas");
		
		int a = -1;
		
		do {
			
			System.out.println(MENU_ROL);
			int opcion = br.read();
			
			switch (opcion) {
			
			case 1 -> mostrarMenuEmpresario();
			
			
			}
			
		} while ( a != 0);
			System.out.println("Saliendo.... Gracias");
			
			//dbman.disconnect();
		
	
		}
	
	private static void mostrarMenuEmpresario() throws IOException { // INCOMPLETO
		
		System.out.println(MENU_EMPRESARIO);
		
		int a = -1;
		int b = 0;  // ????

		do {
			
			int opcion = br.read();
			
            switch (opcion) {
            
            case 1 -> System.out.println(dbman.getInfoTiendasConCapital()); // consulto informacion de la tienda
			case 2 -> dbman.getArticulos(); // consultar articulos
			case 3 -> dbman.consultarBalance(null); // consultar capital  -> Como le paso la tienda?
			case 4 -> System.out.println(dbman.searchArticuloByIdArt( b = br.read())); // consultar la informacion de un articulo determinado   
			case 5 -> dbman.addArticulo(null);  // añadir un articulo
			case 6 -> dbman.deleteArticuloById(0); // eliminar un articulo determinado
			case 7 -> dbman.updateArticulo(null); // modificar un articulo existente
			case 8 -> dbman.addMarca(null); // añadir una marca			
			case 9 -> dbman.addTienda(null); // añadir una tienda
			case 10 -> dbman.addEmpleado(null); // añadir un empleado
			case 11 -> dbman.getEmpleados(); // listar empleados
			case 12 -> a = 0; // salir
			
			}
			
		}while (a != 0);
		
	}

	}

