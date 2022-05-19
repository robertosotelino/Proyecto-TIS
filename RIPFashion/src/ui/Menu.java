package ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import db.interfaces.DBManager;
import db.jdbc.JDBCManager;
import pojos.Articulo;
import pojos.Empleado;
import pojos.Marca;
import pojos.Tienda;



public class Menu {

	private static DBManager dbman = new JDBCManager(); 
	
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final String[] MENU_ROL = { "1.-Rol Empresario", "2.-Rol Cliente","3.-Salir del programa"};
	private static final String[] MENU_EMPRESARIO = { "1.-Consultar informacion de la tienda","2.- Listar Articulos", 
			"3.- Consultar capital","4.- Consultar articulo por id", "5.- Añadir un articulo",
			"6.- Eliminar un articulo","7.- Modificar articulo","8.- Añadir marca",
			"9.- Listar empleados", "10.- Salir"};

	public static void main (String[] args) throws IOException {
		
		dbman.connect();
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
			
			dbman.disconnect();
		
	
		}

	private static void mostrarMenuEmpresario() throws IOException {
		
		System.out.println("/n/n*****MENU DEL EMPRESARIO*****/n/n");
		
		int respuesta = -1;
		
		do {
			
			respuesta = mostrarMenu(MENU_EMPRESARIO);
			
			switch(respuesta) {
			
				case 1 -> consultarInfoTiendasConCapital(); // done
				case 2 -> listarArticulos(); // done
				case 3 -> consultarCapital(); // done
				case 4 -> consultarArticuloPorId(); // done
				case 5 -> añadirArticulo();  // done
				case 6 -> eliminarArticulo(); // done
				case 7 -> modificarArticulo(); // done
				case 8 -> añadirMarca(); // duda
				case 9 -> listarEmpleados (); // done
			
			}
			
		} while(respuesta != 0);
		
	}

	private static void listarEmpleados() {
		
		ArrayList <Empleado> empleados = dbman.getEmpleados();
		
		System.out.println("\n Información de empleados \n");
		
		int i ;
		
		for (i=0 ; i < empleados.size() ; i++) {
			
			System.out.println(empleados.get(i).toString());
			
		}
		
		
	}

	private static void añadirMarca() throws IOException {
		
		System.out.println("Introduzca el nombre de la marca");
		
		String nombre = br.readLine();
		
		Marca marca = new Marca (nombre, null); // le tengo que pasar un null o como??'
		
		dbman.addMarca(marca);
		
	}

	private static void modificarArticulo() throws IOException {
		
		System.out.println("Que artículo quieres modificar ( introduce su id )");
		
		
		int idA ;
		
		listarArticulos();
		boolean exito;
		
		do {
			
			idA = br.read();
			exito = comprobarIdCorrecto(idA);
			
		}while(exito ==true);
		
		System.out.println("El id es correcto");
		
		ArrayList <Articulo> articulo = dbman.searchArticuloByIdArt (idA);
		
		Articulo a = articulo.get(0);
		
		Articulo aModificado = updateArticulo (a);
		
		boolean b = dbman.updateArticulo(aModificado);
		
		if (b == true) {
			
			System.out.println("Articulo modificado correctamente");
			
		} else {
			
			System.out.println("No ha sido posible modificar el articulo; intentelo de nuevo");
		}
		
	}
	
	private static Articulo updateArticulo(Articulo articulo) throws IOException {
		
		System.out.println("Categoria del artículo :");
		
		String categoria = br.readLine();
		
		System.out.println("Campaña del articulo");
		
		String campaña = br.readLine();
		
		System.out.println("Color del articulo");
		
		String color = br.readLine();
		
		System.out.println("Precio del articulo ( numero entero )");
		
		int precio = br.read();
		
		Marca m = selecMarca();
			
		int sexo = 5;
		
		do {
			
		System.out.println("Sexo (introduzca 1 si es para hombre o 0 si es para mujer)");
		
		sexo = br.read();
		
		if (sexo == 1) {
				
			articulo.setSexo(true);
			
		}else if( sexo == 0) {
			
			articulo.setSexo(false);
			
		}else if (sexo != 1 || sexo != 0) {
			
			System.out.println("No ha introducido un dato válido");
			
		}
		
		}while (sexo != 1 || sexo != 0);

		articulo.setCampaña(campaña);
		articulo.setCategoria(categoria);
		articulo.setColor(color);
		articulo.setMarca(m); 
		articulo.setPrecio(precio);
		return articulo;
	}
	
	
	private static void eliminarArticulo() throws IOException {
		
		listarArticulos();
		boolean exito = false;
		int id ;
		do {
			
		System.out.println("Id del articulo que desea eliminar");
		
	    id = br.read();
		
		exito = comprobarIdCorrecto(id);
		
		}while(exito = true);
		
		dbman.deleteArticuloById(id);
		
		System.out.println("Articulo eliminado correctamente");
	}
	
	private static boolean comprobarIdCorrecto (int id) {
		
        ArrayList <Articulo> articulos = dbman.getArticulos();
		
        int i ;
        boolean exito= false;
        
        for ( i = 0 ; i < articulos.size(); i ++) {
        	
        	if ( articulos.get(i).getIdArt() == id) {
        		
        		exito = true;
        		
        	}else {
        		
        		exito = false;
        	}
        	
        }
		
		return exito;
		
	}

