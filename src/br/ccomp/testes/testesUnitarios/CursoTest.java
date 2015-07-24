package br.ccomp.testes.testesUnitarios;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ccomp.modelo.Curso;
import br.ccomp.modelo.Departamento;

public class CursoTest {

	@Test
	public void testGetId() {
		Curso curso = new Curso();
		curso.setId(1);
		
		assertEquals((Integer) 1, curso.getId());
	}
	
	@Test
	public void testGetNome() {
		Curso curso = new Curso();
		curso.setNome("nome");
		
		assertEquals("nome", curso.getNome());
	}
	
	@Test
	public void testGetSigla() {
		Curso curso = new Curso();
		curso.setSigla("sigla");
		
		assertEquals("sigla", curso.getSigla());
	}
	
	@Test
	public void testGetDepartamento() {
		Curso curso = new Curso();
		Departamento dpt = new Departamento();
		Departamento dpt2 = new Departamento();
		curso.setDepartamento(dpt);
		
		assertEquals(dpt, curso.getDepartamento());
		assertNotEquals(dpt2, curso.getDepartamento());
	}
	
}
