package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.modelo.Curso;
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
	
	public ArrayList<Departamento> findAll() throws SQLException{
		ArrayList<Departamento> departamentos = new ArrayList<Departamento>();
		Connection conn = null;
		
		String sql = "SELECT * FROM DEPARTAMENTO";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				departamentos.add(new br.ccomp.modelo.Departamento(
						rs.getInt("DEPARTAMENTO.ID"),
						rs.getString("DEPARTAMENTO.NOME"),
						rs.getString("DEPARTAMENTO.SIGLA")));
			}
			
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return departamentos;
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
