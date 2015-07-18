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
	
	public ArrayList<Departamento> listarDepartamentos(){
		ArrayList<Departamento> departamentos = null;
		try {
			departamentos = departamentoGateway.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		return departamentos;
	}
	
	public boolean inserirDepartamento(String nome, String sigla){
		departamentoGateway.insert(nome, sigla);
		return false;
	}
}
