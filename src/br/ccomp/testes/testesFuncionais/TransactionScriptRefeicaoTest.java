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
import br.ccomp.transactions.RoteiroAtualizaRefeicao;
import br.ccomp.transactions.RoteiroBuscarRefeicao;
import br.ccomp.transactions.RoteiroCriaRefeicao;
import br.ccomp.transactions.RoteiroListaRefeicao;

public class TransactionScriptRefeicaoTest {

	private RoteiroAtualizaRefeicao RAR;
	private RoteiroBuscarRefeicao RBR;
	private RoteiroCriaRefeicao RCR;
	private RoteiroListaRefeicao RLR;
	
	@Before
	public void setUp() throws Exception {
		RAR = new RoteiroAtualizaRefeicao();
		RBR = new RoteiroBuscarRefeicao();
		RCR = new RoteiroCriaRefeicao();
		RLR = new RoteiroListaRefeicao();
	}
	
	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionFactory.getConnection();
		RAR.execute(1, "Pão de Sal", "");
		String sql = "DELETE FROM REFEICAO WHERE descricao = 'Pão com Manteiga'";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		prst.executeUpdate();
		prst.close();
	}

	@Test
	public void testInserirRefeicao() {
		try {
			boolean test = RCR.execute("Pão com Manteiga", "", TipoRefeicao.DESJEJUM, Turno.MANHA);
			assertEquals(test,true);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testListarRefeicao() {
		boolean test = RLR.execute() instanceof ArrayList<?>;
		assertEquals(test,true);
	}

	@Test
	public void testRecuperarRefeicao() {
		boolean test = RBR.execute(2) instanceof Refeicao;
		assertEquals(test,true);
	}

	@Test
	public void testAlterarRefeicao() {
		try {
			boolean test = RAR.execute(1, "Pão sem Sal", "Agua");
			assertEquals(test,true);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

}
