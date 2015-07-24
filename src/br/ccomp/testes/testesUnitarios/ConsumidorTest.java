package br.ccomp.testes.testesUnitarios;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Sexo;
import br.ccomp.modelo.Titulo;

public class ConsumidorTest {
	
	@Test
	public void testGetId() {
		Consumidor consumidor = new Consumidor();
		consumidor.setId(1);
		
		assertEquals((Integer)1, consumidor.getId());
	}
	
	@Test
	public void testGetNome() {
		Consumidor consumidor = new Consumidor();
		consumidor.setNome("nome");
		
		assertEquals("nome", consumidor.getNome());
	}
	
	@Test
	public void testGetMatricula() {
		Consumidor consumidor = new Consumidor();
		consumidor.setMatricula(123456);
		
		assertEquals(123456, consumidor.getMatricula());
	}
	
	@Test
	public void testGetAnoIngresso() {
		Consumidor consumidor = new Consumidor();
		consumidor.setAnoIngresso(2015);
		
		assertEquals(2015, consumidor.getAnoIngresso());
	}
	
	@Test
	public void testGetSexo() {
		Consumidor consumidor = new Consumidor();
		consumidor.setSexo(Sexo.MASCULINO);
		
		assertEquals(Sexo.MASCULINO, consumidor.getSexo());
		assertNotEquals(Sexo.FEMININO, consumidor.getSexo());
	}
	
	@Test
	public void testGetTitulo() {
		Consumidor consumidor = new Consumidor();
		consumidor.setTitulo(Titulo.DOUTORADO);
		
		assertEquals(Titulo.DOUTORADO, consumidor.getTitulo());
		assertNotEquals(Titulo.ESPECIALIZACAO, consumidor.getTitulo());
		assertNotEquals(Titulo.MESTRADO, consumidor.getTitulo());
	}
	
	@Test
	public void testGetCPF() {
		Consumidor consumidor = new Consumidor();
		consumidor.setCpf("12345678901");
		
		assertEquals("12345678901", consumidor.getCpf());
	}
	
	@Test
	public void testIsValidCPF() {
		Consumidor consumidor = new Consumidor();
		
		assertEquals(false, consumidor.isValidCPF("12345678901"));
		assertEquals(true, consumidor.isValidCPF("19363556557"));
	}
	
}
