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
import com.innova4b.aena.modelo.Billete;
import com.innova4b.aena.modelo.Pasajero;
import com.innova4b.aena.servicio.AvionServicio;
import com.innova4b.aena.servicio.BilleteServicio;
import com.innova4b.aena.servicio.PasajeroServicio;
import com.innova4b.aena.servicio.impl.AvionServicioImpl;
import com.innova4b.aena.servicio.impl.BilleteServicioImpl;
import com.innova4b.aena.servicio.impl.PasajeroServicioImpl;


/**
 * Servlet implementation class AvionServlet
 */
@Controller
public class PasajeroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public PasajeroServlet() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(value = "/pasajero/*", method = RequestMethod.GET)
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		if ("/gestion".equals(request.getPathInfo())){
			PasajeroServicio pS = new PasajeroServicioImpl();
			List<Pasajero> listPasajeros = pS.listarPasajeros();
			request.getSession().setAttribute("listPasajeros",listPasajeros);
			request.getRequestDispatcher("/WEB-INF/views/pasajero/listarpasajeros.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	@RequestMapping(value = "/pasajero/*", method = RequestMethod.POST)
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if ("/accion".equals(request.getPathInfo())) {
			String act = request.getParameter("act");
			String idPasajero = request.getParameter("seleccionado");
			PasajeroServicio pasajeroServ = new PasajeroServicioImpl();
			if ("borrar".equals(act)) {
				pasajeroServ.borrarPasajero(idPasajero);
				List<Pasajero> pasajeros = pasajeroServ.listarPasajeros();
				request.getSession().setAttribute("listPasajeros", pasajeros);
				request.getRequestDispatcher("/WEB-INF/views/pasajero/listarpasajeros.jsp").forward(request, response);
			} else if ("asignar".equals(act)) {
				Pasajero pasajero = pasajeroServ.findById(idPasajero);
				request.getSession().setAttribute("pasajero", pasajero);
				AvionServicio aS = new AvionServicioImpl();
				List<Avion> aviones = aS.listarAvionesObjeto();
				request.getSession().setAttribute("aviones", aviones);
				request.getRequestDispatcher("/WEB-INF/views/billete/formBillete.jsp").forward(request, response);
			} else if ("insertar".equals(act)) {
				request.getRequestDispatcher("/WEB-INF/views/pasajero/formPasajero.jsp").forward(request, response);
			}
		} else if ("/asignado".equals(request.getPathInfo())) {
			String idPasajero = request.getParameter("pasajero");
			String codigo = request.getParameter("codigo");
			String asiento = request.getParameter("asiento");
			String estado = request.getParameter("estado");
			String fecha = request.getParameter("fecha");
			String avionId = request.getParameter("avion");
			PasajeroServicio pasajeroServ = new PasajeroServicioImpl();
			Pasajero pasajero = pasajeroServ.findById(idPasajero);
			AvionServicio aS = new AvionServicioImpl();
			Avion avion = aS.findById(avionId);
			
			BilleteServicio billeteServ = new BilleteServicioImpl();
			Billete billete = new Billete ();
			billete.setCodgo(codigo);
			billete.setAsiento(asiento);
			billete.setAvion(avion);
			billete.setEstado(estado);
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
			try {
				Date formatFecha = sdf.parse(fecha);
				java.sql.Date sqlFecha = new java.sql.Date(formatFecha.getTime());
				billete.setFecha(sqlFecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			
			billeteServ.insertarBillete(billete, pasajero);
			List<Pasajero> listPasajeros = pasajeroServ.listarPasajeros();
			request.getSession().setAttribute("listPasajeros",listPasajeros);
			request.getRequestDispatcher("/WEB-INF/views/pasajero/listarpasajeros.jsp").forward(request, response);
		} else if ("/rellenado".equals(request.getPathInfo())) {
			String nombre = request.getParameter("nombre");
			String apellidos = request.getParameter("apellidos");
			String fechaNacimiento = request.getParameter("fecha");
			
			Pasajero pasajero = new Pasajero();
			
			java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat("dd/MM/yyyy");
			try {
				Date formatFecha = sdf.parse(fechaNacimiento);
				java.sql.Date sqlFecha = new java.sql.Date(formatFecha.getTime());
				pasajero.setFechaNacimiento(sqlFecha);
			} catch (ParseException e) {
				e.printStackTrace();
			}
			pasajero.setNombre(nombre);
			pasajero.setApellidos(apellidos);
			
			PasajeroServicio pS = new PasajeroServicioImpl();
			pS.insertarPasajero(pasajero);
			List<Pasajero> listPasajeros = pS.listarPasajeros();
			request.getSession().setAttribute("listPasajeros",listPasajeros);
			request.getRequestDispatcher("/WEB-INF/views/pasajero/listarpasajeros.jsp").forward(request, response);
		}
	}

}