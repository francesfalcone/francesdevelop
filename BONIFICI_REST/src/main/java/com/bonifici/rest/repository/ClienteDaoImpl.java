package com.bonifici.rest.repository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import com.bonifici.rest.component.Cliente;

@Repository
public class ClienteDaoImpl implements ClienteDao {

	@Autowired
	private HibernateTemplate template;
	
	public HibernateTemplate getTemplate() {
		return template;
	}

	public void setTemplate(HibernateTemplate template) {
		this.template = template;
	}

	@Override
	public Cliente getCliente(int id) {
		Cliente cliente = (Cliente) getTemplate().get(Cliente.class, new Integer(id));
		return cliente;
	}

}
