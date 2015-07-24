package br.ccomp.testes.testesFuncionais;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ccomp.gateway.ConnectionFactory;
import br.ccomp.modelo.Curso;
import br.ccomp.transactions.RoteiroAtualizaCurso;
import br.ccomp.transactions.RoteiroBuscaCurso;
import br.ccomp.transactions.RoteiroCriaCurso;
import br.ccomp.transactions.RoteiroListaCurso;

public class TransactionScriptCursoTest {

	private RoteiroAtualizaCurso RAC;
	private RoteiroBuscaCurso RBC;
	private RoteiroCriaCurso RCC;
	private RoteiroListaCurso RLC;
	
	@Before
	public void setUp() throws Exception {
		RAC = new RoteiroAtualizaCurso();
		RBC = new RoteiroBuscaCurso();
		RCC = new RoteiroCriaCurso();
		RLC = new RoteiroListaCurso();
	}
	
	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionFactory.getConnection();
		RAC.execute(1,"Ciencia da Computação", "CComp", 1);
		String sql = "DELETE FROM CURSO WHERE nome = 'RandomStuff'";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		prst.executeUpdate();
		prst.close();
	}

	@Test
	public void testInserirCurso() {
		try {
			boolean test = RCC.execute("RandomStuff", "RSt", 2);
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAlterarCurso() {
		try {
			boolean test = RAC.execute(1,"ALotOfRandomStuff", "AlRSt", 2);
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetCurso() {
		try {
			boolean test = RBC.execute(1) instanceof Curso;
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testListarCurso() {
		boolean test = RLC.execute() instanceof ArrayList<?>;
		assertEquals(test,true);
	}

}
