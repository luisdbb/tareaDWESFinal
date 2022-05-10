package com.luisdbb.TareaDWES.DAO;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.modelo.Bono;
import com.luisdbb.TareaDWES.modelo.Usuario;

public interface BonoDAO {

	public void insertarBono(Bono b);

	public void eliminarBono(Bono b);

	public void modificarBono(Bono b);

	public void verDetallesBono(Bono b);

	public Bono findById(int idBono);

	public ArrayList<Bono> todosBonos();

	public ArrayList<Bono> filtrarBonosPorCategoria(char c);

	public Bono filtrarBonoPorUsuario(Usuario u);

	public boolean actualizarBono(Bono b);

}
