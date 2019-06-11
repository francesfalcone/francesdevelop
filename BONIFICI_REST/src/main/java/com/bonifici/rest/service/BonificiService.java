package com.bonifici.rest.service;

import com.bonifici.rest.component.Cliente;
import com.bonifici.rest.component.Conto;
import com.bonifici.rest.component.Transazione;

public interface BonificiService {
	
	public Cliente getCliente(int id);
	public Conto getConto(int id);
	public Conto updateConto(Conto conto);
	public Transazione getTransazione(String id);
	public String createTransazione(Transazione transazione);
	public String executeTransazione(Transazione transazione, Conto conto);


}
