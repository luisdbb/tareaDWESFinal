package com.luisdbb.TareaDWES.DAO;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.modelo.Parada;

public interface ParadaDAO {
	public ArrayList<Parada> buscarParadasPorNombre(String nombre);

	public boolean insertarParada(Parada p);

	public boolean eliminarParada(Parada p);

	public boolean modificarParada(Parada p);

	public void verDetallesParada(Parada p);

	public Parada findById(int idParada);

	public ArrayList<Parada> todasParadas();
}
