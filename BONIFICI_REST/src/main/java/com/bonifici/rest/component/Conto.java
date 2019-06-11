package com.bonifici.rest.component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="Conto")
public class Conto implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 3766846169294114600L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codice_conto")
	private int codiceConto;
	
	@Column(name="saldo")
	private BigDecimal saldo;
	
	@Column(name="iban")
	private String iban;
	
	@Column(name="commissione")
	private BigDecimal commissione;
	
	@ManyToOne
    @JoinColumn(name="codice_cliente", nullable=false)
	private Cliente cliente;
	
	@OneToMany(mappedBy="conto", fetch=FetchType.EAGER)
    private Set<Transazione> transazioni;
	
	
	public Conto(){	}


	public Conto(int codiceConto, BigDecimal saldo, String iban,
			BigDecimal commissione, Cliente cliente,
			Set<Transazione> transazioni) {
		super();
		this.codiceConto = codiceConto;
		this.saldo = saldo;
		this.iban = iban;
		this.commissione = commissione;
		this.cliente = cliente;
		this.transazioni = transazioni;
	}


	public int getCodiceConto() {
		return codiceConto;
	}


	public void setCodiceConto(int codiceConto) {
		this.codiceConto = codiceConto;
	}


	public BigDecimal getSaldo() {
		return saldo;
	}


	public void setSaldo(BigDecimal saldo) {
		this.saldo = saldo;
	}


	public String getIban() {
		return iban;
	}


	public void setIban(String iban) {
		this.iban = iban;
	}


	public BigDecimal getCommissione() {
		return commissione;
	}


	public void setCommissione(BigDecimal commissione) {
		this.commissione = commissione;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Set<Transazione> getTransazioni() {
		return transazioni;
	}


	public void setTransazioni(Set<Transazione> transazioni) {
		this.transazioni = transazioni;
	}


	@Override
	public String toString() {
		return "Conto [codiceConto=" + codiceConto + ", saldo=" + saldo
				+ ", iban=" + iban + ", commissione=" + commissione
				+ ", cliente=" + cliente + ", transazioni=" + transazioni + "]";
	}

}
