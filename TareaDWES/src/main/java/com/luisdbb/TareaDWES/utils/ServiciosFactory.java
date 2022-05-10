package com.luisdbb.TareaDWES.utils;

import com.luisdbb.TareaDWES.servicios.*;
import com.luisdbb.TareaDWES.serviciosImpl.*;

public class ServiciosFactory {
	public static ServiciosFactory servicios;

	public static ServiciosFactory getServicios() {
		if (servicios == null)
			servicios = new ServiciosFactory();
		return servicios;
	}

	public ServiciosBono getServiciosBonos() {
		return new ServiciosBonoImpl();
	}

	public ServiciosUsuario getServiciosUsuarios() {
		return new ServiciosUsuariompl();
	}

	public ServiciosLinea getServiciosLineas() {
		return new ServiciosLineaImpl();
	}

	public ServiciosViaje getServiciosViajes() {
		return new ServiciosViajeImpl();
	}

	public ServiciosParada getServiciosParadas() {
		return new ServiciosParadaImpl();
	}

}
