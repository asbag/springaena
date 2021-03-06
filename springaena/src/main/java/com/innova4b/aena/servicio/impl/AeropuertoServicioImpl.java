package com.innova4b.aena.servicio.impl;

import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.innova4b.aena.servicio.AeropuertoServicio;
import com.innova4b.aena.servicio.EstadoEmbarqueServicio;
import com.innova4b.aena.sessionfactory.HibernateUtil;

import org.hibernate.Criteria;
import org.hibernate.criterion.Restrictions;

import com.innova4b.aena.modelo.Aeropuerto;
import com.innova4b.aena.modelo.EstadoEmbarque;
import com.innova4b.aena.modelo.PuertaEmbarque;

public class AeropuertoServicioImpl implements AeropuertoServicio {

	@Override
	public int numPuertasDisponibles() {
		Aeropuerto aerop = new Aeropuerto();

		return aerop.getNumPuertas();
	}

	@Override
	public List<String> obtenerAeropuertos() {
		List<String> list = null;
		Query query = null;
		Session session = null;
		try {
			String hql = "select A.nombre from Aeropuerto A";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql);
			list = query.list();
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Exception in HqlQuery");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public Aeropuerto findByName(String name) {
		Session session = null;
		Criteria criteria = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			criteria = session.createCriteria(Aeropuerto.class);
			criteria.add(Restrictions.eq("nombre", name));
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		transaction.commit();
		return (Aeropuerto)criteria.list();
	}

	@Override
	public Aeropuerto findById(Long idAeropuerto) {
		Session session = null;
		Criteria criteria = null;
		Transaction transaction = null;
		try {
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			transaction = session.beginTransaction();
			criteria = session.createCriteria(Aeropuerto.class);
			criteria.add(Restrictions.eq("id", idAeropuerto));
		} catch (HibernateException e) {
			e.printStackTrace();
		}
		List<Aeropuerto> aeropuertos = criteria.list();
		transaction.commit();
		return aeropuertos.get(0);
	}
	
	@Override
	public int numPuertasEmbarque(String nombre) {
		List<Integer> list = null;
		Query query = null;
		Session session = null;
		try {
			String hql = "select A.numPuertas from Aeropuerto A where A.nombre = :nombre";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql).setString("nombre", nombre);
			list = (List<Integer>)query.list();
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Exception in HqlQuery");
			e.printStackTrace();
		}
		return list.get(0);
	}

	@Override
	public List<Aeropuerto> listarAeropuertos() {
		List<Aeropuerto> list = null;
		Query query = null;
		Session session = null;
		try {
			String hql = "from Aeropuerto A";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql);
			list = (List<Aeropuerto>)query.list();
			transaction.commit();
		} catch (HibernateException e) {
			System.out.println("Exception in HqlQuery");
			e.printStackTrace();
		}
		return list;
	}

	@Override
	public void borrarAeropuerto(String idAeropuerto) {
		List<Integer> list = null;
		Integer resultado = null;
		Query query = null;
		Session session = null;

		Long idAerop = Long.parseLong(idAeropuerto);
		Aeropuerto aeropuerto = this.findById(idAerop);
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "select cast(count(A.nombre) as int) from Aeropuerto A where A.id = :idAeropuerto";
		query = session.createQuery(hql).setString("idAeropuerto", idAeropuerto);
		list = (List<Integer>)query.list();
		resultado = list.get(0);
		if (resultado > 0) {		
			Set<PuertaEmbarque> embarques = aeropuerto.getEmbarques();
			for (PuertaEmbarque pE : embarques) {
				aeropuerto.getEmbarques().remove(pE);
				pE.setAeropuerto(null);
				session.delete(pE);
			}
			session.delete(aeropuerto);
			/*
			String hqldel = "delete from Aeropuerto A where A.id = :idAeropuerto";
			query = session.createQuery(hqldel).setString("idAeropuerto", idAeropuerto);
			query.executeUpdate();
			*/
		}
		transaction.commit();
	}

	@Override
	public void insertarAeropuerto(Aeropuerto aeropuerto) {
		Session session = null;
		
		EstadoEmbarqueServicio eS = new EstadoEmbarqueServicioImpl();
		EstadoEmbarque estadoEmbarque = eS.findByNombre("correcto");
		
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		
		
		
		Transaction transaction = session.beginTransaction();
		int numPuertas = aeropuerto.getNumPuertas();
		//Contamos las puertas que debe tener el aeropuerto
		for(int x = 0; x < numPuertas; x++) {
			PuertaEmbarque pE = new PuertaEmbarque();
			pE.setAeropuerto(aeropuerto);
			pE.setNum(aeropuerto.getNombre() + Integer.toString(x));
			pE.setEstadoEmbarque(estadoEmbarque);
			estadoEmbarque.anyadirEmbarque(pE);
			session.save(estadoEmbarque);
		}
		session.save(aeropuerto);
		transaction.commit();
	}
}
