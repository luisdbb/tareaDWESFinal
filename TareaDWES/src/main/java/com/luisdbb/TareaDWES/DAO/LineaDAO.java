package com.luisdbb.TareaDWES.DAO;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.modelo.Linea;

public interface LineaDAO {
	public ArrayList<Linea> buscarLineasPorNombre(String nombre);

	public Linea existeLinea(String nombre);

	public boolean insertarLinea(Linea l);
}
