package pojos;

public class Tienda {

	private String nombreTienda;
	private String horario;
	private String ubicacion;
	private String categoria;
	private int capitalTienda;
	
	public Tienda() {
		
		super();
		
	}

	public Tienda(String nombreTienda, String horario, String ubicacion, String categoria, int capitalTienda) {
		
		super();
		this.nombreTienda = nombreTienda;
		this.horario = horario;
		this.ubicacion = ubicacion;
		this.categoria = categoria;
		this.capitalTienda = capitalTienda;
		
	}

	public String getNombreTienda() {
		
		return nombreTienda;
		
	}
	
	public void setNombreTienda(String nombreTienda) {
		
		this.nombreTienda = nombreTienda;
	}
	
	public int getCapitalTienda() {
		
		return capitalTienda;
		
	}
	
	public void setCapitalTienda(int capitalTienda) {
		
		this.capitalTienda = capitalTienda;
		
	}
	
	public String getHorario() {
		
		return horario;
		
	}
	
	public void setHorario(String horario) {
		
		this.horario = horario;
		
	}
	
	public String getUbicacion() {
		
		return ubicacion;
		
	}
	public void setUbicacion(String ubicacion) {
		
		this.ubicacion = ubicacion;
		
	}
	
	public String getCategoria() {
		
		return categoria;
		
	}
	public void setCategoria(String categoria) {
		
		this.categoria = categoria;
		
	}

	@Override
	public String toString() {
		
		return "Tienda [nombreTienda=" + nombreTienda + ", capitalTienda=" + capitalTienda + ", horario=" + horario
				+ ", ubicacion=" + ubicacion + ", categoria=" + categoria + "]";
		
	}
	
	
	
}
