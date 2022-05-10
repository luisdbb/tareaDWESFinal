package com.luisdbb.TareaDWES.modelo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="paradas")
public class Parada {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable=false, unique=true, length=30)
	private String nombre;

	@ManyToMany(mappedBy="paradas")
	private Set<Linea> lineas = new HashSet<Linea>();

	public Parada() {
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Linea> getLineas() {
		return lineas;
	}

	public void setLineas(Set<Linea> lineas) {
		this.lineas = lineas;
	}

	public Parada(int id, String nombre, Set<Linea> lineas) {
		super();
		this.id = id;
		this.nombre = nombre;
		this.lineas = lineas;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, lineas, nombre);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Parada other = (Parada) obj;
		return id == other.id && Objects.equals(lineas, other.lineas) && Objects.equals(nombre, other.nombre);
	}
	
	

}
