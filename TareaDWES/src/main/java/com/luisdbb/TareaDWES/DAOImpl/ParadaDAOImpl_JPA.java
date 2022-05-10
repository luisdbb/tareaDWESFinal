package com.luisdbb.TareaDWES.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.luisdbb.TareaDWES.DAO.ParadaDAO;
import com.luisdbb.TareaDWES.modelo.Parada;
import com.luisdbb.TareaDWES.utils.Jpa;

public class ParadaDAOImpl_JPA implements ParadaDAO {

	@Override
	public ArrayList<Parada> buscarParadasPorNombre(String nombre) {
		// Busqueda de Paradas por nombre mediante JPA
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Parada> cq = cb.createQuery(Parada.class);
			Root<Parada> r = cq.from(Parada.class);
			Predicate p = null;
			Path<String> nombreStr = r.get("nombre");
			p = cb.like(nombreStr, nombre);
			cq.select(r).where(p);
			return (ArrayList<Parada>) em.createQuery(cq).getResultList();
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;

	}

	@Override
	public boolean insertarParada(Parada p) {
		boolean ret = false;
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(p);
			em.getTransaction().commit();
			ret = true;
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}
		return ret;
	}

	@Override
	public boolean eliminarParada(Parada p) {
		boolean ret = false;
		return ret;
	}

	@Override
	public boolean modificarParada(Parada p) {
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
