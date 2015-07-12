package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ccomp.modelo.Ticket;

public class TicketGateway {
Connection con = ConnectionFactory.getConnection();
	
	public void insert(Ticket ticket){
		String sql = "INSERT INTO ticket" +
				"VALUES (?,?,?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setInt(1, ticket.getConsumidor().getId());
			prst.setFloat(2, ticket.getValor());
			prst.setInt(3, ticket.getRefeicao().getId());
			prst.setBoolean(4, ticket.isPago());
			
			ResultSet rs = prst.executeQuery();
			
			prst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
			
	}
	
	public void delete(Integer idTicket) throws SQLException{
		String sql = "DELETE FROM ticket" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, idTicket);
		
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		
	}
	
	public ResultSet findAll() throws SQLException{
		String sql = "SELECT * FROM ticket";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}

	public ResultSet find(Integer id) throws SQLException{
		String sql = "SELECT * FROM ticket" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, id);
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}
}
