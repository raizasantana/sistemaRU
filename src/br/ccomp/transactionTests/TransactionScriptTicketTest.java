package br.ccomp.transactionTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

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

	@Test
	public void testInserirTicket() {
		try {
			boolean test = TST.inserirTicket(TSC.getConsumidorMatricula(12345), TSR.recuperarRefeicao(1), 1);
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
