package com.luisdbb.TareaDWES.fachada;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

import com.luisdbb.TareaDWES.modelo.*;
import com.luisdbb.TareaDWES.utils.ServiciosFactory;
import com.luisdbb.TareaDWES.utils.Utilidades;

public class FachadaAdministrativa {
	public static ServiciosFactory servicios = ServiciosFactory.getServicios();
	Scanner in = new Scanner(System.in);

	public FachadaAdministrativa(int opcion) {
		switch (opcion) {
		case 0:
			System.out.println("SALIR");
			break;
		case 1:
			System.out.println("Opcion VISUALIZAR VIAJES EFECTUADOS por un USUARIO entre FECHAS mediante JDBC");
			System.out.println("Introduzca el NIF del usuario:");
			String nif = in.nextLine();
			Usuario usuario = servicios.getServiciosUsuarios().buscarUsuarioPorNombre(nif);
			if (usuario != null) {
				LocalDate fechaIni, fechaFin;
				System.out.println("Introduzca la primera fecha:");
				fechaIni = Utilidades.leerFecha();
				System.out.println("Introduzca la segunda fecha:");
				fechaFin = Utilidades.leerFecha();
				ArrayList<Viaje> viajesusuario = servicios.getServiciosViajes()
						.mostrarViajesDeUsuarioEntreFechas(usuario, fechaIni, fechaFin);
				if (viajesusuario.size() > 0) {
					System.out.println(
							"El usuario de NIF:" + usuario.getNombre() + " ha realizado los siguientes viajes entre el "
									+ fechaIni.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + " y el  "
									+ fechaFin.format(DateTimeFormatter.ofPattern("dd/MM/YYYY")) + ":");
					for (Viaje v : viajesusuario) {
						System.out.println("Viaje ID:" + v.getId() + " comenzó el "
								+ v.getFechahora().format(DateTimeFormatter.ofPattern("dd/MM/YYYY hh:mm:ss"))
								+ " de duración " + v.getDuracion() + " mins. Pasó por las líneas:");
						for (Linea l : v.getLineas())
							System.out.print(l.getId() + "--> Linea " + l.getNombre() + ",\t");
					}
				} else
					System.out.println("No se han encontrado viajes del usuario de NIF:" + usuario.getNombre()
							+ " efectuados entre el " + fechaIni + " y el " + fechaFin);
			} else
				System.out.println("EROR: No se ha encontrado un usuario con ese NIF.");
			break;
		case 2:
			System.out.println("Opcion ACTUALIZAR BONO de Usuario");
			System.out.println("Introduzca el NIF del usuario:");
			nif = in.nextLine();
			usuario = servicios.getServiciosUsuarios().buscarUsuarioPorNombre(nif);
			if (usuario != null) {
				if (usuario.getBono() == null)
					usuario.setBono(servicios.getServiciosBonos().mostrarBonoPorUsuario(usuario));
			} else {
				System.out.println("EROR: No se ha encontrado un usuario con ese NIF.");
				break;
			}
			System.out.println("El usuario " + usuario.getId() + "\tNIF: " + nif + "\tcategoria: "
					+ Character.toUpperCase(usuario.getBono().getCategoria()) + "\tsaldo actual: "
					+ Utilidades.mostrarDouble2Decimales(usuario.getBono().getSaldo()) + "euros.");
			boolean valido = false;
			float cantidad = 0.00f;
			do {
				System.out.println("Introduzca la cantidad a recargar (entero enter 10 y 50 euros)");
				cantidad = Float.valueOf(in.nextInt());
				valido = (cantidad >= 10 && cantidad <= 50);
				if (!valido) {
					System.out.println("La cantidad a recargar debe estar entre 10 y 50 euros.");
				}
				System.out.println(
						"Quiere recargar " + Utilidades.mostrarDouble2Decimales(cantidad) + "euros. ¿Es correcto??");
				valido = Utilidades.leerBoolean();
			} while (!valido);
			usuario.getBono().setSaldo(usuario.getBono().getSaldo() + cantidad);
			if (!servicios.getServiciosBonos().actualizarBono(usuario.getBono())) {
				System.out.println("Hubo un eror al actualizar el saldo del bono del usuario.");
			}
			System.out.println("El nuevo saldo del usuario es: "
					+ Utilidades.mostrarDouble2Decimales(usuario.getBono().getSaldo()) + "euros.\n");
			break;
		case 3:
			System.out.println("CRUD Lineas: ");
			Linea nueva = new Linea();
			String nombre = "";
			valido = false;
			do {
				System.out.println("Introduzca el nombre para la nueva linea:");
				nombre = in.next();

				valido = (nombre.length() >= 1 && nombre.length() <= 30);
				if (!valido)
					System.out.println("El nombre de la nueva linea debe ser de menos de 30 caracteres.");
				valido = (servicios.getServiciosLineas().existeLinea(nombre) == null);
				if (!valido)
					System.out.println("ERROR: Ya existe una linea con el nombre " + nombre);
			} while (!valido);
			System.out.println("El nombre "+ nombre +" elegido para la neuva linea es válido. ");
			nueva.setNombre(nombre);
			
			System.out.println("Introduzca ahora las paradas de la nueva linea:");
			boolean parar = false;
			do {
				System.out.println("Las paradas registradas en el sistema actualmente son las siguientes:");
				ArrayList<Parada> todasparadas = servicios.getServiciosParadas().mostrarParadas();
				if (todasparadas.size() > 0)
					for (Parada p : todasparadas)
						System.out.println("\tParada" + p.getId() + "-> " + p.getNombre() + ".");
				else
					System.out.println("No hay paradas registradas en el sistema actualmente.");

				System.out.println("Introduzca el nombre de la parada o 0 para parar de introducir lineas:");
				String nombreparada = in.next();
				if (parar = (nombreparada.equals("0")))
					break;
				else {
					boolean existe = (servicios.getServiciosParadas().existeParada(nombre) == null);
					if (!existe) {
						System.out.println("La parada no existe. ¿Desea crearla para ser añadida a la nueva linea??");
						boolean resp = Utilidades.leerBoolean();
						if (resp) {
							Parada nuevaparada = new Parada();
							nuevaparada.setNombre(nombreparada);
							nuevaparada.setLineas(new HashSet<Linea>());
							if (!servicios.getServiciosParadas().nuevaParada(nuevaparada))
								System.out.println("ERROR al crear al nueva parada. No se añadirá a la nueva linea.");
							else {
								if (nueva.getParadas() == null)
									nueva.setParadas(new HashSet<Parada>());
								nueva.getParadas().add(nuevaparada);
								System.out.println("Se ha añadido la parada "+ nuevaparada.getNombre()+ " a las parada de la nueva linea "+ nueva.getNombre());
							}
						}
					} else {
						Parada existente = servicios.getServiciosParadas().existeParada(nombreparada);
						if (nueva.getParadas() == null)
							nueva.setParadas(new HashSet<Parada>());
						nueva.getParadas().add(existente);
						System.out.println("Se ha añadido la parada "+ existente.getNombre()+ " a las parada de la nueva linea "+ nueva.getNombre());
					}
				}

			} while (!parar);

			System.out.println(
					"La nueva linea que se va a crear es: " + nueva.getNombre() + " que pasa por las paradas:\t");
			for (Parada p : nueva.getParadas())
				System.out.print(p.getNombre() + ",\t");
			System.out.println(
					"¿Es correcto así? (En caso de contestar NO se cancelará el proceso y deberá comenzar de nuevo.)");
			boolean resp = Utilidades.leerBoolean();
			if (resp)
				if (servicios.getServiciosLineas().crearLinea(nueva))
					System.out.println("Se ha creado la nueva linea de id:" + nueva.getId());
				else
					System.out.println("ERROR: Hubo error al crear al nueva linea.");
			break;
		case 4:
			System.out.println("CRUD Paradas:");
			//TODO
			
			
			
			break;
		default:
			System.out.println("Opcion incorrecta.");
		}

	}
}
