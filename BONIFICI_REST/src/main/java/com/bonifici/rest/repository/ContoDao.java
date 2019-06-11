package com.bonifici.rest.repository;

import com.bonifici.rest.component.Conto;

public interface ContoDao {
	
	public Conto getConto(int id);
	public Conto updateConto(Conto conto);

}
