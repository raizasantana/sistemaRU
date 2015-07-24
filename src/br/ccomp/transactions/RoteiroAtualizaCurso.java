package br.ccomp.transactions;

import br.ccomp.gateway.CursoGateway;

public class RoteiroAtualizaCurso {
	
	public boolean execute(int id, String nome, String sigla, int idDepartamento) throws Exception{
		
		CursoGateway cursoGateway = new CursoGateway();
		if(nome.equals("") || sigla.equals(""))
			throw new Exception("Curso com dado obrigatorio nao preenchido");
		
		if(!cursoGateway.find(id, sigla)){
			cursoGateway.update(id, nome, sigla, idDepartamento);
			return true;
		}else
			throw new Exception("Curso de mesma sigla ja cadastrado");
	}
	

}
