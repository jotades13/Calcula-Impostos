package com.jotahemmy.servico;

public interface CalculaImpostos {
	
	double calculoIr(double valor,double valorAcm);
	double calculoINSS(double valor,double descontoAcm);
	void calculoImpostos(double valor, double acmIr, double acmInss,double descPensao, boolean geraEmpresa);
	

}
