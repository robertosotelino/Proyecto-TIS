package ui;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import db.interfaces.DBManager;
import db.interfaces.UsuariosManager;
import db.jdbc.JDBCManager;
import db.jpa.JPAUsuariosManager;
import pojos.Articulo;
import pojos.Cliente;
import pojos.Empleado;
import pojos.Marca;
import pojos.Rol;
import pojos.Tienda;
import logging.MyLogger;
import pojos.Usuario;
import xml.testTienda;

import java.util.logging.*;

import javax.xml.bind.JAXBException;

public class Menu {
	
	private final static testTienda testT = new testTienda();
	private final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);
	private static DBManager dbman = new JDBCManager(); 
	private static UsuariosManager userman = new JPAUsuariosManager();
	private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	private static final String[] MENU_ROL = { "-Salir del programa", "-Rol Empresario","-Rol Cliente"};
	private static final String[] MENU_EMPRESARIO = {"- Salir" ,"-Consultar informacion de la tienda ","- Listar Articulos", 
			"- Consultar capital","- Consultar articulo por id", "- Añadir un articulo",
			"- Eliminar un articulo","- Modificar articulo","- Añadir marca", "- Listar empleados"
			,"Añadir empleado","marshalling", "unMarshalling"};
	private static final String [] MENU_CLIENTE = {"-Salir ", "Consultar informacion de las tiendas",
			"Buscar articulos por sexo"};
	static Usuario usuario;
	
	
	public static void main (String[] args) throws IOException, JAXBException {
		MyLogger.setupFromFile(); 
		dbman.connect();
		userman.connect();
		System.out.println("Bienvenido a nuestras tiendas");
	
		int respuesta = -1;
		do {
			
			respuesta = mostrarMenu(MENU_ROL);// solo para verificar que funcionan los metodos sin pasar por el login
			//respuesta = mostrarMenu(MENU_INICIO);
		//	LOGGER.fine("El usuario ha seleccionado la opcion " + respuesta + " en el menu principal");
		
			switch(respuesta) {
			// solo para verificar que funcionan los metodos sin pasar por el login
			case 1-> mostrarMenuEmpresario();
			case 2 -> mostrarMenuCliente();
			// solo para verificar que funcionan los metodos sin pasar por el login
			//case 1 -> registrarse();
			//case 2 -> login();
		}
			
	} while(respuesta != 0);
		
			System.out.println("Saliendo.... Gracias");
			
			dbman.disconnect();
		
	
		}
	
	private static void login() throws JAXBException {
		
		try {
			
			System.out.println("Indique su email:");
			String email = br.readLine();
			System.out.println("Indique su contraseña:");
			String pass = br.readLine();
			usuario = userman.checkLogin(email, pass);
			
			if (usuario == null) {
				
				System.out.println("Email o contraseña incorrectos");
				
			} else {
				
				switch(usuario.getRol().getNombre()) {
				
					case "cliente" -> mostrarMenuCliente();
					case "empresario" -> mostrarMenuEmpresario();

				}
			}
			
		} catch(IOException e) {
			
			LOGGER.warning("Error en el registro\n" + e);
			
		}
	}

	private static void mostrarMenuCliente() throws NumberFormatException, IOException {
		
		System.out.println("\n\n*****MENU DEL Cliente*****\n\n");
		
		int respuesta = -1;
		
		do {
			
			respuesta = mostrarMenu(MENU_CLIENTE);
			
			switch(respuesta) {
			
				case 1 -> consultarInformacionTiendasSinCapital();
				case 2 -> buscarArticulosPorSexo();
			}
			
		} while(respuesta != 0);
		
		System.out.println(" \n\n Gracias, tenga un buen dia \n\n");
		
		
	}

	private static void buscarArticulosPorSexo() {
		
		try {
			
			System.out.println("\nIndique el sexo de los articulos deseados");
			System.out.println("\n 0 : hombre \n 1 : mujer\n");
		
			boolean sexo = Boolean.parseBoolean(br.readLine());
			
			if (sexo == false) {
			
			ArrayList <Articulo> articulos = dbman.searchArticuloPorSexo(sexo);
			
			for ( int i = 0 ; i < articulos.size() ; i ++) {
				
			System.out.println(articulos.get(i).toString());
			
			}
			
			} else if (sexo == true) {
				
			ArrayList <Articulo> articulos = dbman.searchArticuloPorSexo(sexo);
			
			for ( int i = 0 ; i < articulos.size() ; i ++) {
				
			System.out.println(articulos.get(i).toString());
			
			}
			
			} else {
				
				System.out.println("Asegurese de indicar un valor adecuado");
			}
			
		} catch (IOException e) {
			
			System.out.println("Error al introducir el sexo , asegurese de introducirlo correctamente");
		}
	}

	

	private static void registrarse() {
		
		try {
			
			System.out.println("Indique su email:");
			String email = br.readLine();
			System.out.println("Indique su contraseña:");
			String pass = br.readLine();
			MessageDigest md = MessageDigest.getInstance("MD5"); // oculto la contraseña
			md.update(pass.getBytes());
			byte[] hash = md.digest();
			System.out.println("Indique su nombre:");
			String nombre = br.readLine();
			System.out.println("Indique sus apellidos:");
			String apellidos = br.readLine();
			System.out.println("Indique su direccion");
			String direccion = br.readLine();
			System.out.println(userman.getRoles());
			System.out.println("Indique el id del rol:");
			int rolId = Integer.parseInt(br.readLine());
			// bucle
			Rol rol = userman.getRolById(rolId);
			Usuario usuario = new Usuario(email, hash, rol);
			rol.addUsuario(usuario);
			userman.addUsuario(usuario);
			
			
			if(rol.getNombre().equals("cliente")) {
				
				Cliente cliente = new Cliente(nombre, apellidos, email, direccion);
				dbman.addCliente(cliente);
				
			} else {
				
				añadirEmpleado();
				
			}
			
		} catch(IOException | NoSuchAlgorithmException e) {
			
			LOGGER.warning("Error en el registro\n" + e);
			
		}
	}
	
	
	private static void mostrarMenuEmpresario() throws IOException, JAXBException {
		
		System.out.println("\n\n*****MENU DEL EMPRESARIO*****\n\n");
		
		int respuesta = -1;
		
		do {
			
			respuesta = mostrarMenu(MENU_EMPRESARIO);
			
			switch(respuesta) {
				case 0 -> respuesta = 0;
				case 1 -> consultarInfoTiendasConCapital(); // done
				case 2 -> listarArticulos(); // done
				case 3 -> consultarCapital(); // done
				case 4 -> consultarArticuloPorId(); // done
				case 5 -> añadirArticulo();  // done
				case 6 -> eliminarArticulo(); // done
				case 7 -> modificarArticulo(); // 
				case 8 -> newMarca(); // 
				case 9 -> listarEmpleados (); // done
				case 10 -> añadirEmpleado (); // done
				case 11 -> testT.marshalling();
				case 12 -> testT.unMarshalling();
		

			}
			
		} while(respuesta != 0);
		
	}

	private static void añadirEmpleado() throws IOException {
		
		System.out.println("\n ¿Cual sera el puesto del empleado? \n");
		
		String tipo = br.readLine();
		 
		int r ;
		int i;
		Tienda t = new Tienda ();
		
		do {
			
			System.out.println("Seleccione la tienda en la que va a trabajar el nuevo empleado");
			
			ArrayList <Tienda> tiendas = dbman.getInfoTiendasSinCapital();
			
			for ( i= 0 ; i < tiendas.size() ; i++) {
				
				System.out.println("Tienda : "+i+tiendas.get(i).toString());
			}
			 
			r = Integer.parseInt(br.readLine());
			
			switch(r) {
			
			case 0 -> t = tiendas.get(0);
			case 1 -> t = tiendas.get(1);
			case 2 -> t = tiendas.get(2);
			
			}
			
		}while (r < 0 || r > 2);
		
		Empleado e = new Empleado ();
		System.out.println("\n\nLa tienda donde va a trabajar es "+t.getNombreTienda());
		e.setTienda(t);
		e.setTipo(tipo);
		
		dbman.addEmpleado(e);
		System.out.println("Empleado añadido");
	}

	private static void listarEmpleados() {
		
		ArrayList <Empleado> empleados = dbman.getEmpleados();
		
		System.out.println("\n Información de empleados \n");
		
		int i ;
		
		for (i=0 ; i < empleados.size() ; i++) {
			
			System.out.println(empleados.get(i).toString());
			
		}
		
		
	}

	private static void newMarca()  { 
		
		
		Marca marca = new Marca ();
		
		System.out.println("Introduzca el nombre de la marca");
		
		String nombre;
		
		try {
			
			nombre = br.readLine();	
		
			marca.setNombre(nombre);
		
		
		
		System.out.println("A que tienda quiere que pertenezca"); 
		
		ArrayList <Tienda> tiendas = dbman.getInfoTiendasSinCapital();
		
		System.out.println();
		int respuesta ;
		boolean exito = false ;
		
		do {
			
		for (int i=0 ; i < tiendas.size(); i++) {
			
			System.out.println(i + " : " +tiendas.get(i).getNombreTienda());  
			
		}
		
		respuesta = Integer.parseInt(br.readLine());
		
		switch (respuesta) {
		
		case 0 -> marca.setTienda(tiendas.get(0));
		case 1 -> marca.setTienda(tiendas.get(1));
		case 2 -> marca.setTienda(tiendas.get(2));
		
		}
	
		
		if (respuesta == 0 || respuesta ==1 || respuesta == 2) {
			
			dbman.addMarca(marca);
			exito = true;
			
		}

		
		}while (!exito);

		
		} catch (IOException e) {
			
			LOGGER.severe("Error al añadir la marca \n" + e.toString());
			
		}
				
	}	
	private static void modificarArticulo() throws IOException {
		

		int idA ;
		Articulo a = new Articulo();
		listarArticulos();
		boolean exito;
		
		do {
			System.out.println("Que artículo quieres modificar ( introduce su id )");
			idA = Integer.parseInt(br.readLine());br.read();
			System.out.println("ID DEL ARTICULO"+idA);
			exito = comprobarIdCorrectoArticulo(idA);
			
		}while(exito !=true);
		
		System.out.println("El id es correcto");
		
		ArrayList <Articulo> articulo = dbman.searchArticuloByIdArt (idA);
		
		for ( int f = 0 ; f < articulo.size() ; f ++) {
			
			a = articulo.get(f);
			
		}
		
		
		Articulo aModificado = updateArticulo (a);
		
		boolean b = dbman.updateArticulo(aModificado);
		
		if (b == true) {
			
			System.out.println("Articulo modificado correctamente");
			
		} else {
			
			System.out.println("No ha sido posible modificar el articulo; intentelo de nuevo");
		}
		
	}
	
	private static Articulo updateArticulo(Articulo articulo) {
		
		try {
			
		System.out.println("Categoria del artículo :");
		
		String categoria;
		
		categoria = br.readLine();
		
		
		System.out.println("Campaña del articulo");
		
		String campaña = br.readLine();
		
		System.out.println("Color del articulo");
		
		String color = br.readLine();
		
		System.out.println("Precio del articulo ( numero entero )");
		
		int precio = Integer.parseInt(br.readLine());
		
		Marca m = selecMarca();
			
		int sexo;
		boolean exito = false;
		
		do {
		
		System.out.println("Sexo (introduzca 1 si es para hombre o 0 si es para mujer)");
		
		sexo = Integer.parseInt(br.readLine());
		
		System.out.println("VALOR "+sexo);
		if (sexo == 1) {
				
			articulo.setSexo(true);
			exito = true;
			System.out.println("BOOLEAN 1" + exito);
		}else if( sexo == 0) {
			
			articulo.setSexo(false);
			exito = true;
			System.out.println("BOOLEAN 2" + exito);
		}else  {
			
			System.out.println("No ha introducido un dato válido");
			
		}

		System.out.println("BOOLEAN"+exito);
		}while (exito != true);
		
		articulo.setCampaña(campaña);
		articulo.setCategoria(categoria);
		articulo.setColor(color);
		articulo.setMarca(m); 
		articulo.setPrecio(precio);
		
		} catch (IOException e) {
			
			System.out.println("Error al añadir un articulo"+ e);
		}
		return articulo;
	}
	
	private static void eliminarArticulo() throws IOException { 
		
		listarArticulos();
		boolean exito = false;
		int id ;
		
		do {
			
		System.out.println("Id del articulo que desea eliminar");
		
	    id = Integer.parseInt(br.readLine());
		
		exito = comprobarIdCorrectoArticulo(id);
		
		}while(exito != true);
		
		dbman.deleteArticuloById(id);
		
		System.out.println("Articulo eliminado correctamente");
		
	}
	
	
	private static boolean comprobarIdCorrectoArticulo (int id) {
		
        ArrayList <Articulo> articulos = dbman.getArticulos();
        
        int i ;
        boolean exito= false;
        
        for ( i = 0 ; i < articulos.size(); i ++) {
        	
        	if ( articulos.get(i).getIdArt()== id) {
        		
        		exito = true;
        		
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
		
		int precio = Integer.parseInt(br.readLine());
		
		Marca m = selecMarca();
			
		int sexo = 5;
		boolean exito = false;
		do {
			
			System.out.println("Sexo (introduzca 1 si es para hombre o 0 si es para mujer)");
			
			sexo = Integer.parseInt(br.readLine());
			
			if (sexo == 1) {
					
				articulo.setSexo(true);
				exito = true;
				
			}else if( sexo == 0) {
				
				articulo.setSexo(false);
				exito = true;
				
			}else  {
				
				System.out.println("No ha introducido un dato válido");
				
			}

		
			}while (exito != true);

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
		
	
		Marca m = new Marca ();
		boolean exito = false;
		do {
		marca = br.readLine();
		
		for (int i=0 ; i< marcas.size(); i++) {
			
			if (marca.equalsIgnoreCase(marcas.get(i).getNombre())) { // por si el usuario la introduce en mayusculas/ minusculas
				
				System.out.println("Marca encontrada en la base de datos");
				exito = true;
				m = marcas.get(i);
				
			}
			
		}
		
		}while (exito!=true);
		
		return m;
	}
	
	private static void consultarArticuloPorId() throws IOException {
		
		boolean exito = false;
		int a ;
		
		do {
			
		System.out.println("Introduce un id");
		
		a = Integer.parseInt(br.readLine());
		
		exito = comprobarIdCorrectoArticulo (a);
		
		} while ( !exito);
		
		ArrayList<Articulo> articulo = dbman.searchArticuloByIdArt(a);
		
		for ( int j = 0 ; j < articulo.size() ; j++ ) {
			
			System.out.println(articulo.get(j).toString());
			
		}
		
		
		
	}

	
	private static void consultarCapital() throws IOException { 
		
		boolean exito = false ;
		int r ;
		int i;
		Tienda t ;
		
		ArrayList <Tienda> tiendas = new ArrayList <Tienda>();

		do {
			
		tiendas = dbman.getInfoTiendasConCapital();
			
		for ( i = 0 ; i < dbman.getInfoTiendasConCapital().size() ; i++) {
				
			System.out.println(i + ":"+tiendas.get(i).getNombreTienda());
				
		}
			
		r = Integer.parseInt(br.readLine());
		
		if (r == 0|| r == 1 ||r == 2) {
			
			switch(r) {
			
			case 0 :
				
				t =tiendas.get(0);
				int capital = dbman.consultarCapital(t);
				System.out.println("El capital de la tienda seleccionada es : " + capital);
				break;
				
			case 1 :
				
				t = tiendas.get(1);
				int c = dbman.consultarCapital(t);
				System.out.println("El capital de la tienda seleccionada es : " + c);
				break;
				
			case 2 :
				
				t = tiendas.get(2);
				int ca = dbman.consultarCapital(t);
				System.out.println("El capital de la tienda seleccionada es : " + ca);
				break;
			
			}
			
			exito = true;
			
		} else {
			
			exito = false;
		}
			
		
		}while  (exito != true);
	
		
		
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
	
	private static void consultarInformacionTiendasSinCapital() { // HACER TO STRING SIN CAPI

		int i ;
		
		ArrayList <Tienda> tiendas = new ArrayList <Tienda>();
				
		tiendas = dbman.getInfoTiendasSinCapital();
		
		for ( i = 0 ; i < dbman.getInfoTiendasSinCapital().size() ; i++) {
			
			System.out.println(i +"-"+tiendas.get(i).toStringSinCapital());
			
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

