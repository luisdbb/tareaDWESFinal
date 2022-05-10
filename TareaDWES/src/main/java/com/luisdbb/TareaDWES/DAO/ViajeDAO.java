package com.luisdbb.TareaDWES.DAO;

import java.time.LocalDateTime;
import java.util.ArrayList;

import com.luisdbb.TareaDWES.modelo.Usuario;
import com.luisdbb.TareaDWES.modelo.Viaje;

public interface ViajeDAO {
	public ArrayList<Viaje> buscarViajesDeUsuarioEntreFechas(Usuario u, LocalDateTime fechaini, LocalDateTime fechafin);
}
