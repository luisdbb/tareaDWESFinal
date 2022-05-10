package com.luisdbb.TareaDWES.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.luisdbb.TareaDWES.DAO.UsuarioDAO;

import com.luisdbb.TareaDWES.modelo.Usuario;

public class UsuarioDAOImpl_JDBC implements UsuarioDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public UsuarioDAOImpl_JDBC(Connection con) {
		this.con = con;
	}

	public void insertarUsuario(Usuario u) {
		// TODO Auto-generated method stub

	}

	public void eliminarUsuario(Usuario u) {
		// TODO Auto-generated method stub

	}

	public void modificarUsuario(Usuario u) {
		// TODO Auto-generated method stub

	}

	public void verDetallesUsuario(Usuario u) {
		// TODO Auto-generated method stub

	}

	public Usuario findById(int idUsuario) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Usuario> todosUsuarios() {
		// TODO Auto-generated method stub
		return null;
	}

	public Usuario filtrarUsuarioPorNombre(String s) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Usuario> filtrarUsuarioPorEdad(int edad) {
		// TODO Auto-generated method stub
		return null;
	}

}
