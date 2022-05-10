package com.luisdbb.TareaDWES.modelo;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "trasbordosviaje")
public class TransbordosViaje {

	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 1L;

		@Column(name = "idparada")
		private int idparada;
		@Column(name = "idviaje")
		private int idviaje;

		public Id() {
		}

		public Id(int idparada, int idviaje) {
			this.idparada = idparada;
			this.idviaje = idviaje;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + idviaje;
			result = prime * result + idparada;
			return result;
		}

		@Override
		public boolean equals(Object o) {
			if (this == o)
				return true;
			if (o == null)
				return false;
			if (getClass() != o.getClass())
				return false;
			Id other = (Id) o;
			if (idparada != other.idparada)
				return false;
			if (idviaje != other.idviaje)
				return false;
			return true;
		}
	}

	@EmbeddedId
	private Id id = new Id();
	@ManyToOne
	@JoinColumn(name = "idparada", insertable = false, updatable = false)
	private Parada parada;

	@ManyToOne
	@JoinColumn(name = "idviaje", insertable = false, updatable = false)
	private Viaje viaje;

	public TransbordosViaje() {
	}

	public TransbordosViaje(Parada p, Viaje v) {
		this.viaje = v;
		this.parada = p;
		this.id.idparada = p.getId();
		this.id.idviaje = v.getId();
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Parada getParada() {
		return parada;
	}

	public void setParada(Parada parada) {
		this.parada = parada;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, parada, viaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TransbordosViaje other = (TransbordosViaje) obj;
		return Objects.equals(id, other.id) && Objects.equals(parada, other.parada)
				&& Objects.equals(viaje, other.viaje);
	}

}
