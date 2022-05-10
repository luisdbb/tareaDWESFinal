package com.luisdbb.TareaDWES.fachada;

import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import com.luisdbb.TareaDWES.modelo.*;
import com.luisdbb.TareaDWES.utils.ServiciosFactory;
import com.luisdbb.TareaDWES.utils.Utilidades;

public class FachadaUsuario {
	public static ServiciosFactory servicios = ServiciosFactory.getServicios();
	Scanner in = new Scanner(System.in);

	public FachadaUsuario(int opcion) {
		switch (opcion) {
		case 0:
			System.out.println("SALIR");
			break;
		case 1:
			System.out.println("Opcion REGISTRARSE");
			System.out.println("Introduzca su NIF:");
			String nombre = in.nextLine();
			System.out.println("Introduzca su edad:");
			int edad = in.nextInt();
			System.out.println("Introduzca la categoria de su bono (A, B o C):");
			char cat = in.nextLine().charAt(0);
			// TODO Deberian validarse los valores introducidos por el usuario para el
			// nombre, para la edad y para la categoria

			Bono nuevobono = new Bono();
			nuevobono.setCategoria(cat);
			Usuario nuevousuario = new Usuario();
			nuevousuario.setNombre(nombre);
			nuevousuario.setEdad(edad);
			nuevousuario.setBono(nuevobono);
			if (servicios.getServiciosUsuarios().crearUsuario(nuevousuario))
				System.out.println("Se ha creado un nuevo usuario de ID:" + nuevousuario.getId());
			else
				System.out.println("Hubo un error y no se creó el nuevo usuario.");
			break;
		case 2:
			System.out.println("Opcion BUSCAR LINEAS/PARADAS POR NOMBRE");
			System.out.println("Introduzca el nombre de la linea/parada a buscar:");
			nombre = in.nextLine();
			ArrayList<Linea> lineasfiltradas = servicios.getServiciosLineas().buscarLineasPorNombre(nombre);
			if (lineasfiltradas.size() > 0) {
				System.out.println("Las siguientes lineas coinciden con su filtro de busqueda: <" + nombre + ">");
				for (Linea l : lineasfiltradas) {
					System.out.println("Linea " + l.getId() + " de nombre: " + l.getNombre());
					String lineasparadas = " que pasa por las paradas:";
					for (Parada p : l.getParadas())
						lineasparadas += "\tP" + p.getId() + " (" + p.getNombre() + ")";
					System.out.println(lineasparadas);
				}
			} else
				System.out.println("No hay lineas que se ajusten a su búsqueda.");

			ArrayList<Parada> paradasfiltradas = servicios.getServiciosParadas().buscarParadasPorNombre(nombre);
			if (paradasfiltradas.size() > 0) {
				System.out.println("Las siguientes paradas coinciden con su filtro de busqueda: <" + nombre + ">");
				for (Parada p : paradasfiltradas) {
					System.out.println("Parada " + p.getId() + " de nombre: " + p.getNombre());
					String lineasparadas = "por la que pasan las lineas:";
					for (Linea l : p.getLineas())
						lineasparadas += "\tLinea " + l.getId() + " (" + l.getNombre() + ")";
					System.out.println(lineasparadas);
				}
			} else
				System.out.println("No hay paradas que se ajusten a su búsqueda.");

			break;
		case 3:
			System.out.println("Opcion RECARGAR SALDO");
			System.out.println("Introduzca su NIF:");
			String nif = in.nextLine();
			Usuario usuario = servicios.getServiciosUsuarios().buscarUsuarioPorNombre(nif);
			if (usuario != null) {
				System.out.println("Bienvenid@ " + usuario.getNombre());
				System.out.println("El saldo actual de su bono es de: " + usuario.getBono().getSaldo() + " categoria "
						+ usuario.getBono().getCategoria());
				System.out.println("Introduzca la cantidad a recargar en euros:");
				float recarga = Float.valueOf(Double.toString(Utilidades.leerDouble()));
				// TODO necesario validar el valor introducido para la recarga
				usuario.getBono().setSaldo(usuario.getBono().getSaldo() + recarga);
				if (servicios.getServiciosUsuarios().modificarUsuario(usuario))
					System.out.println("Su nuevo saldo es de:" + usuario.getBono().getSaldo() + " euros.");
				else
					System.out.println("Hubo un error y no se modificó el saldo del bono del usuario.");
			} else
				System.out.println("EROR: No se ha encontrado un usuario con ese NIF.");
			break;
		case 4:
			System.out.println("Opcion SUBIR CATEGORIA");
			// TODO
			System.out.println("Introduzca su NIF:");
			nif = in.nextLine();
			usuario = servicios.getServiciosUsuarios().buscarUsuarioPorNombre(nif);
			if (usuario != null) {
				System.out.println("Bienvenid@ " + usuario.getNombre());
				cat = usuario.getBono().getCategoria();
				System.out.println("La categoria del bono es " + cat);
				if (cat == 'C')
					System.out.println("Su bono no puede subir de categoria.");
				else {
					System.out.println("¿Seguro que desea subir la categoria de su bono?");
					boolean confirmacion = Utilidades.leerBoolean();
					if (confirmacion) {
						switch (cat) {
						case 'A':
							usuario.getBono().setCategoria('B');
							break;
						case 'B':
							usuario.getBono().setCategoria('C');
							break;
						}
						servicios.getServiciosUsuarios().modificarUsuario(usuario);
					}
				}

			} else
				System.out.println("EROR: No se ha encontrado un usuario con ese NIF.");
			break;
		case 5:
			System.out.println("Opcion EMBARCAR");
			System.out.println("Introduzca su NIF:");
			nif = in.nextLine();
			usuario = servicios.getServiciosUsuarios().buscarUsuarioPorNombre(nif);
			if (usuario != null) {
				System.out.println("Bienvenid@ " + usuario.getNombre());
				float tarifa = calcularTarifa(usuario.getBono().getCategoria(), LocalTime.now());
				if (usuario.getBono().getSaldo() >= tarifa) {
					// Se descuenta la tarifa del viaje en el bono del usuario
					usuario.getBono().setSaldo(usuario.getBono().getSaldo() - tarifa);
					ArrayList<Linea> todaslineas = servicios.getServiciosLineas().mostrarLineas();
					System.out.println("Seleccione el id de la linea en la que embarca: ");
					for (Linea l : todaslineas) {
						System.out.println("ID:" + l.getId() + " Linea " + l.getNombre());
					}
					int idlinea = in.nextInt();

					Linea lineaembarque = servicios.getServiciosLineas().findById(idlinea);
					if (lineaembarque != null) {
						Viaje nuevoviaje = new Viaje();
						nuevoviaje.setPentrada(null);
						nuevoviaje.setUsuario(usuario);
						LocalDateTime aux = LocalDateTime.of(LocalDate.now(), null);
						nuevoviaje.setFechahora(aux);
						nuevoviaje.getLineas().add(lineaembarque);
						usuario.getViajes().add(nuevoviaje);
						if (servicios.getServiciosUsuarios().modificarUsuario(usuario))
							System.out.println("Registrado nuevo viaje de ID:" + nuevoviaje.getId() + " desde la linea "
									+ lineaembarque.getId() + " (" + nuevoviaje.getFechahora()
									+ ")\tQue tenga buen viaje, " + usuario.getNombre() + ".");
						else
							System.out.println("Hubo un error al embarcar y no se registró su viaje.");
					} else
						System.out.println("EROR: No se ha encontrado una Linea con ese ID.");
				} else {
					System.out.println("No tiene saldo suficiente en su bono.");
					break;
				}
			} else
				System.out.println("EROR: No se ha encontrado un usuario con ese NIF.");
			break;
		case 6:
			System.out.println("Opcion TRASBORDO");
			System.out.println("Introduzca su NIF:");
			nif = in.nextLine();
			usuario = servicios.getServiciosUsuarios().buscarUsuarioPorNombre(nif);
			if (usuario != null) {
				System.out.println("Bienvenid@ de nuevo, " + usuario.getNombre());
				// se muestran las lineas al usuario
				ArrayList<Linea> todaslineas = servicios.getServiciosLineas().mostrarLineas();
				System.out.println("Seleccione el id de la linea en la que hace trasbordo: ");
				for (Linea l : todaslineas) {
					System.out.println("ID:" + l.getId() + " Linea " + l.getNombre());
				}
				int idlinea = in.nextInt();
				Linea lineatrasbordo = servicios.getServiciosLineas().findById(idlinea);
				if (lineatrasbordo != null) {
					Viaje viajeactual = servicios.getServiciosUsuarios().getViajeActual(usuario);
					viajeactual.getLineas().add(lineatrasbordo);
					if (servicios.getServiciosUsuarios().modificarUsuario(usuario))
						System.out.println("Registrado trasbordo del viaje de ID:" + viajeactual.getId()
								+ " en la linea " + lineatrasbordo.getId() + "\tGracias por viajar con nosotros, "
								+ usuario.getNombre() + ".");
					else
						System.out.println("Hubo un error y no se registró su trasbordo en el viaje.");
				} else
					System.out.println("EROR: No se ha encontrado una Linea con ese ID.");
			} else
				System.out.println("EROR: No se ha encontrado un usuario con ese NIF.");
			break;
		case 7:
			System.out.println("Opcion DESEMBARCAR");
			System.out.println("Introduzca su NIF:");
			nif = in.nextLine();
			usuario = servicios.getServiciosUsuarios().buscarUsuarioPorNombre(nif);
			if (usuario != null) {
				System.out.println("Bienvenid@ de nuevo, " + usuario.getNombre());
				ArrayList<Linea> todaslineas = servicios.getServiciosLineas().mostrarLineas();
				System.out.println("Seleccione el id de la linea en la que desembarca: ");
				for (Linea l : todaslineas) {
					System.out.println("ID:" + l.getId() + " Linea " + l.getNombre());
				}
				int idlinea = in.nextInt();
				Linea lineadesembarque = servicios.getServiciosLineas().findById(idlinea);
				if (lineadesembarque != null) {
					Viaje viajeactual = servicios.getServiciosUsuarios().getViajeActual(usuario);
					Date ahora = Date.valueOf(LocalDate.now());
					int duracion = 99;
					// TODO calcular diferencia entre ahora yviajeactual.getFecha()
					viajeactual.setDuracion(duracion);
					viajeactual.getLineas().add(lineadesembarque);

					// viajeactual.setSalida(PArada);

					if (servicios.getServiciosUsuarios().modificarUsuario(usuario))
						System.out.println("Registrado desembarque del viaje de ID:" + viajeactual.getId()
								+ " desde la linea " + lineadesembarque.getId() + " (duracion del viaje: "
								+ viajeactual.getDuracion() + "minutos)\tGracias por viajar con nosotros, "
								+ usuario.getNombre() + ".");
					else
						System.out.println("Hubo un error al embarcar y no se registró su viaje.");
				} else
					System.out.println("EROR: No se ha encontrado una Linea con ese ID.");
			} else
				System.out.println("EROR: No se ha encontrado un usuario con ese NIF.");

			break;
		case 8:
			System.out.println("Opcion VISUALIZAR VIAJES EFECTUADOS");
			System.out.println("Introduzca el NIF del usuario:");
			nif = in.nextLine();
			usuario = servicios.getServiciosUsuarios().buscarUsuarioPorNombre(nif);
			if (usuario != null) {
				ArrayList<Viaje> viajesusuario = servicios.getServiciosViajes().mostrarViajesDeUsuario(usuario);
				if (viajesusuario.size() > 0) {
					System.out.println(
							"El usuario de NIF:" + usuario.getNombre() + " ha realizado los siguientes viajes:");
					for (Viaje v : viajesusuario) {
						System.out.println("Viaje ID:" + v.getId() + " comenzó el " + v.getFechahora() + " de duración "
								+ v.getDuracion() + " mins. Pasó por las líneas:");
						for (Linea l : v.getLineas())
							System.out.print(l.getId() + "--> Linea " + l.getNombre() + ",\t");
					}
				} else
					System.out.println("No se han encontrado viajes del usuario de NIF:" + usuario.getNombre());
			} else
				System.out.println("EROR: No se ha encontrado un usuario con ese NIF.");
			break;
		case 9:
			System.out.println("Calcular Tarifa:");
			System.out.println("Introduzca la categoría (A, B, C)");
			cat = in.nextLine().charAt(0);
			System.out.println("INtroduzca la hora de inicio del viaje:");
			LocalTime hora = Utilidades.leerHora();
			float tarifa = calcularTarifa(Character.toUpperCase(cat), hora);
			System.out.print("La tarifa para un viaje a las " + hora + " en categoria " + Character.toUpperCase(cat) + " cuesta ");
			System.out.printf("%.2f", tarifa); 
			System.out.println("euros.\n");
			break;
		default:
			System.out.println("Opcion incorrecta.");
		}

	}

	private float calcularTarifa(char categoria, LocalTime hora) {
		float precio = 1.00F;
		LocalTime mediodia = LocalTime.of(12, 0, 0);
		switch (categoria) {
		case 'A':
			if (hora.isAfter(mediodia))
				precio = 1.20F;
			break;
		case 'B':
			if (hora.isAfter(mediodia))
				precio = 1.20F;
			precio *= 0.95;
			break;
		case 'C':
			if (hora.isAfter(mediodia))
				precio = 1.20F;
			precio *= 0.90;
			break;
		default:
		}
		return precio;
	}
}
