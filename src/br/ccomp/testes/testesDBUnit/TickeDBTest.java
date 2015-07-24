package br.ccomp.testes.testesDBUnit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.mysql.fabric.xmlrpc.base.Array;
import com.mysql.jdbc.PreparedStatement;

import br.ccomp.gateway.ConnectionFactory;
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
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from TICKET where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		rst.next();
		
		Integer refeicao = rst.getInt("id_refeicao");
		
    	
    	assertEquals(refeicao, tG.find(1).getRefeicao().getId());
    	
    	prst.close();
    	rst.close();
		
	}
	
	@Test
	public void testUpdate() throws SQLException
	{
		tG.update(1, false);
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from TICKET where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		rst.next();
		
		Integer pago = 1;
		Integer zero = 0;
				
		pago = rst.getInt("pago");

		assertEquals(zero, pago);
		

    	prst.close();
    	rst.close();

	}
	
	@Test
	public void testInsert() throws SQLException
	{
		
		tG.insert(1, (float) 9.0, 1,true);
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from TICKET where valor = 9.0");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		rst.next();
		float valor = rst.getFloat("valor");
		float nove = 9.0f;
		
		assertEquals(nove, valor);
		
    	prst.close();
    	rst.close();
	}
	
	
	

}
