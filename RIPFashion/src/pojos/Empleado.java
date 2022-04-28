package pojos;

public class Empleado {

	private int id;
	private String tipo;
	private Tienda tienda ;
	
	public Empleado() {
		
		super();
		
	}
	
	public Empleado(int id, String tipo, Tienda tienda) {
		
		super();
		this.id = id;
		this.tipo = tipo;
		this.tienda=tienda;
		
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
		return "Empleado [id=" + id + ", tipo=" + tipo + ", tienda=" + tienda + "]";
	}
	
	

}
