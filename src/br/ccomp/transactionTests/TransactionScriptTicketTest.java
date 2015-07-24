package br.ccomp.transactionTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ccomp.gateway.ConnectionFactory;
import br.ccomp.modelo.Ticket;
import br.ccomp.transactions.TransactionScriptConsumidor;
import br.ccomp.transactions.TransactionScriptRefeicao;
import br.ccomp.transactions.TransactionScriptTicket;

public class TransactionScriptTicketTest {

	TransactionScriptTicket TST;
	TransactionScriptConsumidor TSC;
	TransactionScriptRefeicao TSR;
	
	@Before
	public void setUp() throws Exception {
		TST = new TransactionScriptTicket();
		TSC = new TransactionScriptConsumidor();
		TSR = new TransactionScriptRefeicao();
	}
	
	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionFactory.getConnection();
		TSR.alterarRefeicao(1, "Pão de Sal", "");
		String sql = "DELETE FROM ticket WHERE id_consumidor in (SELECT id from consumidor where matricula = 54321)";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		prst.executeUpdate();
		prst.close();
	}

	@Test
	public void testInserirTicket() {
		try {
			boolean test = TST.inserirTicket(TSC.getConsumidorMatricula(54321), TSR.recuperarRefeicao(1), 1);
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAlterarTicket() {
		try {
			boolean test = TST.alterarTicket(1,false);
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetTicket() {
		try {
			boolean test = TST.getTicket(1) instanceof Ticket;
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testListarTickets() {
		try {
			boolean test = TST.listarTickets() instanceof ArrayList<?>;
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testListarTicketsInt() {
		try {
			boolean test = TST.listarTickets(12345) instanceof ArrayList<?>;
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
