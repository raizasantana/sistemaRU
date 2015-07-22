package br.ccomp.gateway;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.modelo.Aluno;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Curso;
import br.ccomp.modelo.Departamento;
import br.ccomp.modelo.Funcionario;
import br.ccomp.modelo.Sexo;
import br.ccomp.modelo.Titulo;

public class ConsumidorGateway {

	Connection con = ConnectionFactory.getConnection();
	
	public boolean  insertFuncionario(int departamento, String nome, String cpf, String titulo, int matricula, int anoIngresso, String sexo){
		String sql = "INSERT INTO consumidor (nome, cpf, matricula, sexo, ano_ingresso, titulo, id_departamento)" +
				"VALUES (?,?,?,?,?,?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, nome);
			prst.setString(2, cpf);
			prst.setInt(3, matricula);
			prst.setString(4, sexo);
			prst.setInt(5, anoIngresso);
			prst.setString(6, titulo);
			prst.setInt(7, departamento);
			
			prst.executeUpdate();
			
			prst.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean insertAluno(int curso, String nome,String cpf,int matricula, int anoIngresso, String sexo){
		String sql = "INSERT INTO consumidor(nome, cpf, matricula, sexo, ano_ingresso, id_curso) " +
				"VALUES (?,?,?,?,?,?)";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setString(1, nome);
			prst.setString(2, cpf);
			prst.setInt(3, Integer.valueOf(matricula));
			prst.setString(4, sexo);
			prst.setInt(5, Integer.valueOf(anoIngresso));
			prst.setInt(6, curso);
			
			prst.executeUpdate();
			
			prst.close();
			
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public boolean delete(Integer idConsumidor) {
		String sql = "DELETE FROM consumidor" +
				"WHERE id = ?";
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setInt(1, idConsumidor);
			ResultSet rs = prst.executeQuery();
			prst.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Consumidor> findAll() {
		ArrayList<Consumidor> cons = new ArrayList<Consumidor>();
		Connection conn = null;
		
		String sql = "select c.*, d.*, cu.* from consumidor c left join curso cu on (c.id_curso = cu.id) " +
						" left join departamento d on (d.id = c.id_departamento)";
		
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			
			while (rs.next()){
				Consumidor c;
				if(rs.getString("c.id_departamento") == null) //Criar aluno
				{
					Aluno a = new Aluno();
					a.setNome(rs.getString("c.nome"));
					a.setId(rs.getInt("id"));
					a.setAnoIngresso(rs.getInt("c.ano_ingresso"));
					a.setMatricula(rs.getInt("matricula"));
					a.setCpf(rs.getString("c.cpf"));
					a.setSexo(getSexo(rs.getString("c.sexo")));
					Curso cursoTemp = new Curso();
					cursoTemp.setId(rs.getInt("cu.id"));
					cursoTemp.setNome(rs.getString("cu.nome"));
					cursoTemp.setSigla(rs.getString("cu.sigla"));
					a.setCurso(cursoTemp);
							
					cons.add(a);
				}
				else if(rs.getString("c.id_curso") == null) //Criar funcionario
				{
					Funcionario f  = new Funcionario();
					f.setNome(rs.getString("c.nome"));
					f.setAnoIngresso(rs.getInt("c.ano_ingresso"));
					f.setCpf(rs.getString("c.cpf"));
					f.setMatricula(rs.getInt("c.matricula"));
					f.setSexo(getSexo(rs.getString("c.sexo")));
					f.setTitulo(getTitulo(rs.getString("c.titulo")));
					f.setDepartamento(new Departamento(rs.getInt("d.id"), rs.getString("d.nome"), rs.getString("d.sigla")));
					
					cons.add(f);
				}
			}
		} catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		
		return cons;
	}



	public Titulo getTitulo(String tit)
	{
		if(tit.equals(Titulo.DOUTORADO.getNome()))
			return Titulo.DOUTORADO;
		else if (tit.equals(Titulo.ESPECIALIZACAO.getNome()))
			return Titulo.ESPECIALIZACAO;
		else 
			return Titulo.MESTRADO;
	}
	public Sexo getSexo(String sexo)
	{
		if(sexo.equals(Sexo.FEMININO.getNome()))
			return Sexo.FEMININO;
		else
			return Sexo.MASCULINO;
	}
	
	public Consumidor findByMatricula(Integer matricula) throws SQLException{
		String sql = "SELECT * FROM consumidor " +
				"WHERE matricula = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, matricula);
		ResultSet rs = prst.executeQuery();
		
		Aluno consumidor_a = null;
		Funcionario consumidor_f = null;
		if(rs.next()){
			if(rs.getString("id_curso") == null)
				consumidor_f = new Funcionario(rs.getInt("id"), rs.getInt("matricula"));
			else
				consumidor_a = new Aluno(rs.getInt("id"), rs.getInt("matricula"));
		}
		
		prst.close();
		return consumidor_a == null ? consumidor_f : consumidor_a;
	}
	
	public Consumidor find(Integer id) {
		String sql = "select cu.sigla, d.sigla, c.* from consumidor c left join curso cu on (c.id_curso = cu.id)" + 
						" left join departamento d on (d.id = c.id_departamento)" +
				" WHERE c.id = ?";
		
		try {
			PreparedStatement prst = con.prepareStatement(sql);
			prst.setInt(1, id);
			ResultSet rs = prst.executeQuery();
			
			
			if(rs.next()) {
				if(rs.getString("c.id_curso") == null) {
					Funcionario c = new Funcionario();
					c.setAnoIngresso(rs.getInt("c.ano_ingresso"));
					c.setCpf(rs.getString("c.cpf"));
					c.setId(rs.getInt("c.id"));
					c.setMatricula(rs.getInt("c.matricula"));
					c.setNome(rs.getString("c.nome"));
					c.setSexo(getSexo(rs.getString("c.sexo")));				
					c.setTitulo(getTitulo(rs.getString("c.titulo")));
					Departamento dTemp = new Departamento();
					dTemp.setSigla(rs.getString("d.sigla"));
					dTemp.setId(rs.getInt("c.id_departamento"));
					c.setDepartamento(dTemp);
					
					rs.close();
					prst.close();
					
					return c;
				} else {
					Aluno c = new Aluno();
					c.setAnoIngresso(rs.getInt("c.ano_ingresso"));
					c.setCpf(rs.getString("c.cpf"));
					c.setId(rs.getInt("c.id"));
					c.setMatricula(rs.getInt("c.matricula"));
					c.setNome(rs.getString("c.nome"));
					c.setSexo(getSexo(rs.getString("c.sexo")));
					Curso cursoTemp = new Curso();
					cursoTemp.setId(rs.getInt("c.id_curso"));
					cursoTemp.setSigla(rs.getString("cu.sigla"));
					
					c.setCurso(cursoTemp);
					
					rs.close();
					prst.close();
					
					return c;
				}
			} else {
				return null;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}		
	}
	
	public boolean findbyCpf(String cpf) {
		boolean achou = false;
		
		String sql = "SELECT * FROM consumidor " +
				"WHERE cpf = ?";
		
		Consumidor cons = null;
		PreparedStatement prst;
		try {
			prst = con.prepareStatement(sql);
			prst.setString(1, cpf);
			ResultSet rs = prst.executeQuery();
			if(rs.next()) {
				achou = true;
			}
			prst.close();
			return achou;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public void update(Integer id, int anoIngresso, int matricula, String nome,
			String sexo) throws SQLException {
		// TODO Auto-generated method stub
		String sql = "update consumidor set ano_ingresso = ?, matricula = ?, nome = ?, sexo = ? where id = ?";
		
		PreparedStatement ps = con.prepareStatement(sql);
		ps.setInt(1,anoIngresso);
		ps.setInt(2, matricula);
		ps.setString(3,nome);
		ps.setString(4,sexo);
		ps.setInt(5,id);
		
		ps.executeUpdate();
		
		ps.close();
		
	}
	
	
}

