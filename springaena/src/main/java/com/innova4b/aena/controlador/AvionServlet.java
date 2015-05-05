package com.innova4b.aena.controlador;

import java.io.IOException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.innova4b.aena.modelo.Avion;
import com.innova4b.aena.modelo.Compania;
import com.innova4b.aena.servicio.AvionServicio;
import com.innova4b.aena.servicio.CompaniaServicio;
import com.innova4b.aena.servicio.impl.AvionServicioImpl;
import com.innova4b.aena.servicio.impl.CompaniaServicioImpl;


/**
 * Servlet implementation class AvionServlet
 */
@Controller
public class AvionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AvionServlet() {
        super();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
    @RequestMapping(value = "/avion/listar_caducados", method = RequestMethod.GET)
	protected String listarCaducados () {
		AvionServicio as = new AvionServicioImpl();
		List<String> listAviones = as.listarAvionesCaducados();
		return "listavcaduc";
    }
}
