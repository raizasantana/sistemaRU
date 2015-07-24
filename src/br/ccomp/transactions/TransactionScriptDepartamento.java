package br.ccomp.transactions;

import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.gateway.DepartamentoGateway;
import br.ccomp.modelo.Departamento;

public class TransactionScriptDepartamento {
private DepartamentoGateway departamentoGateway;
	
	public TransactionScriptDepartamento() {
		departamentoGateway = new DepartamentoGateway();
	}
	
	public Object execute(String acao){
		//switch pra acao 
		
		// return ou null pras transactions de insert que nao possuem retorno ou um Departamento pros de get ou uma lista pro de listar 
		return null;
	}
	
	public ArrayList<Departamento> listarDepartamentos(){
		ArrayList<Departamento> departamentos = null;
		try {
			departamentos = departamentoGateway.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return departamentos;
	}
	
	public boolean inserirDepartamento(String nome, String sigla) throws SQLException {
		try {
			return departamentoGateway.insert(nome, sigla);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}
	
	public boolean alterarDepartamento(int id, String nome, String sigla) throws Exception {
		if(nome.equals("") || sigla.equals(""))
			throw new Exception("Dados inv�lidos");
		if(departamentoGateway.find(sigla, id))
			throw new Exception("Departamento de mesma sigla j� cadastrado");
		
		departamentoGateway.update(id, nome, sigla);
		return true;
	}
	
	public Departamento getDepartamento(int id){
		Departamento dpt = null;
		try {
			dpt = departamentoGateway.find(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return dpt;
	}
}
