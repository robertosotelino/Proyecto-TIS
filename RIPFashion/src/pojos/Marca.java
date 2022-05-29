package pojos;

import java.util.ArrayList;

@XmlRootElement(name = "Marca")
@XmlAccessorType(XmlAccessType.FIELD)
public class Marca {
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

	@Override
	public String toString() {
		return "Marca [idM=" + idM + ", nombre=" + nombre + ", tienda=" + tienda + "]";
	}
	

	

}
