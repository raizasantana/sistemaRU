package br.ccomp.testes.testesUnitarios;

import org.junit.Test;
import static org.junit.Assert.*;

import br.ccomp.modelo.Aluno;
import br.ccomp.modelo.Curso;

public class AlunoTest {

	@Test
	public void test_getCurso() {
		Aluno aluno = new Aluno();
		Curso curso = new Curso();
		Curso curso2 = new Curso();
		
		aluno.setCurso(curso);
		assertEquals(curso, aluno.getCurso());
		assertNotEquals(curso2, aluno.getCurso());
	}
	
}
