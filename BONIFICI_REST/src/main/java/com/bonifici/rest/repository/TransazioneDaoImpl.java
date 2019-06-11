package com.bonifici.rest.repository;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;
import com.bonifici.rest.component.Transazione;

@Repository
public class TransazioneDaoImpl implements TransazioneDao {
	
	@Autowired
	private HibernateTemplate template;
	
	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Override
	public Transazione getTransazione(String id) {
		Transazione transazione = (Transazione) getTemplate().get(Transazione.class, id);
		return transazione;
	}

	@Override
	public String createTransazione(Transazione transazione) {
		Session session;
		try {
			session = getTemplate().getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			session = getTemplate().getSessionFactory().openSession();
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.save(transazione);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return transazione.getIdTransazione();
	}

	@Override
	public String executeTransazione(Transazione transazione) {
		Session session;
		try {
			session = getTemplate().getSessionFactory().getCurrentSession();
		} catch (HibernateException e) {
			session = getTemplate().getSessionFactory().openSession();
		}
		Transaction transaction = null;
		try {
			transaction = session.beginTransaction();
			session.saveOrUpdate(transazione);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			e.printStackTrace();
		}
		return transazione.getCro();
	}
	
	

}
