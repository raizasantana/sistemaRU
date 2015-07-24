package br.ccomp.testes.testesDBUnit;

import static org.junit.Assert.assertEquals;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import junit.framework.TestCase;

import org.dbunit.Assertion;
import org.dbunit.DatabaseTestCase;
import org.dbunit.IDatabaseTester;
import org.dbunit.JdbcDatabaseTester;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.database.DatabaseConnection;
import org.dbunit.database.IDatabaseConnection;
import org.dbunit.dataset.DataSetException;
import org.dbunit.dataset.IDataSet;
import org.dbunit.dataset.ITable;
import org.dbunit.dataset.xml.FlatXmlDataSet;
import org.dbunit.dataset.xml.FlatXmlDataSetBuilder;
import org.dbunit.operation.DatabaseOperation;
import org.junit.Before;
import org.junit.Test;

import com.mysql.jdbc.PreparedStatement;

import br.ccomp.gateway.ConnectionFactory;
import br.ccomp.gateway.CursoGateway;
import br.ccomp.gateway.DepartamentoGateway;
import br.ccomp.modelo.Curso;
import br.ccomp.modelo.Departamento;

public class CursoDBTest extends TestCase{
	
	private IDatabaseTester databaseTester;
	private CursoGateway cG;
	
    public CursoDBTest(String name)
    {
        super(name);
        cG = new CursoGateway();
        
	}
    
    
    @Test
    public void testFindBySigla() throws Exception
    {
    	Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CURSO where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();

		if (rst.next()) {
			String sigla = rst.getString("sigla");
			assertEquals(true,cG.find(sigla));
		}
    }
    
    @Test 
    public void testFindByID() throws SQLException
    {
    	Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CURSO where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		
		rst.next();
		
		Integer id = rst.getInt("id");
		
		assertEquals(id, cG.find(1).getId());
    }
    
   @Test
	public void testInsert() throws Exception {
		cG.insert("Engenharia da Computacao", "EComp", 1);
		
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CURSO where sigla = 'EComp'");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		rst.next();
	
		assertEquals(1, rst.getInt("id_departamento"));
		prst.close();
		

	}
    
   @Test
   public void testUpdate() throws SQLException
   {
	  
	   cG.update(1,"Curso Atualizado","CAT",1);
	  
	   Connection con = ConnectionFactory.getConnection();
	   PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CURSO where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		String sigla = "";
		if (rst.next()) {
			sigla = rst.getString("sigla");
		}
		
		assertEquals("CAT", sigla);
	}
	   
	
   
	@Test
	public void testMesmaSiglaAcha() throws SQLException {
		Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from CURSO where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		String sigla = "";
		if (rst.next()) {
			 sigla = rst.getString("sigla");
			prst = (PreparedStatement) con
					.prepareStatement("insert into CURSO (nome, sigla) values ('Curso de Teste 1',?)");
			prst.setString(1,sigla);
			
			prst.executeUpdate();
			
		}
		assertEquals(true, cG.find(1, sigla));
	}
   
   
   @Test
   public void testMesmaSiglaNaoAcha() throws SQLException
   {
	   assertEquals(false, cG.find(44,"HIST"));
   }
   

 
    
    
	

}
