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
@Table(name = "viajelineas")
public class ViajeLineas {
	@Embeddable
	public static class Id implements Serializable {
		private static final long serialVersionUID = 1L;

		@Column(name = "idlinea")
		private int idlinea;
		@Column(name = "idviaje")
		private int idviaje;

		public Id() {
		}

		public Id(int idlinea, int idviaje) {
			this.idlinea = idlinea;
			this.idviaje = idviaje;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + idviaje;
			result = prime * result + idlinea;
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
			if (idlinea != other.idlinea)
				return false;
			if (idviaje != other.idviaje)
				return false;
			return true;
		}
	}

	@EmbeddedId
	private Id id = new Id();
	@ManyToOne
	@JoinColumn(name = "idlinea", insertable = false, updatable = false)
	private Linea linea;

	@ManyToOne
	@JoinColumn(name = "idviaje", insertable = false, updatable = false)
	private Viaje viaje;

	public ViajeLineas() {
	}

	public ViajeLineas(Linea l, Viaje v) {
		this.viaje = v;
		this.linea = l;
		this.id.idlinea = l.getId();
		this.id.idviaje = v.getId();
	}

	public Id getId() {
		return id;
	}

	public void setId(Id id) {
		this.id = id;
	}

	public Linea getLinea() {
		return linea;
	}

	public void setLinea(Linea linea) {
		this.linea = linea;
	}

	public Viaje getViaje() {
		return viaje;
	}

	public void setViaje(Viaje viaje) {
		this.viaje = viaje;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, linea, viaje);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ViajeLineas other = (ViajeLineas) obj;
		return Objects.equals(id, other.id) && Objects.equals(linea, other.linea) && Objects.equals(viaje, other.viaje);
	}

}
