package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ccomp.modelo.Refeicao;

public class RefeicaoGateway {
	
Connection con = ConnectionFactory.getConnection();
	
	public void insert(Refeicao refeicao){
		String sql = "INSERT INTO refeicao" +
				"VALUES (?,?,?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, refeicao.getTurno().name());
			prst.setString(2, refeicao.getDescrcicao());
			prst.setString(3, refeicao.getOpcaoVegetariana());
			prst.setString(4, refeicao.getTipo().name());
			
			ResultSet rs = prst.executeQuery();
			
			prst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
			
	}
	
	public void delete(Integer idRefeicao) throws SQLException{
		String sql = "DELETE FROM refeicao" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, idRefeicao);
		
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		
	}
	
	public ResultSet findAll() throws SQLException{
		String sql = "SELECT * FROM refeicao";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}

	public ResultSet find(Integer id) throws SQLException{
		String sql = "SELECT * FROM refeicao" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, id);
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}

}
