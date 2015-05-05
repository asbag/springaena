package com.innova4b.aena.servicio;

import java.util.List;

import com.innova4b.aena.modelo.Aeropuerto;

public interface AeropuertoServicio {
	public int numPuertasDisponibles ();
	
	public List<String> obtenerAeropuertos();
	public List<Aeropuerto> listarAeropuertos();
	public Aeropuerto findByName(String name);
	public Aeropuerto findById(Long id);
	public int numPuertasEmbarque(String nombre);
	public void borrarAeropuerto(String idAeropuerto);
	public void insertarAeropuerto(Aeropuerto aeropuerto);
}