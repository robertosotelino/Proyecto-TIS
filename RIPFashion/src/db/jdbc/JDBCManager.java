package db.jdbc;


import pojos.Articulo;
import pojos.Cliente;
import pojos.Empleado;
import pojos.Marca;
import pojos.Tienda;
import java.util.logging.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Scanner;

import db.interfaces.DBManager;
import factory.Factory;




public class JDBCManager implements DBManager {

    private static Connection c;
    final static Logger LOGGER = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME); 
    private static final String DB_LOCATION = "./db/RIPFashion.db";
    private static final String ficheroInicializacion = "./db/ddl.sql";

    // codigo SQL a usar ¡¡¡ CONSULTAS SQL PROBAR EN LA BASE DE DATOS !!! 

    private static Statement stmt;

    private static final String sqlAddArticulo = "INSERT INTO Articulos (Categoria,Campaña,Color,Sexo,Precio) VALUES (?,?,?,?,?);";

    private static final String sqlAddCliente = "INSERT ITO Clientes(Nombre,Apellido,Mail,Direccion) VALUES (?,?,?,?);";
    private static final String sqlAddEmpleado = "INSERT INTO Empleados (Tipo, NombreTienda) VALUES (?,?);";
    private static final String sqlAddMarca = "INSERT INTO Marcas (Nombre) VALUES (?);";
    private static final String sqlAddTienda = "INSERT INTO Tiendas (NombreTienda,Horario,Ubicacion,Categoria,CapitalTienda) VALUES (?,?,?,?,?);";
    

    private static final String sqlGetArticulos = "SELECT * FROM Articulos;";
    private static final String sqlSearchArticuloByIdArt = "SELECT * FROM Articulos WHERE Id_art=?;";
    private static final String sqlDeleteArticuloById = "DELETE FROM Articulos WHERE Id_art=?;";
    private static final String sqlUpdateArticulo = "UPDATE Articulos SET Categoria=?, Campana=?, Color=?,Sexo=?, Precio=? WHERE Id_art=?;";
    private static final String sqlGetMarcas =  "SELECT * FROM Marcas;";
    private static final String sqlGetTiendas = "SELECT * FROM Tiendas;";
    private static final String sqlGetBalance = "";
    private static final String sqlGetEmpleados = "";
    private static final String sqlCountElementsFromTable = "SELECT COUNT(*) AS Count FROM ";
   
    
    private static final int NUM_CLIENTES = 10;
    private static final int NUM_EMPLEADOS = 10;
    private static final int MARCAS = 10;
    private static final int ARTICULOS = 10;
    
    /*
     * Conexion con la base de datos
     */
    public void connect() {
    	
    try {
    	
        Class.forName("org.sqlite.JDBC");
        c = DriverManager.getConnection("jdbc:sqlite:" + DB_LOCATION);
        stmt = c.createStatement();
        createTables();
        inicializarTablas();
        LOGGER.info("Se ha configurado la base de datos con éxito");

    } catch (ClassNotFoundException | SQLException e) {
        
    	LOGGER.severe("Error al inicializar la base de datos\n" + e.toString());
    }
    
 }
    /*
     * Desconexion con la base de datos
     */
    public void disconnect() {

        try {
        	System.out.println("hola");
            stmt.close();
            c.close();
            LOGGER.info("Se ha desconectado de la base de datos con éxito");
            
        } catch (SQLException e) {
            
        	LOGGER.warning("Error al desconectarse la base de datos\n" + e.toString());
        	
        }
    }
    
	private void createTables() throws SQLException{
		File file = new File(ficheroInicializacion);
	    try (Scanner scanner = new Scanner(file)){
	    	String sqlInicializacion = "";
	    	while (scanner.hasNextLine()) {
	    		sqlInicializacion += scanner.nextLine();
	    	}
	    	stmt.executeUpdate(sqlInicializacion);
		} catch (FileNotFoundException e) {
			
			LOGGER.severe("Error al leer fichero sql\n" + e.toString());
		}
	}
	
    private void inicializarTablas() { // para insertar valores en las tablas
    	
    	ArrayList <Marca> marcas = new ArrayList <Marca>();
    	
        if (countElementsFromTable("Clientes") == 0) {
        	
            for (int i = 0; i < NUM_CLIENTES; i++) {
            	
                addCliente(Factory.generarClienteAleatorio());
                
            }
        }
        
        if (countElementsFromTable("Empleados") == 0) {
        	
            for(int i = 0; i < NUM_EMPLEADOS; i++) {
            	
                addEmpleado(Factory.generarEmpleadoAleatorio());
            }   
        }
            
        if (countElementsFromTable("Marcas") == 0) {
            	
             for(int i = 0; i < MARCAS; i++) {
                	
              Marca m = Factory.generarMarcasAleatorias();
              
              marcas.add(m);
              addMarca(m);
              
            }
        }
    
        
        if (countElementsFromTable("Articulos") == 0) {
         	
    		for(int i = 0; i < ARTICULOS; i++) {
                	
            Articulo a = Factory.generarArticuloAleatorio();
            
            a.setMarca(marcas.get(i));
            
            addArticulo(a); // cuando en factory genero un articulo, 
       }
    		
    }
        
        
        
        }
        
    
           
    /*
     * Añado un articulo nuevo
     */
    public void addArticulo(Articulo a) {
    	
        try {
        	
        	PreparedStatement prep = c.prepareStatement(sqlAddArticulo);
        	prep.setString(1, a.getCategoria());
        	prep.setString(2, a.getCampaña());
        	prep.setString(3, a.getColor());
        	prep.setBoolean(4, a.getSexo());
        	prep.setInt(5, a.getPrecio());
        	prep.setInt(6, a.getMarca().getIdM());
        	prep.executeUpdate();
            prep.close();

        } catch (SQLException e) {
        	     
        }
        
    }
    
    /*
     * Añado un cliente
     */
    public void addCliente(Cliente cl) {
    	
        try {
        	
            PreparedStatement prep = c.prepareStatement(sqlAddCliente);
            prep.setString(1, cl.getNombre());
            prep.setString(2, cl.getApellido());
            prep.setString(3, cl.geteMail());
            prep.setString(4, cl.getDireccion());
            prep.executeUpdate();
            prep.close();
            
        } catch (SQLException e) {
           
        }
        
        	
        }
    
    /*
     * Añado un empleado a la base de datos
     */
    public void addEmpleado (Empleado e) {
    	
           try {
        	
            PreparedStatement prep = c.prepareStatement(sqlAddEmpleado);
            prep.setString(1, e.getTipo());
            prep.setString(2, e.getTienda().getNombreTienda()); 
            prep.executeUpdate();
            prep.close();
            
           } catch (SQLException e1) {
               
           }

    }
    
    /*
     * Añado una marca a mi base de datos
     */
    public void addMarca (Marca m) {
    	
    	try {
    		
    		PreparedStatement prep = c.prepareStatement(sqlAddMarca);
    		prep.setString(1, m.getNombre());
    		
    	}catch (SQLException e) {
    		
    	}
    	
    }
    
    /*
     * Añado una tienda a mi base de datos
     */
    public void addTienda (Tienda t) {
    	
    	try {
    		
    		PreparedStatement prep = c.prepareStatement(sqlAddTienda);
    		prep.setString(1, t.getNombreTienda());
    		prep.setString(2, t.getHorario());
    		prep.setString(3, t.getUbicacion());
    		prep.setString(4, t.getCategoria());
    		prep.setInt(5, t.getCapitalTienda());
    		prep.executeUpdate();
            prep.close();

    	}catch (SQLException e) {
    		
    	}
    	
    }
    
    /*
     * Obtengo los articulos
     */
    public ArrayList<Articulo> getArticulos() { 
    	
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        
        try {
        	
            ResultSet rs = stmt.executeQuery(sqlGetArticulos);
            
            while(rs.next()){
            	
            
            	int ida = rs.getInt("IdArt");
            	int idM = rs.getInt("IdMarca");
            	String categoria = rs.getString("Categoria");
            	String campana = rs.getString("Campaña");
            	String color = rs.getString("Colo");
            	boolean sexo = rs.getBoolean("Sexo");
            	int precio = rs.getInt("Precio");
            	
            	Marca m = new Marca();
            	m.setIdM(idM);
            	Articulo a = new Articulo (ida , m , categoria, campana, color, sexo,precio );
            	articulos.add(a);
                
            }
            
            rs.close();
            
        } catch (SQLException e) {
            
        }
        
        return articulos;
        
    }

    /*
     * Busco un articulo en funcion de un id conocido
     */
    public ArrayList<Articulo> searchArticuloByIdArt(int i) {
    	
        ArrayList<Articulo> articulos = new ArrayList<Articulo>();
        
        try (PreparedStatement prep = c.prepareStatement(sqlSearchArticuloByIdArt)){
        	
            prep.setString(1, "%" + i + "%");
            
            ResultSet rs = prep.executeQuery();
            
            while (rs.next()) {
            	
              int id = rs.getInt("IdArt");
              int idM = rs.getInt("IdMarca");
              String categoria = rs.getString("Categoria");
              String campaña = rs.getString("Campaña");
              String color = rs.getString("Color");
              boolean sexo = rs.getBoolean("sexo");
              int precio = rs.getInt("Precio");
              Marca marca = new Marca();
              marca.setIdM(idM);
              Articulo a = new Articulo(id, marca, categoria, campaña, color , sexo ,precio);
              articulos.add(a);
              
            }
            
            rs.close();
            
        } catch (SQLException e) {
           
        }
        
        return articulos;
        
    }
    
    /*
     * Obtengo las marcas
     */
    public ArrayList<Marca> getMarcas() {
    	
    	ArrayList<Marca> marcas = new ArrayList<Marca>();
    	
         try (ResultSet rs = stmt.executeQuery(sqlGetMarcas)){
            
            while (rs.next()) {
            	
              int id = rs.getInt("IdMarca");
              String nombre = rs.getString("NombreMarca");
              Tienda tienda = new Tienda ();
              String nombreTienda = rs.getString("NombreTienda");
              tienda.setNombreTienda(nombreTienda);
              
              marcas.add(new Marca(id, nombre, tienda));
              
            }
            
            rs.close();
            
        } catch (SQLException e) {
           
        }
        
        return marcas;
    	
    }
    
    /*
     * Obtengo informacion sobre las tiendas con capital ( para administrador )
     */
    public ArrayList <Tienda> getInfoTiendasConCapital (){
    	
    	ArrayList <Tienda> tiendas = new ArrayList <Tienda>();
    	
        try (ResultSet rs = stmt.executeQuery(sqlGetTiendas)){
            
            while (rs.next()) {
            	
              String nombreTienda = rs.getString("NombreTienda");
              int capital = rs.getInt("Capital");
              String horario = rs.getString("Horario");
              String ubicacion = rs.getString("Ubicacion");
              String categoria = rs.getString("Categoria");
              
              
              tiendas.add(new Tienda(nombreTienda, horario, ubicacion , categoria, capital));
              
            }
            
            rs.close();
            
        } catch (SQLException e) {
           
        }
        
        return tiendas;
    	
    	
    }
    
    /*
     * Obtengo informacion sobre las tiendas sin capital ( para clientes )
     */
    public ArrayList <Tienda> getInfoTiendasSinCapital (){
    	
    	ArrayList <Tienda> tiendas = new ArrayList <Tienda>();
    	
        try (ResultSet rs = stmt.executeQuery(sqlGetTiendas)){
            
            while (rs.next()) {
            	
              String nombreTienda = rs.getString("NombreTienda");
              String horario = rs.getString("Horario");
              String ubicacion = rs.getString("Ubicacion");
              String categoria = rs.getString("Categoria");

              
              tiendas.add(new Tienda(nombreTienda, horario, ubicacion , categoria));
              
            }
            
            rs.close();
            
        } catch (SQLException e) {
        	
           
        }
        
        return tiendas;
    	
    	
    }
    
    public ArrayList <Empleado> getEmpleados(){
    	
    	ArrayList <Empleado> empleados = new ArrayList <Empleado>();
    	
        try (ResultSet rs = stmt.executeQuery(sqlGetEmpleados)){
            
            while (rs.next()) {
            	
              int id = rs.getInt("IdEmpleado");
              String tipo = rs.getString("Tipo");
              Tienda tienda = new Tienda();
              String nombre = rs.getString("NombreTienda");
              tienda.setNombreTienda(nombre);

              empleados.add(new Empleado(id, tipo, tienda));
              
            }
            
            rs.close();
            
        } catch (SQLException e) {
           
        }
        
        return empleados;
    	
    }
    

    /*
     * Eliminar un articulo de la base de datos en funcion de un id conocido
     */
    public boolean deleteArticuloById(int i) {
    	
        boolean exito = false;
        
        try (PreparedStatement prep = c.prepareStatement(sqlDeleteArticuloById)){
        	
            prep.setInt(1, i);
            
            int resultado = prep.executeUpdate();
            
            if (resultado == 1) {
            	
                exito = true;
                
            }
        } catch (SQLException e) {
          
        }
        
        return exito;
        
    }

    /*
     * Actualizo un articulo de la base de datos
     */
    public boolean updateArticulo(Articulo a) {
    	
        boolean exito = false;
        
        try (PreparedStatement prep = c.prepareStatement(sqlUpdateArticulo)){
        	
        	prep.setInt(1, a.getIdArt());
            prep.setString(2, a.getCategoria());
            prep.setString(3, a.getCampaña());
            prep.setString(4, a.getColor());
            prep.setBoolean(5, a.getSexo());
            prep.setDouble(6, a.getPrecio());
            
            int resultado = prep.executeUpdate();
            
            if (resultado == 1) {
            	
                exito = true;
                
            }
            
        } catch (SQLException e) {
            
        }
        
        return exito;
        
    }
    
    
    public int consultarBalance(Tienda t) { // ?????
    	
        
        int capital = 0;
        
        try (PreparedStatement prep = c.prepareStatement(sqlGetBalance)){
        	
        	prep.setInt(1, t.getCapitalTienda());
            
            
            capital = t.getCapitalTienda();
            

         } catch (SQLException e) {
             
         }
        
        return capital;
    		
    }

    public int countElementsFromTable(String nombreTabla) {
        int numElementos = 0;
        try (ResultSet rs = stmt.executeQuery(sqlCountElementsFromTable + nombreTabla + ";");){
            numElementos = rs.getInt("Count");
            rs.close();
        } catch (SQLException e) {
          
        }
        return numElementos;
    }
    
    
}

    




    
    








    
    




