package com.innova4b.aena.servicio;

import com.innova4b.aena.modelo.EstadoEmbarque;

public interface EstadoEmbarqueServicio {
	public EstadoEmbarque findByNombre(String estado);
}