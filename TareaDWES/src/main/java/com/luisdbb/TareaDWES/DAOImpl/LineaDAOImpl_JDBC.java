package com.luisdbb.TareaDWES.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.luisdbb.TareaDWES.DAO.LineaDAO;

import com.luisdbb.TareaDWES.modelo.Linea;

public class LineaDAOImpl_JDBC implements LineaDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public LineaDAOImpl_JDBC(Connection con) {
		this.con = con;
	}

	public ArrayList<Linea> buscarLineasPorNombre(String cadena) {
		ArrayList<Linea> ret = new ArrayList<Linea>();					
		try {
			ps = con.prepareStatement("select * from lineas where nombre LIKE CONCAT( '%',?,'%')");
			ps.setString(1, cadena);
			rs = ps.executeQuery();
			if (rs.next()) {
				Linea aux = new Linea(rs.getInt("id"), rs.getString(2), null, null); 
				ret.add(aux);
			}
		} catch (SQLException e) {
			System.out.println("error al consultar por viajes de un usuario entre fechas: " + e.getMessage());
		}
		return ret;
	}

	@Override
	public Linea existeLinea(String nombre) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean insertarLinea(Linea l) {
		// TODO Auto-generated method stub
		return false;
	}
}
