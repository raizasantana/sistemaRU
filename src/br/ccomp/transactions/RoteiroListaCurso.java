package br.ccomp.transactions;

import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.gateway.CursoGateway;
import br.ccomp.modelo.Curso;

public class RoteiroListaCurso {
	
	public ArrayList<Curso> execute(){
		CursoGateway cursoGateway =  new CursoGateway();
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		
		try {
			cursos = cursoGateway.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cursos;
	}

}
