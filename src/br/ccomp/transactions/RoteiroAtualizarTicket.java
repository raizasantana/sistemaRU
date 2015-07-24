package br.ccomp.transactions;

import java.sql.SQLException;

import br.ccomp.gateway.TicketGateway;

public class RoteiroAtualizarTicket {
	
	public boolean execute(Integer id, Boolean pago) throws SQLException{
		TicketGateway ticketGateway = new TicketGateway();
		return ticketGateway.update(id, pago);
	}

}
