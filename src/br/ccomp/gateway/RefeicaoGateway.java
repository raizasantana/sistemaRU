package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.TipoRefeicao;
import br.ccomp.modelo.Turno;

public class RefeicaoGateway {
	
Connection con = ConnectionFactory.getConnection();
	
	public void insert(String turno, String descricao, String opVegan, String tipo){
		String sql = "INSERT INTO refeicao (turno, descricao, opcao_vegetariana, tipo) " +
				"VALUES (?,?,?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, turno);
			prst.setString(2, descricao);
			prst.setString(3, opVegan);
			prst.setString(4, tipo);
			
			prst.executeUpdate();
			
			prst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} 		
			
	}
	
	public void delete(Integer idRefeicao) throws SQLException{
		String sql = "DELETE FROM refeicao " +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, idRefeicao);
		
		prst.executeUpdate();
		
		prst.close();
		
	}
	
	public ArrayList<Refeicao> findAll() throws SQLException{
		ArrayList<Refeicao> refeicoes = new ArrayList<Refeicao>();
		Connection conn = null;
		
		String sql = "SELECT * FROM refeicao";
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				Refeicao r = new Refeicao(
						rs.getInt("refeicao.id"),
						rs.getString("refeicao.descricao"),
						rs.getString("refeicao.opcao_vegetariana"),
						TipoRefeicao.valueOf(rs.getString("refeicao.tipo")),
						Turno.valueOf(rs.getString("refeicao.turno")));
						
				refeicoes.add(r);
			}
			ps.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return refeicoes;
	}

	public Refeicao findById(Integer id) throws SQLException{
		Refeicao refeicao = new Refeicao();
		Connection conn = null;
		
		String sql = "SELECT * FROM refeicao WHERE id = ?";
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				refeicao = new Refeicao(
						rs.getInt("refeicao.id"),
						rs.getString("refeicao.descricao"),
						rs.getString("refeicao.opcao_vegetariana"),
						TipoRefeicao.valueOf(rs.getString("refeicao.tipo")),
						Turno.valueOf(rs.getString("refeicao.turno")));
						
				
			}
			ps.close();
			conn.close();
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		
		return refeicao;
	}
	
	public void update(int id, String descricao, String opVegan){
		String sql = "UPDATE refeicao SET descricao = ?, opcao_vegetariana = ? WHERE ID = ?";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, descricao);
			prst.setString(2, opVegan);
			prst.setInt(3, id);
			
			prst.executeUpdate();
			
			prst.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
