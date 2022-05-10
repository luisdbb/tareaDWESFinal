package com.luisdbb.TareaDWES.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.luisdbb.TareaDWES.DAO.LineaDAO;
import com.luisdbb.TareaDWES.modelo.Linea;
import com.luisdbb.TareaDWES.utils.Jpa;

public class LineaDAOImpl_JPA implements LineaDAO {

	public ArrayList<Linea> buscarLineasPorNombre(String nombre) {
		//Busqueda de lineas por nombre mediante JPA
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Linea> cq = cb.createQuery(Linea.class);
			Root<Linea> r = cq.from(Linea.class);
			Predicate p = null;
			Path<String> nombreStr = r.get("nombre");
			p = cb.like(nombreStr, nombre);
			cq.select(r).where(p);
			return (ArrayList<Linea>) em.createQuery(cq).getResultList();
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public Linea existeLinea(String nombre) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Linea> cq = cb.createQuery(Linea.class);
			Root<Linea> r = cq.from(Linea.class);
			Predicate p = null;
			Path<String> nombreStr = r.get("nombre");
			p = cb.equal(nombreStr, nombre);
			cq.select(r).where(p);
			return (Linea) em.createQuery(cq).getSingleResult();
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}
		return null;
	}

	@Override
	public boolean insertarLinea(Linea l) {
		// TODO Auto-generated method stub
		return false;
	}

}
