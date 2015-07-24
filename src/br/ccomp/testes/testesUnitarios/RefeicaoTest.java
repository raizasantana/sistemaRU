package br.ccomp.testes.testesUnitarios;

import static org.junit.Assert.*;

import org.junit.Test;

import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.TipoRefeicao;
import br.ccomp.modelo.Turno;

public class RefeicaoTest {

	@Test
	public void testGetId() {
		Refeicao refeicao = new Refeicao();
		refeicao.setId(1);
		
		assertEquals((Integer) 1, refeicao.getId());
	}
	
	@Test
	public void testGetDescricao() {
		Refeicao refeicao = new Refeicao();
		refeicao.setDescricao("descricao");;
		
		assertEquals("descricao", refeicao.getDescricao());
	}
	
	@Test
	public void testGetTurno() {
		Refeicao refeicao = new Refeicao();
		refeicao.setTurno(Turno.MANHA);
		
		assertEquals(Turno.MANHA, refeicao.getTurno());
		assertNotEquals(Turno.TARDE, refeicao.getTurno());
		assertNotEquals(Turno.NOITE, refeicao.getTurno());
	}
	
	@Test
	public void testGetOpcaoVegetariana() {
		Refeicao refeicao = new Refeicao();
		refeicao.setOpcaoVegetariana("opc");
		
		assertEquals("opc", refeicao.getOpcaoVegetariana());
	}
	
	@Test
	public void testGetTipo() {
		Refeicao refeicao = new Refeicao();
		refeicao.setTipo(TipoRefeicao.ALMOCO);
		
		assertEquals(TipoRefeicao.ALMOCO, refeicao.getTipo());
		assertNotEquals(TipoRefeicao.DESJEJUM, refeicao.getTipo());
		assertNotEquals(TipoRefeicao.JANTA, refeicao.getTipo());
	}
}
