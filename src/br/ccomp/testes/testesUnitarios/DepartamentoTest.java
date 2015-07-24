package br.ccomp.testes.testesUnitarios;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ccomp.modelo.Departamento;

public class DepartamentoTest {

	@Test
	public void testGetId() {
		Departamento dpt = new Departamento();
		dpt.setId(1);
		
		assertEquals((Integer) 1, dpt.getId());
	}
	
	@Test
	public void testGetNome() {
		Departamento dpt = new Departamento();
		dpt.setNome("nome");
		
		assertEquals("nome", dpt.getNome());
	}
	
	@Test
	public void testGetSigla() {
		Departamento dpt = new Departamento();
		dpt.setSigla("sigla");
		
		assertEquals("sigla", dpt.getSigla());
	}
}