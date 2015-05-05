package com.innova4b.servicio.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import com.innova4b.modelo.Avion;
import com.innova4b.servicio.AvionServicio;
import com.innova4b.sessionfactory.HibernateUtil;

public class AvionServicioImpl implements AvionServicio {
	//Listar aviones con licencia caducada
	Session session;

	@Override
	public List<String> listarAvionesCaducados () {
		List<String> list = null;

		Query query = null;
		Session session = null;
		try {
			String hql = "select A.modelo from Avion A where A.estadoLicencia = 0";
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
	public List<String> listarAvionesEspanioles() {
		Query query = null;
		Session session = null;
		List<String> list = null;

		try{
			//String hql = "select A.modelo from Avion A left join A.rutas R left join R.aeropuertoOrigen P where P.pais = :pais";
			//String hql = "select A.modelo from Avion A where A.rutas in (select R.id from Ruta R left join R.aeropuertoOrigen O where O.pais = :pais) or A.rutas in (select J.id from Ruta J left join J.aeropuertoDestino D where D.pais = :pais)";
			//String hql = "select A.modelo from Avion A where A.rutas in (select J.id from Ruta J left join J.aeropuertoDestino D where D.pais = :pais)";
			//String hql = "select A.id from Aeropuerto.A where A.pais = :pais";
			String hql = "select P.modelo from Avion P left join P.rutas R where R.aeropuertoOrigen in (select A.id from Aeropuerto A where A.pais = :pais) or R.aeropuertoDestino in (select D.id from Aeropuerto D where D.pais = :pais)";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql).setString("pais", "ES");
			list = query.list();
			transaction.commit();
		} catch (HibernateException e){
			e.printStackTrace();
		}

		return list;
	}

	@Override
	public List<String> listarAviones() {
		Query query = null;
		Session session = null;
		List<String> list = null;

		try{
			String hql = "select A.modelo from Avion A";
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
	public List<Avion> listarAvionesObjeto() {
		Query query = null;
		Session session = null;
		List<Avion> list = null;

		try{
			String hql = "from Avion A";
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
	public Integer numAsientosReservados(String avion) {
		Query query = null;
		Session session = null;
		List<Integer> list = null;
		Integer resultado = null;
		
		try{
			String hql = "select cast(count(B.codgo) as int) from Billete B left join B.avion A where A.modelo = :avion";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql).setString("avion", avion);
			list = (List<Integer>)query.list();
			resultado = list.get(0);
			transaction.commit();
		} catch (HibernateException e){
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public Integer numAsientosOcupados(String avion) {
		Query query = null;
		Session session = null;
		List<Integer> list = null;
		Integer resultado = null;
		
		try{
			String hql = "select cast(count(B.codgo) as int) from Billete B left join B.avion A where A.modelo = :avion and B.estado = :estado";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql).setString("avion", avion).setString("estado", "si");
			list = (List<Integer>)query.list();
			resultado = list.get(0);
			transaction.commit();
		} catch (HibernateException e){
			e.printStackTrace();
		}

		return resultado;
	}

	@Override
	public void insertarAvion(Avion avion) {
		List<Integer> list = null;
		Integer resultado = null;
		Query query = null;
		Session session = null;
		session = HibernateUtil.getSessionFactory().getCurrentSession();
		Transaction transaction = session.beginTransaction();
		String hql = "select cast(count(A.modelo) as int) from Avion A where A.modelo = :modelo or A.codigoLicencia = :codgoLicencia";
		query = session.createQuery(hql).setString("modelo", avion.getModelo()).setString("codgoLicencia", avion.getCodigoLicencia());
		list = (List<Integer>)query.list();
		resultado = list.get(0);
		if (resultado == 0) session.save(avion);
		transaction.commit();
	}

	@Override
	public Avion findById(String avionId) {
		Query query = null;
		Session session = null;
		List<Avion> list = null;
		
		try{
			String hql = "from Avion A where A.id = :idAvion";
			session = HibernateUtil.getSessionFactory().getCurrentSession();
			Transaction transaction = session.beginTransaction();
			query = session.createQuery(hql).setString("idAvion", avionId);
			list = query.list();
			transaction.commit();
		} catch (HibernateException e){
			e.printStackTrace();
		}

		return list.get(0);
	}

}
