package com.jotahemmy.servico;

import com.jotahemmy.model.TabelaImpostos;
import com.jotahemmy.model.TabelaInss;
import com.jotahemmy.model.TabelaIr;

public class Calculos implements CalculaImpostos {

	private Double resultado = 0.00;
	private Double jaPago = 0.00;
	private Double tetoInss = 0.00;
	
	@Override
	public double calculoIr(double valor, double valorAcm) {
	
		TabelaIr tabIr = new TabelaIr(1903.98,
									  1903.99,2826.65,7.50,142.80,
									  2826.66,3751.05,15.00,354.80,
			        				  3751.06,4664.68,22.50,636.13,
			        				  4664.68,27.50,869.36);
		
		if (valorAcm>0) {
			if(valorAcm <= tabIr.getVlrA1()) {
				jaPago=0.00;
			}else if(valorAcm >= tabIr.getVlrB1() && valorAcm <= tabIr.getVlrB2()) {
				jaPago = (valorAcm * tabIr.getPercentualB()/100.00)-tabIr.getDeducaoB();
			}else if(valorAcm >= tabIr.getVlrC1() && valorAcm <= tabIr.getVlrC2()) {
				jaPago = (valorAcm * tabIr.getPercentualC()/100.00)-tabIr.getDeducaoC();
			}else if(valorAcm >= tabIr.getVlrD1() && valorAcm <= tabIr.getVlrD2()) {
				jaPago = (valorAcm * tabIr.getPercentualD()/100.00)-tabIr.getDeducaoD();
			}else {
				jaPago = (valorAcm * tabIr.getPercentualE()/100.00)-tabIr.getDeducaoE();
			}
		}
		
		valor = valor + valorAcm;
		
		if(valor <= tabIr.getVlrA1()) {
			resultado=0.00;
		}else if(valor >= tabIr.getVlrB1() && valor <= tabIr.getVlrB2()) {
			resultado = (valor * tabIr.getPercentualB()/100.00)-tabIr.getDeducaoB();
		}else if(valor >= tabIr.getVlrC1() && valor <= tabIr.getVlrC2()) {
			resultado = (valor * tabIr.getPercentualC()/100.00)-tabIr.getDeducaoC();
		}else if(valor >= tabIr.getVlrD1() && valor <= tabIr.getVlrD2()) {
			resultado = (valor * tabIr.getPercentualD()/100.00)-tabIr.getDeducaoD();
		}else {
			resultado = (valor * tabIr.getPercentualE()/100.00)-tabIr.getDeducaoE();
		}

		return resultado - jaPago;
	}

	@Override()
	public double calculoINSS(double valor, double descontoAcm) {
		
		TabelaInss tabInss = new TabelaInss(1100.00,7.50,
											1100.01,2203.48,9.00,
											2203.49,3305.22,12.00,
											3305.23,6433.57,14.00);
		
		tetoInss = tabInss.getVlrD2()*tabInss.getPercentualD()/100.00;
		
		if(descontoAcm >= tetoInss ) {
			descontoAcm = tetoInss; // evitar falha no cálculo
			resultado = 0.00;
		}else{
			if(valor<=tabInss.getVlrA1()) {
			    resultado = valor * tabInss.getPercentualA()/100.00;  
			}else if (valor>=tabInss.getVlrB1() && valor <= tabInss.getVlrB2()) {
				resultado = valor * tabInss.getPercentualB()/100.00;
			}else if (valor>=tabInss.getVlrC1() && valor <= tabInss.getVlrC2()) {
				resultado = valor * tabInss.getPercentualC()/100.00;
			}else if (valor>=tabInss.getVlrD1() && valor <= tabInss.getVlrD2()) {
				resultado = valor * tabInss.getPercentualD()/100.00;
			}else {
				resultado = tetoInss;
			}	
		}
				
		return (resultado>(tetoInss-descontoAcm)) ? tetoInss-descontoAcm : resultado;
	}

