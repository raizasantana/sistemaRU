package br.ccomp.transactions;

import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.gateway.CursoGateway;
import br.ccomp.modelo.Curso;

public class TransactionScriptCurso {
	private CursoGateway cursoGateway;
	
	public TransactionScriptCurso(){
		cursoGateway = new CursoGateway();
	}
	
	public void inserirCurso(String nome, String sigla, int idDepartamento) throws Exception{
		if(nome.equals("") || sigla.equals(""))
			throw new Exception("Curso com dado obrigatório não preenchido");
		
		if (!cursoGateway.find(sigla)){	
			cursoGateway.insert(nome, sigla, idDepartamento);
		}
		else
			throw new Exception("Curso de mesma sigla já cadastrado");
	}
	
	public void alterarCurso(int id, String nome, String sigla, int idDepartamento) throws Exception{
		if(nome.equals("") || sigla.equals(""))
			throw new Exception("Curso com dado obrigatório não preenchido");
		
		if(!cursoGateway.find(id, sigla))
			cursoGateway.update(id, nome, sigla, idDepartamento);
		else
			throw new Exception("Curso de mesma sigla já cadastrado");
	}
	
	public Curso getCurso(int id) throws Exception{
		Curso curso = cursoGateway.find(id);
		
		if (curso == null)
			throw new Exception("Nenhum curso encontrado");
		
		return curso;
	}
	
	public ArrayList<Curso> listarCurso(){
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try {
			cursos = cursoGateway.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cursos;
	}
}
