package br.ccomp.transactions;

import java.sql.SQLException;

import br.ccomp.gateway.ConsumidorGateway;

public class RoteiroAtualizaConsumidor {
	
	public boolean execute(int id, int ano, int matricula, String nome, String sexo) throws SQLException {
		ConsumidorGateway cG = new ConsumidorGateway();
		try {
			cG.update(id, ano, matricula, nome, sexo);
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		}
	}

}
