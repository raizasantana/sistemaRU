package br.ccomp.transactions;

import java.util.ArrayList;

import br.ccomp.gateway.TicketGateway;
import br.ccomp.modelo.Ticket;

public class RoteiroListarTicket {
	

	public ArrayList<Ticket> execute() throws Exception{
		TicketGateway ticketGateway = new TicketGateway();
		ArrayList<Ticket> tickets = ticketGateway.findAll();
		
		if(tickets.isEmpty())
			throw new Exception("Nenhum ticket registrado");
		
		return tickets;
	}

}
