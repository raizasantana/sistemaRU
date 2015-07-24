package br.ccomp.transactions;

import br.ccomp.gateway.CursoGateway;

public class RoteiroCriaCurso {
	
	public boolean execute(String nome, String sigla, int idDepartamento) throws Exception{
		
		CursoGateway cursoGateway = new CursoGateway();
		if(nome.equals("") || sigla.equals(""))
			throw new Exception("Curso com dado obrigatorio nao preenchido");
		
		if (!cursoGateway.find(sigla)){	
			cursoGateway.insert(nome, sigla, idDepartamento);
			return true;
		}
		else
			throw new Exception("Curso de mesma sigla ja cadastrado");
	}

}
