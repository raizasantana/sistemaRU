package dbUnitTests;

import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import br.ccomp.gateway.RefeicaoGateway;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.TipoRefeicao;
import junit.framework.TestCase;

public class RefeicaoDBTest extends TestCase{
	
	private RefeicaoGateway rG;
	
	public RefeicaoDBTest()
	{
		rG = new RefeicaoGateway();
	}
	
	@Test
	public void testFindByID() throws SQLException
	{
		Refeicao r = new Refeicao();
		r.setId(1);
		assertEquals(r.getId(), rG.findById(1).getId());
		
	}
	
	public void testInsert() throws SQLException
	{
		rG.insert("MANHA", "REFEICAO 4","", "DESJEJUM");
		assertEquals("MANHA", rG.findById(4).getTurno().getNome());
	}
	
	public void testUpdate() throws SQLException
	{
		rG.update(4, "Refeicao 4", "Opcao Vegana 4");
		assertEquals("Opcao Vegana 4", rG.findById(4).getOpcaoVegetariana());
		
	}

}
