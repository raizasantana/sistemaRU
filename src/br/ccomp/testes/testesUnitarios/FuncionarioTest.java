package br.ccomp.testes.testesUnitarios;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ccomp.modelo.Departamento;
import br.ccomp.modelo.Funcionario;

public class FuncionarioTest {

	@Test
	public void testGetDepartamento() {
		Funcionario funcionario = new Funcionario();
		Departamento dpt = new Departamento();
		Departamento dpt2 = new Departamento();
		funcionario.setDepartamento(dpt);
		
		assertEquals(dpt, funcionario.getDepartamento());
		assertNotEquals(dpt2, funcionario.getDepartamento());
	}

}
