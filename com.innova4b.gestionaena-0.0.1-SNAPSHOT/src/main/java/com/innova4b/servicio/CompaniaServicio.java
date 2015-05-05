package com.innova4b.servicio;

import java.util.List;
import com.innova4b.modelo.Compania;

public interface CompaniaServicio {
	public List<String> listarCompanias();
	
	public Compania findByName(String nombre);
}