	private static void añadirArticulo() throws IOException {

		
		Articulo articulo = newArticulo();
		
		dbman.addArticulo(articulo);
		
		System.out.println("Articulo añadido correctamente a la base de datos");
	
	}
	
	private static Articulo newArticulo() throws IOException {
		
		Articulo articulo = new Articulo();
		
		System.out.println("Categoria del artículo :");
		
		String categoria = br.readLine();
		
		System.out.println("Campaña del articulo");
		
		String campaña = br.readLine();
		
		System.out.println("Color del articulo");
		
		String color = br.readLine();
		
		System.out.println("Precio del articulo ( numero entero )");
		
		int precio = br.read();
		
		int exito = 1;
		
		Marca m = selecMarca();
			
		int sexo = 5;
		
		do {
			
		System.out.println("Sexo (introduzca 1 si es para hombre o 0 si es para mujer)");
		
		sexo = br.read();
		
		if (sexo == 1) {
				
			articulo.setSexo(true);
			
		}else if( sexo == 0) {
			
			articulo.setSexo(false);
			
		}else if (sexo != 1 || sexo != 0) {
			
			System.out.println("No ha introducido un dato válido");
			
		}
		
		}while (sexo != 1 || sexo != 0);

		articulo.setCampaña(campaña);
		articulo.setCategoria(categoria);
		articulo.setColor(color);
		articulo.setMarca(m); 
		articulo.setPrecio(precio);
		
		return articulo;
		
	}

	private static Marca selecMarca () throws IOException {
		
		ArrayList <Marca> marcas = dbman.getMarcas();
		System.out.println("Nombre de la marca a la que pertenece ( existente )");
		int j ;
		
		for ( j = 0 ; j < marcas.size() ; j++) {
			
			System.out.println(marcas.get(j).getNombre());
			
		}
		
		String marca;
		
		int i ;
	
		do {
			
		marca = br.readLine();
		for (i=0 ; i< marcas.size(); i++) {
			
			if (marca == marcas.get(i).getNombre()) {
				
				System.out.println("Marca encontrada en la base de datos");
				
			}
			
		}
		
		}while (marcas.get(i).getNombre() != marca);
		
		return marcas.get(i);
		
	}
	
	private static void consultarArticuloPorId() throws IOException {
		
		boolean exito = false;
		int a ;
		
		do {
			
		System.out.println("Introduce un id");
		
		a = br.read(); 
		
		exito = comprobarIdCorrecto (a);
		
		} while ( exito == true);
		
		ArrayList<Articulo> articulo = dbman.searchArticuloByIdArt(a);
		
		System.out.println(articulo.get(0).toString()); // posicion 0 ya que el arraylist solo deberia contener un articulo ya que los id son unicos
		
		
	}

	
	private static void consultarCapital() throws IOException { // como especifico la tienda
		
		consultarInformacionTiendasSinCapital();
		int r = 10;
		Tienda t = new Tienda ();
		
		do {
			
		System.out.println("Seleccione la tienda de la que quiere consultar el capital");
		System.out.println("1- Delfin&Maria n 2- Sterling \n 3- Delfin 1953");
		 
		r = br.read();
		
		switch(r) {
		
		case 1 -> t.setNombreTienda("Delfin&Maria");
		case 2 -> t.setNombreTienda("Sterling");
		case 3 -> t.setNombreTienda("Delfin 1953");
		}
		
		}while  (r != 1 || r != 2 || r != 3);
		
		int capital = dbman.consultarBalance(t);
		
		System.out.println("El capital de la tienda seleccionada es : " + capital);
		
	}

	private static void listarArticulos() { 
		
		ArrayList <Articulo> articulos = new ArrayList <Articulo>();
		
		articulos = dbman.getArticulos();
		
		int i = 0;
		
		for (i = 0 ; i < dbman.getArticulos().size() ; i ++) {
			
			System.out.println(articulos.get(i).toString());
			
		}
		
	}

	private static void consultarInfoTiendasConCapital() {

		int i ;
		
		ArrayList <Tienda> tiendas = new ArrayList <Tienda>();
				
		tiendas = dbman.getInfoTiendasConCapital();
		
		for ( i = 0 ; i < dbman.getInfoTiendasConCapital().size() ; i++) {
			
			System.out.println(tiendas.get(i).toString());
			
		}
		
	}
	
	private static void consultarInformacionTiendasSinCapital() {

		int i ;
		
		ArrayList <Tienda> tiendas = new ArrayList <Tienda>();
				
		tiendas = dbman.getInfoTiendasSinCapital();
		
		for ( i = 0 ; i < dbman.getInfoTiendasSinCapital().size() ; i++) {
			
			System.out.println(tiendas.get(i).toString());
			
		}
		
	}

	private static int mostrarMenu(String[] opciones) {
		
		int respuesta = -1;
		do {
			System.out.println("\nElija una opción:");
			for(int i = 1; i < opciones.length; i++) {
				System.out.println(i + ". " + opciones[i]);
			}
			System.out.println("0. " + opciones[0]);
			try {
				respuesta = Integer.parseInt(br.readLine());
			} catch (IOException e) {
				
			} catch (NumberFormatException e) {
				
			}
			if(respuesta < 0 || respuesta >= opciones.length) {
				System.out.println("Por favor introduzca un número válido");
			}
		} while(respuesta < 0 || respuesta >= opciones.length);
		return respuesta;
		
	}
	

	}

