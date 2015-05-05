package com.innova4b.servicio;

import java.util.List;
import com.innova4b.modelo.Pasajero;

public interface PasajeroServicio {
	public List<Pasajero> listarPasajeros();
	public Pasajero findById(String idPasajero);
	public void insertarPasajero(Pasajero pasajero);
	public void borrarPasajero(String idPasajero);
}
