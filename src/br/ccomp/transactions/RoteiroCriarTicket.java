package br.ccomp.transactions;

import br.ccomp.gateway.TicketGateway;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.Turno;

public class RoteiroCriarTicket {
	
	public boolean execute(Consumidor consumidor, Refeicao refeicao, int pago) throws Exception{
		float valor = 0f;
		
		TicketGateway ticketGateway = new TicketGateway();
		
		if(consumidor == null)
			throw new Exception("Consumidor nao encontrado para a matricula");
		valor = consumidor.getValor(refeicao.getTurno());
		boolean isPago = pago == 1 ? true : false;
		return ticketGateway.insert(consumidor.getId(), valor, refeicao.getId(), isPago);
	}

}