	@Override
	public void calculoImpostos(double valor, double acmIr, double acmInss, double descPensao, boolean geraEmpresa) {

		double irpj=0.00;
		double pis=0.00;
		double cofins=0.00;
		double csll=0.00;
		double iss=0.00;
		double fundoReserva=0.00;
		double pensao=0.00;
		double txADM=0.00;
		double ir=0.00;
		double inss=0.00;
		
		double empresaPJ=0.00;
		double empresaPF=0.00;
		double valorBruto=0.00;
		double vlrBrutoPJ=0.00;
		double vlrBrutoPF=0.00;
		double descontos=0.00;
		double vlrLiquidoPJ=0.00;
		double vlrLiquidoPF=0.00;

		TabelaImpostos tbI = new TabelaImpostos(1.50,3.00,0.65, 1.00,5.00,
												0.00,30.00,3.50,98.00,2.00);
		valorBruto = valor;
		
		if (geraEmpresa) {
			valorBruto = valorBruto * ((100.00-(tbI.getPis()+tbI.getCofins()))/100);
			vlrBrutoPJ = valorBruto * tbI.getEmpresaPJ()/100.00;
			vlrBrutoPF = valorBruto * tbI.getEmpresaPF()/100.00;
		
			irpj 	= vlrBrutoPJ * tbI.getIrpj()/100.00;
			txADM 	= vlrBrutoPJ * tbI.getTxADM()/100.00;
			pis		= vlrBrutoPJ * tbI.getPis()/100.00;
			cofins 	= vlrBrutoPJ * tbI.getCofins()/100.00;
			iss 	= vlrBrutoPJ * tbI.getIss()/100.00;
			csll	= vlrBrutoPJ * tbI.getCsll()/100.00;
			descontos = (irpj+txADM+pis+cofins+csll+iss);
			
			vlrLiquidoPJ = vlrBrutoPJ - descontos;

			System.out.println("Pessoa Juridica:");
			System.out.println("----------------");
			System.out.println("Valor Bruto: " + String.format("%.2f",vlrBrutoPJ));
			System.out.println("IRPJ: " + String.format("%.2f",irpj));
			System.out.println("TX.ADM: " + String.format("%.2f",txADM));
			System.out.println("PIS: " + String.format("%.2f",pis));
			System.out.println("COFINS: " + String.format("%.2f",cofins));
			System.out.println("CSLL: " + String.format("%.2f",csll));
			System.out.println("Total Descontos: " + String.format("%.2f",descontos));
			System.out.println("Valor Liquido: " + String.format("%.2f",vlrBrutoPJ-descontos));
			System.out.println("");

		}else {
			vlrBrutoPF = valorBruto;
		}
		
		ir 		  	 = this.calculoIr(vlrBrutoPF, acmIr);
		inss 	  	 = this.calculoINSS(vlrBrutoPF, acmInss);
		txADM 	  	 = vlrBrutoPF * tbI.getTxADM()/100.00;
		descontos 	 = ir + inss + txADM;
		vlrLiquidoPF = vlrBrutoPF - descontos;
	
		if(descPensao>0.00) {
			pensao 		 = vlrLiquidoPF * (descPensao/100.00);
			descontos 	 = ir + inss + txADM + pensao;
			vlrLiquidoPF = vlrBrutoPF - descontos;
		}
		
		System.out.println("Pessoa Física:");
		System.out.println("----------------");
		System.out.println("Valor Bruto: " + String.format("%.2f",vlrBrutoPF));
		System.out.println("IR: " + String.format("%.2f",ir));
		System.out.println("TX.ADM: " + String.format("%.2f",txADM));
		System.out.println("INSS: " + String.format("%.2f",inss));
		System.out.println("PENSAO: " + String.format("%.2f",pensao));
		System.out.println("Total Descontos: " + String.format("%.2f",descontos));
		System.out.println("Valor Liquido: " + String.format("%.2f",vlrBrutoPF-descontos));
		
	}

}
