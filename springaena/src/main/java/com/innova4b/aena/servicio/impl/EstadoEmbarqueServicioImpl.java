package com.innova4b.aena.servicio.impl;

import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;


import com.innova4b.aena.servicio.EstadoEmbarqueServicio;
import com.innova4b.aena.sessionfactory.HibernateUtil;



import com.innova4b.aena.modelo.EstadoEmbarque;


public class EstadoEmbarqueServicioImpl implements EstadoEmbarqueServicio {

	@Override
	public EstadoEmbarque findByNombre(String estado) {
			Query query = null;
			Session session = null;
			List<EstadoEmbarque> list = null;
			
			try{
				String hql = "from EstadoEmbarque E where E.nombre = :estado";
				session = HibernateUtil.getSessionFactory().getCurrentSession();
				Transaction transaction = session.beginTransaction();
				query = session.createQuery(hql).setString("estado", estado);
				list = query.list();
				transaction.commit();
			} catch (HibernateException e){
				e.printStackTrace();
			}

			return list.get(0);
	}
	
}
