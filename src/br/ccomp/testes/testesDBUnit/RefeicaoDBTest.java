package br.ccomp.testes.testesDBUnit;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;

import br.ccomp.gateway.ConnectionFactory;
import br.ccomp.gateway.RefeicaoGateway;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.TipoRefeicao;
import junit.framework.TestCase;

public class RefeicaoDBTest extends TestCase {

	private RefeicaoGateway rG;

	public RefeicaoDBTest() {
		rG = new RefeicaoGateway();
	}

	@Test
	public void testFindByID() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from REFEICAO where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		rst.next();

		String desc = rst.getString("descricao");

		assertEquals(desc, rG.findById(1).getDescricao());

		prst.close();
		rst.close();

	}

	public void testInsert() throws SQLException {
		rG.insert("MANHA", "REFEICAO 4", "Opcao Vegetariana 10", "DESJEJUM");
		// assertEquals("MANHA", rG.findById(5).getTurno().getNome());

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from REFEICAO where opcao_vegetariana = 'Opcao Vegetariana 10'");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		rst.next();

		assertEquals("REFEICAO 4", rst.getString("descricao"));
		prst.close();
		rst.close();
	}

	public void testUpdate() throws SQLException {
		rG.update(1, "Refeicao Nova", "Opcao Vegana Nova");

		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from REFEICAO where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		String opV = "";
		if (rst.next()) {
			opV = rst.getString("opcao_vegetariana");
		}

		assertEquals("Opcao Vegana Nova", opV);

	}

}
