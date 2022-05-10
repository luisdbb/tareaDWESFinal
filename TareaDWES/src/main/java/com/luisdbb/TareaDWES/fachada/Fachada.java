package com.luisdbb.TareaDWES.fachada;

import com.luisdbb.TareaDWES.utils.*;

public class Fachada {
	private static Fachada portal;
	//DAOFactory factoriaDAO = DAOFactory.getDAOs();
	ServiciosFactory factoriaServicios = ServiciosFactory.getServicios();

	public static Fachada getPortal() {
		if (portal == null)
			portal = new Fachada();
		return portal;
	}
	
	public void mostrarMenuSeleccionRol() {
		System.out.println("Seleccione su rol:");
		System.out.println("1.  Usuario de transportes.");
		System.out.println("2.  Administrador.");
		System.out.println("0.  Salir");
	}

	public void mostrarMenuPrincipalUsuario() {
		System.out.println("Seleccione la opcion:");
		System.out.println("1.  Registrarse");
		System.out.println("2.  Buscar líneas/paradas por nombre");
		System.out.println("3.  Ver/Recargar saldo de bono");
		System.out.println("4.  Subir de categoria");
		System.out.println("5.  Embarcar en viaje (nuevo)");
		System.out.println("6.  Trasbordo en viaje (cambio de linea)");
		System.out.println("7.  Salir del viaje");
		System.out.println("8.  Visualizar viajes efectuados");
		System.out.println("9.  Calcular Tarifa");
		System.out.println("0.  Salir");
	}
	
	public void mostrarMenuPrincipalAdministrador() {
		System.out.println("Seleccione la opcion:");
		System.out.println("1.  Visualizar Viajes de un Usuario entre FECHAS");
		System.out.println("2.  Actualizar Bono de Usuario");
		System.out.println("3.  CRUD Lineas");
		System.out.println("4.  CRUD Paradas");
		System.out.println("0.  Salir");
	}
}
	