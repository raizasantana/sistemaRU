package br.ccomp.transactions;

import java.sql.ResultSet;
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
	
	public void alterarDepartamento(int id, String nome, String sigla) throws Exception {
		if(nome.equals("") || sigla.equals(""))
			throw new Exception("Dados inválidos");
		if(departamentoGateway.find(sigla, id))
			throw new Exception("Departamento de mesma sigla já cadastrado");
		
		departamentoGateway.update(id, nome, sigla);
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
