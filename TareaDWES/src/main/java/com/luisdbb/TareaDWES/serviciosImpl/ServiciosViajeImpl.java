package com.luisdbb.TareaDWES.serviciosImpl;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

import com.luisdbb.TareaDWES.servicios.ServiciosViaje;
import com.luisdbb.TareaDWES.utils.DAOFactory;

import com.luisdbb.TareaDWES.modelo.Viaje;
import com.luisdbb.TareaDWES.modelo.Usuario;

public class ServiciosViajeImpl implements ServiciosViaje {
	DAOFactory daos = DAOFactory.getDAOs();

	public boolean crearViaje(Viaje v) {
		// TODO Auto-generated method stub
		return false;
	}

	public void eliminarViaje(Viaje v) {
		// TODO Auto-generated method stub

	}

	public boolean modificarViaje(Viaje v) {
		// TODO Auto-generated method stub
		return false;
	}

	public void verDetallesViaje(Viaje v) {
		// TODO Auto-generated method stub

	}

	public Viaje findById(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Viaje> mostrarViajes() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Viaje> mostrarViajesPorFecha(LocalDateTime fecha) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Viaje> mostrarViajesDeUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Viaje> mostrarViajesDeUsuarioEntreFechas(Usuario u, LocalDate fechaini, LocalDate fechafin) {
		// Realizar por JDBC nativo
		return daos.getViajeDAO_JDBC().buscarViajesDeUsuarioEntreFechas(u, fechaini.atStartOfDay(), (fechafin.plusDays(1)).atStartOfDay());
	}

}
