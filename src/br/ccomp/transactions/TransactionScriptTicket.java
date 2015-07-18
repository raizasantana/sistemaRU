package br.ccomp.transactions;

import java.util.ArrayList;

import br.ccomp.gateway.TicketGateway;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Ticket;

public class TransactionScriptTicket {
	private TicketGateway ticketGateway;
	
	public TransactionScriptTicket(){
		ticketGateway = new TicketGateway();
	}
	
	public void inserirTicket(Consumidor consumidor, float valor, Integer idRefeicao, int pago) throws Exception{
		float valor_f = valor;
		/*try{
			valor_f = Float.parseFloat(valor.replace(',', '.'));
		} catch (NumberFormatException e) {
			throw new Exception("O valor informado � inv�lido");
		}
		*/
		System.out.println(valor_f);
		
		if(valor_f <= 0)
			throw new Exception("Valor do ticket inv�lido");
		
		if(consumidor == null)
			throw new Exception("Consumidor n�o encontrado para a matr�cula");
		
		boolean isPago = pago == 1 ? true : false;
		ticketGateway.insert(consumidor.getId(), valor_f, idRefeicao, isPago);
	}
	
	public void alterarTicket(Integer id, Boolean pago){
		ticketGateway.update(id, pago);
	}
	
	public Ticket getTicket(int id) throws Exception{
		Ticket ticket = ticketGateway.find(id); 
		if(ticket == null)
			throw new Exception("Ticket n�o encontrado");
		
		return ticket;
	}
	
	public ArrayList<Ticket> listarTickets() throws Exception{
		ArrayList<Ticket> tickets = ticketGateway.findAll();
		
		if(tickets.isEmpty())
			throw new Exception("Nenhum ticket registrado");
		
		return tickets;
	}
	
	public ArrayList<Ticket> listarTickets(int matriculaConsumidor) throws Exception{
		ArrayList<Ticket> tickets = ticketGateway.findAll(matriculaConsumidor);
		
		if(tickets.isEmpty())
			throw new Exception("Nenhum ticket encontrado para o consumidor desejado");
		
		return tickets;
	}
}
