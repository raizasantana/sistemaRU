package br.ccomp.transactions;

import java.sql.SQLException;

import br.ccomp.gateway.RefeicaoGateway;

public class RoteiroAtualizaRefeicao {
	
	public boolean execute(Integer id, String descricao, String opVegan) throws SQLException {
		RefeicaoGateway refeicaoGateway = new RefeicaoGateway();
		try {
			refeicaoGateway.update(id, descricao, opVegan);
			return true;
		} catch (SQLException e) {
			throw e;
		}
	}

}
