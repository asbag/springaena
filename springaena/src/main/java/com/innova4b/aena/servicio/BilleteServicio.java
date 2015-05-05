package com.innova4b.aena.servicio;

import java.util.List;


import com.innova4b.aena.modelo.Billete;
import com.innova4b.aena.modelo.Pasajero;

public interface BilleteServicio {
	public List<Billete> listarBilletes();
	public void insertarBillete(Billete billete, Pasajero pasajero);
}
