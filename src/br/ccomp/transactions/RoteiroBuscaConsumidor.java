package br.ccomp.transactions;

import java.sql.SQLException;

import br.ccomp.gateway.ConsumidorGateway;
import br.ccomp.modelo.Consumidor;

public class RoteiroBuscaConsumidor {
	
	public Consumidor execute(int id)	{
		ConsumidorGateway cG = new ConsumidorGateway();
		Consumidor c = null;
		try {
			 c = cG.find(id);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return c;
	}

}
