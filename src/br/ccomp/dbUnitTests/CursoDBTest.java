package dbUnitTests;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
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

import br.ccomp.gateway.ConnectionFactory;
import br.ccomp.gateway.CursoGateway;
import br.ccomp.modelo.Curso;

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
    	assertEquals(true,cG.find("CComp"));
    }
    
    @Test 
    public void testFindByID() throws SQLException
    {
    	Curso c = new Curso();
    	c.setId(1);
    	assertEquals(c.getId(), cG.find(1).getId());
    }
    
   @Test
    public void testInsert() throws Exception
    {
	   cG.insert("Engenharia da Computacao","EComp" ,1);
    	assertEquals(true, cG.find("EComp"));
    }
    
   @Test
   public void testUpdate() throws SQLException
   {
	   Curso c = cG.find(2);
	   c.setSigla("ABC");
	   cG.update(c.getId(),c.getNome(),c.getSigla(), c.getDepartamento().getId());
	   
	   assertEquals(true,cG.find("ABC"));
   }
   
   @Test
   public void testMesmaSiglaAcha() throws SQLException
   {
	   assertEquals(true, cG.find(3,"EComp"));
   
   }
   
   
   @Test
   public void testMesmaSiglaNaoAcha() throws SQLException
   {
	   assertEquals(true, cG.find(44,"EComp"));
   }
   

 
    
    
	

}
