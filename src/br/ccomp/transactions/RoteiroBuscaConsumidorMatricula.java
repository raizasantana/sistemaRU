package br.ccomp.transactions;

import java.sql.SQLException;

import br.ccomp.gateway.ConsumidorGateway;
import br.ccomp.modelo.Consumidor;

public class RoteiroBuscaConsumidorMatricula {
	
	public Consumidor execute(Integer matricula) {
		ConsumidorGateway consGT = new ConsumidorGateway();
		
		try {
			return consGT.findByMatricula(matricula);
		} catch (SQLException e) {
			e.printStackTrace();
			return null;
		}
	}

}
