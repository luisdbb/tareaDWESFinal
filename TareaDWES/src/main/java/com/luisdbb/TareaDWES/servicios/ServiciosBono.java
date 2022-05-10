package com.luisdbb.TareaDWES.servicios;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.modelo.Bono;
import com.luisdbb.TareaDWES.modelo.Usuario;

public interface ServiciosBono {

	public boolean crearBono(Bono b);

	public void eliminarBono(Bono b);

	public boolean modificarBono(Bono b);

	public boolean actualizarBono(Bono b);
	
	public void verDetallesBono(Bono b);

	public Bono findById(int id);

	public ArrayList<Bono> mostrarBonos();

	public ArrayList<Bono> mostrarBonosPorCategoria(char c);

	public Bono mostrarBonoPorUsuario(Usuario u);
	

}
