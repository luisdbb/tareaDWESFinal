package com.luisdbb.TareaDWES.DAOImpl;

import java.util.ArrayList;

import com.luisdbb.TareaDWES.DAO.BonoDAO;

import com.luisdbb.TareaDWES.modelo.Bono;
import com.luisdbb.TareaDWES.modelo.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BonoDAOImpl_JDBC implements BonoDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public BonoDAOImpl_JDBC(Connection con) {
		this.con = con;
	}

	public void insertarBono(Bono b) {
		try {
			ps = con.prepareStatement("insert into bonos (categoria, saldo) values ('"+b.getCategoria()+"', 0.0)");
			ps.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Error al insertar en bonos " + e.getMessage());
		}
	}

	public void eliminarBono(Bono b) {
		// TODO Auto-generated method stub

	}

	public void modificarBono(Bono b) {
		// TODO Auto-generated method stub

	}

	public void verDetallesBono(Bono b) {
		// TODO Auto-generated method stub

	}

	public Bono findById(int idBono) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Bono> todosBonos() {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<Bono> filtrarBonosPorCategoria(char c) {
		// TODO Auto-generated method stub
		return null;
	}

	public Bono filtrarBonoPorUsuario(Usuario u) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean actualizarBono(Bono b) {
		// TODO Auto-generated method stub
		return true;
	}

//	public void actualizarBonosMayores50() {
//		try {
//			ps = con.prepareStatement("update bonos set categoria='C' where id in ( select idbono from usuarios where edad > 50); ");
//			ps.executeUpdate();
//		} catch (SQLException e) {
//			System.out.println("Error al insertar en bonos " + e.getMessage());
//		}
//		
//	}

}
