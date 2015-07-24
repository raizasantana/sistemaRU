package br.ccomp.transactions;

import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.gateway.RefeicaoGateway;
import br.ccomp.modelo.Refeicao;

public class RoteiroListaRefeicao {
	
	public ArrayList<Refeicao> execute(){
		RefeicaoGateway refeicaoGateway = new RefeicaoGateway();
		ArrayList<Refeicao> refeicoes = new ArrayList<Refeicao>();
		//TODO descomentar
		try {
			refeicoes = refeicaoGateway.findAll();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return refeicoes;
	}

}
