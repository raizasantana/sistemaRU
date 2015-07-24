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
import br.ccomp.transactions.TransactionScriptDepartamento;

public class TransactionScriptDepartamentoTest {

	TransactionScriptDepartamento TSD;
	
	@Before
	public void setUp() throws Exception {
		TSD = new TransactionScriptDepartamento();
	}
	
	@After
	public void tearDown() throws Exception {
		Connection con = ConnectionFactory.getConnection();
		TSD.alterarDepartamento(1,"Departamento de Ciencia da Computa��o", "DCC");
		String sql = "DELETE FROM DEPARTAMENTO WHERE nome = 'Departamento de Hist�ria'";
		
		PreparedStatement prst = con.prepareStatement(sql);
		
		prst.executeUpdate();
		prst.close();
	}

	@Test
	public void testListarDepartamentos() {
		boolean test = TSD.listarDepartamentos() instanceof ArrayList<?>;
		assertEquals(test,true);
	}

	@Test
	public void testInserirDepartamento() {
		try {
			boolean test = TSD.inserirDepartamento("Departamento de Hist�ria", "DH");
			assertEquals(test,true);
		} catch (SQLException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testAlterarDepartamento() {
		try {
			boolean test = TSD.alterarDepartamento(1,"Departamento de Ciencia da Computa��o", "DeCC");
			assertEquals(test,true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testGetDepartamento() {
		boolean test = TSD.getDepartamento(2) instanceof Departamento;
		assertEquals(test,true);
	}

}
