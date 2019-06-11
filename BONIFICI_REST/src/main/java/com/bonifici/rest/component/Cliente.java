package com.bonifici.rest.component;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
@Table(name="Cliente")
public class Cliente implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5029152429594801099L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="codice_cliente")
	private int codiceCliente;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="cognome")
	private String cognome;
	
	@Fetch(FetchMode.SUBSELECT)
	@OneToMany(mappedBy="cliente", fetch=FetchType.LAZY)
    private Set<Conto> conti;
	
	@OneToMany(mappedBy="cliente", fetch=FetchType.EAGER)
    private Set<Transazione> transazioni;

	public Cliente() {
	}

	public Cliente(int codiceCliente, String nome, String cognome,
			Set<Conto> conti, Set<Transazione> transazioni) {
		super();
		this.codiceCliente = codiceCliente;
		this.nome = nome;
		this.cognome = cognome;
		this.conti = conti;
		this.transazioni = transazioni;
	}

	public int getCodiceCliente() {
		return codiceCliente;
	}

	public void setCodiceCliente(int codiceCliente) {
		this.codiceCliente = codiceCliente;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCognome() {
		return cognome;
	}

	public void setCognome(String cognome) {
		this.cognome = cognome;
	}

	public Set<Conto> getConti() {
		return conti;
	}

	public void setConti(Set<Conto> conti) {
		this.conti = conti;
	}

	public Set<Transazione> getTransazioni() {
		return transazioni;
	}

	public void setTransazioni(Set<Transazione> transazioni) {
		this.transazioni = transazioni;
	}

	@Override
	public String toString() {
		return "Cliente [codiceCliente=" + codiceCliente + ", nome=" + nome
				+ ", cognome=" + cognome + ", conti=" + conti
				+ ", transazioni=" + transazioni + "]";
	}


}
