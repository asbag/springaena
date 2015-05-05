package com.innova4b.aena.servicio;

import java.util.List;
import com.innova4b.aena.modelo.Compania;

public interface CompaniaServicio {
	public List<String> listarCompanias();
	
	public Compania findByName(String nombre);
}
