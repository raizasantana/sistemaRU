package br.ccomp.transactions;

import java.sql.SQLException;

import br.ccomp.gateway.RefeicaoGateway;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.TipoRefeicao;
import br.ccomp.modelo.Turno;

public class RoteiroCriaRefeicao {
	
	public boolean execute(String descricao, String opcaoVegetariana, TipoRefeicao tipo, Turno turno) throws SQLException{

		RefeicaoGateway refeicaoGateway = new RefeicaoGateway();
		Refeicao refeicao = new Refeicao();
		
		refeicao.setDescricao(descricao);
		refeicao.setOpcaoVegetariana(opcaoVegetariana);
		refeicao.setTipo(tipo);
		refeicao.setTurno(turno);
		
		refeicaoGateway.insert(refeicao.getTurno().getNome(), refeicao.getDescricao(), 
				refeicao.getOpcaoVegetariana(), refeicao.getTipo().getNome());
		
		return true;
	}

}
