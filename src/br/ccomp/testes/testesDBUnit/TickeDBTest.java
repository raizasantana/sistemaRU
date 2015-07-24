package br.ccomp.testes.testesDBUnit;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Array;

import br.ccomp.gateway.TicketGateway;
import br.ccomp.modelo.Ticket;
import junit.framework.TestCase;

public class TickeDBTest extends TestCase{
	
	private TicketGateway tG;
	
	public TickeDBTest()
	{
		tG = new TicketGateway();
	}
	
	@Test
	public void testFind() throws SQLException
	{
		Ticket t = new Ticket();
		t.setId(1);
		assertEquals(t.getId(), tG.find(1).getId());
	}
	
	@Test
	public void testFindAllByMAtricula() throws SQLException
	{
		ArrayList<Ticket> ts = tG.findAll(1234);
		assertEquals(2,ts.size());
	}
	
	@Test
	public void testFindAll() throws SQLException
	{
		ArrayList<Ticket> ts = tG.findAll();
		assertEquals(3,ts.size());
	}
	
	@Test
	public void testUpdate() throws SQLException
	{
		tG.update(1, false);
		assertEquals(false, tG.find(1).getPago());
	}
	
	@Test
	public void testInsert() throws SQLException
	{
		int qtd = tG.findAll().size();
		boolean in = tG.insert(1, (float) 1.0, 1,true);
		assertEquals(qtd+1, tG.findAll().size());
	}
	
	
	

}
