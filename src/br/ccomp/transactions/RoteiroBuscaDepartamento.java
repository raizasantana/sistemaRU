package br.ccomp.transactions;

import java.sql.SQLException;

import br.ccomp.gateway.DepartamentoGateway;
import br.ccomp.modelo.Departamento;

public class RoteiroBuscaDepartamento {
	public Departamento execute(int id) {
		DepartamentoGateway departamentoGateway = new DepartamentoGateway();
		Departamento dpt = null;
		try {
			dpt = departamentoGateway.find(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return dpt;
	}

}
