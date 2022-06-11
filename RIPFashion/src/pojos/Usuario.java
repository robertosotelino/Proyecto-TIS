package pojos;

import java.io.Serializable;
import java.util.Objects;

import jakarta.persistence.Basic;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.TableGenerator;

@Entity
@Table(name = "Usuarios")

public class Usuario implements Serializable{
	
	private static final long serialVersionUID = 874420479559831017L;
	
	@Id
    @GeneratedValue(strategy=GenerationType.IDENTITY) // genera la clave en la base de datos
	private int id;
	
	private String email;
	@Basic (fetch = FetchType.LAZY) // especificamos al codigo que el tipo de dato es BLOB
	@Lob // BLOB
	private byte[] password;
	
	@ManyToOne(fetch=FetchType.EAGER) // tipo de relación
    @JoinColumn(name="IdRol") // en la base de datos rol se ve como IdRol
	private Rol rol;
	
	public Usuario() {
		super();
	}

	public Usuario(String email, byte[] hash, Rol rol) {
		this.email = email;
		this.password = hash;
		this.rol = rol;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public byte[] getPassword() {
		return password;
	}

	public void setPassword(byte[] password) {
		this.password = password;
	}

	public Rol getRol() {
		return rol;
	}

	public void setRol(Rol rol) {
		this.rol = rol;
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
		Usuario other = (Usuario) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "Usuario [id=" + id + ", email=" + email + "]";
	}
}
