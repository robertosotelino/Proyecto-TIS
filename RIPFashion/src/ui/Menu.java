package ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;

import db.interfaces.DBManager;
import db.jdbc.JDBCManager;

public class Menu {

	//private static DBManager dbman = new JDBCManager();
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private final static DateTimeFormatter formatterFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	private static final String[] MENU_ROL = { "1.-Rol Empresario", "2.-Rol Cliente","3.-Salir del programa"};
	private static final String[] MENU_EMPRESARIO = { "Listar Articulos", "Buscar articulo", "Listar empleadps", "Salir"};

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
	
	private static void mostrarMenuEmpresario() throws IOException {
		
		System.out.println(MENU_EMPRESARIO);
		
		int a = -1;
		
		do {
			int opcion = br.read();
			
			
		}while (a != 0);
		
	}

	}

