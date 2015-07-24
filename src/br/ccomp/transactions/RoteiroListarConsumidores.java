package br.ccomp.transactions;

import java.sql.SQLException;
import java.util.ArrayList;

import br.ccomp.gateway.ConsumidorGateway;
import br.ccomp.modelo.Consumidor;

public class RoteiroListarConsumidores {

	public ArrayList<Consumidor> execute() {
		ConsumidorGateway consGT = new ConsumidorGateway();
		ArrayList<Consumidor> cons = null;

		try {
			cons = consGT.findAll();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return cons;
	}

}
