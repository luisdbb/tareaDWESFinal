package com.luisdbb.TareaDWES.servicios;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.modelo.Usuario;
import com.luisdbb.TareaDWES.modelo.Viaje;

public interface ServiciosUsuario {

	public boolean crearUsuario(Usuario u);

	public void eliminarUsuario(Usuario u);

	public boolean modificarUsuario(Usuario u);

	public void verDetallesUsuario(Usuario u);

	public Usuario findById(int id);

	public ArrayList<Usuario> mostrarUsuarios();

	public ArrayList<Usuario> mostrarUsuariosPorEdad(int edad);

	public Usuario buscarUsuarioPorNombre(String nombre);

	public boolean recargarSaldoBono(float f);

	public boolean subirCategoria();
	
	public void entrar();
	
	public void salir();

	public Viaje getViajeActual(Usuario u);
}
