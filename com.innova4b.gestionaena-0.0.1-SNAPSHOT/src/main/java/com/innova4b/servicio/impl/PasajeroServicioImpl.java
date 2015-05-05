package com.innova4b.servicio.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import com.innova4b.modelo.Pasajero;
import com.innova4b.servicio.PasajeroServicio;
import com.innova4b.sessionfactory.HibernateUtil;

public class PasajeroServicioImpl implements PasajeroServicio {
	//Listar aviones con licencia caducada
	Session session;

	@Override
	public List<Pasajero> listarPasajeros() {
		Query query = null;
		Session session = null;
		List<Pasajero> list = null;

		try{
			String hql = "from Pasajero P";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql);
			list = query.list();
			transaction.commit();
		} catch (HibernateException e){
			e.printStackTrace();
		}

		return list;
	}
	@Override
	public Pasajero findById(String pasajeroID) {
		Query query = null;
		Session session = null;
		List<Pasajero> list = null;
		
		try{
			String hql = "from Pasajero P where P.id = :idPasajero";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql).setString("idPasajero", pasajeroID);
			list = query.list();
			transaction.commit();
		} catch (HibernateException e){
			e.printStackTrace();
		}

		return list.get(0);
	}
	
	@Override
	public void insertarPasajero(Pasajero pasajero) {
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		//String hql = "select cast(count(P.nombre) as int) from Pasajero P where P.id = :idPasajero";
		//query = session.createQuery(hql).setLong("idPasajero", pasajero.getId());
		//list = (List<Integer>)query.list();
		//resultado = list.get(0);
		//if (resultado == 0) 
		session.save(pasajero);
		transaction.commit();
	}
	
	@Override
	public void borrarPasajero(String idPasajero) {
		List<Integer> list = null;
		Integer resultado = null;
		Query query = null;
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "select cast(count(P.nombre) as int) from Pasajero P where P.id = :idPasajero";
		query = session.createQuery(hql).setString("idPasajero", idPasajero);
		list = (List<Integer>)query.list();
		resultado = list.get(0);
		if (resultado > 0) {
			String hqldel = "delete from Pasajero P where P.id = :idPasajero";
			query = session.createQuery(hqldel).setString("idPasajero", idPasajero);
			query.executeUpdate();
		}
		transaction.commit();
	}
}
