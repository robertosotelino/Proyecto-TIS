package pojos;

public class Cliente {

	private String nombre;
	private String apellido;
	private String eMail;
	private String direccion;
	
	public Cliente() {
		
		super();
		
	}

	public Cliente(String nombre, String apellido, String eMail, String direccion) {
		
		super();
		this.nombre = nombre;
		this.apellido = apellido;
		this.eMail = eMail;
		this.direccion = direccion;
		
	}

	public String getNombre() {
		
		return nombre;
		
	}
	
	public void setNombre(String nombre) {
		
		this.nombre = nombre;
		
	}
	
	public String getApellido() {
		
		return apellido;
	}
	
	public void setApellido(String apellido) {
		
		this.apellido = apellido;
		
	}
	
	public String geteMail() {
		
		return eMail;
		
	}
	public void seteMail(String eMail) {
		
		this.eMail = eMail;
		
	}
	
	public String getDireccion() {
		
		return direccion;
		
	}
	public void setDireccion(String direccion) {
		
		this.direccion = direccion;
		
	}

	@Override
	public String toString() {
		
		return "Cliente [nombre=" + nombre + ", apellido=" + apellido + ", eMail=" + eMail + ", direccion=" + direccion
				+ "]";
		
	}
	
	
}
