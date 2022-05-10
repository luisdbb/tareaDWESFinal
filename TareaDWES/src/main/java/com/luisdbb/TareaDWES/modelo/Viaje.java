package com.luisdbb.TareaDWES.modelo;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "viajes")
public class Viaje {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@Column(nullable = false)
	private LocalDateTime fechahora;

	@Column(nullable = false)
	private int duracion = 0;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "idusuario")
	private Usuario usuario;

	@ManyToMany(mappedBy = "viajes")
	private Set<Linea> lineas = new HashSet<Linea>();

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "trasbordosviaje", joinColumns = { @JoinColumn(name = "idviaje") }, inverseJoinColumns = {
			@JoinColumn(name = "idparada") })
	private Set<Parada> trasbordos = new HashSet<Parada>();

	@ManyToOne()
	@JoinColumn(name = "idpentrada", unique = true)
	private Parada pentrada;

	@ManyToOne()
	@JoinColumn(name = "idsalida", unique = true)
	private Parada psalida;

	public Viaje() {
	}

	public Viaje(int id, LocalDateTime fechahora, int duracion, Usuario usuario, Set<Linea> lineas,
			Set<Parada> trasbordos, Parada pent, Parada psal) {
		super();
		this.id = id;
		this.fechahora = fechahora;
		this.duracion = duracion;
		this.usuario = usuario;
		this.lineas = lineas;
		this.trasbordos = trasbordos;
		this.pentrada = pent;
		this.psalida = psal;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public LocalDateTime getFechahora() {
		return fechahora;
	}

	public void setFechahora(LocalDateTime fechahora) {
		this.fechahora = fechahora;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Set<Linea> getLineas() {
		return lineas;
	}

	public void setLineas(Set<Linea> lineas) {
		this.lineas = lineas;
	}

	public Set<Parada> getTrasbordos() {
		return trasbordos;
	}

	public void setTrasbordos(Set<Parada> trasbordos) {
		this.trasbordos = trasbordos;
	}

	public Parada getPentrada() {
		return pentrada;
	}

	public void setPentrada(Parada pentrada) {
		this.pentrada = pentrada;
	}

	public Parada getPsalida() {
		return psalida;
	}

	public void setPsalida(Parada psalida) {
		this.psalida = psalida;
	}

	@Override
	public int hashCode() {
		return Objects.hash(duracion, fechahora, id, usuario, lineas, trasbordos, pentrada, psalida);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Viaje other = (Viaje) obj;
		return duracion == other.duracion && Objects.equals(fechahora, other.fechahora) && id == other.id
				&& Objects.equals(usuario, other.usuario) && Objects.equals(lineas, other.lineas)
				&& Objects.equals(trasbordos, other.trasbordos) && Objects.equals(pentrada, other.pentrada)
				&& Objects.equals(psalida, other.psalida);
	}

}
