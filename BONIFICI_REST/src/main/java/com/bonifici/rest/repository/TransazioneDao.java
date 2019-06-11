package com.bonifici.rest.repository;

import com.bonifici.rest.component.Transazione;

public interface TransazioneDao {

	public Transazione getTransazione(String id);
	public String createTransazione(Transazione transazione);
	public String executeTransazione(Transazione transazione);
	
}
