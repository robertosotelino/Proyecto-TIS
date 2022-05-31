package pojos;

import java.io.Serializable;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@XmlRootElement(name = "Articulo")
@XmlAccessorType(XmlAccessType.FIELD)
public class Articulo implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = -5483181536256859033L;
	@XmlElement
    private int idArt;
    @XmlElement
	private String categoria;
	@XmlElement
	private String campaña;
	@XmlElement
	private String color;
	@XmlElement
	private boolean sexo;
	@XmlElement
	private int precio;
	@XmlTransient
	private Marca marca;
	
	public Articulo() {
	
	}

	public Articulo(int idArt, Marca marca,String categoria, String campaña, String color, boolean sexo, int precio) {
		
		super();
		this.idArt = idArt;
		this.marca = marca;
		this.categoria = categoria;
		this.campaña = campaña;
		this.color = color;
		this.sexo = sexo;
		this.precio = precio;
		
	}

	public int getIdArt() {
		return idArt;
	}

	public void setIdArt(int idArt) {
		this.idArt = idArt;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public Marca getMarca() {
		return marca;
	}

	public void setMarca(Marca marca) {
		this.marca = marca;
	}

	@Override
	public String toString() {
		return "\nId del articulo" + idArt + "categoria:" + categoria + "campaña=" + campaña + ", color=" + color
				+ "sexo:" + sexo + "precio: " + precio + "$" + "marca:" + marca.getNombre() + "\n";
	}

	

	

	
	
	
	
}
