package pojos;

public class Marca {
	
	private int idM;
	private String nombre;
	private Tienda tienda;
	//private Articulo articulo;
	public Marca() {
		
		super();
		
	}

	public Marca(int idM, String nombre, Tienda tienda) {
		
		super();
		this.idM = idM;
		this.nombre = nombre;
		this.tienda = tienda;
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

	@Override
	public String toString() {
		return "Marca [idM=" + idM + ", nombre=" + nombre + ", tienda=" + tienda + "]";
	}
	

	

}
