package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.modelo.Curso;
import br.ccomp.modelo.Departamento;

public class CursoGateway {

	Connection con = ConnectionFactory.getConnection();
	
	public void insert(String nome, String sigla, Integer idDept){
		String sql = "INSERT INTO curso(nome, sigla, id_departamento) " +
				"VALUES (?,?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, nome);
			prst.setString(2, sigla);
			prst.setInt(3, idDept);
			
			prst.executeUpdate();
			
			prst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void delete(Integer idCurso) throws SQLException{
		String sql = "DELETE FROM curso " +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, idCurso);
		
		prst.executeUpdate();
		
		prst.close();
		
	}
	
	public ArrayList<Curso> findAll() throws SQLException{
		ArrayList<Curso> cursos = new ArrayList<Curso>();
		Connection conn = null;
		
		String sql = "SELECT * FROM CURSO JOIN DEPARTAMENTO ON CURSO.ID_DEPARTAMENTO = DEPARTAMENTO.ID";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				Curso c = new Curso(
						rs.getInt("CURSO.ID"),
						rs.getString("CURSO.NOME"),
						rs.getString("CURSO.SIGLA"),
						new br.ccomp.modelo.Departamento(
								rs.getInt("DEPARTAMENTO.ID"),
								rs.getString("DEPARTAMENTO.NOME"),
								rs.getString("DEPARTAMENTO.SIGLA")));
				cursos.add(c);
			}
			
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		return cursos;
	}
	
	public void update(int id, String nome, String sigla, Integer idDept){
		String sql = "UPDATE CURSO SET NOME = ?, SIGLA = ?, ID_DEPARTAMENTO = ? WHERE ID = ?";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, nome);
			prst.setString(2, sigla);
			prst.setInt(3, idDept);
			prst.setInt(4, id);
			
			prst.executeUpdate();
			
			prst.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public Curso find(Integer id) throws SQLException{
		String sql = "SELECT * FROM curso " +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, id);
		ResultSet rs = prst.executeQuery();
		
		Curso c = null;
		if(rs.next()){
			c = new Curso(
					rs.getInt("id"),
					rs.getString("nome"),
					rs.getString("sigla"),
					new Departamento(rs.getInt("id_departamento")));
		}
		
		prst.close();
		return c;
	}
	
	//TODO Identificar pq o executeQuery diz que a sintax ta errada e fazer o select funcionar
	public boolean find(String sigla) throws SQLException{
		String sql = "SELECT * FROM curso " +
				"WHERE sigla = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setString(1, sigla);
		
		ResultSet rs = prst.executeQuery();
		
		boolean achou = false;
		
		if(rs.next())
			achou = true;
		
		prst.close();
		
		return achou;
	}
	
	public boolean find(int id, String sigla) throws SQLException{
		String sql = "SELECT * FROM curso " +
				"WHERE sigla = ? AND id != ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setString(1, sigla);
		prst.setInt(2, id);
		
		ResultSet rs = prst.executeQuery();
		
		boolean achou = false;
		
		if(rs.next())
			achou = true;
		
		prst.close();
		
		return achou;
	}
}
