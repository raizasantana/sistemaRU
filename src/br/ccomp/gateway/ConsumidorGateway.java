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
	
	public void insertFuncionario(int departamento, String nome, String cpf, String titulo, int matricula, int anoIngresso, String sexo){
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void insertAluno(int curso, String nome,String cpf,int matricula, int anoIngresso, String sexo){
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
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(Integer idConsumidor) throws SQLException{
		String sql = "DELETE FROM consumidor" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, idConsumidor);
		
		prst.executeQuery();
		
		prst.close();
	}
	
	public ArrayList<Consumidor> findAll() throws SQLException
	{
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
		}catch (SQLException | ClassNotFoundException e) {
			e.printStackTrace();
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
	public ResultSet find(Integer id) throws SQLException{
		String sql = "SELECT * FROM consumidor" +
				"WHERE id = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, id);
		ResultSet rs = prst.executeQuery();
		
		prst.close();
		return rs;
	}
	
	public Consumidor findMatricula(Integer matricula) throws SQLException{
		String sql = "SELECT * FROM consumidor " +
				"WHERE matricula = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setInt(1, matricula);
		ResultSet rs = prst.executeQuery();
		
		Consumidor consumidor = null;
		if(rs.next()){
			consumidor = new Consumidor(rs.getInt("id"), rs.getInt("matricula"));
		}
		
		prst.close();
		return consumidor;
	}
	
	public boolean findbyCpf(String cpf) throws SQLException{
		boolean achou = false;
		
		String sql = "SELECT * FROM consumidor " +
				"WHERE cpf = ?";
		
		PreparedStatement prst = con.prepareStatement(sql);
		prst.setString(1, cpf);
		
		ResultSet rs = prst.executeQuery();
		
		
		if(rs.next())
		{
			achou = true;
		}
		
		prst.close();
		return achou;
	}
		/*
		if(rs.next())
		{
			cons = new Consumidor();
			cons.setMatricula(rs.getInt("consumidor.matricula"));
			cons.setNome(rs.getString("consumidor.nome"));
			cons.setCpf(rs.getString("consumidor.cpf"));			
			cons.setSexo(getSexo(rs.getString("sconsumidor.exo")));			
			cons.setTitulo(getTitulo(rs.getString("consumidor.titulo")));
			cons.setAnoIngresso(rs.getInt("consumidor.ano"));
			
			
		}
		
		
		
		
		
	}*/
	
	
	
	
}

