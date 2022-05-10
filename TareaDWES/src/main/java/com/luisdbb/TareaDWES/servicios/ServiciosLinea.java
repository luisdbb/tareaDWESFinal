package com.luisdbb.TareaDWES.servicios;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.modelo.Usuario;
import com.luisdbb.TareaDWES.modelo.Linea;

public interface ServiciosLinea {
	
	public boolean crearLinea(Linea l);

	public void eliminarViaje(Linea l);

	public boolean modificarLinea(Linea l);

	public void verDetallesLinea(Linea l);

	public Linea findById(int id);
	
	/***
	 * Funcion que devuelve la linea de nombre que se pasa como parametro o null si no existe
	 * @param nombre
	 * @return
	 */
	public Linea existeLinea(String nombre);

	public ArrayList<Linea> mostrarLineas();

	/***
	 * Funcion que devuelve la lista de lineas cuyo nombre coincide/es parte del que se pasa como parametro
	 * @param nombre
	 * @return
	 */
	public ArrayList<Linea> buscarLineasPorNombre(String nombre);
	
	public ArrayList<Linea> mostrarLineasDeUsuario(Usuario u);
}
