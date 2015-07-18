package br.ccomp.transactions;

import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.gateway.RefeicaoGateway;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.TipoRefeicao;
import br.ccomp.modelo.Turno;

public class TransactionScriptRefeicao {
private RefeicaoGateway refeicaoGateway;
	
	public TransactionScriptRefeicao(){
		refeicaoGateway = new RefeicaoGateway();
	}
	
	// Retorna sucesso ou falha (true ou false)
	// TODO: voltar a verificar antes de inserir
	public boolean inserirRefeicao(String descricao, String opcaoVegetariana, TipoRefeicao tipo, Turno turno){
//		try {
//			
//			if (!refeicaoGateway.find(sigla)){
				Refeicao refeicao = new Refeicao();
				
				refeicao.setDescricao(descricao);
				refeicao.setOpcaoVegetariana(opcaoVegetariana);
				refeicao.setTipo(tipo);
				refeicao.setTurno(turno);
				
				refeicaoGateway.insert(refeicao.getTurno().getNome(), refeicao.getDescricao(), 
						refeicao.getOpcaoVegetariana(), refeicao.getTipo().getNome());
				
				return true;
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
	}
	
	public boolean deletarRefeicao(int id){
		//return refeicaoGateway.delete(id);
		return false;
	}
	
	public ArrayList<Refeicao> listarRefeicao(){
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
	
	public Refeicao recuperarRefeicao(Integer idRefeicao){
		try {
			return refeicaoGateway.findById(idRefeicao);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	public void alterarRefeicao(Integer id, String descricao, String opVegan) {
		refeicaoGateway.update(id, descricao, opVegan);
	}
}
