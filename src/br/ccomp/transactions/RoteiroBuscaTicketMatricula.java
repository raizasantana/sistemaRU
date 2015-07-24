package br.ccomp.transactions;

import java.util.ArrayList;

import br.ccomp.gateway.TicketGateway;
import br.ccomp.modelo.Ticket;

public class RoteiroBuscaTicketMatricula {
	
	public ArrayList<Ticket> execute(int matriculaConsumidor) throws Exception{
		TicketGateway ticketGateway = new TicketGateway();
		ArrayList<Ticket> tickets = ticketGateway.findAll(matriculaConsumidor);
		
		if(tickets.isEmpty())
			throw new Exception("Nenhum ticket encontrado para o consumidor desejado");
		
		return tickets;
	}

}
