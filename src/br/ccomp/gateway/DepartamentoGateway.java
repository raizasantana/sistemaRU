package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ccomp.modelo.Departamento;

public class DepartamentoGateway {
	
	Connection con = ConnectionFactory.getConnection();
	
	public void insert(Departamento dept){
		String sql = "INSERT INTO departamento" +
				"VALUES (?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, dept.getNome());
			prst.setString(2, dept.getSigla());
			
			ResultSet rs = prst.executeQuery();
			
			prst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
			
	}
	
	public void delete(Integer idDepartamento) throws SQLException{
		String sql = "DELETE FROM departamento" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, idDepartamento);
		
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		
	}
	
	public ResultSet findAll() throws SQLException{
		String sql = "SELECT * FROM departamento";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}

	public ResultSet find(Integer id) throws SQLException{
		String sql = "SELECT * FROM departamento" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, id);
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}
}
