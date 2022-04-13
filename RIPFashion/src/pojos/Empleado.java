package pojos;

public class Empleado {

	private int id;
	private String tipo;
	
	public Empleado() {
		
		super();
		
	}
	
	public Empleado(int id, String tipo) {
		
		super();
		this.id = id;
		this.tipo = tipo;
		
	}

	public int getId() {
		
		return id;
		
	}
	
	public void setId(int id) {
		
		this.id = id;
		
	}
	
	public String getTipo() {
		
		return tipo;
		
	}
	public void setTipo(String tipo) {
		
		this.tipo = tipo;
	}
	
	@Override
	public String toString() {
		
		return "Empleado [id=" + id + ", tipo=" + tipo + "]";
		
	}
	

}
