package com.luisdbb.TareaDWES.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class Jpa {

	public static Jpa jpa;
	private static EntityManagerFactory emf = null;

	public static Jpa getManager() {
		if (jpa == null)
			jpa = new Jpa();
		return jpa;
	}

	private Jpa() {
		emf = Persistence.createEntityManagerFactory("transportes");
	}

	public EntityManager createEntityManager() {
		EntityManager entityManager = emf.createEntityManager();
		return entityManager;
	}
}
