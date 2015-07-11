package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import br.ccomp.modelo.Curso;
import br.ccomp.modelo.Departamento;

public class CursoGateway {

Connection con = ConnectionFactory.getConnection();
	
	public void insert(Curso curso, Departamento dept){
		String sql = "INSERT INTO curso" +
				"VALUES (?,?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, curso.getNome());
			prst.setString(2, curso.getSigla());
			prst.setInt(3, dept.getId());
			
			ResultSet rs = prst.executeQuery();
			
			prst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 		
			
	}
	
	public void delete(Integer idCurso) throws SQLException{
		String sql = "DELETE FROM curso" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, idCurso);
		
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		
	}
	
	public ResultSet findAll() throws SQLException{
		String sql = "SELECT * FROM curso";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}

	public ResultSet find(Integer id) throws SQLException{
		String sql = "SELECT * FROM curso" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, id);
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}
}
