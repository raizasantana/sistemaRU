package br.ccomp.testes.testesFuncionais;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ TransactionScriptConsumidorTest.class, TransactionScriptCursoTest.class,
		TransactionScriptDepartamentoTest.class, TransactionScriptRefeicaoTest.class,
		TransactionScriptTicketTest.class })
public class TransactionTests {

}
