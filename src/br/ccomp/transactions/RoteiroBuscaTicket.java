package br.ccomp.transactions;

import br.ccomp.gateway.TicketGateway;
import br.ccomp.modelo.Ticket;

public class RoteiroBuscaTicket {
	
	public Ticket execute(int id) throws Exception{
		
		TicketGateway ticketGateway = new TicketGateway();
		
		Ticket ticket = ticketGateway.find(id);
		
		if(ticket == null)
			throw new Exception("Ticket nao encontrado");
		
		return ticket;
	}
}
