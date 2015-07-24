package br.ccomp.testes.testesDBUnit;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;
import com.sun.org.apache.xpath.internal.functions.FuncBoolean;

import br.ccomp.gateway.ConnectionFactory;
import br.ccomp.gateway.ConsumidorGateway;
import br.ccomp.modelo.Aluno;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Funcionario;
import br.ccomp.modelo.Sexo;
import br.ccomp.modelo.Titulo;

public class ConsumidorDBTest {

	private static final Object FEMININO = null;
	private static final Sexo MASCULINO = null;
	private static final Object MESTRADO = null;
	private ConsumidorGateway consGtw;

	public ConsumidorDBTest() {

		consGtw = new ConsumidorGateway();

	}

	@Test
	public void testInsertFuncionario() throws SQLException {
		Funcionario f = new Funcionario(1000, 1000);

		consGtw.insertFuncionario(1, "Roberta", "90891775315", "MESTRADO",
				1000, 2000, "FEMININO");

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CONSUMIDOR where cpf = '90891775315'");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();

		if (rst.next()) {
			assertEquals("MESTRADO", rst.getString("titulo"));
			prst.close();
		}
	}

	@Test
	public void testInsertAluno() throws SQLException {

		consGtw.insertAluno(1, "Alex", "78928087449", 20000, 2000, "MASCULINO");

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CONSUMIDOR where cpf = '78928087449'");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();

		if (rst.next()) {
			assertEquals("78928087449", rst.getString("cpf"));
			prst.close();
		}
	}

	@Test
	public void testGetTitulo() throws SQLException {

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CONSUMIDOR where cpf = '90891775315'");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();

		if (rst.next()) {
			int id = rst.getInt("id");
			assertEquals("MESTRADO", consGtw.find(id).getTitulo().getNome());
		}
	}

	@Test
	public void testGetSexo() throws SQLException {
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CONSUMIDOR where cpf = '90891775315'");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();

		if (rst.next()) {
			int id = rst.getInt("id");
			assertEquals(FEMININO, consGtw.find(id).getSexo());
		}
		
	}

	@Test
	public void testFindByMatricula() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CONSUMIDOR where cpf = '90891775315'");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();

		if (rst.next()) {
			int matricula = rst.getInt("matricula");
			
			assertEquals("90891775315", consGtw.findByMatricula(matricula).getCpf());
		}
	}

	@Test
	public void testFind() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CONSUMIDOR where cpf = '90891775315'");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();

		if (rst.next()) {
			int id = rst.getInt("id");
			assertEquals("90891775315", consGtw.find(id).getCpf());
		}
	}

	@Test
	public void testFindByCpf() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CONSUMIDOR where cpf = '90891775315'");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();

		if (rst.next()) {
			String cpf = rst.getString("cpf");
			assertEquals(true, consGtw.findbyCpf(cpf));
		}
	}

	@Test
	public void testUpdate() throws SQLException {
		
		consGtw.update(1, 2016, 345, "MAISA", "FEMININO");
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CONSUMIDOR where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();

		if (rst.next()) {
		int anoIngresso = rst.getInt("ano_ingresso");	
		assertEquals(2016, consGtw.find(1).getAnoIngresso());
		}
	}

}
