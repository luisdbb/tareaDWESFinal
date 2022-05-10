package com.luisdbb.TareaDWES.serviciosImpl;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.modelo.Parada;
import com.luisdbb.TareaDWES.servicios.ServiciosParada;
import com.luisdbb.TareaDWES.utils.DAOFactory;

public class ServiciosParadaImpl implements ServiciosParada {
	DAOFactory daos = DAOFactory.getDAOs();

	@Override
	public boolean nuevaParada(Parada nuevaparada) {
		return daos.getParadaDAO_JPA().insertarParada(nuevaparada);
	}
	
	@Override
	public boolean crearParada(Parada p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void eliminarParada(Parada p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean modificarParada(Parada p) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void verDetallesParada(Parada p) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Parada findById(int idParada) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Parada> mostrarParadas() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Parada> buscarParadasPorNombre(String nombre) {
		return daos.getParadaDAO_JDBC().buscarParadasPorNombre(nombre);
	}

	@Override
	public Parada existeParada(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}


	
}
