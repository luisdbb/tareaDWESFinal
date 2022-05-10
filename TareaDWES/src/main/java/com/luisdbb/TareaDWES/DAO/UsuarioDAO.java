package com.luisdbb.TareaDWES.DAO;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.modelo.Usuario;

public interface UsuarioDAO {
	
	public void insertarUsuario(Usuario u);

	public void eliminarUsuario(Usuario u);

	public void modificarUsuario(Usuario u);

	public void verDetallesUsuario(Usuario u);

	public Usuario findById(int idUsuario);

	public ArrayList<Usuario> todosUsuarios();

	public Usuario filtrarUsuarioPorNombre(String s);

	public ArrayList<Usuario> filtrarUsuarioPorEdad(int edad);
}
