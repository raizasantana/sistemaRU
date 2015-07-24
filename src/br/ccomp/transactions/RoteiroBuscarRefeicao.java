package br.ccomp.transactions;

import java.sql.SQLException;

import br.ccomp.gateway.RefeicaoGateway;
import br.ccomp.modelo.Refeicao;

public class RoteiroBuscarRefeicao {
	
	public Refeicao execute(Integer idRefeicao){
		RefeicaoGateway refeicaoGateway = new RefeicaoGateway();
		try {
			return refeicaoGateway.findById(idRefeicao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

}
