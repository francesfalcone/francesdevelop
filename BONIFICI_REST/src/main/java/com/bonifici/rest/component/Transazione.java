package com.bonifici.rest.component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="Transazione")
public class Transazione implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2510943578448501904L;

	@Id
	@Column(name="id_transazione")
	private String idTransazione;
	
	@Column(name="importo")
	private BigDecimal importo;
	
	@Column(name="data_esecuzione")
	private Date dataEsecuzione;
	
	@Column(name="commissione")
	private BigDecimal commissione;
	
	@Column(name="nominativo_beneficiario")
	private String nominativoBeneficiario;
	
	@Column(name="cro")
	private String cro;
	
	@Column(name="iban_beneficiario")
	private String ibanBeneficiario;
	
	@Column(name="tipologia")
	private String tipologia;
	
	@Column(name="divisa")
	private String divisa;
	
	@Column(name="causale")
	private String causale;
	
	@ManyToOne
    @JoinColumn(name="codice_cliente", nullable=false)
	private Cliente cliente;
	
	@ManyToOne
    @JoinColumn(name="codice_conto", nullable=false)
	private Conto conto;
	
	
	public Transazione(){}


	public Transazione(String idTransazione, BigDecimal importo,
			Date dataEsecuzione, BigDecimal commissione,
			String nominativoBeneficiario, String cro, String ibanBeneficiario,
			String tipologia, String divisa, String causale, Cliente cliente,
			Conto conto) {
		super();
		this.idTransazione = idTransazione;
		this.importo = importo;
		this.dataEsecuzione = dataEsecuzione;
		this.commissione = commissione;
		this.nominativoBeneficiario = nominativoBeneficiario;
		this.cro = cro;
		this.ibanBeneficiario = ibanBeneficiario;
		this.tipologia = tipologia;
		this.divisa = divisa;
		this.causale = causale;
		this.cliente = cliente;
		this.conto = conto;
	}


	public String getIdTransazione() {
		return idTransazione;
	}


	public void setIdTransazione(String idTransazione) {
		this.idTransazione = idTransazione;
	}


	public BigDecimal getImporto() {
		return importo;
	}


	public void setImporto(BigDecimal importo) {
		this.importo = importo;
	}


	public Date getDataEsecuzione() {
		return dataEsecuzione;
	}


	public void setDataEsecuzione(Date dataEsecuzione) {
		this.dataEsecuzione = dataEsecuzione;
	}


	public BigDecimal getCommissione() {
		return commissione;
	}


	public void setCommissione(BigDecimal commissione) {
		this.commissione = commissione;
	}


	public String getNominativoBeneficiario() {
		return nominativoBeneficiario;
	}


	public void setNominativoBeneficiario(String nominativoBeneficiario) {
		this.nominativoBeneficiario = nominativoBeneficiario;
	}


	public String getCro() {
		return cro;
	}


	public void setCro(String cro) {
		this.cro = cro;
	}


	public String getIbanBeneficiario() {
		return ibanBeneficiario;
	}


	public void setIbanBeneficiario(String ibanBeneficiario) {
		this.ibanBeneficiario = ibanBeneficiario;
	}


	public String getTipologia() {
		return tipologia;
	}


	public void setTipologia(String tipologia) {
		this.tipologia = tipologia;
	}


	public String getDivisa() {
		return divisa;
	}


	public void setDivisa(String divisa) {
		this.divisa = divisa;
	}


	public String getCausale() {
		return causale;
	}


	public void setCausale(String causale) {
		this.causale = causale;
	}


	public Cliente getCliente() {
		return cliente;
	}


	public void setCliente(Cliente cliente) {
		this.cliente = cliente;
	}


	public Conto getConto() {
		return conto;
	}


	public void setConto(Conto conto) {
		this.conto = conto;
	}


	@Override
	public String toString() {
		return "Transazione [idTransazione=" + idTransazione + ", importo="
				+ importo + ", dataEsecuzione=" + dataEsecuzione
				+ ", commissione=" + commissione + ", nominativoBeneficiario="
				+ nominativoBeneficiario + ", cro=" + cro
				+ ", ibanBeneficiario=" + ibanBeneficiario + ", tipologia="
				+ tipologia + ", divisa=" + divisa + ", causale=" + causale
				+ ", cliente=" + cliente + ", conto=" + conto + "]";
	}


}
