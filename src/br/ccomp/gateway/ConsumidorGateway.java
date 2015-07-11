package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ccomp.modelo.Consumidor;

public class ConsumidorGateway {

	Connection con = ConnectionFactory.getConnection();
	
	public void insert(Consumidor consumidor){
		String sql = "INSERT INTO consumidor" +
				"VALUES (?,?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, consumidor.getNome());
			prst.setString(2, consumidor.getCpf());
			prst.setInt(3, consumidor.getMatricula());
			prst.setString(4, consumidor.getSexo().name());
			prst.setInt(5, consumidor.getAnoIngresso());
			prst.setString(6, consumidor.getTitulo().name());
			
			ResultSet rs = prst.executeQuery();
			
			prst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(Integer idConsumidor) throws SQLException{
		String sql = "DELETE FROM consumidor" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, idConsumidor);
		
		ResultSet rs = prst.executeQuery();
		
		prst.close();
	}
	
	public ResultSet findAll() throws SQLException{
		String sql = "SELECT * FROM consumidor";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}
	
	public ResultSet find(Integer id) throws SQLException{
		String sql = "SELECT * FROM consumidor" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, id);
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}
}
