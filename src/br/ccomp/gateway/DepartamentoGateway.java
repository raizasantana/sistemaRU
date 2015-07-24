package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.modelo.Departamento;

public class DepartamentoGateway {
	
	Connection con = ConnectionFactory.getConnection();
	
	public boolean insert(String nome, String sigla) throws SQLException{
		String sql = "INSERT INTO DEPARTAMENTO(nome, sigla) " +
				"VALUES (?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, nome);
			prst.setString(2, sigla);
			
			prst.executeUpdate();
			
			prst.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 		
			
	}
	
	public boolean update(int id, String nome, String sigla) throws SQLException{
		String sql = "UPDATE DEPARTAMENTO SET NOME = ?, SIGLA = ? WHERE ID = ?";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, nome);
			prst.setString(2, sigla);
			prst.setInt(3, id);
			
			prst.executeUpdate();
			
			prst.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			throw e;
		} 		
			
	}
	
	public void delete(Integer idDepartamento) throws SQLException{
		String sql = "DELETE FROM DEPARTAMENTO " +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, idDepartamento);
		
		prst.executeQuery();
		
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

	public Departamento find(Integer id) throws SQLException{
		String sql = "SELECT * FROM DEPARTAMENTO " +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, id);
		ResultSet rs = prst.executeQuery();
		
		Departamento dpt = null;
		if(rs.next()){
			dpt = new Departamento(
					rs.getInt("id"),
					rs.getString("nome"),
					rs.getString("sigla"));
		}
		
		prst.close();
		return dpt;
	}
	
	public boolean find(String sigla) throws SQLException{
		String sql = "SELECT * FROM DEPARTAMENTO " +
				"WHERE sigla = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setString(1, sigla);
		
		ResultSet rs = prst.executeQuery();
		
		boolean achou = false;
		
		if(rs.next())
			achou = true;
		
		prst.close();
		//con.close();
		rs.close();
		
		return achou;
	}
	
	public boolean find(String sigla, int id) throws SQLException{
		String sql = "SELECT * FROM DEPARTAMENTO " +
				"WHERE sigla = ? AND id != ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setString(1, sigla);
		prst.setInt(2, id);
		
		ResultSet rs = prst.executeQuery();
		
		boolean achou = false;
		
		if(rs.next())
			achou = true;
		
		prst.close();
		rs.close();
		
		return achou;
	}
}
