package pojos;

public class Articulo {
	
    private int idArt;
	private String categoria;
	private String campaña;
	private String color;
	private boolean sexo;
	private int precio;
	
	public Articulo() {
	
	}

	public Articulo(int idArt, String categoria, String campaña, String color, boolean sexo, int precio) {
		
		super();
		this.idArt = idArt;
		this.categoria = categoria;
		this.campaña = campaña;
		this.color = color;
		this.sexo = sexo;
		this.precio = precio;
		
	}

	
	public String getCategoria() {
		
		return categoria;
		
	}
	
	public void setCategoria(String categoria) {
		
		this.categoria = categoria;
		
	}
	
	public int getIdArt() {
		
		return idArt;
	}
	
	public void setIdArt(int idArt) {
		
		this.idArt = idArt;
		
	}
	
	public String getCampaña() {
		
		return campaña;
	}
	
	public void setCampaña(String campaña) {
		
		this.campaña = campaña;
		
	}
	
	public String getColor() {
		
		return color;
		
	}
	
	public void setColor(String color) {
		
		this.color = color;
		
	}
	
	public boolean getSexo() {
		
		return sexo;
		
	}
	
	public void setSexo(boolean sexo) {
		
		this.sexo = sexo;
		
	}
	
	public int getPrecio() {
		
		return precio;
		
	}
	
	public void setPrecio(int precio) {
		
		this.precio = precio;
		
	}

	@Override
	public String toString() {
		
		return "Articulo [categoria=" + categoria + ", idArt=" + idArt + ", campaña=" + campaña + ", color=" + color
				+ ", sexo=" + sexo + ", precio=" + precio + "]";
		
	}
	
	
	
	
}
