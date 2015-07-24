package br.ccomp.transactions;

import java.sql.SQLException;

import br.ccomp.gateway.ConsumidorGateway;

public class RoteiroCriarAluno {
	
	public boolean execute(int curso, String nome,String cpf,int matricula, int anoIngresso, String sexo){
		
		if(!isValidCPF(cpf))
			return false;
		
		ConsumidorGateway consGT = new ConsumidorGateway();
		
		try {
			if(consGT.findbyCpf(cpf))
				return false;
		} catch (SQLException e1) {
			e1.printStackTrace();
			return false;
		}
				
		return consGT.insertAluno(curso, nome, cpf, matricula, anoIngresso,sexo);

	}
	

	private int calcularDigito(String str, int[] peso) {
		int soma = 0;
		for (int indice=str.length()-1, digito; indice >= 0; indice-- ) {
			digito = Integer.parseInt(str.substring(indice,indice+1));
			soma += digito*peso[peso.length-str.length()+indice];
		}
		soma = 11 - soma % 11;
		return soma > 9 ? 0 : soma;
	}

	public boolean isValidCPF(String cpf) {
		if ((cpf==null) || (cpf.length()!=11)) return false;

		int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
		Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	
	}

}
