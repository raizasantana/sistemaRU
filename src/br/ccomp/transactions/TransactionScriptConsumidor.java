package br.ccomp.transactions;

import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.gateway.ConsumidorGateway;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Sexo;
import br.ccomp.modelo.Titulo;

public class TransactionScriptConsumidor {
	
	int[] pesoCPF = {11, 10, 9, 8, 7, 6, 5, 4, 3, 2};
	
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

		Integer digito1 = calcularDigito(cpf.substring(0,9), pesoCPF);
		Integer digito2 = calcularDigito(cpf.substring(0,9) + digito1, pesoCPF);
		return cpf.equals(cpf.substring(0,9) + digito1.toString() + digito2.toString());
	
	}
	
	public TransactionScriptConsumidor(){
		
	}
	
	//Aluno
	public boolean criarAluno(int curso, String nome,String cpf,int matricula, int anoIngresso, String sexo) {
		//Validar cpf
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
	
	//Funcionario
	public boolean criarFuncionario(int departamento, String nome, String cpf, String titulo, int matricula, int anoIngresso, String sexo) {
		//Validar cpf
		if(!isValidCPF(cpf))
			return false;
		
		ConsumidorGateway consGT = new ConsumidorGateway();
		
		try {
			if(consGT.findbyCpf(cpf))
				return false;
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		
		return consGT.insertFuncionario(departamento, nome, cpf, titulo, matricula, anoIngresso, sexo);
	}
	
	public ArrayList<Consumidor> listarConsumidores() {
		ConsumidorGateway consGT = new ConsumidorGateway();
		ArrayList<Consumidor> cons = null;
		
		try {
			cons = consGT.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return cons;
	}
	
	public Consumidor getConsumidor(int id)	{
		ConsumidorGateway cG = new ConsumidorGateway();
		Consumidor c = null;
		try {
			 c = cG.find(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}
	
	public boolean atualizarConsumidor(int id, int ano, int matricula, String nome, String sexo) throws SQLException {
		ConsumidorGateway cG = new ConsumidorGateway();
		try {
			cG.update(id, ano, matricula, nome, sexo);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public Titulo getTitulo(String tit) {
		if(tit.equals(Titulo.DOUTORADO.getNome()))
			return Titulo.DOUTORADO;
		else if (tit.equals(Titulo.ESPECIALIZACAO.getNome()))
			return Titulo.ESPECIALIZACAO;
		else 
			return Titulo.MESTRADO;
	}
	
	public Sexo getSexo(String sexo) {
		if(sexo.equals(Sexo.FEMININO.getNome()))
			return Sexo.FEMININO;
		else
			return Sexo.MASCULINO;
	}

	public Consumidor getConsumidorMatricula(Integer matricula) {
		ConsumidorGateway consGT = new ConsumidorGateway();
		
		try {
			return consGT.findByMatricula(matricula);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

}
