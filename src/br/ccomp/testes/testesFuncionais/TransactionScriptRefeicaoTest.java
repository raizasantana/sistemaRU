package br.ccomp.testes.testesFuncionais;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ccomp.gateway.ConnectionFactory;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.TipoRefeicao;
import br.ccomp.modelo.Turno;
import br.ccomp.transactions.TransactionScriptRefeicao;

public class TransactionScriptRefeicaoTest {

	TransactionScriptRefeicao TSR;
	
	@Before
	public void setUp() throws Exception {
		TSR = new TransactionScriptRefeicao();
	}
	
	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionFactory.getConnection();
		TSR.alterarRefeicao(1, "P�o de Sal", "");
		String sql = "DELETE FROM REFEICAO WHERE descricao = 'P�o com Manteiga'";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		prst.executeUpdate();
		prst.close();
	}

	@Test
	public void testInserirRefeicao() {
		try {
			boolean test = TSR.inserirRefeicao("P�o com Manteiga", "", TipoRefeicao.DESJEJUM, Turno.MANHA);
			assertEquals(test,true);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testListarRefeicao() {
		boolean test = TSR.listarRefeicao() instanceof ArrayList<?>;
		assertEquals(test,true);
	}

	@Test
	public void testRecuperarRefeicao() {
		boolean test = TSR.recuperarRefeicao(2) instanceof Refeicao;
		assertEquals(test,true);
	}

	@Test
	public void testAlterarRefeicao() {
		try {
			boolean test = TSR.alterarRefeicao(1, "P�o sem Sal", "Agua");
			assertEquals(test,true);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

}
