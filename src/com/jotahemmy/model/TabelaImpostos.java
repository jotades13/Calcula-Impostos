package com.jotahemmy.model;

public class TabelaImpostos {
	
	private Double irpj;
	private Double pis;
	private Double cofins;
	private Double csll;
	private Double iss;
	private Double fundoReserva;
	private Double pensao;
	private Double txADM;
	private Double empresaPJ;
	private Double empresaPF;
	public TabelaImpostos(Double irpj, Double pis, Double cofins, Double csll, Double iss, Double fundoReserva,
			Double pensao, Double txADM, Double empresaPJ, Double empresaPF) {
		super();
		this.irpj = irpj;
		this.pis = pis;
		this.cofins = cofins;
		this.csll = csll;
		this.iss = iss;
		this.fundoReserva = fundoReserva;
		this.pensao = pensao;
		this.txADM = txADM;
		this.empresaPJ = empresaPJ;
		this.empresaPF = empresaPF;
	}
	public Double getIrpj() {
		return irpj;
	}
	public void setIrpj(Double irpj) {
		this.irpj = irpj;
	}
	public Double getPis() {
		return pis;
	}
	public void setPis(Double pis) {
		this.pis = pis;
	}
	public Double getCofins() {
		return cofins;
	}
	public void setCofins(Double cofins) {
		this.cofins = cofins;
	}
	public Double getCsll() {
		return csll;
	}
	public void setCsll(Double csll) {
		this.csll = csll;
	}
	public Double getIss() {
		return iss;
	}
	public void setIss(Double iss) {
		this.iss = iss;
	}
	public Double getFundoReserva() {
		return fundoReserva;
	}
	public void setFundoReserva(Double fundoReserva) {
		this.fundoReserva = fundoReserva;
	}
	public Double getPensao() {
		return pensao;
	}
	public void setPensao(Double pensao) {
		this.pensao = pensao;
	}
	public Double getTxADM() {
		return txADM;
	}
	public void setTxADM(Double txADM) {
		this.txADM = txADM;
	}
	public Double getEmpresaPJ() {
		return empresaPJ;
	}
	public void setEmpresaPJ(Double empresaPJ) {
		this.empresaPJ = empresaPJ;
	}
	public Double getEmpresaPF() {
		return empresaPF;
	}
	public void setEmpresaPF(Double empresaPF) {
		this.empresaPF = empresaPF;
	}
	
}
