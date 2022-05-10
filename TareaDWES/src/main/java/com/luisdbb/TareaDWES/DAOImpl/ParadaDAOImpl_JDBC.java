package com.luisdbb.TareaDWES.DAOImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.luisdbb.TareaDWES.DAO.ParadaDAO;
import com.luisdbb.TareaDWES.modelo.Linea;
import com.luisdbb.TareaDWES.modelo.Parada;

public class ParadaDAOImpl_JDBC implements ParadaDAO {
	private Connection con;
	private PreparedStatement ps;
	private ResultSet rs;

	public ParadaDAOImpl_JDBC(Connection con) {
		this.con = con;
	}

	@Override
	public ArrayList<Parada> buscarParadasPorNombre(String nombre) {
		ArrayList<Parada> ret = new ArrayList<Parada>();
		try {
			ps = con.prepareStatement("select * from paradas where nombre LIKE CONCAT( '%',?,'%')");
			ps.setString(1, nombre);
			rs = ps.executeQuery();
			if (rs.next()) {
				Parada aux = new Parada(rs.getInt("id"), rs.getString(2), null); 
				ret.add(aux);
			}
		} catch (SQLException e) {
			System.out.println("error al consultar por viajes de un usuario entre fechas: " + e.getMessage());
		}
		return ret;
	}

	@Override
	public boolean insertarParada(Parada p) {
		// TODO Auto-generated method stub
		boolean ret = false;
		return ret;
	}

	@Override
	public boolean eliminarParada(Parada p) {
		// TODO Auto-generated method stub
		boolean ret = false;
		return ret;
	}

	@Override
	public boolean modificarParada(Parada p) {
		// TODO Auto-generated method stub
		boolean ret = false;
		return ret;
	}

	@Override
	public void verDetallesParada(Parada p) {
		// TODO Auto-generated method stub

	}

	@Override
	public Parada findById(int idParada) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ArrayList<Parada> todasParadas() {
		// TODO Auto-generated method stub
		return null;
	}

}
