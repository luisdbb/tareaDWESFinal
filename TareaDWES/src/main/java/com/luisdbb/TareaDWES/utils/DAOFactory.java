package com.luisdbb.TareaDWES.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;

import com.luisdbb.TareaDWES.DAO.*;
import com.luisdbb.TareaDWES.DAOImpl.*;

public class DAOFactory {

	private static DAOFactory f;

	private Connection con;// Para la parte de JDBC nativo

	// Patrón singleton a la factoria DAO
	public static DAOFactory getDAOs() {
		if (f == null)
			f = new DAOFactory();
		f.getCon();
		return f;
	}

	public Connection getCon() {
		try {
			if (con == null) {
				Properties prop = new Properties();
				MysqlDataSource m = new MysqlDataSource();
				FileInputStream fis;

				// Abrimos un canal de lectura al fichero de texto plano
				try {
					fis = new FileInputStream("src/main/resources/db.properties");
					// cargamos la informacion del fichero properties
					prop.load(fis);
					// asignamos al origen de datos las propiedades leidas del fichero properties
					m.setUrl(prop.getProperty("url"));
					m.setPassword(prop.getProperty("password"));
					m.setUser(prop.getProperty("usuario"));
					// obtengo la conexion
					con = m.getConnection();
				} catch (FileNotFoundException e) {
					System.out.println("Error al acceder al fichero properties " + e.getMessage());
				} catch (IOException e) {
					System.out.println("Error al leer las propiedades del fichero properties" + e.getMessage());
				} catch (SQLException e) {
					System.out.println("Error al conectar a la base de datos:usuario,password....");
				}
				return con;
			}
		} catch (Exception e) {
			System.out.println("Se ha producido una excepion al conectar con la BD."+ e.getMessage());
			e.printStackTrace();
		}
		return con;
	}

	// El patron factory proporciona metodos que devuelven los objetos complejos DAO
	// Implementaciones de los DAO mediante JPA
	public BonoDAO getBonoDAO_JPA() {
		return new BonoDAOImpl_JPA();
	}

	public UsuarioDAO getUsuarioDAO_JPa() {
		return new UsuarioDAOImpl_JPA();
	}

	public ViajeDAO getViajeDAO_JPA() {
		return new ViajeDAOImpl_JPA();
	}

	public LineaDAO getLineaDAO_JPA() {
		return new LineaDAOImpl_JPA();
	}
	
	public ParadaDAO getParadaDAO_JPA() {
		return new ParadaDAOImpl_JPA();
	}

	// Implementaciones de los DAO mediante JDBC nativo
	public BonoDAO getBonoDAO_JDBC() {
		return new BonoDAOImpl_JDBC(con);
	}

	public UsuarioDAO getUsuarioDAO_JDBC() {
		return new UsuarioDAOImpl_JDBC(con);
	}

	public ViajeDAO getViajeDAO_JDBC() {
		return new ViajeDAOImpl_JDBC(con);
	}

	public LineaDAO getLineaDAO_JDBC() {
		return new LineaDAOImpl_JDBC(con);
	}
	public ParadaDAO getParadaDAO_JDBC() {
		return new ParadaDAOImpl_JDBC(con);
	}
}
