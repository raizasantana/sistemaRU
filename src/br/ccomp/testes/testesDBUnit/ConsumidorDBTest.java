package br.ccomp.dbUnitTests;

import static org.junit.Assert.*;

import java.sql.SQLException;

import org.junit.Test;

import com.sun.org.apache.xpath.internal.functions.FuncBoolean;

import br.ccomp.gateway.ConsumidorGateway;
import br.ccomp.modelo.Aluno;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Funcionario;
import br.ccomp.modelo.Sexo;
import br.ccomp.modelo.Titulo;

public class ConsumidorDBTest {

	private static final Object MESTRADO = null;
	private static final Object FEMININO = null;
	private static final Sexo MASCULINO = null;
	private ConsumidorGateway consGtw;
	
	public ConsumidorDBTest(){
		
		consGtw = new ConsumidorGateway();
		
	}
	
	
	
	@Test
	public void testInsertFuncionario() throws SQLException{
		Funcionario f = new Funcionario(1000, 1000);
		consGtw.insertFuncionario(1, "", "90891775315", "MESTRADO", 1000, 2000, "");
		
		assertEquals(true, consGtw.findbyCpf("90891775315"));
	}
	
	@Test
	public void testInsertAluno() throws SQLException{
		Aluno a = new Aluno(2000, 20000);
		consGtw.insertAluno(1, "", "78928087449", 20000, 2000, "");
		
		assertEquals(true, consGtw.findbyCpf("78928087449"));
	}
	
	@Test
	public void testGetTitulo() throws SQLException{
		assertEquals(MESTRADO, consGtw.findByMatricula(345).getTitulo());
	}
	
	@Test
	public void testGetSexo() throws SQLException
	{
		assertEquals(FEMININO, consGtw.findByMatricula(1234).getSexo());
	}
	
	@Test
	public void testFindByMatricula() throws SQLException
	{
		assertEquals((Integer)1, consGtw.findByMatricula(1234).getId());
	}
	
	@Test
	public void testFind() throws SQLException
	{
		assertEquals("124355534", consGtw.find(1).getCpf());
	}
	
	@Test
	public void testFindByCpf() throws SQLException
	{
		assertEquals(true, consGtw.findbyCpf("124355534"));
	}
	
	@Test
	public void testUpdate() throws SQLException
	{
		consGtw.update(2, 2016, 345, "MAISA", "FEMININO");
		assertEquals(2016, consGtw.find(2).getAnoIngresso());
		
	}
	
}
