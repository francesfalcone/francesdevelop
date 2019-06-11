package com.bonifici.rest.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bonifici.rest.component.Conto;

@Repository
public class ContoDaoImpl implements ContoDao {
	
	@Autowired
	private HibernateTemplate template;
	
	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Override
	public Conto getConto(int id) {
		Conto conto = (Conto) getTemplate().get(Conto.class, new Integer(id));
		return conto;
	}
	
	@Override
	public Conto updateConto(Conto conto){
		Session session;
		try {
			session = getTemplate().getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			session = getTemplate().getSessionFactory().openSession();
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(conto);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return conto;
	}

}
