package br.ccomp.transactionTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.ccomp.modelo.Departamento;
import br.ccomp.transactions.TransactionScriptDepartamento;

public class TransactionScriptDepartamentoTest {

	TransactionScriptDepartamento TSD;
	
	@Before
	public void setUp() throws Exception {
		TSD = new TransactionScriptDepartamento();
	}

	@Test
	public void testListarDepartamentos() {
		boolean test = TSD.listarDepartamentos() instanceof ArrayList<?>;
		assertEquals(test,true);
	}

	@Test
	public void testInserirDepartamento() {
		try {
			boolean test = TSD.inserirDepartamento("Departamento de História", "DH");
			assertEquals(test,true);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAlterarDepartamento() {
		try {
			boolean test = TSD.alterarDepartamento(3,"Departamento de História", "DH");
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetDepartamento() {
		boolean test = TSD.getDepartamento(2) instanceof Departamento;
		assertEquals(test,true);
	}

}
