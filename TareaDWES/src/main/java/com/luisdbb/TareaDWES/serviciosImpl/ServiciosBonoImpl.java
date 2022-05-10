package com.luisdbb.TareaDWES.serviciosImpl;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.servicios.ServiciosBono;
import com.luisdbb.TareaDWES.utils.DAOFactory;

import com.luisdbb.TareaDWES.modelo.Bono;
import com.luisdbb.TareaDWES.modelo.Usuario;

public class ServiciosBonoImpl implements ServiciosBono {
	DAOFactory daos = DAOFactory.getDAOs();

	public boolean crearBono(Bono b) {
		// TODO Auto-generated method stub
		return false;
	}

	public void eliminarBono(Bono b) {
		// TODO Auto-generated method stub

	}

	public boolean modificarBono(Bono b) {
		// A través de JDBC
		try {
			daos.getBonoDAO_JDBC().modificarBono(b);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public void verDetallesBono(Bono b) {
		// TODO Auto-generated method stub

	}

	public Bono findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Bono> mostrarBonos() {
		return daos.getBonoDAO_JDBC().todosBonos();
	}

	public ArrayList<Bono> mostrarBonosPorCategoria(char c) {
		// TODO Auto-generated method stub
		return null;
	}

	public Bono mostrarBonoPorUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

	public boolean actualizarBono(Bono b) {
		try {
			daos.getBonoDAO_JDBC().actualizarBono(b);
			return true;
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion:" + e.getMessage());
			return false;
		}
	}

}
