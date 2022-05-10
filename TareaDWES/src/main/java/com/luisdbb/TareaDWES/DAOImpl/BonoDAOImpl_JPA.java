package com.luisdbb.TareaDWES.DAOImpl;

import java.util.ArrayList;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.luisdbb.TareaDWES.DAO.BonoDAO;

import com.luisdbb.TareaDWES.modelo.Bono;
import com.luisdbb.TareaDWES.modelo.Usuario;
import com.luisdbb.TareaDWES.utils.Jpa;

public class BonoDAOImpl_JPA implements BonoDAO {

	public void insertarBono(Bono b) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			em.persist(b);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}

	}

	public void eliminarBono(Bono b) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			try {
				em.remove(b);
				em.getTransaction().commit();
			} catch (Exception e) {
				System.out.println("Se ha producido una excepcion " + e.getMessage());
				e.printStackTrace();
			}
		} finally {
			em.close();
		}
	}

	public void modificarBono(Bono b) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			em.getTransaction().begin();
			b = em.merge(b);
			em.getTransaction().commit();
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		} finally {
			if (em != null) {
				em.close();
			}
		}

	}

	public void verDetallesBono(Bono b) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			b = em.find(Bono.class, b.getId());
			System.out.println(b);
		} catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		} finally {
			em.close();
		}
	}

	public Bono findById(int idBono) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			Bono b = em.find(Bono.class, idBono);
			return b;
		} 
		catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		}finally {
			em.close();
		}
		return null;
	}

	@SuppressWarnings("unchecked")
	public ArrayList<Bono> todosBonos() {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaQuery<Object> cq = em.getCriteriaBuilder().createQuery();
			cq.select(cq.from(Bono.class));
			Query q = em.createQuery(cq);
			return (ArrayList<Bono>) q.getResultList();
		} 
		catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		}finally {
			em.close();
		}
		return null;
	}

	public ArrayList<Bono> filtrarBonosPorCategoria(char c) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Bono> cq = cb.createQuery(Bono.class);
			Root<Bono> r = cq.from(Bono.class);
			Predicate p = null;
			Path<Character> categoriaStr = r.get("categoria");
			p = cb.equal(categoriaStr, c);
			cq.select(r).where(p);
			return (ArrayList<Bono>) em.createQuery(cq).getResultList();
		} 
		catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		}finally {
			em.close();
		}
		return null;
	}

	public Bono filtrarBonoPorUsuario(Usuario u) {
		EntityManager em = Jpa.getManager().createEntityManager();
		try {
			CriteriaBuilder cb = em.getCriteriaBuilder();
			CriteriaQuery<Bono> cq = cb.createQuery(Bono.class);
			Root<Bono> r = cq.from(Bono.class);
			Predicate p = null;
			Path<Usuario> usuario = r.get("usuario");
			p = cb.equal(usuario, u);
			cq.select(r).where(p);
			return (Bono) em.createQuery(cq).getSingleResult();
		} 
		catch (Exception e) {
			System.out.println("Se ha producido una excepcion " + e.getMessage());
			e.printStackTrace();
		}finally {
			em.close();
		}
		return null;
	}

	@Override
	public boolean actualizarBono(Bono b) {
		// TODO Auto-generated method stub
		return true;
	}


}
