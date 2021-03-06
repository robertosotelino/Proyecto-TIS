package db.interfaces;

import java.util.ArrayList;

import pojos.Articulo;
import pojos.Cliente;
import pojos.Empleado;
import pojos.Marca;
import pojos.Tienda;

public interface DBManager {

	void connect();
	
	void disconnect();
	
	void addArticulo(Articulo a);
	
	void addCliente(Cliente cl);
	
	void addEmpleado (Empleado e);
	
	void addMarca (Marca m);
	
	
	ArrayList<Articulo> getArticulos();
	
    public Articulo searchArticuloByIdArt(int i);
	
	ArrayList<Marca> getMarcas();
	
	ArrayList <Tienda> getInfoTiendasConCapital ();
	
	ArrayList <Tienda> getInfoTiendasSinCapital ();
	
    ArrayList <Empleado> getEmpleados();
	
	boolean deleteArticuloById(int i);
	
	boolean updateArticulo(Articulo a);
	
	int consultarCapital(Tienda t);
	
	ArrayList<Articulo> searchAticuloPorCamapa├▒a ( String color);
	

	ArrayList<Articulo> getArticulosPorMarca(int idM);

	public ArrayList<Marca> getMarcasPorTienda(String nombre);
	
}
