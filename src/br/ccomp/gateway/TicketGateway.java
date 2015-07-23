package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.Ticket;
import br.ccomp.modelo.Turno;

public class TicketGateway {
	Connection con = ConnectionFactory.getConnection();
	
	public boolean insert(Integer idConsumidor, Float valor, Integer idRefeicao, Boolean pago) throws SQLException{
		String sql = "INSERT INTO ticket (id_consumidor, valor, id_refeicao, pago) " +
				"VALUES (?,?,?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setInt(1, idConsumidor);
			prst.setFloat(2, valor);
			prst.setInt(3, idRefeicao);
			prst.setBoolean(4, pago);
			
			prst.executeUpdate();
			
			prst.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 		
		
		
	}
	
	public boolean update(Integer id, Boolean pago) throws SQLException{
		String sql = "UPDATE TICKET SET PAGO = ? WHERE ID = ?";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			int isPago = pago ? 1 : 0;
			prst.setInt(1, isPago);
			prst.setInt(2, id);
			
			prst.executeUpdate();
			
			prst.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 		
			
	}
	
	public ArrayList<Ticket> findAll() throws SQLException{
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM ticket t, refeicao r, consumidor c " +
				"WHERE t.id_refeicao = r.id AND t.id_consumidor = c.id";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		ResultSet rs = prst.executeQuery();
		
		while(rs.next()){
			boolean pago = rs.getInt("t.pago") == 1 ? true : false;
			Turno turno = null;
			
			if(rs.getString("r.turno").equals(Turno.MANHA.getNome())) turno = Turno.MANHA;
			else if(rs.getString("r.turno").equals(Turno.TARDE.getNome())) turno = Turno.TARDE;
			else if(rs.getString("r.turno").equals(Turno.NOITE.getNome())) turno = Turno.NOITE;
			
			Ticket ticket = new Ticket(
					rs.getInt("t.id"),
					rs.getFloat("t.valor"),
					new Refeicao(
							rs.getInt("r.id"),
							turno,
							rs.getString("r.descricao")),
					new Consumidor(
							rs.getInt("c.matricula")),
					pago 		
					);
			
			tickets.add(ticket);
		}
		
		prst.close();
		return tickets;
	}
	
	public ArrayList<Ticket> findAll(int matriculaConsumidor) throws SQLException{
		ArrayList<Ticket> tickets = new ArrayList<Ticket>();
		String sql = "SELECT * FROM ticket t, refeicao r, consumidor c " +
				"WHERE t.id_refeicao = r.id AND t.id_consumidor = c.id AND c.matricula = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, matriculaConsumidor);
		
		ResultSet rs = prst.executeQuery();
		
		while(rs.next()){
			boolean pago = rs.getInt("t.pago") == 1 ? true : false;
			Turno turno = null;
			
			if(rs.getString("r.turno").equals(Turno.MANHA.getNome())) turno = Turno.MANHA;
			else if(rs.getString("r.turno").equals(Turno.TARDE.getNome())) turno = Turno.TARDE;
			else if(rs.getString("r.turno").equals(Turno.NOITE.getNome())) turno = Turno.NOITE;
			
			Ticket ticket = new Ticket(
					rs.getInt("t.id"),
					rs.getFloat("t.valor"),
					new Refeicao(
							rs.getInt("r.id"),
							turno,
							rs.getString("r.descricao")),
					new Consumidor(
							rs.getInt("c.matricula")),
					pago 		
					);
			
			tickets.add(ticket);
		}
		
		prst.close();
		return tickets;
	}

	public Ticket find(Integer id) throws SQLException{
		String sql = "SELECT * FROM ticket t, refeicao r, consumidor c " +
				"WHERE t.id = ? AND t.id_refeicao = r.id AND t.id_consumidor = c.id";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, id);
		ResultSet rs = prst.executeQuery();
		
		Ticket ticket = null;
		if(rs.next()){
			boolean pago = rs.getInt("t.pago") == 1 ? true : false;
			Turno turno = null;
			
			if(rs.getString("r.turno").equals(Turno.MANHA.getNome())) turno = Turno.MANHA;
			else if(rs.getString("r.turno").equals(Turno.TARDE.getNome())) turno = Turno.TARDE;
			else if(rs.getString("r.turno").equals(Turno.NOITE.getNome())) turno = Turno.NOITE;
			
			ticket = new Ticket(
					rs.getInt("t.id"),
					rs.getFloat("t.valor"),
					new Refeicao(
							rs.getInt("r.id"),
							turno,
							rs.getString("r.descricao")),
					new Consumidor(
							rs.getInt("c.matricula")),
					pago 		
					);
		}
		
		prst.close();
		return ticket;
	}
}
