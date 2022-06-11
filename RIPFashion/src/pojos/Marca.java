package pojos;

import java.io.Serializable;
import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Marca")
@XmlAccessorType(XmlAccessType.FIELD)
public class Marca implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = -1324200261531760390L;
	/**
	 * 
	 */
	
	@XmlElement
	private int idM;
	@XmlElement
	private String nombre;
	@XmlTransient
	private Tienda tienda;
	@XmlElement(name = "Articulo")
	@XmlElementWrapper(name = "Articulo")
	private ArrayList<Articulo> articulos;

	public Marca() {
		
		super();
		
	}

	public Marca(int idM, String nombre, Tienda tienda) {
		
		super();
		this.idM = idM;
		this.nombre = nombre;
		this.tienda = tienda;
	}

	public Marca(String nombre, Tienda tienda) {
		
		super();
		this.nombre = nombre;
		this.tienda = tienda;
	}
	
	public Marca(String nombre) {
		
		super();
		this.nombre = nombre;
		
	}

	public String getNombre() {
		
		return nombre;
		
	}
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public int getIdM() {
		
		return idM;
		
	}
	
	public void setIdM(int idM) {
		
		this.idM = idM;
	}
	
	

	public Tienda getTienda() {
		
		return tienda;
		
	}

	public void setTienda(Tienda tienda) {
		
		this.tienda = tienda;
		
	}
	
	public void addArticulo(Articulo a) {
		if(!articulos.contains(a))
			articulos.add(a);
	}
	
	

	public ArrayList<Articulo> getArticulos() {
		return articulos;
	}

	public void setArticulos(ArrayList<Articulo> articulos) {
		this.articulos = articulos;
	}

	@Override
	public String toString() {
		return "\n Marca (idM) : " + idM + "\n Nombre : " + nombre + 
				"\n Tienda : " + tienda.getNombreTienda() ;
	}
	

	

}
