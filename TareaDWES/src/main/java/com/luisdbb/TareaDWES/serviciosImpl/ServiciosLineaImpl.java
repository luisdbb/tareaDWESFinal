package com.luisdbb.TareaDWES.serviciosImpl;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.servicios.ServiciosLinea;
import com.luisdbb.TareaDWES.utils.DAOFactory;

import com.luisdbb.TareaDWES.modelo.Linea;
import com.luisdbb.TareaDWES.modelo.Usuario;

public class ServiciosLineaImpl implements ServiciosLinea {
	DAOFactory daos = DAOFactory.getDAOs();

	public boolean crearLinea(Linea l) {
		return daos.getLineaDAO_JPA().insertarLinea(l);
	}

	public void eliminarViaje(Linea l) {
		// TODO Auto-generated method stub

	}

	public boolean modificarLinea(Linea l) {
		// TODO Auto-generated method stub
		return false;
	}

	public void verDetallesLinea(Linea l) {
		// TODO Auto-generated method stub

	}

	public Linea findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Linea> mostrarLineas() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Linea> mostrarLineasDeUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Linea> buscarLineasPorNombre(String nombre) {
		return daos.getLineaDAO_JDBC().buscarLineasPorNombre(nombre);
	}

	public Linea existeLinea(String nombre) {
		return daos.getLineaDAO_JPA().existeLinea(nombre);
	}

}
