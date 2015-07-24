package br.ccomp.testes.testesDBUnit;

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

public class DepartamentoDBTest extends TestCase{
	
	private DepartamentoGateway dG;
	
    public DepartamentoDBTest(String name)
    {
        super(name);
        dG = new DepartamentoGateway();
    }
    
    @Test
    public void testFindBySigla() throws Exception
    {
    	Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from DEPARTAMENTO where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		String sigla = "";
		
		if (rst.next()) {
			sigla = rst.getString("sigla");
		}
    	assertEquals(true,dG.find(sigla));
    	
    	prst.close();
    	rst.close();
    }
    
    @Test 
    public void testFindByID() throws SQLException
    {
    	Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from DEPARTAMENTO where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		rst.next();
		
		Integer id = rst.getInt("id");
    	
    	assertEquals(id, dG.find(1).getId());
    	
    	prst.close();
    	rst.close();
    }
	
    @Test 
    public void testFindBySiglaEId() throws SQLException
    {
    	Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from DEPARTAMENTO where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		rst.next();		
		Integer id = rst.getInt("id");
		String sigla = rst.getString("sigla");
    	assertEquals(false, dG.find(sigla, id));
    	
    	prst.close();
    	rst.close();
    }
	
    @Test
     public void testInsert() throws Exception
     {
    	dG.insert("Departamento de Matematica","DMat");
    	
    	Connection con = ConnectionFactory.getConnection();
		PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from DEPARTAMENTO where sigla = 'DMat'");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		rst.next();
	
		assertEquals("DMat", rst.getString("sigla"));
		prst.close();
		
    	
     }
    
    @Test
    public void testUpdate() throws SQLException
    {
 	   dG.update(1,"Departamento Teste 1","DT1");
 	   
 	  Connection con = ConnectionFactory.getConnection();
	   PreparedStatement prst = (PreparedStatement) con
				.prepareStatement("select * from DEPARTAMENTO where id = 1");

		// Pega o valor atual
		ResultSet rst = prst.executeQuery();
		String sigla = "";
		if (rst.next()) {
			sigla = rst.getString("sigla");
		}
		
		assertEquals("DT1", sigla);
 	   
    }

}
