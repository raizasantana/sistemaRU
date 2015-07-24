package br.ccomp.testes.testesFuncionais;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ccomp.gateway.ConnectionFactory;
import br.ccomp.modelo.Aluno;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Curso;
import br.ccomp.modelo.Departamento;
import br.ccomp.modelo.Funcionario;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.Sexo;
import br.ccomp.modelo.Ticket;
import br.ccomp.modelo.TipoRefeicao;
import br.ccomp.modelo.Titulo;
import br.ccomp.modelo.Turno;
import br.ccomp.transactions.RoteiroAtualizarTicket;
import br.ccomp.transactions.RoteiroBuscaTicket;
import br.ccomp.transactions.RoteiroBuscaTicketMatricula;
import br.ccomp.transactions.RoteiroCriarTicket;
import br.ccomp.transactions.RoteiroListarTicket;

public class TransactionScriptTicketTest {

	private RoteiroAtualizarTicket RAT;
	private RoteiroBuscaTicket RBT;
	private RoteiroCriarTicket RCT;
	private RoteiroListarTicket RLT;
	private RoteiroBuscaTicketMatricula RBTM;
	
	@Before
	public void setUp() throws Exception {
		RAT = new RoteiroAtualizarTicket();
		RBT = new RoteiroBuscaTicket();
		RCT = new RoteiroCriarTicket();
		RLT = new RoteiroListarTicket();
		RBTM = new RoteiroBuscaTicketMatricula();
	}
	
	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionFactory.getConnection();
		RAT.execute(1,true);
		String sql = "DELETE FROM ticket WHERE id_consumidor in (SELECT id from consumidor where matricula = 54321)";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		prst.executeUpdate();
		prst.close();
	}
	
	private Titulo getTitulo(String tit)	{
		if(tit.equals(Titulo.DOUTORADO.getNome()))
			return Titulo.DOUTORADO;
		else if (tit.equals(Titulo.ESPECIALIZACAO.getNome()))
			return Titulo.ESPECIALIZACAO;
		else 
			return Titulo.MESTRADO;
	}
	
	private Sexo getSexo(String sexo) {
		if(sexo.equals(Sexo.FEMININO.getNome()))
			return Sexo.FEMININO;
		else
			return Sexo.MASCULINO;
	}

	private Consumidor queryConsumidor() throws Exception{
		Connection con = ConnectionFactory.getConnection();
		String sql = "SELECT * FROM consumidor WHERE matricula = 54321)";
		try{
			PreparedStatement prst = con.prepareStatement(sql);
	
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
			}
			prst.close();
		} catch (Exception e) {
			throw e;
		}
		throw new Exception("Consumidor Não Encontrado");
	}
	
	public Refeicao queryRefeicao() throws Exception{
		Refeicao refeicao = new Refeicao();
		Connection conn = null;
		
		String sql = "SELECT * FROM REFEICAO WHERE id = 1";
				
		try {
			Class.forName("com.mysql.jdbc.Driver");
			conn = ConnectionFactory.getConnection();
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, 1);
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
		} catch (Exception e) {
			throw e;
		}
		
		
		return refeicao;
	}
	
	@Test
	public void testInserirTicket() {
		try {
			boolean test = RCT.execute(queryConsumidor(), queryRefeicao(), 1);
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAlterarTicket() {
		try {
			boolean test = RAT.execute(1,false);
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetTicket() {
		try {
			boolean test = RBT.execute(1) instanceof Ticket;
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testListarTickets() {
		try {
			boolean test = RLT.execute() instanceof ArrayList<?>;
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testListarTicketsInt() {
		try {
			boolean test = RBTM.execute(12345) instanceof ArrayList<?>;
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

}
