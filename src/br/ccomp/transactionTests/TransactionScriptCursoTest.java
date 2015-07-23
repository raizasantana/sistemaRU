package br.ccomp.transactionTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

import br.ccomp.modelo.Curso;
import br.ccomp.transactions.TransactionScriptCurso;

public class TransactionScriptCursoTest {

	private TransactionScriptCurso TSC;
	
	@Before
	public void setUp() throws Exception {
		TSC = new TransactionScriptCurso();
	}

	@Test
	public void testInserirCurso() {
		try {
			boolean test = TSC.inserirCurso("RandomStuff", "RSt", 2);
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAlterarCurso() {
		try {
			boolean test = TSC.alterarCurso(5,"ALotOfRandomStuff", "AlRSt", 2);
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetCurso() {
		try {
			boolean test = TSC.getCurso(1) instanceof Curso;
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testListarCurso() {
		boolean test = TSC.listarCurso() instanceof ArrayList<?>;
		assertEquals(test,true);
	}

}
