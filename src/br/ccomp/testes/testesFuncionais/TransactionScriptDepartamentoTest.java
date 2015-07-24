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
import br.ccomp.modelo.Departamento;
import br.ccomp.transactions.RoteiroAtualizarDepartamento;
import br.ccomp.transactions.RoteiroBuscaDepartamento;
import br.ccomp.transactions.RoteiroCriaDepartamento;
import br.ccomp.transactions.RoteiroListaDepartamento;

public class TransactionScriptDepartamentoTest {

	private RoteiroAtualizarDepartamento RAD;
	private RoteiroBuscaDepartamento RBD;
	private RoteiroCriaDepartamento RCD;
	private RoteiroListaDepartamento RLD;
	
	@Before
	public void setUp() throws Exception {
		RAD = new RoteiroAtualizarDepartamento();
		RBD = new RoteiroBuscaDepartamento();
		RCD = new RoteiroCriaDepartamento();
		RLD = new RoteiroListaDepartamento();
	}
	
	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionFactory.getConnection();
		RAD.execute(1,"Departamento de Ciencia da Computação", "DCC");
		String sql = "DELETE FROM DEPARTAMENTO WHERE nome = 'Departamento de História'";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		prst.executeUpdate();
		prst.close();
	}

	@Test
	public void testListarDepartamentos() {
		boolean test = RLD.execute() instanceof ArrayList<?>;
		assertEquals(test,true);
	}

	@Test
	public void testInserirDepartamento() {
		try {
			boolean test = RCD.execute("Departamento de História", "DH");
			assertEquals(test,true);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAlterarDepartamento() {
		try {
			boolean test = RAD.execute(1,"Departamento de Ciencia da Computação", "DeCC");
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetDepartamento() {
		boolean test = RBD.execute(2) instanceof Departamento;
		assertEquals(test,true);
	}

}
