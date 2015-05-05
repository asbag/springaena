package com.innova4b.servicio;


import com.innova4b.modelo.EstadoEmbarque;

public interface EstadoEmbarqueServicio {
	public EstadoEmbarque findByNombre(String estado);
}