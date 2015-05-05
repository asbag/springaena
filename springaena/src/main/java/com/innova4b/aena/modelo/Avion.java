package com.innova4b.aena.modelo;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.FetchType;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


public class Avion implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer id = null;

	private String modelo;
	private int maxPasajeros;
	private Date horaSalida;
	private Date horaLlegada;
	private String codigoLicencia;
	private String estadoLicencia;
	private java.util.Date caducidadLicencia;
	private Set<Ruta> rutas = new HashSet<Ruta>();
	private Set<Billete> billetes;
	private Compania compania;
	private Estado estado;
	private PuertaEmbarque embarque;
	
	public Integer getId() {
		return id;
	}

	protected void setId(Integer id) {
		this.id = id;
	}


	public String getModelo() {
		return modelo;
	}


	public void setModelo(String modelo) {
		this.modelo = modelo;
	}


	public int getMaxPasajeros() {
		return maxPasajeros;
	}


	public void setMaxPasajeros(int maxPasajeros) {
		this.maxPasajeros = maxPasajeros;
	}


	public Date getHoraSalida() {
		return horaSalida;
	}


	public void setHoraSalida(Date horaSalida) {
		this.horaSalida = horaSalida;	
	}


	public Date getHoraLlegada() {
		return horaLlegada;
	}


	public void setHoraLlegada(Date horaLlegada) {
		this.horaLlegada = horaLlegada;
	}


	public String getCodigoLicencia() {
		return codigoLicencia;
	}


	public void setCodigoLicencia(String codigoLicencia) {
		this.codigoLicencia = codigoLicencia;
	}


	public String isEstadoLicencia() {
		return estadoLicencia;
	}


	public void setEstadoLicencia(String estado_licencia) {
		this.estadoLicencia = estado_licencia;
	}


	public Date getCaducidadLicencia() {
		return caducidadLicencia;
	}


	public void setCaducidadLicencia(Date caducidadLicencia) {
		this.caducidadLicencia = caducidadLicencia;
	}

	public Set<Ruta> getRutas() {
		return rutas;
	}

	public void setRutas(Set<Ruta> rutas) {
		this.rutas = rutas;
	}

	public Set<Billete> getBilletes() {
		return billetes;
	}

	public void setBilletes(Set<Billete> billetes) {
		this.billetes = billetes;
	}

	public Compania getCompania() {
		return compania;
	}

	public void setCompania(Compania compania) {
		this.compania = compania;
	}

	public Estado getEstado() {
		return estado;
	}

	public void setEstado(Estado estado) {
		this.estado = estado;
	}

	public PuertaEmbarque getEmbarque() {
		return embarque;
	}

	public void setEmbarque(PuertaEmbarque embarque) {
		this.embarque = embarque;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Avion other = (Avion) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
}
