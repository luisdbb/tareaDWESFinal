package com.luisdbb.TareaDWES.servicios;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.modelo.Parada;

public interface ServiciosParada {

	public boolean crearParada(Parada p);

	public void eliminarParada(Parada p);

	public boolean modificarParada(Parada p);

	public void verDetallesParada(Parada p);

	public Parada findById(int idParada);

	public ArrayList<Parada> mostrarParadas();
	/***
	 * Funcion que devuelve la lista de paradas cuyo nombre coincide/es parte del que se pasa como parametro
	 * @param nombre
	 * @return
	 */
	public ArrayList<Parada> buscarParadasPorNombre(String nombre);
	/***
	 * Funcion que devuelve la parada de nombre que se pasa como parametro o null si no existe
	 * @param nombre
	 * @return
	 */
	public Parada existeParada(String nombre);

	/***
	 * Crea una parada basica de nombre el que se pasa como parametro
	 * @param nuevaparada
	 * @return
	 */
	public boolean nuevaParada(Parada nuevaparada);
}
