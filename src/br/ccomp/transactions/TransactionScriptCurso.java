package br.ccomp.transactions;

import java.io.Console;
import java.sql.SQLException;

import br.ccomp.gateway.CursoGateway;
import br.ccomp.modelo.Curso;
import br.ccomp.modelo.Departamento;

public class TransactionScriptCurso {
	private CursoGateway cursoGateway;
	
	public TransactionScriptCurso(){
		cursoGateway = new CursoGateway();
	}
	
	// Retorna sucesso ou falha (true ou false)
	// TODO: voltar a verificar antes de inserir
	public boolean inserirCurso(String nome, String sigla, int idDepartamento){
//		try {
//			
//			if (!cursoGateway.find(sigla)){
				Curso curso = new Curso();
				Departamento departamento = new Departamento(idDepartamento);
				
				curso.setNome(nome);
				curso.setSigla(sigla);
				curso.setDepartamento(departamento);
				
				cursoGateway.insert(curso);
				
				return true;
//			}
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		return false;
	}
}
