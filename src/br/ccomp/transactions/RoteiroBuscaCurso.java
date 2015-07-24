package br.ccomp.transactions;

import br.ccomp.gateway.CursoGateway;
import br.ccomp.modelo.Curso;

public class RoteiroBuscaCurso {
	
	public Curso execute(int id) throws Exception{
		CursoGateway cursoGateway = new CursoGateway();
		Curso curso = cursoGateway.find(id);
		
		if (curso == null)
			throw new Exception("Nenhum curso encontrado");
		
		return curso;
	}

}
