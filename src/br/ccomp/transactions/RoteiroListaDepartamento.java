package br.ccomp.transactions;

import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.gateway.DepartamentoGateway;
import br.ccomp.modelo.Departamento;

public class RoteiroListaDepartamento {
	
	public ArrayList<Departamento> execute(){
		DepartamentoGateway departamentoGateway = new DepartamentoGateway();
		ArrayList<Departamento> departamentos = null;
		try {
			departamentos = departamentoGateway.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		} 
		
		return departamentos;
	}

}
