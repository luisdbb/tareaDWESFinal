package com.luisdbb.TareaDWES.modelo;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name="usuarios")
public class Usuario {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	private int edad;

	@Column(nullable = false)
	private String nombre;

	@OneToOne()
	@JoinColumn(name = "idbono", unique = true, nullable = false)
	private Bono bono;
	
	@OneToMany(mappedBy="usuario", 
				cascade = CascadeType.ALL, fetch = FetchType.EAGER)
	private Set<Viaje> viajes = new HashSet<Viaje>();

	public Usuario() {
	}

	public Usuario(int id, int edad, String nombre, Bono bono, Set<Viaje> viajes) {
		super();
		this.id = id;
		this.edad = edad;
		this.nombre = nombre;
		this.bono = bono;
		this.viajes = viajes;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Bono getBono() {
		return bono;
	}

	public void setBono(Bono bono) {
		this.bono = bono;
	}

	public Set<Viaje> getViajes() {
		return viajes;
	}

	public void setViajes(Set<Viaje> viajes) {
		this.viajes = viajes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(bono, edad, id, nombre, viajes);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Usuario other = (Usuario) obj;
		return Objects.equals(bono, other.bono) && edad == other.edad && id == other.id
				&& Objects.equals(nombre, other.nombre) 
				&& Objects.equals(viajes, other.viajes);
	}

	

}
