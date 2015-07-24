package dbUnitTests;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.SQLException;

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

    protected void setUp() throws Exception
    {
        databaseTester = new JdbcDatabaseTester("com.mysql.jdbc.Driver",
            "jdbc:mysql://localhost:3306/bandejao", "root", "root");

        // initialize your dataset here
        IDataSet dataSet = this.getDataSet();
        databaseTester.setDataSet( dataSet );
    }

    protected void tearDown() throws Exception
    {
	// will call default tearDownOperation
        databaseTester.onTearDown();
    }
    
    protected IDataSet getDataSet() throws Exception {
		return new FlatXmlDataSetBuilder().build(new FileInputStream("src/dbUnitTests/dataset.xml"));
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
	

}
