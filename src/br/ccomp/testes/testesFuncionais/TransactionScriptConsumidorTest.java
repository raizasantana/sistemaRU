package br.ccomp.testes.testesFuncionais;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.ccomp.gateway.ConnectionFactory;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Sexo;
import br.ccomp.modelo.Titulo;
import br.ccomp.transactions.TransactionScriptConsumidor;

public class TransactionScriptConsumidorTest {

	private TransactionScriptConsumidor TSC;

	@Before
	public void setUp() throws Exception {
		TSC = new TransactionScriptConsumidor();
	}

	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionFactory.getConnection();
		TSC.atualizarConsumidor(2, 2010, 54321, "Rafael", "MASCULINO");
		String sql = "DELETE FROM CONSUMIDOR WHERE nome = 'jorandom' OR nome = 'marandom'";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		prst.executeUpdate();
		prst.close();
	}

	@Test
	public void testIsValidCPF() {
		boolean test = TSC.isValidCPF("39612499543");
		assertEquals(test,true);
	}

	@Test
	public void testCriarAluno() {
		boolean test = TSC.criarAluno(1, "jorandom", "50840922833", 22, 2005, "MASCULINO");
		assertEquals(test,true);
	}

	@Test
	public void testCriarFuncionario() {
		boolean test = TSC.criarFuncionario(1, "marandom", "38284420481", "DOUTORADO", 654321, 2005, "FEMININO");
		assertEquals(test,true);
	}

	@Test
	public void testListarConsumidores() {
		boolean test = TSC.listarConsumidores() instanceof ArrayList<?>;
		assertEquals(test,true);
	}

	@Test
	public void testGetConsumidor() {
		boolean test = TSC.getConsumidor(2) instanceof Consumidor;
		assertEquals(test,true);
	}

	@Test
	public void testAtualizarConsumidor() {
		try {
			boolean test = TSC.atualizarConsumidor(2, 2010, 54321, "Rafaela", "FEMININO");
			assertEquals(test,true);
		} catch (SQLException e) {
			fail("SQL Exception Thrown");
		}
	}

	@Test
	public void testGetTitulo() {
		boolean test = TSC.getTitulo("DOUTORADO") instanceof Titulo;
		assertEquals(test,true);
	}

	@Test
	public void testGetSexo() {
		boolean test = TSC.getSexo("MASCULINO") instanceof Sexo;
		assertEquals(test,true);
	}

	@Test
	public void testGetConsumidorMatricula() {
		boolean test = TSC.getConsumidorMatricula(123456) instanceof Consumidor;
		assertEquals(test,true);
	}

}
