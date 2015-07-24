package br.ccomp.testes.testesUnitarios;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.Ticket;

public class TicketTest {

	@Test
	public void testGetId() {
		Ticket tkt = new Ticket();
		tkt.setId(1);
		
		assertEquals((Integer) 1, tkt.getId());
	}
	
	@Test
	public void testGetPago() {
		Ticket tkt = new Ticket();
		tkt.setPago(true);
		
		assertEquals(true, tkt.getPago());
	}
	
	@Test
	public void testGetValor() {
		Ticket tkt = new Ticket();
		tkt.setValor(1f);
		
		assertEquals(1f, tkt.getValor(), 0.001f);
	}
	
	@Test
	public void testGetRefeicao() {
		Ticket tkt = new Ticket();
		Refeicao refeicao = new Refeicao();
		Refeicao refeicao2 = new Refeicao();
		tkt.setRefeicao(refeicao);
		
		assertEquals(refeicao, tkt.getRefeicao());
		assertNotEquals(refeicao2, tkt.getRefeicao());
	}
	
	@Test
	public void testGetConsumidor() {
		Ticket tkt = new Ticket();
		Consumidor consumidor = new Consumidor();
		Consumidor consumidor2 = new Consumidor();
		tkt.setConsumidor(consumidor);
		
		assertEquals(consumidor, tkt.getConsumidor());
		assertNotEquals(consumidor2, tkt.getConsumidor());
	}
}
