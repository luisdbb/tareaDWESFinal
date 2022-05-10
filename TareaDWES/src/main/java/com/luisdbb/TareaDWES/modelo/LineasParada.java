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
@Table(name="lineasparada")
public class LineasParada {

	@Embeddable
	public static class Id implements Serializable{
		private static final long serialVersionUID = 1L;
		
		@Column(name="idlinea")
		private int idlinea;
		@Column(name="idparada")
		private int idparada;
		
		public Id(){}
		public Id(int idlinea, int idparada) {
			this.idlinea = idlinea;
			this.idparada = idparada;
		}
		
		@Override
		public int hashCode() {
			final int prime= 31;
			int result = 1;
			result = prime * result + idparada;
			result = prime * result + idlinea;
			return result;
		}
		
		@Override
		public boolean equals(Object o) {
			if(this == o)
				return true;
			if(o==null)
				return false;
			if(getClass() != o.getClass())
				return false;
			Id other = (Id) o;
			if(idlinea != other.idlinea)
				return false;
			if(idparada != other.idparada)
				return false;
			return true;
		}
	} 
	
	@EmbeddedId
	private Id id = new Id();
	@ManyToOne
	@JoinColumn(name="idlinea", insertable=false, updatable=false)
	private Linea linea;

	@ManyToOne
	@JoinColumn(name="idparada", insertable=false, updatable=false)
	private Parada parada;
	
	public LineasParada() {}
	
	public LineasParada(Linea l, Parada p) {
		this.parada = p;
		this.linea =l;
		this.id.idlinea = l.getId();
		this.id.idparada = p.getId();
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

	public Parada getParada() {
		return parada;
	}

	public void setParada(Parada parada) {
		this.parada = parada;
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, linea, parada);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		LineasParada other = (LineasParada) obj;
		return Objects.equals(id, other.id) && Objects.equals(linea, other.linea)
				&& Objects.equals(parada, other.parada);
	}
	
	
	
}
