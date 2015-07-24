package br.ccomp.transactions;

import java.sql.SQLException;

import br.ccomp.gateway.DepartamentoGateway;

public class RoteiroCriaDepartamento {
	
	public boolean execute(String nome, String sigla) throws SQLException {
		DepartamentoGateway departamentoGateway = new DepartamentoGateway();
		try {
			return departamentoGateway.insert(nome, sigla);
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
