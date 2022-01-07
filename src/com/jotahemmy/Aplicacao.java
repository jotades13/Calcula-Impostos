package com.jotahemmy;

import com.jotahemmy.servico.Calculos;

public class Aplicacao {

	public static void main(String[] args) {
		
		Calculos Impostos = new Calculos();
		
		//System.out.println(Impostos.calculoIr(2500.00, 500.00));
		//System.out.println(IR.calculoINSS(50000.00, 200.00));
		
		//                       valor    acmIr   acmInss pensao empresa
		Impostos.calculoImpostos(10000.00, 0.00, 100.00, 0.00,  true);
	}
}
