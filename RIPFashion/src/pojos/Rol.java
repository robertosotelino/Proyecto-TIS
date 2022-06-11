package pojos;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Entity
@Table(name = "Roles")

public class Rol implements Serializable{
	private static final long serialVersionUID = -2919555888136927186L;
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nombre;
	@OneToMany(mappedBy="rol")
	private List<Usuario> usuarios;
	
	public Rol() {
		
	}
	
	public Rol(String nombre) {
		this.nombre = nombre;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Rol other = (Rol) obj;
		
		return id == other.id;
		
	}

	@Override
	public String toString() {
		return "Rol [id=" + id + ", nombre=" + nombre + "]";
	}

	public void addUsuario(Usuario usuario) {
		usuarios.add(usuario);
	}
}
