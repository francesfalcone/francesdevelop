package com.bonifici.rest.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.bonifici.rest.component.Cliente;
import com.bonifici.rest.component.Conto;
import com.bonifici.rest.component.Transazione;
import com.bonifici.rest.repository.ClienteDao;
import com.bonifici.rest.repository.ContoDao;
import com.bonifici.rest.repository.TransazioneDao;

@Service
@Transactional(readOnly = true, propagation = Propagation.REQUIRED, rollbackFor = {java.lang.Exception.class})
public class BonificiServiceImpl implements BonificiService {
	
	@Autowired
	private ClienteDao clienteDao;
	@Autowired
	private ContoDao contoDao;
	@Autowired
	private TransazioneDao transazioneDao;
	
	@Override
	public Cliente getCliente(int id){
		return clienteDao.getCliente(id);
	}
	
	@Override
	public Conto getConto(int id){
		return contoDao.getConto(id);
	}
	
	@Override
	public Conto updateConto(Conto conto){
		return contoDao.updateConto(conto);
	}

	@Override
	public String createTransazione(Transazione transazione){
		return transazioneDao.createTransazione(transazione);
	}
	
	@Override
	public String executeTransazione(Transazione transazione, Conto conto){
		contoDao.updateConto(conto);
		return transazioneDao.executeTransazione(transazione);
	}
	
	@Override
	public Transazione getTransazione(String id){
		return transazioneDao.getTransazione(id);
	}
	

}
