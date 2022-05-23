package db.interfaces;

import java.util.List;

import pojos.Rol;
import pojos.Usuario;

public interface UsuariosManager {

	void connect();
	void disconnect();
	void addRol(Rol rol);
	List<Rol> getRoles();
	Rol getRolById(int rolId);
	void addUsuario(Usuario usuario);
	Usuario checkLogin(String email, String pass);
}
