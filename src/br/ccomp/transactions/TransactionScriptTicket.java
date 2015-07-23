package br.ccomp.transactions;

import java.util.ArrayList;

import br.ccomp.gateway.TicketGateway;
import br.ccomp.modelo.Consumidor;
import br.ccomp.modelo.Refeicao;
import br.ccomp.modelo.Ticket;
import br.ccomp.modelo.Turno;

public class TransactionScriptTicket {
	private TicketGateway ticketGateway;
	
	public TransactionScriptTicket(){
		ticketGateway = new TicketGateway();
	}
	
	public void inserirTicket(Consumidor consumidor, Refeicao refeicao, int pago) throws Exception{
		float valor = 0f;
		
		if(consumidor == null)
			throw new Exception("Consumidor não encontrado para a matrícula");
		
		if(refeicao.getTurno() == Turno.MANHA && consumidor.getClass().getName().equals("br.ccomp.modelo.Aluno"))
			valor = 0.5f;
		else if(refeicao.getTurno() == Turno.MANHA && consumidor.getClass().getName().equals("br.ccomp.modelo.Funcionario"))
			valor = 3f;
		else if(consumidor.getClass().getName().equals("br.ccomp.modelo.Aluno"))
			valor = 1f;
		else if(consumidor.getClass().getName().equals("br.ccomp.modelo.Funcionario"))
			valor = 6f;
		
		
		boolean isPago = pago == 1 ? true : false;
		ticketGateway.insert(consumidor.getId(), valor, refeicao.getId(), isPago);
	}
	
	public void alterarTicket(Integer id, Boolean pago){
		ticketGateway.update(id, pago);
	}
	
	public Ticket getTicket(int id) throws Exception{
		Ticket ticket = ticketGateway.find(id); 
		if(ticket == null)
			throw new Exception("Ticket não encontrado");
		
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
