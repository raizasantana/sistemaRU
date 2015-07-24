package br.ccomp.testes.testesDBUnit;

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
    	assertEquals(true,dG.find("DCC"));
    }
    
    @Test 
    public void testFindByID() throws SQLException
    {
    	Departamento d = new Departamento();
    	d.setId(1);
    	assertEquals(d.getId(), dG.find(1).getId());
    }
	
    @Test 
    public void testFindBySiglaEId() throws SQLException
    {
    	Departamento d = new Departamento();
    	d.setId(1);
    	d.setSigla("DP1");
    	assertEquals(false, dG.find("DP1",1));
    }
	
    @Test
     public void testInsert() throws Exception
     {
    	dG.insert("Departamento de Matematica","DMat");
    	assertEquals(true, dG.find("DMat"));
     }
    
    @Test
    public void testUpdate() throws SQLException
    {
 	   Departamento d = dG.find(2);
 	   d.setSigla("DPX");
 	   dG.update(d.getId(),d.getNome(),d.getSigla());
 	   
 	   assertEquals(true,dG.find("DPX"));
    }

}
