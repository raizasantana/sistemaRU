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
import br.ccomp.transactions.RoteiroAtualizaConsumidor;
import br.ccomp.transactions.RoteiroBuscaConsumidor;
import br.ccomp.transactions.RoteiroBuscaConsumidorMatricula;
import br.ccomp.transactions.RoteiroCriarAluno;
import br.ccomp.transactions.RoteiroCriarFuncionario;
import br.ccomp.transactions.RoteiroListarConsumidores;

public class TransactionScriptConsumidorTest {

	private RoteiroAtualizaConsumidor RAC;
	private RoteiroBuscaConsumidor RBC;
	private RoteiroBuscaConsumidorMatricula RBCM;
	private RoteiroCriarAluno RCA;
	private RoteiroCriarFuncionario RCF;
	private RoteiroListarConsumidores RLC;

	@Before
	public void setUp() throws Exception {
		RAC = new RoteiroAtualizaConsumidor();
		RBC = new RoteiroBuscaConsumidor();
		RBCM = new RoteiroBuscaConsumidorMatricula();
		RCA = new RoteiroCriarAluno();
		RCF = new RoteiroCriarFuncionario();
		RLC = new RoteiroListarConsumidores();
	}

	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionFactory.getConnection();
		RAC.execute(2, 2010, 54321, "Rafael", "MASCULINO");
		String sql = "DELETE FROM CONSUMIDOR WHERE nome = 'jorandom' OR nome = 'marandom'";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		prst.executeUpdate();
		prst.close();
	}

	@Test
	public void testCriarAluno() {
		boolean test = RCA.execute(1, "jorandom", "50840922833", 123456, 2005, "MASCULINO");
		assertEquals(test,true);
	}

	@Test
	public void testCriarFuncionario() {
		boolean test = RCF.execute(1, "marandom", "38284420481", "DOUTORADO", 654321, 2005, "FEMININO");
		assertEquals(test,true);
	}

	@Test
	public void testListarConsumidores() {
		boolean test = RLC.execute() instanceof ArrayList<?>;
		assertEquals(test,true);
	}

	@Test
	public void testGetConsumidor() {
		boolean test = RBC.getConsumidor(2) instanceof Consumidor;
		assertEquals(test,true);
	}

	@Test
	public void testAtualizarConsumidor() {
		try {
			boolean test = RAC.execute(2, 2010, 54321, "Rafaela", "FEMININO");
			assertEquals(test,true);
		} catch (SQLException e) {
			fail("SQL Exception Thrown");
		}
	}

	@Test
	public void testGetConsumidorMatricula() {
		boolean test = RBCM.execute(12345) instanceof Consumidor;
		assertEquals(test,true);
	}

}
