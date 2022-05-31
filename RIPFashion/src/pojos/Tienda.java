package pojos;

import java.util.ArrayList;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;


@XmlRootElement(name = "Tienda")
@XmlAccessorType(XmlAccessType.FIELD)
public class Tienda {
	@XmlElement
	private String nombreTienda;
	@XmlElement
	private String horario;
	@XmlElement
	private String ubicacion;
	@XmlElement
	private String categoria;
	@XmlElement
	private int capitalTienda;
	
	@XmlElement(name = "Marca")
	@XmlElementWrapper(name = "Marca")
	private ArrayList<Marca> marcas;
	
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

	public Tienda(String nombreTienda, String horario, String ubicacion, String categoria) {
		
		super();
		this.nombreTienda = nombreTienda;
		this.horario = horario;
		this.ubicacion = ubicacion;
		this.categoria = categoria;

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
	public void addMarca(Marca m) {
		if(!marcas.contains(m))
			marcas.add(m);
	}

public String toStringSinCapital() {
		
		return "\nNombre de la tienda : " + nombreTienda + "\nhorario:" + horario
				+ "\nubicacion:" + ubicacion + "\ncategoria:" + categoria + "\n\n\n";
		
	}
	@Override
	public String toString() {
		
		return "\nNombre de la tienda : " + nombreTienda + "\ncapital=" + capitalTienda + "\nhorario:" + horario
				+ "\nubicacion:" + ubicacion + "\ncategoria:" + categoria + "\n\n\n";
		
	}
	
	
	
}
