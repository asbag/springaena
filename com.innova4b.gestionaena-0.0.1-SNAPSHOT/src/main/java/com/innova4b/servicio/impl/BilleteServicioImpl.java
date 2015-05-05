package com.innova4b.servicio.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.innova4b.modelo.Billete;
import com.innova4b.modelo.Pasajero;
import com.innova4b.servicio.BilleteServicio;
import com.innova4b.sessionfactory.HibernateUtil;

public class BilleteServicioImpl implements BilleteServicio {
	//Listar aviones con licencia caducada
	Session session;

	public List<Billete> listarBilletes() {
		Query query = null;
		Session session = null;
		List<Billete> list = null;

		try{
			String hql = "from Billete B";
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
	public void insertarBillete(Billete billete, Pasajero pasajero) {
		List<Integer> list = null;
		Integer resultado = null;
		Query query = null;
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "select cast(count(B.codgo) as int) from Billete B where B.codgo = :codgo";
		query = session.createQuery(hql).setString("codgo", billete.getCodgo());
		list = (List<Integer>)query.list();
		resultado = list.get(0);
		if (resultado == 0) {
			billete.setPasajero(pasajero);
			pasajero.anyadirBillete(billete);
			session.save(pasajero);
			session.save(billete);
		}
		transaction.commit();
	}
}
