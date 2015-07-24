package br.ccomp.transactions;

import br.ccomp.gateway.DepartamentoGateway;

public class RoteiroAtualizarDepartamento {
	public boolean execute(int id, String nome, String sigla) throws Exception {
		
		DepartamentoGateway departamentoGateway = new DepartamentoGateway();
		if(nome.equals("") || sigla.equals(""))
			throw new Exception("Dados invalidos");
		if(departamentoGateway.find(sigla, id))
			throw new Exception("Departamento de mesma sigla ja cadastrado");
		
		departamentoGateway.update(id, nome, sigla);
		return true;
	}
}
