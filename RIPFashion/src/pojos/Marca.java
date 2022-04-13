package pojos;

public class Marca {
	
	private int idM;
	private String nombre;
	
	
	public Marca() {
		
		super();
		
	}

	public Marca(int idM, String nombre) {
		
		super();
		this.idM = idM;
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
	
	@Override
	public String toString() {
		return "Marca [nombre=" + nombre + ", idM=" + idM + "]";
	}
	
	

}
